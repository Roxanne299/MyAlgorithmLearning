package com.zgy.model_design.state;

public class SoldState extends SugarState{

    public SoldState()
    {
        System.out.println("���ڳ����ǹ�");
    }
    @Override
    public SugarState handle() {
        System.out.println("�����ǹ�");
        return new SugarSoldOutState();
    }
}
