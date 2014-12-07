package com.snell.michael.highlander;

import org.junit.Test;

import java.util.List;

import static com.snell.michael.highlander.Highlander.only;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class HighlanderListTest {
    private static final List<String> NONE = asList();
    private static final List<String> ONE = asList("ONE");
    private static final List<String> TWO = asList("ONE", "TWO");

    @Test(expected = RuntimeException.class)
    public void listNone() {
        only(NONE);
    }

    @Test
    public void listOne() {
        assertEquals("ONE", only(ONE));
    }

    @Test(expected = RuntimeException.class)
    public void listTwo() {
        only(TWO);
    }
}