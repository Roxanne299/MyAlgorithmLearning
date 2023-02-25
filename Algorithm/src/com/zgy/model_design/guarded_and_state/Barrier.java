package com.zgy.model_design.guarded_and_state;

public class Barrier {
    private int value;
    private int count;

    Barrier(int value) {
        this.value = value;
    }

    synchronized void await() {
        count++;
        while(count < value) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        notifyAll();
    }
}
