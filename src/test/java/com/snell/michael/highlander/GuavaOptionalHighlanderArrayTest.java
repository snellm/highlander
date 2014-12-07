package com.snell.michael.highlander;

import com.google.common.base.Optional;
import org.junit.Test;

import static com.snell.michael.highlander.GuavaOptionalHighlander.optionalOnly;
import static org.junit.Assert.assertEquals;

public class GuavaOptionalHighlanderArrayTest {
    @Test
    public void arrayNone() {
        assertEquals(Optional.<String>absent(), optionalOnly(new String[0]));
    }

    @Test
    public void arrayOne() {
        assertEquals(Optional.of("ONE"), optionalOnly("ONE"));
    }

    @Test(expected = RuntimeException.class)
    public void arrayTwo() {
        optionalOnly("ONE", "TWO");
    }
}