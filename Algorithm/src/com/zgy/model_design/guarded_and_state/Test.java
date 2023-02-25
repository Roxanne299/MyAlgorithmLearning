package com.zgy.model_design.guarded_and_state;

import java.util.Random;

public class Test {
    static void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        int MAX = 5;
        Room room = new Room();
        State state = new EmptyState(room);
        Semaphore semaphore = new Semaphore(MAX,state);
        Random random = new Random();

        for(int n = 0; n < 20; n++) {
            pause(random.nextInt(5) * 10);
            int g = n;
            new Thread(() -> {
                while(true) {
                    semaphore.acquire();
                    System.out.println(g + "ßMˆö");
                    pause(random.nextInt(10) * 100);

                    semaphore.release();
                    System.out.println(g + "ëxˆö");
                }
            }).start();
        }
    }
}
