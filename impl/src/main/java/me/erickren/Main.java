package me.erickren;

import me.erickren.request.HttpRequest;
import me.erickren.request.HttpRequestImpl;
import me.erickren.response.HttpResponse;

import java.util.HashMap;


public class Main {
    public static void main(String[] args) throws Exception {

        HttpRequest httpRequest = new HttpRequestImpl("http://www.juejin.cn/index.html");
//        HttpRequest httpRequest = new HttpRequestImpl("http://csdn.com/");
        HttpResponse httpResponse = httpRequest.get();
        System.out.println(httpResponse);
    }

}
