package me.erickren;

import me.erickren.request.RequestUrl;
import me.erickren.request.RequestUrlImpl;

public class Main {
    public static void main(String[] args) throws Exception {
        String host = "www.baidu.com";
        int port = 80;
        String path = "/";
        RequestUrl requestUrl = new RequestUrlImpl("https://juejin.cn/s/java%20url%20%E5%8F%82%E6%95%B0%E8%A7%A3%E6%9E%90?argument=1&age=2&age=3");

        System.out.println(requestUrl.build());


//        // 创建socket连接
//        Socket socket = new Socket(host, port);
//
//        // 构造HTTP请求
//        String request = "GET " + path + " HTTP/1.1\r\n" +
//                "Host: " + host + "\r\n" +
//                "Connection: close\r\n\r\n";
//
//        // 发送HTTP请求
//        OutputStream outputStream = socket.getOutputStream();
//        outputStream.write(request.getBytes());
//
//        // 接收HTTP响应
//        InputStream inputStream = socket.getInputStream();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//        String line;
//        while ((line = reader.readLine()) != null) {
//            System.out.println(line);
//        }
//
//        // 关闭连接
//        socket.close();
    }
}
