package com.snell.michael.highlander;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static com.snell.michael.highlander.Messages.EXPECTED_ONE_ELEMENT_BUT_FOUND_;
import static com.snell.michael.highlander.Messages.EXPECTED_ONE_ELEMENT_BUT_FOUND_MULTIPLE;

@SuppressWarnings("WeakerAccess")
public class OptionalHighlander {
    private OptionalHighlander() {}

    @SafeVarargs
    public static <T> Optional<T> optionalOnly(T... tees) {
        if (tees.length == 0) {
            return Optional.empty();
        } else if (tees.length == 1) {
            return Optional.of(tees[0]);
        } else {
            throw new RuntimeException(EXPECTED_ONE_ELEMENT_BUT_FOUND_ + tees.length);
        }
    }

    public static <T> Optional<T> optionalOnly(List<T> list) {
        if (list.isEmpty()) {
            return Optional.empty();
        } else if (list.size() == 1) {
            return Optional.of(list.get(0));
        } else {
            throw new RuntimeException(EXPECTED_ONE_ELEMENT_BUT_FOUND_ + list.size());
        }
    }

    public static <T> Optional<T> optionalOnly(Collection<T> collection) {
        if (collection.isEmpty()) {
            return Optional.empty();
        } else if (collection.size() == 1) {
            return Optional.of(collection.iterator().next());
        } else {
            throw new RuntimeException(EXPECTED_ONE_ELEMENT_BUT_FOUND_ + collection.size());
        }
    }

    public static <T> Optional<T> optionalOnly(Collection<T> collection, Predicate<? super T> predicate) {
        return optionalOnly(collection.stream().filter(predicate));
    }

    public static <T> Optional<T> optionalOnly(Iterable<T> iterable) {
        return optionalOnly(iterable.iterator());
    }

    public static <T> Optional<T> optionalOnly(Iterator<T> iterator) {
        if (iterator.hasNext()) {
            T t = iterator.next();
            if (iterator.hasNext()) {
                throw new RuntimeException(EXPECTED_ONE_ELEMENT_BUT_FOUND_MULTIPLE);
            } else {
                return Optional.of(t);
            }
        } else {
            return Optional.empty();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> Optional<T> optionalOnly(Stream<T> stream) {
        return (Optional<T>) stream.collect(optionalOnly());
    }

    public static <T> Optional<T> only(Stream<T> stream, Predicate<? super T> predicate) {
        return optionalOnly(stream.filter(predicate));
    }

    public static <T> Collector<T, ?, Optional<T>> optionalOnly() {
        return new OptionalOnlyCollector<>();
    }
}