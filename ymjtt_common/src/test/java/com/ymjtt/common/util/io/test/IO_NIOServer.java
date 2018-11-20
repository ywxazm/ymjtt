package com.ymjtt.common.util.io.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO服务器端
 * @auther ywx
 * @date 2018/11/19 14:56
 **/
public class IO_NIOServer {

    /*标识数字*/
    private  int flag = 0;
    /*缓冲区大小*/
    private  int BLOCK = 4096;
    /*接受数据缓冲区*/
    private  ByteBuffer sendbuffer = ByteBuffer.allocate(BLOCK);
    /*发送数据缓冲区*/
    private  ByteBuffer receivebuffer = ByteBuffer.allocate(BLOCK);
    private  Selector selector;

    public IO_NIOServer(int port) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();   // 打开服务器套接字通道
        serverSocketChannel.configureBlocking(false);                           // 服务器配置为非阻塞
        ServerSocket serverSocket = serverSocketChannel.socket();               // 检索与此通道关联的服务器套接字
        serverSocket.bind(new InetSocketAddress(port));                         // 进行服务的绑定
        selector = Selector.open();                                             // 通过open()方法找到Selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);         // 注册到selector，等待连接
        System.out.println("Server Start--->:  ");
    }

    private void listen() throws IOException {
        while (true) {
            selector.select();                                              // 选择一组键，并且相应的通道已经打开
            Set<SelectionKey> selectionKeys = selector.selectedKeys();      // 返回此选择器的已选择键集
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                handleKey(selectionKey);
            }
        }
    }

    private void handleKey(SelectionKey selectionKey) throws IOException {
        ServerSocketChannel server;
        SocketChannel client;
        String receiveText;
        String sendText;
        int count;

        if (selectionKey.isAcceptable()) {                                  // 测试此键的通道是否已准备好接受新的套接字连接
            server = (ServerSocketChannel) selectionKey.channel();          // 返回为之创建此键的通道。
            client = server.accept();                                       // 接受到此通道套接字的连接
            client.configureBlocking(false);                                // 配置为非阻塞
            client.register(selector, SelectionKey.OP_READ);                // 注册到selector，等待连接

        } else if (selectionKey.isReadable()) {
            client = (SocketChannel) selectionKey.channel();                // 返回为之创建此键的通道
            receivebuffer.clear();                                          //将缓冲区清空以备下次读取
            count = client.read(receivebuffer);                             //读取服务器发送来的数据到缓冲区中
            if (count > 0) {
                receiveText = new String( receivebuffer.array(),0,count);
                System.out.println("服务器端接受客户端数据--:"+receiveText);
                client.register(selector, SelectionKey.OP_WRITE);
            }

        } else if (selectionKey.isWritable()) {
            sendbuffer.clear();                                             //将缓冲区清空以备下次写入
            client = (SocketChannel) selectionKey.channel();                // 返回为之创建此键的通道
            sendText="message from server--" + flag++;
            sendbuffer.put(sendText.getBytes());                            //向缓冲区中输入数据
            sendbuffer.flip();                                              //将缓冲区各标志复位,因为向里面put了数据标志被改变要想从中读取数据发向服务器,就要复位
            client.write(sendbuffer);                                       //输出到通道
            System.out.println("服务器端向客户端发送数据--："+sendText);
            client.register(selector, SelectionKey.OP_READ);
        }
    }

    public static void main(String[] args) throws IOException {
        int port = 8080;
        IO_NIOServer server = new IO_NIOServer(port);
        server.listen();
    }
}