package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.SwitchClosure;
import org.apache.commons.collections4.functors.NOPClosure;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SwitchClosureLLM_Test {

    @Test
    public void testSwitchClosureWithNullDefault() {
        Predicate<Object> predicate1 = mock(Predicate.class);
        Predicate<Object> predicate2 = mock(Predicate.class);
        Closure<Object> closure1 = mock(Closure.class);
        Closure<Object> closure2 = mock(Closure.class);

        Predicate<Object>[] predicates = new Predicate[]{predicate1, predicate2};
        Closure<Object>[] closures = new Closure[]{closure1, closure2};

        SwitchClosure<Object> switchClosure = new SwitchClosure<>(predicates, closures, null);

        assertNotNull(switchClosure.getDefaultClosure());
        assertTrue(switchClosure.getDefaultClosure() instanceof NOPClosure);
    }

    @Test
    public void testSwitchClosureWithNonNullDefault() {
        Predicate<Object> predicate1 = mock(Predicate.class);
        Predicate<Object> predicate2 = mock(Predicate.class);
        Closure<Object> closure1 = mock(Closure.class);
        Closure<Object> closure2 = mock(Closure.class);
        Closure<Object> defaultClosure = mock(Closure.class);

        Predicate<Object>[] predicates = new Predicate[]{predicate1, predicate2};
        Closure<Object>[] closures = new Closure[]{closure1, closure2};

        SwitchClosure<Object> switchClosure = new SwitchClosure<>(predicates, closures, defaultClosure);

        assertNotNull(switchClosure.getDefaultClosure());
        assertEquals(defaultClosure, switchClosure.getDefaultClosure());
    }
}