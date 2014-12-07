package com.snell.michael.highlander;

import com.google.common.base.Optional;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collector;

import static com.snell.michael.highlander.Messages.EXPECTED_ONE_ELEMENT_BUT_FOUND_MULTIPLE;

class GuavaOptionalOnlyCollector<T> implements Collector<T, AtomicHolder<T>, Optional<T>> {
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
    public Function<AtomicHolder<T>, Optional<T>> finisher() {
        return new Function<AtomicHolder<T>, Optional<T>>() {
            @Override
            public Optional<T> apply(AtomicHolder<T> holder) {
                return holder.getOr(new Function<T, Optional<T>>() {
                    @Override
                    public Optional<T> apply(T t) {
                        return Optional.of(t);
                    }
                }, new Supplier<Optional<T>>() {
                    @Override
                    public Optional<T> get() {
                        return Optional.absent();
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
