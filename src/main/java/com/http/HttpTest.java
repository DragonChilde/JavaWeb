package com.http;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * @author Lee
 * @create 2019/12/16 16:01
 */
public class HttpTest {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);

        while (true)
        {
            try {
                System.out.println("正在监听8080端口............");
                Socket accept = serverSocket.accept();
                InputStream inputStream = accept.getInputStream();

                byte[] bytes = new byte[1024];

                inputStream.read(bytes);

                String s = new String(bytes);
                System.out.println(s);

            } catch (Exception e){
                e.printStackTrace();

            }
        }
    }
}
