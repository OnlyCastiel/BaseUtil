package com.base.io;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * 阻塞IO模型，在JDK1.4之前，java通过阻塞型IO进行与客户端的链接
 *
 * 优势：
 * 1、在无客户端连接时，线程保持阻塞，不占用CPU
 *
 * 缺点：
 * 1、单线程方案无法服务多客户端的连接请求
 * 2、多线程方案，会为每个客户端创建一个线程，而这些线程占用大量内存资源，系统开销
 * 3、多线程方案，线程中可能存在大量空闲的阻塞线程，资源浪费
 */
public class BioServer {


    public static void main(String[] args) {
        BioServer bioServer = new BioServer();
        try {
            bioServer.bioThree();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //最简单的BIO，服务一个客户端
    public void bioOne() throws IOException {
        //创建连接
        ServerSocket serverSocket = new ServerSocket(9000);
        System.out.println("开始监听端口9000...");
        //阻塞式等待连接
        Socket clientSocket = serverSocket.accept();
        System.out.println("收到来自客户端的连接，等待接收数据");
        byte[] bytes = new byte[1024];
        //阻塞式等待数据通信
        //telnet需要通过ctrl+]进入命令模式，一次性发送大量数据
        int read = clientSocket.getInputStream().read(bytes);
        if(read != -1){//未读到结尾
            System.out.println("获取到客户端发送的数据：");
            System.out.println(new String(bytes));
        }
    }

    //依次服务多个客户端方案
    public void bioTwo() throws IOException {
        //创建连接
        ServerSocket serverSocket = new ServerSocket(9000);
        System.out.println("开始监听端口9000...");
        //阻塞式等待连接
        while (true){
            Socket clientSocket = serverSocket.accept();
            System.out.println("收到来自客户端的连接，等待接收数据");
            byte[] bytes = new byte[1024];
            //阻塞式等待数据通信
            int read = clientSocket.getInputStream().read(bytes);
            if(read != -1){//未读到结尾
                System.out.println("获取到客户端发送的数据：");
                System.out.println(new String(bytes));
            }
            clientSocket.close();
        }
    }


    /**
     * 多线程方案服务
     *
     */
    public void bioThree() throws IOException{
        //创建连接
        ServerSocket serverSocket = new ServerSocket(9000);
        System.out.println("开始监听端口9000...");
        //阻塞式等待连接
        while (true){
            Socket clientSocket = serverSocket.accept();
            System.out.println("收到来自客户端的连接，等待接收数据");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    byte[] bytes = new byte[1024];
                    //阻塞式等待数据通信
                    int read = 0;
                    while (true){
                        try {
                            read = clientSocket.getInputStream().read(bytes);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(read != -1){//未读到结尾
                            System.out.println("获取到客户端发送的数据：");
                            System.out.println(new String(bytes,0,read));
                        }
                    }
                }
            }).start();
        }
    }

}
