package com.zgy.model_design.state;

public class SugarM {
    public SugarState state = null;
    public SugarM(){}
    public SugarM(SugarState state)
    {
        this.state = state;
    }
    public SugarState getState() {
        return state;
    }
}
