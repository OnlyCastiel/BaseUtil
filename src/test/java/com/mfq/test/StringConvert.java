package com.mfq.test;

public class StringConvert {

    public static void main(String[] args) {
        String str1 = "afas_*^#-=+/我 发 的 是";
        String regex = "[^\u4e00-\u9fa5_a-zA-Z0-9]";
        str1 = str1.replaceAll(regex,"");
        System.out.println(str1);
    }
}
