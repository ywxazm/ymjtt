package com.ymjtt.common.util.io.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO客户端
 *
 * @auther ywx
 * @date 2018/11/19 14:56
 **/
public class IO_NIOClient {

    /*标识数字*/
    private static int flag = 0;
    /*缓冲区大小*/
    private static int BLOCK = 4096;
    /*接受数据缓冲区*/
    private static ByteBuffer sendbuffer = ByteBuffer.allocate(BLOCK);
    /*发送数据缓冲区*/
    private static ByteBuffer receivebuffer = ByteBuffer.allocate(BLOCK);
    /*服务器端地址*/
    private final static InetSocketAddress SERVER_ADDRESS = new InetSocketAddress("localhost", 8080);

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();             // 打开socket通道
        socketChannel.configureBlocking(false);                         // 设置为非阻塞方式
        Selector selector = Selector.open();                            // 打开选择器
        socketChannel.register(selector, SelectionKey.OP_CONNECT);      // 注册连接服务端socket动作
        socketChannel.connect(SERVER_ADDRESS);                          // 连接

        Set<SelectionKey> selectionKeys;
        Iterator<SelectionKey> iterator;
        SelectionKey selectionKey;
        SocketChannel client;
        String receiveText;
        String sendText;
        int count;
        while (true) {
            selector.select();                                          //选择一组键，其相应的通道已为 I/O 操作准备就绪
            selectionKeys = selector.selectedKeys();                    //返回此选择器的已选择键集
            iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                selectionKey = iterator.next();
                if (selectionKey.isConnectable()) {
                    System.out.println("client connect");
                    client = (SocketChannel) selectionKey.channel();
                    if (client.isConnectionPending()) {                 // 判断此通道上是否正在进行连接操作, 完成套接字通道的连接过程
                        client.finishConnect();
                        System.out.println("完成连接!");
                        sendbuffer.clear();
                        sendbuffer.put("Hello,Server".getBytes());
                        sendbuffer.flip();
                        client.write(sendbuffer);
                    }
                    client.register(selector, SelectionKey.OP_READ);

                } else if (selectionKey.isReadable()) {
                    client = (SocketChannel) selectionKey.channel();
                    receivebuffer.clear();                              //将缓冲区清空以备下次读取
                    count = client.read(receivebuffer);                 //读取服务器发送来的数据到缓冲区中
                    if (count > 0) {
                        receiveText = new String(receivebuffer.array(), 0, count);
                        System.out.println("客户端接受服务器端数据: " + receiveText);
                        client.register(selector, SelectionKey.OP_WRITE);
                    }

                } else if (selectionKey.isWritable()) {
                    sendbuffer.clear();
                    client = (SocketChannel) selectionKey.channel();
                    sendText = "message from client--" + (flag++);
                    sendbuffer.put(sendText.getBytes());
                    sendbuffer.flip();                                  //将缓冲区各标志复位,因为向里面put了数据标志被改变要想从中读取数据发向服务器,就要复位
                    client.write(sendbuffer);
                    System.out.println("客户端向服务器端发送数据： " + sendText);
                    client.register(selector, SelectionKey.OP_READ);
                }
            }
            selectionKeys.clear();
        }
    }
}
