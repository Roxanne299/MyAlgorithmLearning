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
            System.out.print("ѡ����Ҫ�Ĳ�����\n0��������Ϣ\n1:�Ͽ�����\n");
            int x = inputScanner.nextInt();
            switch (x){
                case 0:
                    System.out.print("���뷢�͵���Ϣ��");
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
