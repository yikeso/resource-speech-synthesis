package com.china.ciic.studyweb.speechsynthesis.quartz;

import com.china.ciic.studyweb.speechsynthesis.utils.ExceptionUtil;
import com.china.ciic.studyweb.speechsynthesis.utils.NetUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * 语音合成调度器
 */
public abstract class TtsAdapter implements Job {

    /**
     * 日期路径格式
     */
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    private static final Logger log = LoggerFactory.getLogger(TtsAdapter.class);

    public void ttsRun(){
        if(isPause()){
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                log.error(ExceptionUtil.eMessage(e));
            }
            return;
        }
        if(!NetUtil.isOnline()){
            log.info("网络异常，结束语音合成任务");
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                log.error(ExceptionUtil.eMessage(e));
            }
            return;
        }
        try {
            tts();
        }catch (Exception e){
            log.error(ExceptionUtil.eMessage(e));
        }
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
         ttsRun();
    }

    abstract void tts();

    abstract boolean isPause();
}
