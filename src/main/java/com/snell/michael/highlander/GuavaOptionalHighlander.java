package com.snell.michael.highlander;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static com.google.common.collect.Collections2.filter;
import static com.snell.michael.highlander.Messages.EXPECTED_ONE_ELEMENT_BUT_FOUND_;
import static com.snell.michael.highlander.Messages.EXPECTED_ONE_ELEMENT_BUT_FOUND_MULTIPLE;

@SuppressWarnings("WeakerAccess")
public class GuavaOptionalHighlander {
    private GuavaOptionalHighlander() {}

    @SafeVarargs
    public static <T> Optional<T> optionalOnly(T... tees) {
        if (tees.length == 0) {
            return Optional.absent();
        } else if (tees.length == 1) {
            return Optional.of(tees[0]);
        } else {
            throw new RuntimeException(EXPECTED_ONE_ELEMENT_BUT_FOUND_ + tees.length);
        }
    }

    public static <T> Optional<T> optionalOnly(List<T> list) {
        if (list.isEmpty()) {
            return Optional.absent();
        } else if (list.size() == 1) {
            return Optional.of(list.get(0));
        } else {
            throw new RuntimeException(EXPECTED_ONE_ELEMENT_BUT_FOUND_ + list.size());
        }
    }

    public static <T> Optional<T> optionalOnly(Collection<T> collection) {
        if (collection.isEmpty()) {
            return Optional.absent();
        } else if (collection.size() == 1) {
            return Optional.of(collection.iterator().next());
        } else {
            throw new RuntimeException(EXPECTED_ONE_ELEMENT_BUT_FOUND_ + collection.size());
        }
    }

    public static <T> Optional<T> optionalOnly(Collection<T> collection, Predicate<? super T> predicate) {
        return optionalOnly(filter(collection, predicate));
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
            return Optional.absent();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> Optional<T> optionalOnly(Stream<T> stream) {
        return (Optional<T>) stream.collect(optionalOnly());
    }

    public static <T> Collector<T, ?, Optional<T>> optionalOnly() {
        return new GuavaOptionalOnlyCollector<>();
    }
}