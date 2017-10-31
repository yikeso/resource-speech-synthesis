package com.china.ciic.studyweb.speechsynthesis.quartz;

import com.china.ciic.studyweb.speechsynthesis.quartz.bo.TtsSuccess;
import com.china.ciic.studyweb.speechsynthesis.utils.AudioUtil;
import com.china.ciic.studyweb.speechsynthesis.utils.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * 语音合成slave
 */
public class TtsSlave implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(TtsSlave.class);

    /**
     * 生成wav文件的路径
     */
    String wavPath;
    /**
     * 需要语音合成的文字内容
     */
    String content;
    /**
     * 音频文件的播放时长
     */
    long timeLength;
    /**
     * 语音合成失败的监听类
     * volatile 解决内存可见性的问题
     */
    TtsSuccess success;
    /**
     * 计数器
     */
    CountDownLatch countDownLatch;

    @Override
    public void run() {
        try {
            //有一个任务失败
            //则取消所有的任务
            if(!isSuccess()){
                return;
            }
            AudioUtil.txtToWav(content,wavPath);
            timeLength = AudioUtil.getTimeLen(wavPath);
        } catch (Exception e) {
            log.error(ExceptionUtil.eMessage(e));
            success.setE(e);
            success.setSuccess(false);
        }finally {
            countDownLatch.countDown();
        }
    }

    public TtsSlave setWavPath(String wavPath) {
        this.wavPath = wavPath;
        return this;
    }

    public TtsSlave setContent(String content) {
        this.content = content;
        return this;
    }

    public TtsSlave setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
        return this;
    }

    public TtsSlave setSuccess(TtsSuccess success) {
        this.success = success;
        return this;
    }

    public String getWavPath() {
        return wavPath;
    }

    public String getContent() {
        return content;
    }

    public long getTimeLength() {
        return timeLength;
    }

    public boolean isSuccess() {
        return success.isSuccess();
    }

    public Exception getE() {
        return success.getE();
    }
}
