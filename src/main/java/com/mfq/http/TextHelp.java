package com.mfq.http;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pl on 2016/11/24.
 */
public class TextHelp {
    //定义匹配script脚本标签的正则表达式
    private static final String reg_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
    //定义匹配style样式的正则表达式
    private static final String reg_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";
    //定义匹配html标签的正则表达式
    private static final String reg_html = "<[.[^>]]*>";
    //定义匹配空格的正则表达式
    private static final String reg_space = "\\s*|\t|\r|\n";
    //定义匹配图片的正则
    private static final String reg_Image = "<img.+?src=\"(.+?)\".+?/?>";

    //删除标签
    private static String delTag(String htmlStr) {
        //创建模式,CASE_INSENSITIVE静态常量可以忽略大小写
        Pattern p_script = Pattern.compile(reg_script, Pattern.CASE_INSENSITIVE);
        //创建适配器完成匹配,被匹配中的变成空串
        Matcher m_script = p_script.matcher(htmlStr);// 过滤script标签
        //替换所有空串,达到删除的效果
        htmlStr = m_script.replaceAll("");

        Pattern p_style = Pattern.compile(reg_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);  // 过滤style标签
        htmlStr = m_style.replaceAll("");

        Pattern p_html = Pattern.compile(reg_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);  // 过滤html标签
        htmlStr = m_html.replaceAll("");

        Pattern p_space = Pattern.compile(reg_space, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);  // 过滤空格回车标签
        htmlStr = m_space.replaceAll("");
        return htmlStr.trim(); // 返回文本字符串
    }

    //匹配图片链接
    private List<String> getImgUrlFromHtml(String htmlStr){
        ArrayList<String> imgList = null;

        Pattern p = Pattern.compile(reg_Image,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(htmlStr);

        if(m.find()){
            imgList = new ArrayList<String>();
            int num = m.groupCount();
            for(int i=1 ; i<= num ;i++ ){
                String imageSrcStr = m.group(1);
                int index = m.start();
                String result = htmlStr.substring(index);
                imgList.add(imageSrcStr);
            }
            //提取尾部文本内容

        }

        return imgList;
    }


    //提取所有文本
    public static String getTextFromHtml(String htmlStr) {
        if (null != htmlStr && "" != htmlStr) {
            // 删除html标签
            htmlStr = delTag(htmlStr);
            htmlStr = htmlStr.replaceAll("&nbsp;", "");
            return htmlStr;
        } else {
            return "";
        }
    }


    public static List<String> getTextListFromHtml(String htmlStr){
        ArrayList<String> textList = null;

        //按照标签分段
        Pattern p_html = Pattern.compile(reg_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);  // 过滤html标签
        if(m_html.find()){
            textList = new ArrayList<String>();
            int num = m_html.groupCount();
            int start = 0;
            int end = 0;
            String textStr;
            for(int i=1 ; i<= num ;i++ ){
                //获取文本信息
                String textHtml = m_html.group(i);
                //文本首部index
                end = m_html.start();
                //处理可能包含文本分段： 不在标签内的例外文本
                String result = htmlStr.substring(start,end);
                if(StringUtils.isNotEmpty(result)){
                    textStr = delTag(result);
                    if(StringUtils.isNotEmpty(textStr)){
                        textList.add(textStr);
                    }
                }
                //处理匹配出来的包含文本的标签:<p>怎么办</p>
                textStr = delTag(textHtml);
                if(StringUtils.isNotEmpty(textStr)){
                    textList.add(textStr);
                }
            }
            //处理尾部： 可能不在标签内的例外文本
            String result = htmlStr.substring(end);
            if(StringUtils.isNotEmpty(result)){
                textStr = delTag(result);
                if(StringUtils.isNotEmpty(textStr)){
                    textList.add(textStr);
                }
            }
        }
        return  textList;
    }


    //按照顺序提取富文本的文字与图片
    private static List<String> getContentFromHtml(String htmlStr){
        ArrayList<String> contentList = null;

        Pattern p = Pattern.compile(reg_Image,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(htmlStr);

        if(m.find()){
            contentList = new ArrayList<String>();
            int num = m.groupCount();
            int start = 0;
            int end = 0;
            List<String> textStrList;
            for(int i=1 ; i<= num ;i++ ){
                //获取图片地址
                String imageSrcStr = m.group(i);
                //img标签首部index
                end = m.start();
                //需要处理的包含文本的HTML
                String result = htmlStr.substring(start,end);
                //提取文本内容
                textStrList = getTextListFromHtml(result);

                if(textStrList != null && textStrList.size() >0){
                    contentList.addAll(textStrList);
                }
                contentList.add(imageSrcStr);
            }
            //处理尾部
            String result = htmlStr.substring(end);
            if(StringUtils.isNotEmpty(result)){
                textStrList = getTextListFromHtml(result);
                if(textStrList != null && textStrList.size() >0){
                    contentList.addAll(textStrList);
                }
            }
        }

        return contentList;
    }


    public static void main(String[] args) {
        String htmlStr = "<p>怎么办1</p><p>怎么办2</p><p fromWeixin=\"2\"><img src=\"http://yfdev.oss-cn-hangzhou.aliyuncs.com/avatar/dec/a155ab9d-2e5e-4828-85eb-06f8be03af24.jpg\"></img></p><p fromWeixin=\"0\">怎么办3</p><p>怎么办4</p>";
        //String resultStr = getTextFromHtml(htmlStr);
        List<String> imgStr = getContentFromHtml(htmlStr);

        System.out.println(imgStr);

    }
}
