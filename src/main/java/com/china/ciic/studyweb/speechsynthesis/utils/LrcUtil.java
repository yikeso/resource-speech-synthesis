package com.china.ciic.studyweb.speechsynthesis.utils;

import com.china.ciic.studyweb.speechsynthesis.quartz.TtsSlave;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * 歌词工具
 */
public class LrcUtil {

    /**
     * 将逐句歌词的list集合写入到一个歌词文件中
     * @param lrcList
     * @param tagerPath
     * @param ti 标题
     * @param ar 歌手
     * @param al 主题
     * @param by 编辑
     * @throws IOException
     */
    public static void createLrcFile(
            List<TtsSlave> lrcList, String tagerPath, String ti, String ar, String al, String by
    )throws IOException {
        createLrcFile(lrcList,new File(tagerPath),ti,ar,al,by);
    }

    /**
     * 将逐句歌词的list集合写入到一个歌词文件中
     * @param lrcList
     * @param tagerFile
     * @param ti 标题
     * @param ar 歌手
     * @param al 主题
     * @param by 编辑
     * @throws IOException
     */
    private static void createLrcFile(
            List<TtsSlave> lrcList, File tagerFile, String ti, String ar, String al, String by
    ) throws IOException {
        FileWriter fw = new FileWriter(tagerFile);
        BufferedWriter bw=new BufferedWriter(fw);
        if(ti!=null&&!ti.equals("")){
            bw.write("[ti:"+ti+"]");
            bw.newLine();
        }
        if(ar!=null&&!ar.equals("")){
            bw.write("[ar:"+ar+"]");
            bw.newLine();
        }
        if(al!=null&&!al.equals("")){
            bw.write("[al:"+al+"]");
            bw.newLine();
        }
        if(by!=null&&!by.equals("")){
            bw.write("[by:"+by+"]");
            bw.newLine();
        }
        //将歌词写入文件
        for(TtsSlave lrcVo:lrcList){
            int time = (int)lrcVo.getTimeLength();
            if(time < 0){
                break;
            }
            bw.write(getTimeText(time)+lrcVo.getContent().trim());
            bw.newLine();
        }
        bw.close();
        fw.close();
    }

    /**
     * 将时间毫秒数转换格式
     * @param time 时间毫秒数
     * @return 格式化后的时间字符串
     */
    private static String getTimeText(int time){
        //计算秒和分钟
        int mm=0;
        int ss=0;
        ss=time/1000;
        if(ss>60){
            ss=(time/1000)%60;
            mm=(time/1000-ss)/60;
        }
        String times="[%s:%s:%s]";
        String ms="00";
        //计算毫秒
        if(time!=0){
            ms= time+"";
            ms=ms.substring(ms.length()-3,ms.length());
        }
        return String.format(times, mm>=10?mm:"0"+mm,ss>=10?ss:"0"+ss,ms);
    }

}
