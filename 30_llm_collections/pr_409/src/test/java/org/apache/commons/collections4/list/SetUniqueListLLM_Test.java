package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class SetUniqueListLLM_Test<E> extends AbstractListTest<E> {

    public SetUniqueListTest2() {
        super(SetUniqueListTest2.class.getSimpleName());
    }

    @Test
    public void testAddMethodDocumentation() {
        final SetUniqueList<E> lset = new SetUniqueList<>(new ArrayList<E>(), new HashSet<E>());
        final E obj = (E) Integer.valueOf(1);
        boolean result = lset.add(obj);
        assertEquals(true, result, "Expected add method to return true when adding a new element.");
        result = lset.add(obj);
        assertEquals(false, result, "Expected add method to return false when adding a duplicate element.");
    }
}