package com.china.ciic.studyweb.speechsynthesis.config;

import com.china.ciic.studyweb.speechsynthesis.quartz.BookTtsAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
/**
 * 配置电子书语音合成定时任务的执行周期
 */
@Configuration
public class BookQuartzConfiger {

    private static final Logger log = LoggerFactory.getLogger(BookQuartzConfiger.class);
    /**
     * attention:
     * Details：配置定时任务
     */
    @Bean(name = "bookTtsDetail")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(BookTtsAdapter task) {// ScheduleTask为需要执行的任务

        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        /*
         *  是否并发执行
         *  例如每5s执行一次任务，但是当前任务还没有执行完，就已经过了5s了，
         *  如果此处为true，则下一个任务会执行，如果此处为false，则下一个任务会等待上一个任务执行完后，再开始执行
         */
        jobDetail.setConcurrent(false);
        jobDetail.setName("BookTts");// 设置任务的名字
        jobDetail.setGroup("tts");// 设置任务的分组，这些属性都可以存储在数据库中，在多任务的时候使用

        /*
         * 为需要执行的实体类对应的对象
         */
        jobDetail.setTargetObject(task);

        /*
         * sayHello为需要执行的方法
         * 通过这几个配置，告诉JobDetailFactoryBean我们需要执行定时执行ScheduleTask类中的sayHello方法
         */
        jobDetail.setTargetMethod("ttsRun");
        return jobDetail;
    }

    /**
     * attention:
     * Details：配置定时任务的触发器，也就是什么时候触发执行定时任务
     */
    @Bean(name = "bookTtsTriggerFactory")
    public CronTriggerFactoryBean cronJobTrigger(
            @Qualifier("bookTtsDetail") MethodInvokingJobDetailFactoryBean jobDetail) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(jobDetail.getObject());
        tigger.setCronExpression("0/1 * * * * ?");// 初始时的cron表达式
        tigger.setName("bookTtsTrigger");// trigger的name
        return tigger;
    }

}
