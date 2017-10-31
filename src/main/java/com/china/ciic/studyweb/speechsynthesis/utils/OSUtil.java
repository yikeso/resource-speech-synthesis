package com.china.ciic.studyweb.speechsynthesis.utils;

import java.io.Serializable;

public class OSUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 获取系统类型
	 * @return 返回系统类型的名称
	 */
	public static String getOS() {
		return System.getProperty("os.name");
	}
	
	public static synchronized String getSystemCurrentTimeAsName(){
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return System.currentTimeMillis()+"";
	}
}
