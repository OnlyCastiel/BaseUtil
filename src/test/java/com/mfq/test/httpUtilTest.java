package com.mfq.test;

import com.mfq.http.HttpUtil;

import java.io.IOException;

public class httpUtilTest {

    public static void main(String[] args) {
        String urlPrefix = "https://www.douban.com/group/topic/36305338/?start=";

        for(int i = 1 ;i<121 ;i++){
            int num = i*100;
            String requestUrl = urlPrefix + num;
            try {
                String result = HttpUtil.httpGetString(requestUrl);
                if(result.contains("641176113@qq.com")){
                    System.out.println(requestUrl);
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
