package com.zgy.model_design.state;

public class SugarSoldOutState extends SugarState{
    public SugarSoldOutState()
    {
        System.out.println("�ǹ��Ѿ�����");
    }
    @Override
    public SugarState handle() {
        System.out.println("��ϵ������Ա�����ǹ�");
        return new SugarSoldOutState();
    }
}
