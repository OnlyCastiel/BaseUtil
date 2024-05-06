package com.mfq.io.BIOChat.simpleThread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOChatService {

    private static int PORT = 9999;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        DataOutputStream dataOutputStream = null;
        DataInputStream dataInputStream = null;

        try {
            serverSocket = new ServerSocket(PORT);
            socket = serverSocket.accept();

            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            //持续收取客户端消息
            while (true){
                String message = dataInputStream.readUTF();
                System.out.println("seceive from client: "+ message);

                dataOutputStream.writeUTF("I have receive from you :" +message);
            }

        } catch (IOException e) {
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
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
