package com.snell.michael.highlander;

import com.google.common.base.Optional;
import org.junit.Test;

import java.util.Collection;

import static com.snell.michael.highlander.GuavaOptionalHighlander.optionalOnly;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class GuavaOptionalHighlanderCollectionTest {
    private static final Collection<String> NONE = asList();
    private static final Collection<String> ONE = asList("ONE");
    private static final Collection<String> TWO = asList("ONE", "TWO");

    @Test
    public void collectionNone() {
        assertEquals(Optional.<String>absent(), optionalOnly(NONE));
    }
    
    @Test
    public void collectionOne() {
        assertEquals(Optional.of("ONE"), optionalOnly(ONE));
    }

    @Test(expected = RuntimeException.class)
    public void collectionTwo() {
        optionalOnly(TWO);
    }
}