package com.snell.michael.highlander;

import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;

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

    @Test
    public void listFilter() {
        only(TWO, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.equals("ONE");
            }
        });
    }
}