package org.apache.commons.lang3.concurrent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class MemoizerLLM_Test {

    private Memoizer<Integer, Integer> memoizer;
    private Computable<Integer, Integer> computable;

    @BeforeEach
    public void setUp() {
        computable = arg -> arg * arg;
        memoizer = new Memoizer<>(computable);
    }

    @Test
    public void testCompute() throws InterruptedException, ExecutionException {
        assertEquals(4, memoizer.compute(2));
        assertEquals(9, memoizer.compute(3));
    }

    @Test
    public void testComputeWithRecalculate() throws InterruptedException, ExecutionException {
        Memoizer<Integer, Integer> memoizerWithRecalculate = new Memoizer<>(computable, true);
        assertEquals(4, memoizerWithRecalculate.compute(2));
        assertEquals(9, memoizerWithRecalculate.compute(3));
    }

    @Test
    public void testComputeWithFunction() throws InterruptedException, ExecutionException {
        Function<Integer, Integer> function = arg -> arg * arg;
        Memoizer<Integer, Integer> memoizerWithFunction = new Memoizer<>(function);
        assertEquals(4, memoizerWithFunction.compute(2));
        assertEquals(9, memoizerWithFunction.compute(3));
    }

    @Test
    public void testComputeWithFunctionAndRecalculate() throws InterruptedException, ExecutionException {
        Function<Integer, Integer> function = arg -> arg * arg;
        Memoizer<Integer, Integer> memoizerWithFunctionAndRecalculate = new Memoizer<>(function, true);
        assertEquals(4, memoizerWithFunctionAndRecalculate.compute(2));
        assertEquals(9, memoizerWithFunctionAndRecalculate.compute(3));
    }

    @Test
    public void testComputeWithCancellationException() throws InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        Function<Integer, Future<Integer>> function = arg -> executor.submit(() -> {
            throw new CancellationException();
        });
        Memoizer<Integer, Integer> memoizerWithCancellation = new Memoizer<>(function);
        assertThrows(CancellationException.class, () -> memoizerWithCancellation.compute(1));
    }

    @Test
    public void testComputeWithExecutionException() throws InterruptedException {
        Function<Integer, Integer> function = arg -> {
            throw new RuntimeException("Test exception");
        };
        Memoizer<Integer, Integer> memoizerWithExecutionException = new Memoizer<>(function);
        assertThrows(RuntimeException.class, () -> memoizerWithExecutionException.compute(1));
    }
}