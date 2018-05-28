package com.mfq.BIOChat.multiThread;

import java.io.*;
import java.net.Socket;

public class BIOChatClient {

    //服务器IP地址
    private static final String SERVER_ADDR = "127.0.0.1";

    //服务器端口
    private static final int PORT = 9999;

    public static void main(String[] args) {
        System.out.println("client start.......");
        System.out.println("when you send \"OK\"，it will be disconnect");

        Socket socket = null;
        DataOutputStream outputStream = null;
        DataInputStream inputStream = null;
        try {
            socket = new Socket(SERVER_ADDR,PORT);
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new DataInputStream(socket.getInputStream());
            //持续获取客户输入，发送给服务器
            while (true){
                System.out.println("say what you want !");
                String userNeed = new BufferedReader(new InputStreamReader(System.in)).readLine();
                if(userNeed.equals("OK")){
                    System.out.println("client will be close,thanks for using it.");
                    break;
                }
                outputStream.writeUTF(userNeed);

                //获取服务端回复
                String receive = inputStream.readUTF();
                System.out.println("you have receive from server: "+receive);
            }
        } catch (IOException e) {
            //TODO Exception handle
        }finally {
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}


