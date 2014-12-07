package com.snell.michael.highlander;

import com.google.common.base.Optional;
import org.junit.Test;

import static com.snell.michael.highlander.GuavaOptionalHighlander.optionalOnly;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class GuavaOptionalHighlanderIterableTest {
    private static final Iterable<String> NONE = asList();
    private static final Iterable<String> ONE = asList("ONE");
    private static final Iterable<String> TWO = asList("ONE", "TWO");

    @Test
    public void iterableNone() {
        assertEquals(Optional.<String>absent(), optionalOnly(NONE));
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