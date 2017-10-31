package com.china.ciic.studyweb.speechsynthesis.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class SetSourceConfiger {

    @Bean
    public MyPropertyPlaceholderConfigurer setSource(){
        MyPropertyPlaceholderConfigurer sourceSet = new MyPropertyPlaceholderConfigurer();
        List<Resource> list = new ArrayList();
//        list.add("file:./common.properties");
        list.add(new FileSystemResource("./ttsTaskConfig.properties"));
        Resource[] rs = new Resource[1];
        rs = list.toArray(rs);
        sourceSet.setIgnoreResourceNotFound(true);
        sourceSet.setLocations(rs);
        return sourceSet;
    }
}
