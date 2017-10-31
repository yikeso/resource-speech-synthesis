package com.china.ciic.studyweb.speechsynthesis.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
/**
 * 和Exception有关的一些方法
 * 有 获取Exception的printStackTrace()方法输出的字符串
 * @author kakasun
 *
 */
public class ExceptionUtil {

	private static final long serialVersionUID = 1L;
	/**
	 * 获取Exception的printStackTrace()方法输出的字符串
	 * @param exception Exception 对象
	 * @return
	 */
	public static String eMessage(Exception exception){
		if(exception == null)
	        return "";
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    try{
	    	exception.printStackTrace(new PrintStream(baos));
	    }finally{
	        try {
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    return baos.toString();
	}
}
