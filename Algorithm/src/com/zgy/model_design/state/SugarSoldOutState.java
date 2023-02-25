package com.zgy.model_design.state;

public class SugarSoldOutState extends SugarState{
    public SugarSoldOutState()
    {
        System.out.println("糖果已经售罄");
    }
    @Override
    public SugarState handle() {
        System.out.println("联系工作人员加入糖果");
        return new SugarSoldOutState();
    }
}
