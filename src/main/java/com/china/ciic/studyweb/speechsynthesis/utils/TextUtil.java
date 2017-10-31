package com.china.ciic.studyweb.speechsynthesis.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 文本工具
 */
public class TextUtil {

    /**
     * 字符串切片长度
     */
    private static final int STRING_LENGTH = 1000;

    /**
     * 将传入字符串切片成规定长度
     * @param text
     * @return
     */
    public static List<String> slicesString(String text){
        List<String> list = new ArrayList<String>();
        if(text == null || text.length() == 0){
            return list;
        }
        if(text.length() < 1000){
            list.add(text);
            return list;
        }
        int start = 0;
        int end = STRING_LENGTH;
        int length = text.length();
        for(end = text.indexOf('。',end);end > 0;end = text.indexOf('。',end)){
            end++;
            list.add(text.substring(start,end));
            start = end;
            if (length - end > STRING_LENGTH) {
                end += STRING_LENGTH;
            }else {
                break;
            }
        }
        if(length > start){
            list.add(text.substring(start));
        }
        return list;
    }

    /**
     * 根据标点将文字拆分成句
     * @param text
     * @return
     */
    public static List<String> splitByPunctuation(String text){
        List<String> list = new ArrayList<>();
        if(text == null || text.length() == 0){
            return list;
        }
        String[] ss = text.split("\\pP");
        for(String s:ss){
            s = s.trim();
            if(s.length() == 0){
                continue;
            }
            list.add(s);
        }
        return list;
    }
}
