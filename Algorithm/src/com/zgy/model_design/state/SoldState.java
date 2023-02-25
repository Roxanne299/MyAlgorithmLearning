package com.zgy.model_design.state;

public class SoldState extends SugarState{

    public SoldState()
    {
        System.out.println("正在出售糖果");
    }
    @Override
    public SugarState handle() {
        System.out.println("发放糖果");
        return new SugarSoldOutState();
    }
}
