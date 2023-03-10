package com.zgy.model_design.guard_suspension;

import java.util.concurrent.Callable;

public abstract class GuardedAction<V> implements Callable<V> {
    protected final Predicate guard;
    public GuardedAction(Predicate guard) {
        this.guard = guard;
    }
}