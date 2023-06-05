package me.erickren;

import me.erickren.request.HttpRequest;
import me.erickren.request.HttpRequestImpl;


public class Main {
    public static void main(String[] args) throws Exception {

        HttpRequest httpRequest = new HttpRequestImpl("https://zhuanlan.zhihu.com/p/113444636");
        httpRequest.get();

    }
}
