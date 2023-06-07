package me.erickren.request;

import me.erickren.header.HeaderImpl;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class RequestHeaderImpl extends HeaderImpl implements RequestHeader{

    @Override
    public void setAcceptLanguage(String value) {
        throw new NotImplementedException();
    }

    @Override
    public void setHost(String host) {
        this.setHeader("Host", host);
    }

}
