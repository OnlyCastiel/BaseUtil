package com.mfq.http;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.utils.URLEncodedUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlEncodeUtil {


    public static void main(String[] args) {

        String redirectUrl = "http://pay.whruihongfeng.com/raypic-front/#/ruishow?customerName=客户姓名";
        try {
            String customerNameEncode = com.sun.deploy.net.URLEncoder.encode("客户姓名","UTF-8");
            System.out.println(customerNameEncode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }
}
