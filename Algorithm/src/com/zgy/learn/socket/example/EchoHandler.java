package com.zgy.learn.socket.example;

import com.zgy.learn.socket.Connection;

import com.zgy.learn.socket.MessageHandler;

class EchoHandler implements MessageHandler {
    @Override
    public void onReceive(Connection connection, String message) {
        System.out.println("Got a message from a client:");
        System.out.println(message);
    }
}