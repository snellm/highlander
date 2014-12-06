package com.snell.michael.highlander;

import org.junit.Test;

import static com.snell.michael.highlander.Highlander.only;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class HighlanderListTest {
    @Test
    public void listOne() {
        assertEquals("ONE", only(asList("ONE")));
    }

    @Test(expected = RuntimeException.class)
    public void listTwo() {
        only(asList("ONE", "TWO"));
    }
}