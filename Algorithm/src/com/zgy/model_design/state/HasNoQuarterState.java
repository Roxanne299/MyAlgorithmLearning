package com.zgy.model_design.state;

public class HasNoQuarterState extends SugarState{
    public HasNoQuarterState(){
        System.out.println("�ǹ�����ʼ��������û��Ӳ��Ͷ��");
    }
    @Override
    public SugarState handle() {
        System.out.print("Ͷ��Ӳ��");
        return new HasQuarterState();
    }
}
