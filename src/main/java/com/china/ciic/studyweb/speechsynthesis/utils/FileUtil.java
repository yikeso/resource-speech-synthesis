package com.china.ciic.studyweb.speechsynthesis.utils;

import com.china.ciic.studyweb.speechsynthesis.common.DelayTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 文件工具
 */
public class FileUtil {

    /**
     * 待删除文件的队列
     */
    static final DelayQueue<DelayTask> DELETE_QUEUE = new DelayQueue<>();
    private static final AtomicInteger QUEUE_SIZE = new AtomicInteger();
    /**
     * 默认删除的延迟时间
     */
    static final int DELAY_TIME = 20;
    /**
     * 多线程协作的 锁第三方对象
     */
    static final Object LOCK = new Object();
    private static final Logger log = LoggerFactory.getLogger(FileUtil.class);

    static {
        /**
         * 创建一个新线程用来删除 队列中的文件
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (QUEUE_SIZE.get() < 1) {
                        synchronized (LOCK){
                            try {
                                //删除文件队列为空，让出线程资源
                                LOCK.wait();
                            } catch (InterruptedException e) {
                                log.error(ExceptionUtil.eMessage(e));
                            }
                        }
                    }
                    takeAndRun();
                }
            }
        }).start();
    }

    /**
     * 从延时队列中取出一个任务执行。
     */
    private static void takeAndRun(){
        try {
            DELETE_QUEUE.take().run();
        } catch (InterruptedException e) {
            log.error(ExceptionUtil.eMessage(e));
        }finally {
            QUEUE_SIZE.decrementAndGet();
        }
    }

    /**
     * 格式化file路径
     * @return
     */
    public static String filePathFormat(String filePath){
        return filePath.replaceAll("[\\\\/]+","/");
    }

    /**
     * 根据指定路径创建文件夹
     * @param filePath
     */
    public static void makedir(String filePath){
        if(filePath == null || filePath.length() == 0){
            return;
        }
        File file = new File(filePath);
        if(!file.exists()){
            file.mkdirs();
        }
    }

    /**
     * 延迟删除文件
     * @param filePath
     * @param delayTime 延迟时间
     * @param unit 时间单位
     */
    public static void delayDeleteFile(String filePath,long delayTime,TimeUnit unit){
        DeleteTask task = new DeleteTask(filePath,delayTime,unit);
        DELETE_QUEUE.add(task);
        QUEUE_SIZE.incrementAndGet();
        //向删除文件队列添加删除文件后，唤醒删除文件任务
        synchronized (LOCK){
            LOCK.notify();
        }
    }

    /**
     * 延迟删除文件
     * 默认延迟20s删除
     * @param filePath
     */
    public static void delayDeleteFile(String filePath){
        delayDeleteFile(filePath,DELAY_TIME,TimeUnit.SECONDS);
    }

    /**
     * 删除文件或文件夹
     * @param filePath
     */
    public static void deleteFile(String filePath){
        deleteFile(new File(filePath));
    }

    /**
     * 删除文件或文件夹
     * @param file
     */
    public static void deleteFile(File file){
        if(file.isDirectory()){
            File[] subFiles = file.listFiles();
            for(File f:subFiles){
                deleteFile(f);
            }
        }
        file.delete();
    }

    /**
     * 文件删除任务
     */
    static class DeleteTask implements DelayTask {
        /**
         * 待删除文件路径
         */
        String filePath;
        /**
         * 什么时间删除
         */
        long deleteTime;

        public DeleteTask(String filePath,long delayTime,TimeUnit unit){
            this.filePath = filePath;
            this.deleteTime = System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(delayTime,unit);
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(deleteTime,TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS) < 0?-1:1;
        }

        @Override
        public void run() {
            deleteFile(filePath);
        }
    }
}
