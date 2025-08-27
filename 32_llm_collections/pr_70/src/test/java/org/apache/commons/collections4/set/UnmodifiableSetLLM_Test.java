package org.apache.commons.collections4.set;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import junit.framework.Test;
import org.apache.commons.collections4.BulkTest;
import org.apache.commons.collections4.Unmodifiable;

public class UnmodifiableSetLLM_Test<E> extends AbstractSetTest<E> {

    public UnmodifiableSetTest2(final String testName) {
        super(testName);
    }

    public static Test suite() {
        return BulkTest.makeSuite(UnmodifiableSetTest2.class);
    }

    @Override
    public Set<E> makeObject() {
        return UnmodifiableSet.unmodifiableSet(new HashSet<E>());
    }

    @Override
    public Set<E> makeFullCollection() {
        final HashSet<E> set = new HashSet<>();
        set.addAll(Arrays.asList(getFullElements()));
        return UnmodifiableSet.unmodifiableSet(set);
    }

    @Override
    public boolean isAddSupported() {
        return false;
    }

    @Override
    public boolean isRemoveSupported() {
        return false;
    }

    /**
     * Test that removeIf throws UnsupportedOperationException
     */
    public void testRemoveIf() {
        final Set<E> set = makeFullCollection();
        try {
            set.removeIf(new Predicate<E>() {
                @Override
                public boolean test(E e) {
                    return true;
                }
            });
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException ex) {
            // expected
        }
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }
}