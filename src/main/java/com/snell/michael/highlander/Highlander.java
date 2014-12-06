package com.snell.michael.highlander;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

@SuppressWarnings("WeakerAccess")
public class Highlander {
    private static final String NO_ELEMENTS_FOUND = "Expected onle element but found no elements ";
    private static final String EXPECTED_ONE_ELEMENT_BUT_FOUND = "Expected one element but found ";

    private Highlander() {}

    @SafeVarargs
    public static <T> T only(T... tees) {
        if (tees.length == 0) {
            throw new RuntimeException(NO_ELEMENTS_FOUND);
        } else if (tees.length == 1) {
            return tees[0];
        } else {
            throw new RuntimeException(EXPECTED_ONE_ELEMENT_BUT_FOUND + tees.length);
        }
    }

    public static <T> T only(List<T> list) {
        if (list.isEmpty()) {
            throw new RuntimeException(NO_ELEMENTS_FOUND);
        } else if (list.size() == 1) {
            return list.get(0);
        } else {
            throw new RuntimeException(EXPECTED_ONE_ELEMENT_BUT_FOUND + list.size());
        }
    }

    public static <T> T only(Collection<T> collection) {
        if (collection.isEmpty()) {
            throw new RuntimeException(NO_ELEMENTS_FOUND);
        } else if (collection.size() == 1) {
            return collection.iterator().next();
        } else {
            throw new RuntimeException(EXPECTED_ONE_ELEMENT_BUT_FOUND + collection.size());
        }
    }

    public static <T> T only(Iterable<T> iterable) {
        return only(iterable.iterator());
    }

    public static <T> T only(Stream<T> stream) {
        return only(stream.iterator());
    }

    private static <T> T only(Iterator<T> iterator) {
        if (iterator.hasNext()) {
            T t = iterator.next();
            if (iterator.hasNext()) {
                throw new RuntimeException(EXPECTED_ONE_ELEMENT_BUT_FOUND + "two or more");
            } else {
                return t;
            }
        } else {
            throw new RuntimeException(NO_ELEMENTS_FOUND);
        }
    }

    // TODO Collector?
}
