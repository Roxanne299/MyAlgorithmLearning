package com.zgy.learn.modify;

import java.util.Objects;

class Test{
    //����main��������������
    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 100 ; i++) {
            System.out.println("main-----"+i);
            if(i == 6){
                //�������̣߳�
                TestThread tt = new TestThread("���߳�");
                tt.start();
                tt.join();//����·ɱ������ҧ��
            }
        }
    }
}
