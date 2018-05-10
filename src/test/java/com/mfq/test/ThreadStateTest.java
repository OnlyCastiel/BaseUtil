package com.mfq.test;

import com.alibaba.fastjson.JSONObject;
import com.mfq.http.HttpUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
public class ThreadStateTest {

    public static void main(String[] args) {
        HttpThread http1 = new HttpThread();
        HttpThread http2 = new HttpThread();

        http1.start();
        http2.start();
    }
}

class HttpThread extends Thread{

    @Override
    public void run(){
        try{
*/
/*            System.out.print("请输入: \t");
            String str = new BufferedReader(new InputStreamReader(System.in)).readLine();*//*

            System.out.println("发起Http请求：");
            String result = HttpUtil.httpGetString("http://luckdraw.local.fdc.com.cn/aaa?str=helloworld");
            System.out.println("http请求结果："+result);
        }catch (IOException e){
            System.out.println("请求异常");
        }
    }
}*/
