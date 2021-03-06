package com.snell.michael.highlander;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collector;

import static com.snell.michael.highlander.Messages.EXPECTED_ONE_ELEMENT_BUT_FOUND_MULTIPLE;
import static com.snell.michael.highlander.Messages.EXPECTED_ONE_ELEMENT_BUT_FOUND_NONE;

class OnlyCollector<T> implements Collector<T, AtomicHolder<T>, T> {
    @Override
    public Supplier<AtomicHolder<T>> supplier () {
        return new Supplier<AtomicHolder<T>>() {
            @Override
            public AtomicHolder<T> get() {
                return new AtomicHolder<>();
            }
        };
    }

    @Override
    public BinaryOperator<AtomicHolder<T>> combiner() {
        return new BinaryOperator<AtomicHolder<T>>() {
            @Override
            public AtomicHolder<T> apply(AtomicHolder<T> holder1, AtomicHolder<T> holder2) {
                return holder1.mergeOr(holder2, new Supplier<AtomicHolder<T>>() {
                    @Override
                    public AtomicHolder<T> get() {
                        throw new RuntimeException(EXPECTED_ONE_ELEMENT_BUT_FOUND_MULTIPLE);
                    }
                });
            }
        };
    }

    @Override
    public Function<AtomicHolder<T>, T> finisher() {
        return new Function<AtomicHolder<T>, T>() {
            @Override
            public T apply(AtomicHolder<T> holder) {
                return holder.getOr(new Function<T, T>() {
                    @Override
                    public T apply(T t) {
                        return t;
                    }
                }, new Supplier<T>() {
                    @Override
                    public T get() {
                        throw new RuntimeException(EXPECTED_ONE_ELEMENT_BUT_FOUND_NONE);
                    }
                });
            }
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Characteristics.CONCURRENT, Characteristics.UNORDERED);
    }

    @Override
    public BiConsumer<AtomicHolder<T>, T> accumulator() {
        return new BiConsumer<AtomicHolder<T>, T>() {
            @Override
            public void accept(AtomicHolder<T> holder, T t) {
                holder.setOr(t, new Consumer<T>() {
                    @Override
                    public void accept(T t) {
                        throw new RuntimeException(EXPECTED_ONE_ELEMENT_BUT_FOUND_MULTIPLE);
                    }
                });
            }
        };
    }
}
