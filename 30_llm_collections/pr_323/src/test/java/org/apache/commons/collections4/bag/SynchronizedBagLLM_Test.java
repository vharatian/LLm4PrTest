package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.BulkTest;
import org.junit.Test;
import static org.junit.Assert.*;

public class SynchronizedBagLLM_Test<T> extends AbstractBagTest<T> {

    public SynchronizedBagTest2() {
        super(SynchronizedBagTest2.class.getSimpleName());
    }

    public static junit.framework.Test suite() {
        return BulkTest.makeSuite(SynchronizedBagTest2.class);
    }

    @Override
    public Bag<T> makeObject() {
        return SynchronizedBag.synchronizedBag(new HashBag<T>());
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

    @Override
    protected int getIterationBehaviour() {
        return UNORDERED;
    }

    /**
     * Test to ensure the class documentation change does not affect functionality.
     */
    @Test
    public void testDocumentationChange() {
        SynchronizedBag<T> bag = SynchronizedBag.synchronizedBag(new HashBag<T>());
        assertNotNull(bag);
    }
}