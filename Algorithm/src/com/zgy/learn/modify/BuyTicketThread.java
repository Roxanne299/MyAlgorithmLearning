package com.zgy.learn.modify;

public class BuyTicketThread implements Runnable {
    int ticketNum = 10;
    @Override
    public void run() {
        for (int i = 1; i <= 100 ; i++) {
            if(ticketNum > 0){
                System.out.println("����"+Thread.currentThread().getName()+"���˱������������ĵ�" + ticketNum-- + "�ų�Ʊ");
            }
        }
    }
}
