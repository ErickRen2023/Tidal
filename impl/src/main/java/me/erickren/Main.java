package me.erickren;

import me.erickren.request.HttpRequest;
import me.erickren.request.HttpRequestImpl;
import me.erickren.response.HttpResponse;


public class Main {
    public static void main(String[] args) throws Exception {

        HttpRequest httpRequest = new HttpRequestImpl("http://csdn.com/");
        HttpResponse httpResponse = httpRequest.get();
        System.out.println(httpResponse.getBody());
//        String host = "juejin.cn";
//        int port = 443;
//        String request = "GET / HTTP/1.1\r\n" +
//                "Host: " + host + "\r\n" +
//                "Connection: close\r\n\r\n";
//
//        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
//        SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
//        socket.startHandshake();
//
//        OutputStream os = socket.getOutputStream();
//        os.write(request.getBytes());
//        os.flush();
//
//        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        String inputLine;
//        while ((inputLine = in.readLine()) != null) {
//            System.out.println(inputLine);
//        }
//        in.close();
//        socket.close();
    }

}
