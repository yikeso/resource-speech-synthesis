package com.china.ciic.studyweb.speechsynthesis.quartz;

/**
 * 定时任务常量
 */
public interface QuartzConstants {
    /**
     * 执行定时任务的线程池的大小
     */
    int SCHEDULER_POOL_SIZE = 2;
    /**
     * 应用启动后延时多长时间开始运行定时任务
     * 单位 秒
     */
    int SCHEDULER_STARTUP_DELAY = 10;

    int TTS_SUCCESS = 1;
    /**
     * 资源服务器
     */
    String RESOURCE_DOMAIN_NAME = "http://resource.gbxx123.com";
    /**
     * 课件章节目录资源路径后缀
     */
    String COURSEWARE_INDEX_URL_SUFFIX = "/ops/b_content.xhtml";
    /**
     * 文章语音合成生成 音频文件路径的前缀
     */
    String ARTICLE_TTS_PREFIX = "/articleAudio/";
    /**
     * 电子书语音合成生成 音频文件路径的前缀
     */
    String BOOK_TTS_PREFIX = "/bookAudio/";
}
