package com.snell.michael.highlander;

import org.junit.Test;

import java.util.Optional;

import static com.snell.michael.highlander.Highlander.optionalOnly;
import static org.junit.Assert.assertEquals;

public class HighlanderOptionalArrayTest {
    @Test
    public void arrayNone() {
        assertEquals(Optional.<String>empty(), optionalOnly(new String[0]));
    }

    @Test
    public void arrayOne() {
        assertEquals(Optional.of("ONE"), optionalOnly(new String[] {"ONE"}));
    }

    @Test(expected = RuntimeException.class)
    public void arrayTwo() {
        optionalOnly(new String[] {"ONE", "TWO"});
    }
}