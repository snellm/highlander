package com.snell.michael.highlander;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Supplier;

class AtomicHolder<T> {
    private final AtomicBoolean held = new AtomicBoolean(false);
    private final AtomicReference<T> reference = new AtomicReference<>();

    public void setOr(T t, Consumer<T> consumer) {
        boolean notAlreadyHeld = held.compareAndSet(false, true);
        if (notAlreadyHeld) {
            reference.set(t);
        } else {
            consumer.accept(t);
        }
    }

    public AtomicHolder<T> mergeOr(AtomicHolder<T> other, Supplier<AtomicHolder<T>> supplier) {
        if (!other.held.get()) {
            return this;
        } else if (!held.get()) {
            return other;
        } else {
            return supplier.get();
        }
    }

    public T getOr(Supplier<T> supplier) {
        if (held.get()) {
            return reference.get();
        } else {
            return supplier.get();
        }
    }
}
