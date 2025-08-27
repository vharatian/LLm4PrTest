package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ClosureUtilsLLM_Test {

    @Test
    public void testSwitchClosureNullPointerException() {
        assertThrows(NullPointerException.class, () -> ClosureUtils.switchClosure(null, new Closure[1]));
        assertThrows(NullPointerException.class, () -> ClosureUtils.switchClosure(new Predicate[1], null));
        assertThrows(NullPointerException.class, () -> ClosureUtils.switchClosure(new Predicate[1], new Closure[1], null));
    }

    @Test
    public void testSwitchClosureIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ClosureUtils.switchClosure(new Predicate[1], new Closure[2]));
    }
}