package com.snell.michael.highlander;

import com.google.common.base.Optional;
import org.junit.Test;

import java.util.Collection;
import java.util.function.Predicate;

import static com.snell.michael.highlander.GuavaOptionalHighlander.optionalOnly;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class GuavaOptionalHighlanderStreamTest {
    private static final Collection<String> NONE = asList();
    private static final Collection<String> ONE = asList("ONE");
    private static final Collection<String> TWO = asList("ONE", "TWO");

    @Test
    public void streamNone() {
        assertEquals(Optional.<String>absent(), optionalOnly(NONE.stream()));
    }

    @Test
    public void streamOne() {
        assertEquals(Optional.of("ONE"), optionalOnly(ONE.stream()));
    }

    @Test(expected = RuntimeException.class)
    public void streamTwo() {
        optionalOnly(TWO.stream());
    }

    @Test(expected = RuntimeException.class)
    public void streamDuplicates() {
        optionalOnly(asList("ONE", "ONE").stream());
    }

    @Test
    public void streamFilter() {
        assertEquals(Optional.of("ONE"), TWO.stream()
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String t) {
                        return "ONE".equals(t);
                    }
                })
                .collect(GuavaOptionalHighlander.<String>optionalOnly()));
    }
}