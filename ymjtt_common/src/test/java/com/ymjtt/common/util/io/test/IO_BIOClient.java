package com.ymjtt.common.util.io.test;

import org.junit.Test;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Bio客户端, 模拟N个客户端, 发请N个请求, 服务器端将创建N个线程来进行处理
 * @auther ywx
 * @date 2018/11/19 14:57
 **/
public class IO_BIOClient {

    private static final String ip = "127.0.0.1";

    private static final int port = 8080;

    @Test
    public void connect() {
        try {
            for (int i = 1; i <= 10000000000L; i++) {
                Socket socket = new Socket(ip, port);
                OutputStream os = socket.getOutputStream();
                os.write("BIO Client request".getBytes(Charset.defaultCharset()));
                os.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
