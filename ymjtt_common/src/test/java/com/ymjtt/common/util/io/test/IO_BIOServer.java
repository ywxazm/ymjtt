package com.ymjtt.common.util.io.test;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * 阻塞IO, Bio服务器端, 为每一个请求创建一条线程进行处理
 * 请求数量过多时, 会产生很多的线程, 极大的浪费资源
 * @author  ywx
 * @date    2018/11/19 15:17
 */
public class IO_BIOServer {

    private static final int port = 8080;

    ServerSocket serverSocket;

    @Test
    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        InputStream is = socket.getInputStream();
                        byte[] bt = new byte[1024 * 8];
                        while(-1 != is.read(bt)) {
                            System.out.println("线程信息: " + Thread.currentThread().getName() + ", " + new String(bt, Charset.defaultCharset()));
                        }
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
