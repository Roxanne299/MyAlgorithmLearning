package com.zgy.learn.socket;

public interface MessageHandler {
    public void onReceive(Connection connection, String message);
}