package com.china.ciic.studyweb.speechsynthesis.quartz;

import com.china.ciic.studyweb.speechsynthesis.exception.TtsException;
import com.china.ciic.studyweb.speechsynthesis.quartz.bo.TtsSuccess;
import com.china.ciic.studyweb.speechsynthesis.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 语音合成采用master-slave模式
 * 语音合成master
 * 生成歌词
 */
public class TtsMaster {

    /**
     * 需要进行语音合成的文章内容url
     */
    String content;
    /**
     * 歌词文件生成路径
     */
    String lrcPath;
    /**
     * 生成的小音频文件的缓存路径
     */
    String audioTempPath;
    /**
     * 音频文件的存放路径
     */
    String audioPath;
    /**
     * 合成音频时长
     */
    long audioLeng;

    String ti;//标题
    String ar;//歌手
    String al;//主题
    String by;//编辑

    /**
     * 语音合成的线程池
     */
    static final ThreadPoolExecutor executeor;
    private static final Logger log = LoggerFactory.getLogger(TtsMaster.class);

    static {
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
        int size = Runtime.getRuntime().availableProcessors();
        executeor = new ThreadPoolExecutor(size,size,60,TimeUnit.SECONDS,queue);
    }

    /**
     * 将文字语音合成并生成歌词
     */
    public void ttsAndCreateLrc() throws Exception {
        FileUtil.filePathFormat(audioTempPath);
        if(!audioTempPath.endsWith("/")){
            audioTempPath = audioTempPath + "/";
        }
        List<String> ss = TextUtil.splitByPunctuation(content);
        String timeName = OSUtil.getSystemCurrentTimeAsName();
        List<TtsSlave> slaves = new ArrayList<>();
        TtsSlave ttsSlave;
        int l = ss.size();
        String wavTemp;
        //语音合成任务异常的监听类
        TtsSuccess success = new TtsSuccess();
        CountDownLatch countDownLatch = new CountDownLatch(l);
        //拆分语音合成任务，进行多线程执行
        for(int i = 0; i < l;i++){
            ttsSlave = new TtsSlave();
            ttsSlave.setContent(ss.get(i))
                    .setCountDownLatch(countDownLatch)
                    .setSuccess(success);
            wavTemp = audioTempPath + timeName + "_" + i + ".wav";
            ttsSlave.setWavPath(wavTemp);
            executeor.execute(ttsSlave);
            slaves.add(ttsSlave);
        }
        //等待语音合成任务完成
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            log.error(ExceptionUtil.eMessage(e));
        }
        //语音合成失败检查
        ttsFailedCheck(slaves);
        //统计音频时长
        audioLeng = AudioUtil.countAudioLength(slaves);
        //生成歌词
        LrcUtil.createLrcFile(slaves,lrcPath,ti,ar,al,by);
        //合并音频文件碎片
        audioTempPath = audioTempPath + timeName + ".wav";
        WAVUtil.splitJointWav(audioTempPath,slaves);
        //格式转换
        AudioUtil.wavToMp3(audioTempPath,audioPath);
        /*
         * 删除缓存文件回收资源
         */
        for(TtsSlave sla:slaves){
            FileUtil.delayDeleteFile(sla.getWavPath());
        }
        FileUtil.delayDeleteFile(audioTempPath);
    }

    /**
     * 检查语音合成任务是否失败
     * @param slaves
     * @throws TtsException
     */
    private void ttsFailedCheck(List<TtsSlave> slaves) throws TtsException {
        if(slaves == null || slaves.size() == 0){
            return;
        }
        TtsSlave sla = slaves.get(0);
        if(!sla.isSuccess()){
            throw new TtsException(sla.getE());
        }
    }

    public TtsMaster setContent(String content) {
        this.content = content;
        return this;
    }

    public TtsMaster setLrcPath(String lrcDirPath) {
        this.lrcPath = lrcPath;
        return this;
    }

    public TtsMaster setAudioTempDirPath(String audioTempDirPath) {
        this.audioTempPath = audioTempDirPath;
        return this;
    }

    public TtsMaster setAudioPath(String audioDirPath) {
        this.audioPath = audioDirPath;
        return this;
    }

    public TtsMaster setTi(String ti) {
        this.ti = ti;
        return this;
    }

    public TtsMaster setAr(String ar) {
        this.ar = ar;
        return this;
    }

    public TtsMaster setAl(String al) {
        this.al = al;
        return this;
    }

    public TtsMaster setBy(String by) {
        this.by = by;
        return this;
    }

    public String getLrcPath() {
        return lrcPath;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public long getAudioLength() {
        return audioLeng;
    }
}
