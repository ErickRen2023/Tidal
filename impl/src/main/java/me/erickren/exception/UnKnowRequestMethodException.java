package me.erickren.exception;

public class UnKnowRequestMethodException extends RuntimeException{
    public UnKnowRequestMethodException(String msg){
        super(msg);
    }
}
