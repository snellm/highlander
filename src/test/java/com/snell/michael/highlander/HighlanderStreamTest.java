package com.snell.michael.highlander;

import org.junit.Test;

import java.util.Collection;
import java.util.function.Predicate;

import static com.snell.michael.highlander.Highlander.only;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class HighlanderStreamTest {
    private static final Collection<String> NONE = asList();
    private static final Collection<String> ONE = asList("ONE");
    private static final Collection<String> TWO = asList("ONE", "TWO");

    @Test(expected = RuntimeException.class)
    public void streamNone() {
        only(NONE.stream());
    }

    @Test
    public void streamOne() {
        assertEquals("ONE", only(ONE.stream()));
    }

    @Test(expected = RuntimeException.class)
    public void streamTwo() {
        only(TWO.stream());
    }

    @Test(expected = RuntimeException.class)
    public void streamDuplicates() {
        only(asList("ONE", "ONE").stream());
    }

    @Test
    public void streamFilter() {
        assertEquals("ONE", TWO.stream()
                        .filter(new Predicate<String>() {
                            @Override
                            public boolean test(String t) {
                                return "ONE".equals(t);
                            }
                        })
                        .collect(only())
        );
    }
}