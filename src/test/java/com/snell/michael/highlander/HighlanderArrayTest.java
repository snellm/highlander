package com.snell.michael.highlander;

import org.junit.Test;

import static com.snell.michael.highlander.Highlander.only;
import static org.junit.Assert.assertEquals;

public class HighlanderArrayTest {
    @Test
    public void arrayOne() {
        assertEquals("ONE", only("ONE"));
    }

    @Test(expected = RuntimeException.class)
    public void arrayTwo() {
        only("ONE", "TWO");
    }
}