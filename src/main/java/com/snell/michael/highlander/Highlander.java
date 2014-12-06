package com.snell.michael.highlander;

import java.util.Iterator;

public class Highlander {
    // TODO Optimisation for lists etc
    public static <T> T only(Iterable<T> iterable) {
        Iterator<T> iterator = iterable.iterator();
        if (iterator.hasNext()) {
            T t = iterator.next();
            if (iterator.hasNext()) {
                throw new RuntimeException("Expected one element but found two or more");
            } else {
                return t;
            }
        } else {
            throw new RuntimeException("No elements found");
        }
    }

    // TODO Support arrays, streams etc
}
