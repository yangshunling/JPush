package com.example.anonymous.jpush;

/**
 * Created by Anonymous on 2017/12/14.
 */

public class MessageEvent {
    private String messge;

    public MessageEvent(String messge) {
        this.messge = messge;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }
}
