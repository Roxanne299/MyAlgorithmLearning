package com.zgy.learn.modify;

import java.util.Objects;

class Test{
    //这是main方法，程序的入口
    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 100 ; i++) {
            System.out.println("main-----"+i);
            if(i == 6){
                //创建子线程：
                TestThread tt = new TestThread("子线程");
                tt.start();
                tt.join();//“半路杀出个程咬金”
            }
        }
    }
}
