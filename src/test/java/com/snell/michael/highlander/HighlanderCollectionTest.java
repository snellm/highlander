package com.snell.michael.highlander;

import org.junit.Test;

import java.util.Collection;

import static com.snell.michael.highlander.Highlander.only;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class HighlanderCollectionTest {
    private static final Collection<String> NONE = asList();
    private static final Collection<String> ONE = asList("ONE");
    private static final Collection<String> TWO = asList("ONE", "TWO");

    @Test(expected = RuntimeException.class)
    public void collectionNone() {
        only(NONE);
    }
    
    @Test
    public void collectionOne() {
        assertEquals("ONE", only(ONE));
    }

    @Test(expected = RuntimeException.class)
    public void collectionTwo() {
        only(TWO);
    }
}