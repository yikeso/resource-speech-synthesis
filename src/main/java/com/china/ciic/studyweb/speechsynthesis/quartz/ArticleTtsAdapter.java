package com.china.ciic.studyweb.speechsynthesis.quartz;

import com.china.ciic.studyweb.speechsynthesis.entity.Article;
import com.china.ciic.studyweb.speechsynthesis.entity.ArticleAudio;
import com.china.ciic.studyweb.speechsynthesis.entity.ArticleDetail;
import com.china.ciic.studyweb.speechsynthesis.exception.ArticleTtsException;
import com.china.ciic.studyweb.speechsynthesis.exception.TtsException;
import com.china.ciic.studyweb.speechsynthesis.repositories.ArticleAudioRepository;
import com.china.ciic.studyweb.speechsynthesis.repositories.ArticleDetailRepository;
import com.china.ciic.studyweb.speechsynthesis.repositories.ArticleRepository;
import com.china.ciic.studyweb.speechsynthesis.service.SetService;
import com.china.ciic.studyweb.speechsynthesis.utils.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 文章语音合成
 */
@Component // 此注解必加
@EnableScheduling // 此注解必加
public class ArticleTtsAdapter extends TtsAdapter {

    @Resource
    ArticleAudioRepository articleAudioRepository;

    @Resource
    ArticleRepository articleRepository;

    @Resource
    ArticleDetailRepository articleDetailRepository;

    @Resource
    SetService setService;

    private static final Logger log = LoggerFactory.getLogger(ArticleTtsAdapter.class);

    @Override
    boolean isPause() {
        return false;
    }

    /**
     * 语音合成
     * 分页查询文章进行语音合成
     */
    @Override
    void tts() {
        String columnIdStr = setService.getValue("quartz.ArticleTranslateAudio.cloumnId", "");
        List<Article> articles;
        //查询需要语音合成的文章
        if(columnIdStr.length() > 0){
            try {
                articles = articleRepository.findOutNotTtsArticleByColumId(Long.parseLong(columnIdStr));
            }catch (Exception e){
                log.error(ExceptionUtil.eMessage(e));
                articles = articleRepository.findOutNotTtsArticle();
            }
        }else {
            articles = articleRepository.findOutNotTtsArticle();
        }
        if(articles.size() < 1){
            log.info("暂无文章需要语音合成");
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                log.error(ExceptionUtil.eMessage(e));
            }
            return;
        }
        for(Article a:articles){
            tts(a);
        }
    }

    /**
     * 语音合成指定文章
     * @param article
     */
    private void tts(Article article){
        ArticleDetail detail = articleDetailRepository.findOne(article.getId());
        ArticleAudio articleAudio = new ArticleAudio();
        articleAudio.setArticleId(article.getId());
        articleAudio.setAudioName(article.getTitle());
        articleAudio.setAudioPicUrl(article.getImageOne());
        articleAudio.setAuthor(article.getAuthor());
        articleAudio.setResource("资源中心");
        try {
            log.info("文章《{}》开始语音合成",article.getTitle());
            tts(detail,articleAudio);
            articleAudio.setAudioXmlState(QuartzConstants.TTS_SUCCESS);
            articleAudio.setCreateTime(new Date());
        }catch(TtsException e){
            articleAudio.setAudioXmlState(e.getErrorCode());
            articleAudio.setAudioState(e.getErrorCode());
            articleAudio.setCreateTime(new Date());
        }
//            articleAudioRepository.save(articleAudio);
        log.info(articleAudio.toString());
    }

    private void tts(ArticleDetail articleDetail,ArticleAudio articleAudio) throws TtsException {
        //新建一个xml的作为此次语音合成音频文件的目录
        Document document = DocumentHelper.createDocument();
        //添加根元素articleAudio
        Element articleAudioXml = document.addElement("articleAudio");
        Element articleAudioTitle= articleAudioXml.addElement("title");
        articleAudioTitle.setText(articleAudio.getAudioName());
        //添加音频文件目录
        Element xhtml= articleAudioXml.addElement("xhtml");
        Element chapters = xhtml.addElement("chapters");
        chapters.addAttribute("id", "0");
        Element chapterTitle = chapters.addElement("title");
        chapterTitle.setText(articleAudio.getAudioName());
        //获取语音合成缓存根目录
        String tmpDir = setService.getValue("quartz.ArticleTranslateAudio.lrcTempPath",
                "./lrcTempPath/");
        tmpDir = FileUtil.filePathFormat(tmpDir);
        if(!tmpDir.endsWith("/")){
            tmpDir = tmpDir + "/";
        }
        //获取生成音频文件保存根目录
        String audioRootDirPath = setService.getValue("quartz.ArticleTranslateAudio.audioPath",
                "./articleAudio/");
        audioRootDirPath = FileUtil.filePathFormat(audioRootDirPath);
        if(!audioRootDirPath.endsWith("/")){
            audioRootDirPath = audioRootDirPath + "/";
        }
        String datePath = dtf.format(LocalDateTime.now());
        //此篇文章生成的音频文件的储存路径
        String audioDirPath = audioRootDirPath + datePath + "/" + OSUtil.getSystemCurrentTimeAsName() + "/";
        FileUtil.makedir(tmpDir);
        FileUtil.makedir(audioDirPath);

        List<String> contentArray = TextUtil.slicesString(HtmlUtil.Html2txt(articleDetail.getContent()));
        String audioPath = null;
        String lrcPath;
        String millisTime;
        TtsMaster master;
        try {
            long audioLeng = 0L;
            for(int i = 0;i < contentArray.size();i++){
                millisTime = OSUtil.getSystemCurrentTimeAsName();
                //音频文件的储存路径
                audioPath = audioDirPath + millisTime + ".mp3";
                if(articleAudio.getAudioUrl() == null) {
                    articleAudio.setAudioUrl(audioPath);
                }
                //音频文件对应的歌词文件的路径
                lrcPath = audioDirPath + millisTime + ".lrc";
                //语音合成采用master-slave模式
                // 创建语音合成的master
                master = new TtsMaster();
                master.setAudioPath(audioDirPath)
                        .setAudioTempDirPath(tmpDir)
                        .setLrcPath(audioDirPath)
                        .setContent(contentArray.get(i))
                        .setBy(articleAudio.getAuthor());
                master.ttsAndCreateLrc();
                //chapterst元素（章节）添加子元素chaptersSub（将章节内容分割后的章节片段）
                Element chaptersSub= chapters.addElement("chaptersSub");
                //给元素chaptersSub（将章节内容分割后的章节片段）添加属性id，片段的序号（从0开始）
                chaptersSub.addAttribute("id", Integer.toString(i));
                Element address= chaptersSub.addElement("address");
                Element lrcAddress= chaptersSub.addElement("lrcAddress");
                audioPath = audioPath.replace(audioPath, QuartzConstants.ARTICLE_TTS_PREFIX);
                lrcPath = lrcPath.replace(audioPath, QuartzConstants.ARTICLE_TTS_PREFIX);
                address.setText(audioPath);
                lrcAddress.setText(lrcPath);
                audioLeng += master.getAudioLength();
            }
            //音频索引文件写入磁盘
            String xmlPath = audioDirPath + "/" + "index.xml";
            XmlUtil.writeXml(document,xmlPath);
            xmlPath = xmlPath.replace(audioRootDirPath,QuartzConstants.ARTICLE_TTS_PREFIX);
            articleAudio.setAudioTimeLength(Long.valueOf(audioLeng));
            articleAudio.setAudioXmlUrl(xmlPath);
        }catch (Exception e){
            FileUtil.delayDeleteFile(audioDirPath);
            throw new ArticleTtsException(articleAudio.getAudioName()+"语音合成失败",e);
        }
    }
}
