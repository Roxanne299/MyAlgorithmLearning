package com.zgy.learn.modify;

public class TestThread extends Thread {
    public TestThread(String name){
        super(name);
    }
    @Override
    public void run() {
        for (int i = 1; i <= 10 ; i++) {
            System.out.println(this.getName()+"----"+i);
        }
    }
}
