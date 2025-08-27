package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.Predicate;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class WhileClosureLLM_Test {

    @Test
    public void testWhileClosureWithDoLoopTrue() {
        Predicate<Object> predicate = mock(Predicate.class);
        Closure<Object> closure = mock(Closure.class);

        when(predicate.evaluate(any())).thenReturn(false);

        WhileClosure<Object> whileClosure = new WhileClosure<>(predicate, closure, true);
        whileClosure.execute(new Object());

        verify(closure, times(1)).execute(any());
        verify(predicate, times(1)).evaluate(any());
    }

    @Test
    public void testWhileClosureWithDoLoopFalse() {
        Predicate<Object> predicate = mock(Predicate.class);
        Closure<Object> closure = mock(Closure.class);

        when(predicate.evaluate(any())).thenReturn(false);

        WhileClosure<Object> whileClosure = new WhileClosure<>(predicate, closure, false);
        whileClosure.execute(new Object());

        verify(closure, never()).execute(any());
        verify(predicate, times(1)).evaluate(any());
    }

    @Test
    public void testWhileClosurePredicateTrue() {
        Predicate<Object> predicate = mock(Predicate.class);
        Closure<Object> closure = mock(Closure.class);

        when(predicate.evaluate(any())).thenReturn(true).thenReturn(false);

        WhileClosure<Object> whileClosure = new WhileClosure<>(predicate, closure, false);
        whileClosure.execute(new Object());

        verify(closure, times(1)).execute(any());
        verify(predicate, times(2)).evaluate(any());
    }

    @Test
    public void testWhileClosurePredicateAlwaysTrue() {
        Predicate<Object> predicate = mock(Predicate.class);
        Closure<Object> closure = mock(Closure.class);

        when(predicate.evaluate(any())).thenReturn(true);

        WhileClosure<Object> whileClosure = new WhileClosure<>(predicate, closure, false);

        // To avoid infinite loop in test, we will use a separate thread and interrupt it
        Thread thread = new Thread(() -> whileClosure.execute(new Object()));
        thread.start();
        try {
            Thread.sleep(100); // Let it run for a short time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();

        verify(closure, atLeastOnce()).execute(any());
        verify(predicate, atLeastOnce()).evaluate(any());
    }
}