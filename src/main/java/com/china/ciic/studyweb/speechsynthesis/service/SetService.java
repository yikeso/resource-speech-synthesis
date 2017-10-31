package com.china.ciic.studyweb.speechsynthesis.service;

public interface SetService {

    /**
     * 根据传入的key获取配置的值
     * @param key
     * @return
     */
    String getValue(String key);

    /**
     * 根据传入的key获取配置的值，没有对应值，则返回传入的默认值
     * @param key
     * @param defaultValue
     * @return
     */
    String getValue(String key,String defaultValue);
}
