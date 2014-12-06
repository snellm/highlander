package com.snell.michael.highlander;

import org.junit.Test;

import java.util.Collection;
import java.util.function.Predicate;

import static com.snell.michael.highlander.Highlander.only;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class HighlanderTest {
    // Arrays

    @Test
    public void arrayOne() {
        assertEquals("ONE", only("ONE"));
    }

    @Test(expected = RuntimeException.class)
    public void arrayTwo() {
        only("ONE", "TWO");
    }

    // Lists

    @Test
    public void listOne() {
        assertEquals("ONE", only(asList("ONE")));
    }

    @Test(expected = RuntimeException.class)
    public void listTwo() {
        only(asList("ONE", "TWO"));
    }

    // Collections

    @Test
    public void collectionOne() {
        assertEquals("ONE", only((Collection<String>) asList("ONE")));
    }

    @Test(expected = RuntimeException.class)
    public void collectionTwo() {
        only((Collection<String>) asList("ONE", "TWO"));
    }

    // Iterables

    @Test
    public void iterableOne() {
        assertEquals("ONE", only((Iterable<String>) asList("ONE")));
    }

    @Test(expected = RuntimeException.class)
    public void iterableTwo() {
        only((Iterable<String>) asList("ONE", "TWO"));
    }

    // Streams

    @Test
    public void streamOne() {
        assertEquals("ONE", only(asList("ONE").stream()));
    }

    @Test(expected = RuntimeException.class)
    public void streamTwo() {
        only(asList("ONE", "TWO").stream());
    }

    @Test
    public void streamFilter() {
        assertEquals("ONE", only(asList("ONE", "TWO").stream().filter(new Predicate<String>() {
            @Override
            public boolean test(String t) {
                return "ONE".equals(t);
            }
        })));
    }
}