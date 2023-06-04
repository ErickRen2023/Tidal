package me.erickren;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws Exception {
        String host = "www.baidu.com";
        int port = 80;
        String path = "/";

        // 创建socket连接
        Socket socket = new Socket(host, port);

        // 构造HTTP请求
        String request = "GET " + path + " HTTP/1.1\r\n" +
                "Host: " + host + "\r\n" +
                "Connection: close\r\n\r\n";

        // 发送HTTP请求
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(request.getBytes());

        // 接收HTTP响应
        InputStream inputStream = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        // 关闭连接
        socket.close();
    }
}
