package org.apache.commons.lang3.function;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.function.Function;

public class FailableFunctionLLM_Test {

    @Test
    public void testCompose() throws Throwable {
        FailableFunction<String, Integer, Throwable> parseInt = Integer::parseInt;
        FailableFunction<Integer, Integer, Throwable> square = x -> x * x;
        FailableFunction<String, Integer, Throwable> parseAndSquare = square.compose(parseInt);

        assertEquals(25, parseAndSquare.apply("5"));
        assertThrows(NumberFormatException.class, () -> parseAndSquare.apply("not a number"));
    }

    @Test
    public void testComposeNull() {
        FailableFunction<String, Integer, Throwable> parseInt = Integer::parseInt;
        assertThrows(NullPointerException.class, () -> parseInt.compose(null));
    }
}