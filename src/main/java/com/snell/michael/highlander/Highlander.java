package com.snell.michael.highlander;

import java.util.*;
import java.util.function.*;
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

    public static <T> Collector<T, ?, T> only() {
        return new Collector<T, AtomicHolder<T>, T> () {
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
                        return holder.getOr(new Supplier<T>() {
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
        };
    }
}
