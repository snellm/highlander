package com.snell.michael.highlander;

import java.util.concurrent.atomic.AtomicBoolean;

class Holder<T> {
    private AtomicBoolean held;
    private T t;

    public Holder() {
        held = new AtomicBoolean(false);
    }

    public boolean set(T t) {
        boolean notAlreadyHeld = held.compareAndSet(false, true);
        if (notAlreadyHeld) {
            this.t = t;
            return true;
        } else {
            return t == this.t;
        }
    }

    public T get() {
        if (held.get()) {
            return t;
        } else {
            throw new RuntimeException("No value held");
        }
    }

    public boolean isHeld() {
        return held.get();
    }
}
