package com.zgy.model_design.state;

public class HasNoQuarterState extends SugarState{
    public HasNoQuarterState(){
        System.out.println("糖果机初始化。。。没有硬币投入");
    }
    @Override
    public SugarState handle() {
        System.out.print("投入硬币");
        return new HasQuarterState();
    }
}
