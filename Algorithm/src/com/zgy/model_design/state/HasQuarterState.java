package com.zgy.model_design.state;

public class HasQuarterState extends SugarState{
    public HasQuarterState()
    {
        System.out.println("糖果机有硬币投入");
    }
    @Override
    public SugarState handle() {
        System.out.println("转动糖果机曲柄");
        return new SoldState();
    }
}
