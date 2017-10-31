package com.china.ciic.studyweb.speechsynthesis.utils;

import com.china.ciic.studyweb.speechsynthesis.quartz.bo.ChapterUrl;
import org.dom4j.DocumentHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * html操作的工具类
 */
public class HtmlUtil {

    private final static int OUT_TIME = 60000;

    /**
     * 解析电子书目录xhtml 获取目录信息
     * @param indexXhtml
     */
    public static List<ChapterUrl> analyzeBookIndexHtml(StringBuffer indexXhtml){
        return analyzeBookIndexHtml(indexXhtml.toString());
    }

    /**
     * 解析电子书目录xhtml 获取目录信息
     * @param indexXhtml
     */
    public static List<ChapterUrl> analyzeBookIndexHtml(String indexXhtml){
        Document document = Jsoup.parseBodyFragment(indexXhtml);
        //得到Document对象中的所有<a>标签元素，每一个<a>标签元素都是目录中的一个章节
        Elements elements = document.getElementsByTag("a");
        int size = elements.size();
        List<ChapterUrl> list = new ArrayList<>();
        //电子书章节信息
        ChapterUrl chapterUrl;
        //电子书章节资源路径
        String href;
        for(int i = 0;i < size;i++){
            Element element = elements.get(i);
            href = element.attr("href");
            if(href ==null || href.length() == 0){
                continue;
            }
            chapterUrl = new ChapterUrl();
            chapterUrl.setChapterUrl(href)
                    .setChapterName(element.text().trim());
            list.add(chapterUrl);
        }
        return list;
    }

    /**
     * 根据传入资源路径抓取网页
     * @param resourcePath
     * @return
     * @throws IOException
     */
    public static StringBuffer getHtmlByUrl(String resourcePath) throws IOException {
        URL url = new URL(resourcePath);
        URLConnection connection = url.openConnection();
        HttpURLConnection httpConnection = (HttpURLConnection) connection;
        int responseCode = httpConnection.getResponseCode();
        //判断http请求是否成功
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("请求目录页："+resourcePath+" 失败");
        }
        connection.getContentLengthLong();
        StringBuffer result = new StringBuffer(connection.getContentLength());
        InputStreamReader streamReader = new InputStreamReader(connection.getInputStream(),"UTF-8");
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        String buffer;
        //http响应
        for(buffer = bufferedReader.readLine();buffer != null;buffer = bufferedReader.readLine()){
            result.append(buffer);
        }
        return result;
    }

    /**
     * 将传入html网页过滤标签得到text文本
     * @param html
     * @return
     */
    public static String Html2txt(String html){
        Document document = Jsoup.parse(html);
        Element body = document.body();
        return body.text();
    }

    /**
     * 根据传入资源路径获取网页的无标签文本内容
     * @param resourcePath
     * @return
     * @throws IOException
     */
    public static String getTextByUrl(String resourcePath) throws IOException {
        Document document = Jsoup.parse(new URL(resourcePath), OUT_TIME);
        return document.body().text();
    }

    /**
     * 得到html中锚点元素的innerHtml
     * @param html
     * @param anchor
     * @return
     */
    public static String getElementHtmlByAnchor(String html,String anchor){
        Document document = Jsoup.parseBodyFragment(html);
        Element anchorElement = document.getElementById(anchor);
        return anchorElement.html();
    }
}
