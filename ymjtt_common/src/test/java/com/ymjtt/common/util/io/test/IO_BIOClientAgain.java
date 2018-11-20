package com.ymjtt.common.util.io.test;

import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * 伪异步IO, 本质上还是BIO
 * @author  ywx
 * @date    2018/11/19 17:20
 */
public class IO_BIOClientAgain {

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
