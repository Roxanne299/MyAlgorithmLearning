package com.zgy.model_design.state;

public class HasQuarterState extends SugarState{
    public HasQuarterState()
    {
        System.out.println("�ǹ�����Ӳ��Ͷ��");
    }
    @Override
    public SugarState handle() {
        System.out.println("ת���ǹ�������");
        return new SoldState();
    }
}
