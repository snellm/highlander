package com.snell.michael.highlander;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class HighlanderTest {
    public static final Collection<String> COLLECTION = Arrays.asList("ONE");

    @Test
    public void onlyIterable() {
        assertEquals("ONE", Highlander.only(COLLECTION));
    }
}