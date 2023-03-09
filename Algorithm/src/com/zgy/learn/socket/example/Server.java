package com.zgy.learn.socket.example;

import com.zgy.learn.socket.SocketClient;
import com.zgy.learn.socket.SocketServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

class Server {
    private static Scanner inputScanner = new Scanner(System.in);

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        startServer();
    }

    private static void startServer() {
        System.out.println("Start a server.");
        SocketServer server = new SocketServer(5556, new EchoHandler());
        System.out.println("Please type anything and press enter to close the server...");
        inputScanner.next();
        server.close();
    }

}