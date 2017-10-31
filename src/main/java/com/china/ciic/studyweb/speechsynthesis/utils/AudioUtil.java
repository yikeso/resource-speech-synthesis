package com.china.ciic.studyweb.speechsynthesis.utils;

import com.china.ciic.studyweb.speechsynthesis.quartz.TtsSlave;
import com.iflytek.Qtts;
import it.sauronsoftware.jave.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 音频操作工具，包括文字语音合成，wav转mp3，获取音频时长
 * 
 * 猜测  it.sauronsoftware.jave 调用ffmpeg进行音频时长获取
 * 和音频格式转换 过程中，会造成系统内存泄漏。对java虚拟机内存无影响
 * @author kakasun
 *
 */
public class AudioUtil implements Serializable {
	private static Logger logger = LoggerFactory.getLogger(AudioUtil.class);
	private static final long serialVersionUID = 1L;
	private static final EncodingAttributes mp3Attrs;//音频文件的属性
	private static final Encoder encoder;//音频文件解码器，获取音频时长,音频格式转换.实现对象的重用
	/**
	 * 重入锁 jave 不支持并发
	 */
	private static final ReentrantLock lock = new ReentrantLock();

	/*
	 * 文字语音合成参数
	 */
	private static final boolean is_file = false;// 说明传入的字符串不是一个文件路径
	private static final String serverIP = "10.1.2.200";// tts语音合成的服务器的ip
	// String serverIP = "103.244.67.200";
	private static final String svcid = "";
	private static final String param = "5=3 4=6";
	
	static{
		//音频文件解码器，获取音频时长,音频格式转换
		encoder = new Encoder();
		// 设置mp3音频的属性
		AudioAttributes mp3 = new AudioAttributes();
		mp3.setCodec("libmp3lame");
		mp3.setBitRate(new Integer(64000));
		mp3.setChannels(new Integer(2));
		mp3.setSamplingRate(new Integer(48000));
		// 将mp3音频属性转换为文件的编码方式
		mp3Attrs = new EncodingAttributes();
		mp3Attrs.setFormat("mp3");
		mp3Attrs.setAudioAttributes(mp3);
	}
	
	/**
	 * 将字符串语音合成为wav文件
	 * @param content
	 * @param wavPath
	 * @throws UnsupportedEncodingException 传入字符串，会使用"utf-8"字符集
	 * 转换为byte[]数组，所以有不支持字符集异常抛出
	 */
	public static void txtToWav(String content, String wavPath) throws UnsupportedEncodingException{
		Qtts.synthesize(content, is_file, wavPath, serverIP, svcid, param);
	}
	
	/**
	 * 将wav格式文件转换为mp3格式文件
	 * @param wavPath wav文件路径
	 * @param mp3Path mp3文件路径
	 * @throws IllegalArgumentException
	 * @throws InputFormatException
	 * @throws EncoderException
	 */
	public static void wavToMp3(String wavPath,String mp3Path) throws IllegalArgumentException, EncoderException{
		File wav = new File(wavPath);
		File mp3 = new File(mp3Path);
		lock.lock();
		try {
			encoder.encode(wav, mp3, mp3Attrs);
		}finally {
			lock.unlock();
		}
	}

    /**
     * 获取音频文件播放时长
     * @param path 音频文件
     * @return long 型整数 音频时长毫秒数
     */
    public static long getTimeLen(String path) {
        return getTimeLen(new File(path));
    }

	/**
	 * 获取音频文件播放时长
	 * @param file 音频文件
	 * @return long 型整数 音频时长毫秒数
	 */
	public static long getTimeLen(File file) {
		/*
		 * 获取音频时长失败，都是发生在获取一句歌词，的几十K小wav文件的时长失败
		 * 这会导致两句歌词的显示时间相同。因此给时长一个默认值3s，如果这句歌词的
		 * 时长获取失败，就按3s算。减小歌词显示时间的同步误差。
		 */
		long tlen = 3000l;
		if (file != null && file.exists()) {
			lock.lock();
			try {
				MultimediaInfo m = encoder.getInfo(file);
				tlen = m.getDuration();
			} catch (Exception e) {
				logger.error("获取音频文件 {} 的时长失败\n{}",file.getAbsolutePath(),ExceptionUtil.eMessage(e));
			}finally {
				lock.unlock();
			}
		}
		return tlen;
	}
	/**
	 * 统计多个音频文件的播放时长
	 * @param lrcList
	 * @return
	 */
	public static long countAudioLength(List<TtsSlave> lrcList){
		long total = 0L;
		if(lrcList == null || lrcList.size() == 0){
			return total;
		}
		for(TtsSlave slave:lrcList){
			total += slave.getTimeLength();
		}
		return total;
	}
}
