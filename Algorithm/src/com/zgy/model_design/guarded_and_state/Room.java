package com.zgy.model_design.guarded_and_state;

public class Room {
    private State state;
    public Room(){

    }
    public void changeState(State state){
        this.state = state;
    }
    public State getState()
    {
        return this.state;
    }
}
