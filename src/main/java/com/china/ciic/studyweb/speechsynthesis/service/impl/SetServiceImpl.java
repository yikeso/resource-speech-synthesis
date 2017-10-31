package com.china.ciic.studyweb.speechsynthesis.service.impl;

import com.china.ciic.studyweb.speechsynthesis.config.MyPropertyPlaceholderConfigurer;
import com.china.ciic.studyweb.speechsynthesis.service.SetService;
import com.china.ciic.studyweb.speechsynthesis.utils.FileUtil;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;

@Service("setService")
public class SetServiceImpl implements SetService {

    /**
     * 清空缓存文件夹
     */
    @PostConstruct
    public void clearTtsTemp(){
        //语音合成的缓存文件夹
        String lrcTempPath = this.getValue("quartz.BookTranslateAudio.lrcTempPath",
                "./lrcTempPath/");
        lrcTempPath = FileUtil.filePathFormat(lrcTempPath);
        File temp = new File(lrcTempPath);
        File[] subList = temp.listFiles();
        if (subList != null) {
            for (File sub : subList) {
                FileUtil.delayDeleteFile(sub.getAbsolutePath());
            }
        }
        lrcTempPath = this.getValue("quartz.ArticleTranslateAudio.lrcTempPath",
                "./lrcTempPath/");
        lrcTempPath = FileUtil.filePathFormat(lrcTempPath);
        temp = new File(lrcTempPath);
        subList = temp.listFiles();
        if(subList == null){
            return;
        }
        for(File sub : subList){
            FileUtil.delayDeleteFile(sub.getAbsolutePath());
        }
    }

    /**
     * 根据传入的key获取配置的值
     * @param key
     * @return
     */
    public String getValue(String key){
        return MyPropertyPlaceholderConfigurer.getValue(key);
    }

    /**
     * 根据传入的key获取配置的值，没有对应值，则返回传入的默认值
     * @param key
     * @param defaultValue
     * @return
     */
    public String getValue(String key,String defaultValue){
        String value = MyPropertyPlaceholderConfigurer.getValue(key);
        return value == null?defaultValue:value;
    }
}
