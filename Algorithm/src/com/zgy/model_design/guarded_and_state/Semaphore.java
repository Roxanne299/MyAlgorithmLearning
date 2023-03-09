package com.zgy.model_design.guarded_and_state;

public class Semaphore {
    private int value;
    private State state;

    Semaphore(int value,State state) {
        this.value = value;
        this.state = state;
    }

    synchronized void acquire() {
        while(value == 0) {
            try {
                state.onHavingPeople();
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        value--;
    }

    synchronized void release() {
        value++;
        if(value == 1) {
            notifyAll();
        }
        state.onEmpty();
    }
}
