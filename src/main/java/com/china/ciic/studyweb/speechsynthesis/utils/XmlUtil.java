package com.china.ciic.studyweb.speechsynthesis.utils;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.FileWriter;
import java.io.StringWriter;

public class XmlUtil {

	/**
	 * 将Document对像转换为text文本，同时格式美化
	 * @param document xml的document对象
	 * @return 返回文本字符串
	 * @throws Exception
	 */
	public static String formatXml(Document document) throws Exception{
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        StringWriter sw = new StringWriter(); 
        XMLWriter xw = null;
        String result = null;
        try{
        	xw = new XMLWriter(sw, format);
            xw.setEscapeText(false);
        	xw.write(document);
        	result = sw.toString();
        }
        finally{
        	if(xw != null){
        		xw.flush();
                xw.close();
                sw.flush();
                sw.close();
        	}
        }
        return result;
    }

    /**
     * 将Document对像写入文件，同时格式美化
     * @param document xml的document对象
     * @param path 写入的文件路径
     * @return 返回文本字符串
     * @throws Exception
     */
    public static void writeXml(Document document,String path) throws Exception{
        FileWriter fw = new FileWriter(path);
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        XMLWriter xw = null;
        try {
            xw = new XMLWriter(fw, format);
            xw.setEscapeText(false);
            xw.write(document);
        }finally {
            if(xw != null){
                xw.flush();
                xw.close();
                fw.flush();
                fw.close();
            }
        }
    }
	
}