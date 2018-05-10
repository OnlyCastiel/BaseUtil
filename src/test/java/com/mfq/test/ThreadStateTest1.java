package com.mfq.test;

import com.mfq.http.HttpUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*public class ThreadStateTest1 {

    public static void main(String[] args) {
        HttpThread http1 = new HttpThread(true);
        HttpThread http2 = new HttpThread(false);

        http1.start();
        http2.start();
    }
}

class HttpThread extends Thread{
    private boolean flag;
    public HttpThread(boolean flag){
        this.flag=flag;
    }
    @Override
    public void run(){
        try{
            if(flag){
                System.out.print("请输入: \t");
                String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
            }else {
                System.out.println("发起Http请求：");
                String result = HttpUtil.httpGetString("http://luckdraw.local.fdc.com.cn/aaa?str=helloworld");
                System.out.println("http请求结果："+result);
            }
        }catch (IOException e){
            System.out.println("请求异常");
        }
    }
}*/
