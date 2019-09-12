package com.mfq.http;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlEncodeUtil {

    public static void main(String[] args) {
        try {
            System.out.println(URLEncoder.encode("客","UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }



}
