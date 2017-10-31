package com.china.ciic.studyweb.speechsynthesis.config;

import com.china.ciic.studyweb.speechsynthesis.quartz.QuartzConstants;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;

/**
 * 配置定时任务的执行线程池，并将定时任务添加到调度器
 */
@Configuration
public class SchedulerFactoryConfiger {

    private static final Logger log = LoggerFactory.getLogger(SchedulerFactoryConfiger.class);
    /**
     * spring上下wen
     */
    @Resource
    ApplicationContext applicationContext;

    /**
     * attention:
     * Details：定义quartz调度工厂
     */
    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactory() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setTaskExecutor(Executors.newFixedThreadPool(QuartzConstants.SCHEDULER_POOL_SIZE));
        // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
        bean.setOverwriteExistingJobs(true);
        // 延时启动，应用启动1秒后
        bean.setStartupDelay(QuartzConstants.SCHEDULER_STARTUP_DELAY);
        Map<String, Trigger> tasks = applicationContext.getBeansOfType(Trigger.class);
        Set<String> keys = tasks.keySet();
        Trigger[] triggers = new Trigger[keys.size()];
        int i = 0;
        for(String timedTaskName : keys){
            triggers[i] = tasks.get(timedTaskName);
            log.debug("添加定时任务：{}",timedTaskName);
            i++;
        }
        bean.setTriggers(triggers);
        return bean;
    }
}
