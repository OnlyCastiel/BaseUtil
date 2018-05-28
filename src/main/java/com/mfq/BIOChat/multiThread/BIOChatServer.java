package com.mfq.BIOChat.multiThread;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * 多线程版BIO连接
 *
 * 优点：支持多客户端连接
 *
 * 缺点：每当有一个新的客户端请求接入时，服务端必须创建一个新的线程处理新接入的客户端链路，一个线程只能处理一个客户端连接。
 * 但是线程的开销是很昂贵的，以M计算
 * 在高性能服务器应用领域，往往需要面向成千上万个客户端的并发连接，这种模型显然无法满足高性能、高并发接入的场景。
 * 系统会发生线程堆栈溢出、创建新线程失败等问题，并最终导致进程宕机或者僵死，不能对外提供服务。
 */
public class BIOChatServer {

    //服务器端口
    private static final int PORT = 9999;

    private int connectNum = 0;

    public void init(){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            while (true){
                Socket socket = serverSocket.accept();
                connectNum++;
                System.out.println("get the " + connectNum +" connection !" );

                new ClientHandle(socket).start();
            }

        } catch (IOException e) {
            if(serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        BIOChatServer chatServer = new BIOChatServer();
        System.out.println("server starting~~~");
        chatServer.init();
    }
}

class ClientHandle extends Thread{

    private Socket socket;

    public ClientHandle(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;

        try {
            //持续服务客户端
            while (socket.isConnected()){
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                String userNeed = dataInputStream.readUTF();
                System.out.println("user request for :"+userNeed);

                dataOutputStream.writeUTF("new " + userNeed);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                dataOutputStream.close();
                dataInputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
