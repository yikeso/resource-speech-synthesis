package com.china.ciic.studyweb.speechsynthesis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 和网络相关的方法
 * @author kakasun
 *
 */
public class NetUtil {
	private static Logger logger = LoggerFactory.getLogger(NetUtil.class);
	private static final long serialVersionUID = 1L;

	/**
	 * 测试网络是否通畅
	 * @return 布尔值，true网络畅通，false网络不通
	 * @throws Exception
	 */
	public static boolean isOnline(){
		StringBuilder sb = new StringBuilder();
		Process pro = null;
		try {
			String osName = OSUtil.getOS();
			if(osName.contains("Linux"))
				pro = Runtime.getRuntime().exec("ping -c 4 www.china.com.cn");
			else{
				pro = Runtime.getRuntime().exec("ping www.china.com.cn");
			}
			BufferedReader buf = new BufferedReader(new InputStreamReader(
					pro.getInputStream()));
			String line = null;
			while ((line = buf.readLine()) != null){
				sb.append(line);
			}
		} catch (Exception ex) {
			logger.error(ExceptionUtil.eMessage(ex));
			return false;
		}finally{
			//不销毁，会造成系统内存泄漏
			if(pro != null){
				pro.destroy();
			}
		}
		String str = sb.toString();
		if(str.indexOf("TTL") > 0){
			return true;
		}else if(str.indexOf("ttl") > 0){
			return true;
		}else{
			return false;
		}
	}
}
