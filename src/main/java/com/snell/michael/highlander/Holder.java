package com.snell.michael.highlander;

class Holder<T> {
    private boolean held;
    private T t;

    public Holder() {
        held = false;
    }

    public boolean set(T t) {
        if (!held) {
            held = true;
            this.t = t;
            return true;
        } else {
            return false;
        }
    }

    public T get() {
        if (held) {
            return t;
        } else {
            throw new RuntimeException("No value held");
        }
    }

    public boolean isHeld() {
        return held;
    }
}
