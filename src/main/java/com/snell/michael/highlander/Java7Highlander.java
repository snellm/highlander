package com.snell.michael.highlander;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static com.snell.michael.highlander.Messages.*;

@SuppressWarnings("WeakerAccess")
public class Java7Highlander {
    private Java7Highlander() {
    }

    public static <T> T only(T[] tees) {
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
}
