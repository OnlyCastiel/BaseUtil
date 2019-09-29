package com.mfq.test;

public class DomainTest {

    public static void main(String[] args) {
        String str1="123456";

        String str2 = changeStr(str1);

        System.out.println(str1 == str2);


    }

    public static String changeStr(String str){
        return new String("123456");
    }
}
