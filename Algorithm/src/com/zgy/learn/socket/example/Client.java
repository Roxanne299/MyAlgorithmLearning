package com.zgy.learn.socket.example;

import com.zgy.learn.socket.AlarmAgent;

import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        Scanner inputScanner = new Scanner(System.in);
        AlarmAgent alarmAgent = new AlarmAgent(InetAddress.getLocalHost(), 5556);
        alarmAgent.init();
        while(true){
            System.out.print("选择你要的操作：\n0：发送消息\n1:断开连接\n");
            int x = inputScanner.nextInt();
            switch (x){
                case 0:
                    System.out.print("输入发送的消息：");
                    String s = inputScanner.next();
                    alarmAgent.sendAlarm(s);
                    break;
                case 1:
                    alarmAgent.onDisconnected();
                    break;
            }
        }
    }
}
