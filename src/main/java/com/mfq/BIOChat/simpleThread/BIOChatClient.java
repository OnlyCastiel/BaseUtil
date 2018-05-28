package com.mfq.BIOChat.simpleThread;

import java.io.*;
import java.net.Socket;

public class BIOChatClient {

    private static final String IP_ADDR = "127.0.0.1";

    private static int PORT = 9999;

    public static void main(String[] args) {
        Socket socket = null;
        DataOutputStream dataOutputStream = null;
        DataInputStream dataInputStream = null;
        try {
            socket = new Socket(IP_ADDR,PORT);

            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());

            while (true){
                String userNeed = new BufferedReader(new InputStreamReader(System.in)).readLine();
                dataOutputStream.writeUTF(userNeed);

                String result = dataInputStream.readUTF();
                System.out.println("receive from server :" + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                dataOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                dataInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
