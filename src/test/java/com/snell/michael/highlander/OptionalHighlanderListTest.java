package com.snell.michael.highlander;

import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static com.snell.michael.highlander.OptionalHighlander.optionalOnly;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class OptionalHighlanderListTest {
    private static final List<String> NONE = asList();
    private static final List<String> ONE = asList("ONE");
    private static final List<String> TWO = asList("ONE", "TWO");

    @Test
    public void listNone() {
        assertEquals(Optional.<String>empty(), optionalOnly(NONE));
    }

    @Test
    public void listOne() {
        assertEquals(Optional.of("ONE"), optionalOnly(ONE));
    }

    @Test(expected = RuntimeException.class)
    public void listTwo() {
        optionalOnly(TWO);
    }
}