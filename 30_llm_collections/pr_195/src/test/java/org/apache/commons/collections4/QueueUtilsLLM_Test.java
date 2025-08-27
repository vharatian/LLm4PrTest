package org.apache.commons.collections4;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.LinkedList;
import java.util.Queue;
import org.apache.commons.collections4.functors.TruePredicate;
import org.apache.commons.collections4.queue.PredicatedQueue;
import org.apache.commons.collections4.queue.SynchronizedQueue;
import org.apache.commons.collections4.queue.TransformedQueue;
import org.apache.commons.collections4.queue.UnmodifiableQueue;
import org.junit.Test;

public class QueueUtilsLLM_Test {

    protected Predicate<Object> truePredicate = TruePredicate.truePredicate();
    protected Transformer<Object, Object> nopTransformer = TransformerUtils.nopTransformer();

    @Test
    public void testQueueUtilsConstructor() {
        try {
            QueueUtils.class.getDeclaredConstructor().setAccessible(true);
            QueueUtils.class.getDeclaredConstructor().newInstance();
            fail("Expecting IllegalAccessException for private constructor.");
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException ex) {
            assertTrue("Expected exception for private constructor", ex instanceof IllegalAccessException);
        }
    }
}