package com.snell.michael.highlander;

import org.junit.Test;

import java.util.Optional;

import static com.snell.michael.highlander.Highlander.optionalOnly;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class HighlanderOptionalIterableTest {
    private static final Iterable<String> NONE = asList();
    private static final Iterable<String> ONE = asList("ONE");
    private static final Iterable<String> TWO = asList("ONE", "TWO");

    @Test
    public void iterableNone() {
        assertEquals(Optional.<String>empty(), optionalOnly(NONE));
    }

    @Test
    public void iterableOne() {
        assertEquals(Optional.of("ONE"), optionalOnly(ONE));
    }

    @Test(expected = RuntimeException.class)
    public void iterableTwo() {
        optionalOnly(TWO);
    }
}