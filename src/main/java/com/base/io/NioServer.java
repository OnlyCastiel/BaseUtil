package com.base.io;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * NIO方案，通过channel服务多个客户端
 * 核心类：jdk1.4之后   ServerSocketChannel
 */
public class NioServer {


    public static void main(String[] args) {
        NioServer nioServer = new NioServer();
        try {
            nioServer.nioSector();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void nio() throws IOException {
        //维护一个通道集合
        List<SocketChannel> channelList = new ArrayList<>();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9000));
        serverSocketChannel.configureBlocking(false);
        while (true){
            //单线程非阻塞，获取连接
            SocketChannel accept = serverSocketChannel.accept();
            if(accept != null){
                System.out.println("连接新建成功：");
                //对于新建连接，通过list进行管理
                accept.configureBlocking(false);
                channelList.add(accept);
            }else {
                //遍历所有通道
                Iterator<SocketChannel> iterator = channelList.iterator();
                while (iterator.hasNext()){
                    SocketChannel channel = iterator.next();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int read = channel.read(buffer);
                    if(read > 0){//接收到客户端数据
                        System.out.println(new String(buffer.array(),0,read));
                    }else if(read == -1){//读到结束,断开连接会标注-1
                        iterator.remove();
                    }
                }
            }
        }
    }


    public void nioSector() throws IOException {
        //维护一个通道集合
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9000));
        //配置连接为非阻塞链接
        serverSocketChannel.configureBlocking(false);
        //创建多路复用器
        Selector selector = Selector.open();
        //讲服务端channel通道注册到selector上，注册多路复用器
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            //阻塞等待响应事件，避免服务器CPU消耗
            selector.select();
            //持续获取响应事件，时间处理后，需要移除
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey next = iterator.next();
                if(next.isAcceptable()){
                    //得到服务端链接
                    ServerSocketChannel socketChannel =(ServerSocketChannel) next.channel();
                    //上文中配置了非阻塞，所以此处为非阻塞执行
                    SocketChannel accept = socketChannel.accept();
                    //配置链接为非阻塞，当进行通信时，为非阻塞通信
                    accept.configureBlocking(false);
                    //讲客户端channle注册到selector上，读事件
                    accept.register(selector,SelectionKey.OP_READ);
                    System.out.println("收到来自客户端的链接");
                }
                if(next.isReadable()){
                    //得到客户端连接
                    SocketChannel channel =(SocketChannel) next.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int read = channel.read(buffer);
                    if(read > 0){//接收到客户端数据
                        System.out.println(new String(buffer.array(),0,read));
                    }else if(read == -1){//读到结束,断开连接会标注-1
                        iterator.remove();
                    }
                }
                //事件处理后，需要删除，否则会重复处理
                iterator.remove();
            }
        }
    }
}
