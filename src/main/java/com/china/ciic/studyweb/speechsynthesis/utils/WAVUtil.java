package com.china.ciic.studyweb.speechsynthesis.utils;

import com.china.ciic.studyweb.speechsynthesis.quartz.TtsSlave;

import java.io.*;
import java.util.List;

public class WAVUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 去掉音频文件的头部标签
	 * @param wavByte 音频文件的byte数组
	 * @return 返回没有头标签的wav音频内容byte数组
	 */
	public static byte[] cutTag(byte[] wavByte){
		if(wavByte.length < 44){
			return new byte[0];
		}
		byte[] contentByte = new byte[wavByte.length-44];
		System.arraycopy(wavByte, 44, contentByte, 0, contentByte.length);
		return contentByte;
	}

	/**
	 * 更新wav音频文件的的大小
	 * @param wavPath
	 */
	private static void updateWavLength(String wavPath) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(wavPath,"w");
		try{
			int l = (int)raf.length();
			raf.skipBytes(4);
			raf.write(l);
		}finally {
			raf.close();
		}
	}

	/**
	 * 读取一个wav文件返回一个byte数组
	 * @param path
	 * @return
	 */
	public static byte[] readWAV(String path) throws IOException {
		if(!path.endsWith(".wav")){
			throw new RuntimeException("传入的不是mp3文件");
		}
		byte[] buffer = new byte[1024*100];
		byte[] result;
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
		try {
			fis = new FileInputStream(path);
			int length = 0;
			bos = new ByteArrayOutputStream();
			while((length = fis.read(buffer)) != -1){
				bos.write(buffer, 0, length);
			}
			result = bos.toByteArray();
			return result;
		}finally{
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(bos != null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 将wav音频文件的切片，拼接到一起
	 * @param path
	 * @param sliceWav
	 */
	public static void splitJointWav(String path,List<TtsSlave> sliceWav) throws IOException {
		int index = 0;
		int l = sliceWav.size();
		byte[] buffer;
		FileOutputStream fos = new FileOutputStream(path);
		try{
			buffer = readWAV(sliceWav.get(index).getWavPath());
			fos.write(buffer);
			for (index++; index < l; index++) {
				buffer = readWAV(sliceWav.get(index).getWavPath());
				buffer = cutTag(buffer);
				fos.write(buffer);
			}
		}finally {
			fos.close();
		}
        updateWavLength(path);
	}


	public static void main(String[] args) {
		String path = "K:/lrcTempPath/1480037658557_46.wav";
		byte[] b = new byte[0];
		try {
			b = readWAV(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(b.length);
		System.out.println(cutTag(b).length);
	}
}
