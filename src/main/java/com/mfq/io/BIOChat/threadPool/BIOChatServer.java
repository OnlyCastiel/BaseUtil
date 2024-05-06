package com.mfq.io.BIOChat.threadPool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;


/**
 * 线程池版BIO连接
 *
 * 优点：为了改进一线程一连接模型，后来又演进出了一种通过线程池和消息队列实现N个线程处理M个客户端的模型。
 * 当有新的客户端接入的时候，将客户端的Socket封装成一个Task（该任务实现java.lang.Runnable接口）投递到后端的线程池中进行处理,
 * JDK的线程池维护一个消息队列和N个活跃线程对消息队列中的任务进行处理。通过线程池可以灵活的调配线程资源，
 * 设置线程的最大值，防止由于海量并发接入导致线程耗尽。由于它的底层通信机制依然使用同步阻塞IO，所以被称为 “伪异步”。
 *
 * 缺点：
 * 假如所有可用线程都阻塞住了,后续的IO都将在队列中排队
 */
public class BIOChatServer {

    //服务器端口
    private static final int PORT = 9999;

    private int connectNum = 0;

    public void init(){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            ExecutorService executor = Executors.newCachedThreadPool();

            while (true){
                Socket socket = serverSocket.accept();
                connectNum++;
                System.out.println("get the " + connectNum +" connection !" );

                executor.execute(new ClientHandle(socket));

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

class ClientHandle implements Runnable{

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


class TimeServerHandlerExecutePool {

    private ExecutorService executor;

    public TimeServerHandlerExecutePool(int maxPoolSize, int queueSize) {
        executor = new ThreadPoolExecutor(Runtime.getRuntime()
                .availableProcessors(), maxPoolSize, 120L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<java.lang.Runnable>(queueSize));
    }

    public void execute(Runnable task) {
        executor.execute(task);
    }
}