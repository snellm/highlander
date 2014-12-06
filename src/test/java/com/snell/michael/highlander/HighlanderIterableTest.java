package com.snell.michael.highlander;

import org.junit.Test;

import static com.snell.michael.highlander.Highlander.only;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class HighlanderIterableTest {
    private static final Iterable<String> ONE = asList("ONE");
    private static final Iterable<String> ONE_TWO = asList("ONE", "TWO");

    @Test
    public void iterableOne() {
        assertEquals("ONE", only(ONE));
    }

    @Test(expected = RuntimeException.class)
    public void iterableTwo() {
        only(ONE_TWO);
    }
}