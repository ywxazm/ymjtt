package com.ymjtt.common.util.io.test;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 伪异步IO, 本质上还是BIO, 只是在原有的BIO基础上增加了连接池
 * 当请求数据 > 连接数量时, 其它请求只能等待
 * @author  ywx
 * @date    2018/11/19 17:16
 */
public class IO_BIOServerAgain {

    private static final int port = 8080;

    private static ExecutorService executorService = Executors.newFixedThreadPool(20);

    ServerSocket serverSocket;

    @Test
    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(
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
                }));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
