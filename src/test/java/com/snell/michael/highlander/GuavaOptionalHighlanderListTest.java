package com.snell.michael.highlander;

import com.google.common.base.Optional;
import org.junit.Test;

import java.util.List;

import static com.snell.michael.highlander.GuavaOptionalHighlander.optionalOnly;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class GuavaOptionalHighlanderListTest {
    private static final List<String> NONE = asList();
    private static final List<String> ONE = asList("ONE");
    private static final List<String> TWO = asList("ONE", "TWO");

    @Test
    public void listNone() {
        assertEquals(Optional.<String>absent(), optionalOnly(NONE));
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