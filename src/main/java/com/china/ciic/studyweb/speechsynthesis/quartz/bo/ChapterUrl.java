package com.china.ciic.studyweb.speechsynthesis.quartz.bo;

/**
 * 电子书的章节信息
 */
public class ChapterUrl {
    /**
     * 电子书的章节名称
     */
    String chapterName;
    /**
     * 电子书的章节url路径
     */
    String chapterUrl;

    public String getChapterName() {
        return chapterName;
    }

    public ChapterUrl setChapterName(String chapterName) {
        this.chapterName = chapterName;
        return this;
    }

    public String getChapterUrl() {
        return chapterUrl;
    }

    public ChapterUrl setChapterUrl(String chapterUrl) {
        this.chapterUrl = chapterUrl;
        return this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ChapterUrl{");
        sb.append("chapterName='").append(chapterName).append('\'');
        sb.append(", chapterUrl='").append(chapterUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
