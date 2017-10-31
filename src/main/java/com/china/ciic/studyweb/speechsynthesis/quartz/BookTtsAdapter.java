package com.china.ciic.studyweb.speechsynthesis.quartz;

import com.china.ciic.studyweb.speechsynthesis.entity.BookAudio;
import com.china.ciic.studyweb.speechsynthesis.entity.Courseware;
import com.china.ciic.studyweb.speechsynthesis.entity.CoursewareInfo;
import com.china.ciic.studyweb.speechsynthesis.exception.BookTtsException;
import com.china.ciic.studyweb.speechsynthesis.exception.TtsException;
import com.china.ciic.studyweb.speechsynthesis.quartz.bo.ChapterUrl;
import com.china.ciic.studyweb.speechsynthesis.repositories.BookAudioRepository;
import com.china.ciic.studyweb.speechsynthesis.repositories.CoursewareInfoRepository;
import com.china.ciic.studyweb.speechsynthesis.repositories.CoursewareRepository;
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
import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 电子书语音合成
 */
@Component // 此注解必加
@EnableScheduling //此注解必加
public class BookTtsAdapter extends TtsAdapter {

    @Resource
    BookAudioRepository bookAudioRepository;

    @Resource
    CoursewareRepository coursewareRepository;

    @Resource
    CoursewareInfoRepository coursewareInfoRepository;

    @Resource
    SetService setService;

    private static final Logger log = LoggerFactory.getLogger(BookTtsAdapter.class);

    @Override
    boolean isPause() {
        return false;
    }

    @Override
    void tts() {
        Courseware courseware = findOneCourseware();
        if(courseware == null){
            log.info("暂无需要进行语音合成的电子书");
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                log.error(ExceptionUtil.eMessage(e));
            }
        }
        BookAudio bookAudio = new BookAudio();
        bookAudio.setBookId(courseware.getId());
        bookAudio.setAudioName(courseware.getTitle());
        bookAudio.setAudioPicUrl(courseware.getImageOne());
        bookAudio.setAuthor(courseware.getAuthor());
        bookAudio.setResource("资源中心");
        try{
            log.info("电子书《{}》正在进行语音合成",courseware.getTitle());
            tts(courseware,bookAudio);
            bookAudio.setAudioXmlState(QuartzConstants.TTS_SUCCESS);
            bookAudio.setCreateTime(new Date());
        }catch (TtsException e){
            bookAudio.setAudioXmlState(e.getErrorCode());
            bookAudio.setAudioState(e.getErrorCode());
            bookAudio.setCreateTime(new Date());
        }
//            bookAudioRepository.save(bookAudio);
        log.info(bookAudio.toString());
    }

    /**
     * 对指定电子书进行语音合成
     * @param courseware
     * @param bookAudio
     */
    private void tts(Courseware courseware, BookAudio bookAudio) throws TtsException {
        log.info("开始转换电子书：{}",courseware.getTitle());
        CoursewareInfo coursewareInfo = coursewareInfoRepository.findOne(courseware.getId());
        //新建一个xml的作为此次语音合成音频文件的目录
        Document document = DocumentHelper.createDocument();
        //添加根元素bookAudio
        Element bookAudioXml = document.addElement("bookAudio");
        //添加电子书标题
        Element bookAudioTitle= bookAudioXml.addElement("title");
        bookAudioTitle.setText(courseware.getTitle());
        String bookXmlUrl = coursewareInfo.getUrl();
        //对电子书的资源路径进行判断不是电子书资源跳过
        if(!bookXmlUrl.startsWith("/book")){
            return;
        }
        //得到电子目录html网页的url路径
        String sourcePath = QuartzConstants.RESOURCE_DOMAIN_NAME + bookXmlUrl;
        sourcePath = sourcePath.substring(0, sourcePath.lastIndexOf("/"))
                + QuartzConstants.COURSEWARE_INDEX_URL_SUFFIX;
        StringBuffer indexHtmlStr = null;
        try {
            indexHtmlStr = HtmlUtil.getHtmlByUrl(sourcePath);
        } catch (IOException e) {
            throw new BookTtsException(courseware.getTitle() + " 获取目录失败",e);
        }
        //解析目录中的章节信息
        List<ChapterUrl> chapterUrls = HtmlUtil.analyzeBookIndexHtml(indexHtmlStr);
        //bookAudioXml添加音频文件目录
        Element xhtml= bookAudioXml.addElement("xhtml");
        //语音合成的缓存文件夹
        String lrcTempPath = setService.getValue("quartz.BookTranslateAudio.lrcTempPath",
                "./lrcTempPath/");
        lrcTempPath = FileUtil.filePathFormat(lrcTempPath);
        //获取电子书音频文件存放根文件夹
        String audioRootPath = setService.getValue("quartz.BookTranslateAudio.audioPath",
                "./bookAudio/");
        audioRootPath = FileUtil.filePathFormat(audioRootPath);
        String timeName = OSUtil.getSystemCurrentTimeAsName();
        String bookAudioDirPath = audioRootPath + dtf.format(LocalDateTime.now()) + timeName + "/";
        FileUtil.makedir(lrcTempPath);
        FileUtil.makedir(bookAudioDirPath);
        ChapterUrl cu;
        int chapterUrlSize = chapterUrls.size();
        Element chaptersElement;
        String bookChapterDirPath;
        try {
            long audioLength = 0L;
            for(int i = 0;i < chapterUrlSize;i++){
                cu = chapterUrls.get(i);
                log.info("正在转换电子书《{}》的 {} 章节",courseware.getTitle(),cu.getChapterName());
                log.info("共 {} 章，第 {} 章===========>",
                        Integer.toString(chapterUrlSize), Integer.toString(i + 1));
                chaptersElement= xhtml.addElement("chapters");
                //添加id属性,值为章节序号（从0开始）
                chaptersElement.addAttribute("id", Integer.toString(i));
                //chapterElement添加子元素title（该章节音频文件存放路径）
                Element chapterTitle= chaptersElement.addElement("title");
                chapterTitle.setText(cu.getChapterName());
                bookChapterDirPath = bookAudioDirPath + "/" + timeName + "_" + i + "/";
                FileUtil.makedir(bookChapterDirPath);
                audioLength += ttsBookChapter(cu,chaptersElement,bookChapterDirPath,
                                   sourcePath,lrcTempPath,bookAudio);
            }
            //音频索引文件写入磁盘
            String xmlPath = bookAudioDirPath + "/" + "index.xml";
            XmlUtil.writeXml(document,xmlPath);
            xmlPath = xmlPath.replace(audioRootPath,QuartzConstants.BOOK_TTS_PREFIX);
            bookAudio.setAudioTimeLength(Long.valueOf(audioLength));
            bookAudio.setAudioXmlUrl(xmlPath);
        }catch (Exception e){
            //删除文件释放磁盘资源
            FileUtil.delayDeleteFile(bookAudioDirPath);
            throw new BookTtsException("语音合成失败",e);
        }
    }

    /**
     * 对电子书章节进行语音合成
     * @param cu 章节名称路径信息
     * @param chaptersElement 保存章节音频文件目录的元素
     * @param bookChapterDirPath 电子书音频文件夹路径
     * @param sourcePath  电子书文章目录的路径
     * @param lrcTempPath 语音合成缓存文件夹
     * @return 总的音频时长
     */
    private long ttsBookChapter(ChapterUrl cu, Element chaptersElement,
                                String bookChapterDirPath,String sourcePath,
                                String lrcTempPath, BookAudio bookAudio) throws Exception {
        //章节页面的url地址,url路径是相对于目录页路径的相对路径
        String chapUrl = sourcePath.substring(0, sourcePath.lastIndexOf("/")+1) + cu.getChapterUrl();
        List<String> slices;
        try {
            slices = slicesChapterContent(HtmlUtil.getHtmlByUrl(chapUrl),cu.getChapterUrl());
        } catch (IOException e) {
            throw new BookTtsException("章节 " + cu.getChapterName() + " 获取章节内容失败",e);
        }
        int size = slices.size();
        //生成音频文件路径
        String audioPath = null;
        //生成歌词文件路径
        String lrcPath;
        //章节目录信息
        Element chaptersSub;
        Element address;
        Element lrcAddress;
        //tts任务master
        TtsMaster master;
        long audioLength = 0L;
        for(int i = 0;i < size;i++){
            String timeName = OSUtil.getSystemCurrentTimeAsName();
            audioPath = bookChapterDirPath + timeName + ".mp3";
            lrcPath = bookChapterDirPath + timeName + ".lrc";
            master = new TtsMaster();
            master.setBy(bookAudio.getAuthor())
                    .setContent(slices.get(i))
                    .setAudioTempDirPath(lrcTempPath)
                    .setAudioPath(audioPath)
                    .setLrcPath(lrcPath);
            master.ttsAndCreateLrc();
            chaptersSub= chaptersElement.addElement("chaptersSub");
            chaptersSub.addAttribute("id", Integer.toString(i));
            //给元素chaptersSub（将章节内容分割后的章节片段）
            //添加子元素<address>储存音频文件存放相对路径
            //添加子元素<lrcAddress>储存歌词文件相对路径
            address = chaptersSub.addElement("address");
            lrcAddress = chaptersSub.addElement("lrcAddress");
            //将绝对路径转换为相对路径
            audioPath = audioPath.replace(audioPath, QuartzConstants.BOOK_TTS_PREFIX);
            lrcPath = lrcPath.replace(audioPath, QuartzConstants.BOOK_TTS_PREFIX);
            address.setText(audioPath);
            lrcAddress.setText(lrcPath);
            audioLength += master.getAudioLength();
        }
        if(bookAudio.getAudioUrl() == null){
            bookAudio.setAudioUrl(audioPath);
        }
        return audioLength;
    }

    /**
     * 将章节内容切片成1000字符左右的 切片
     * @param html
     * @return
     */
    private List<String> slicesChapterContent(StringBuffer html,String chapterUrl) {
        int i = chapterUrl.indexOf('#');
        //判断锚点
        if(i < 0){
            return TextUtil.slicesString(HtmlUtil.Html2txt(html.toString()));
        }
        //有锚点只返回锚点元素的text文本
        String anchor = chapterUrl.substring(i+1);
        String anchorText = HtmlUtil.Html2txt(HtmlUtil.getElementHtmlByAnchor(html.toString(),anchor));
        return TextUtil.slicesString(anchorText);
    }


    /**
     * 查询出一篇要进行语音合成的电子书
     * @return
     */
    private Courseware findOneCourseware() {
        String columnIdStr = setService.getValue("quartz.BookTranslateAudio.cloumnId","");
        List<Courseware> coursewares;
        if(columnIdStr.length() == 0) {
            coursewares = coursewareRepository.findOutNotTtsBooks();
        }else {
            try {
                coursewares = coursewareRepository.findOutNotTtsBookByColumId(Long.parseLong(columnIdStr));
            }catch (Exception e){
                coursewares = coursewareRepository.findOutNotTtsBooks();
            }
        }
        if(coursewares == null || coursewares.size() == 0){
            return null;
        }
        return coursewares.get(0);
    }
}
