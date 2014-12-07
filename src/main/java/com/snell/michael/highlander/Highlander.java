package com.snell.michael.highlander;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

@SuppressWarnings("WeakerAccess")
public class Highlander {
    private static final String EXPECTED_ONE_ELEMENT_BUT_FOUND_NONE = "Expected one element but found none";
    private static final String EXPECTED_ONE_ELEMENT_BUT_FOUND_ = "Expected one element but found ";
    private static final String EXPECTED_ONE_ELEMENT_BUT_FOUND_MULTIPLE = EXPECTED_ONE_ELEMENT_BUT_FOUND_ + "multiple";

    private Highlander() {}

    @SafeVarargs
    public static <T> T only(T... tees) {
        if (tees.length == 0) {
            throw new RuntimeException(EXPECTED_ONE_ELEMENT_BUT_FOUND_NONE);
        } else if (tees.length == 1) {
            return tees[0];
        } else {
            throw new RuntimeException(EXPECTED_ONE_ELEMENT_BUT_FOUND_ + tees.length);
        }
    }

    public static <T> T only(List<T> list) {
        if (list.isEmpty()) {
            throw new RuntimeException(EXPECTED_ONE_ELEMENT_BUT_FOUND_NONE);
        } else if (list.size() == 1) {
            return list.get(0);
        } else {
            throw new RuntimeException(EXPECTED_ONE_ELEMENT_BUT_FOUND_ + list.size());
        }
    }

    public static <T> T only(Collection<T> collection) {
        if (collection.isEmpty()) {
            throw new RuntimeException(EXPECTED_ONE_ELEMENT_BUT_FOUND_NONE);
        } else if (collection.size() == 1) {
            return collection.iterator().next();
        } else {
            throw new RuntimeException(EXPECTED_ONE_ELEMENT_BUT_FOUND_ + collection.size());
        }
    }

    public static <T> T only(Iterable<T> iterable) {
        return only(iterable.iterator());
    }

    public static <T> T only(Iterator<T> iterator) {
        if (iterator.hasNext()) {
            T t = iterator.next();
            if (iterator.hasNext()) {
                throw new RuntimeException(EXPECTED_ONE_ELEMENT_BUT_FOUND_MULTIPLE);
            } else {
                return t;
            }
        } else {
            throw new RuntimeException(EXPECTED_ONE_ELEMENT_BUT_FOUND_NONE);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T only(Stream<T> stream) {
        return (T) stream.collect(only());
    }

    public static <T> Collector<T, Holder<T>, T> only() {
        return new Collector<T, Holder<T>, T> () {
            @Override
            public Supplier<Holder<T>> supplier () {
                return new Supplier<Holder<T>>() {
                    @Override
                    public Holder<T> get() {
                        return new Holder<>();
                    }
                };
            }

            @Override
            public BinaryOperator<Holder<T>> combiner() {
                return new BinaryOperator<Holder<T>>() {
                    @Override
                    public Holder<T> apply(Holder<T> holder1, Holder<T> holder2) {
                        if (!holder2.isHeld()) {
                            return holder1;
                        } else if (!holder1.isHeld()) {
                            return holder2;
                        } else {
                            throw new RuntimeException(EXPECTED_ONE_ELEMENT_BUT_FOUND_MULTIPLE);
                        }
                    }
                };
            }

            @Override
            public Function<Holder<T>, T> finisher() {
                return new Function<Holder<T>, T>() {
                    @Override
                    public T apply(Holder<T> holder) {
                        if (holder.isHeld()) {
                            return holder.get();
                        } else {
                            throw new RuntimeException(EXPECTED_ONE_ELEMENT_BUT_FOUND_NONE);
                        }
                    }
                };
            }

            @Override
            public Set<Characteristics> characteristics() {
                return EnumSet.of(Characteristics.CONCURRENT, Characteristics.UNORDERED);
            }

            @Override
            public BiConsumer<Holder<T>, T> accumulator() {
                return new BiConsumer<Holder<T>, T>() {
                    @Override
                    public void accept(Holder<T> holder, T t) {
                        if (!holder.set(t)) {
                            throw new RuntimeException(EXPECTED_ONE_ELEMENT_BUT_FOUND_MULTIPLE);
                        }
                    }
                };
            }
        };
    }
}
