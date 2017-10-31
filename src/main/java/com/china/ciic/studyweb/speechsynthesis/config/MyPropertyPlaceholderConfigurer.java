package com.china.ciic.studyweb.speechsynthesis.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MyPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    static final Map<String,String> properties = new HashMap<String,String>();

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            properties.put(keyStr, value);
        }
    }

    /**
     * 根据key获取value
     * @param key
     * @return
     */
    public static String getValue(String key) {
        return properties.get(key);
    }

}
