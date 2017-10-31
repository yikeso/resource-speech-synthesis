package com.iflytek;

import java.io.UnsupportedEncodingException;

import com.china.ciic.studyweb.speechsynthesis.utils.OSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 该类所在的包名必须是com.iflytek
 * 不可更改，否则会出现外部接口无法调用错误
 *
 */
public class Qtts {
	
	private static Logger log = LoggerFactory.getLogger(Qtts.class);

    static {
        String osName = OSUtil.getOS();
        if (osName.contains("Windows")) {
            // 加载语音合成接口，两个dll文件的加载先后顺序不可颠倒
            System.load("C:/Program Files (x86)/Java/iFlyTTS.dll");
            System.load("C:/Program Files (x86)/Java/QuickTTS.dll");
        } else {
            System.load("/usr/java/default/bin/libqtts.so");
            System.load("/usr/java/default/bin/libiflytts.so");
        }
    }

	//
	// Java_com_iflytek_quicktts_QuickTTSSynth
	// QuickTTSSynth
	// QuickTTSSynthW
	//
	
	public static native long QuickTTSSynth(byte[] text, boolean is_file,
			byte[] output, byte[] serverIP, byte[] svcid, byte[] param);
	
		
	/**
	 * 调用外部接口进行语音合成
	 * @param text 输入的文本，可以由下一变量指定是文件还是直接文本
	 * @param is_file 指明上一个变量是否为文件名，否则认为是文本
	 * @param output 输出的语音文件路径（如C:\xxx.wav）
	 * @param serverIP 指定服务器地址和端口号，形式如(192.168.1.1:10344)
	 * 	可以不指定，由ISP自动寻找。 
	 * @param svcid 指定合成服务的ID，如ce30/intp50之类，一般可为空。
	 * @param param 以“参数类型＝参数值的形式 ”传入参数配置，
	 * 	如“1＝1 2＝3”,具体参数类型和参数值可以参考开发文档或iFlyTTS.h的定义。
	 * @return 如果合成成功，返回0，否则返回错误代码，具体可以
	 * 		参考TTSERRCODE.h文件
	 * @throws UnsupportedEncodingException 
	 */
	public static long synthesize(String text, boolean is_file,
			String output, String serverIP, String svcid, String param) throws UnsupportedEncodingException
	{	
		//QuickTTSSynth方法需传入bytes数组，服务器使用的是utf-8字符集进行解码
		//不同系统的默认字符集不同，可能导致服务器解码失败，需在将字符串转换为
		//byte数组是，使用utf-8字符集进行转换.
		//.getBytes("utf-8")会抛出不支持字符集异常（UnsupportedEncodingException）
		return QuickTTSSynth(
					text.getBytes("utf-8"), is_file, output.getBytes("utf-8"),
					serverIP.getBytes("utf-8"), svcid.getBytes("utf-8"), param.getBytes("utf-8"));
	}
}
