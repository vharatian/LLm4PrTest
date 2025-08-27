package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.EmptyStackException;
import org.junit.jupiter.api.Test;

@SuppressWarnings("deprecation")
public class ArrayStackLLM_Test<E> extends AbstractArrayListTest<E> {

    public ArrayStackTest2() {
        super(ArrayStackTest2.class.getSimpleName());
    }

    public static junit.framework.Test suite() {
        return BulkTest.makeSuite(ArrayStackTest2.class);
    }

    @Override
    public ArrayStack<E> makeObject() {
        return new ArrayStack<>();
    }

    @Test
    public void testMultithreadedAccess() {
        final ArrayStack<E> stack = makeObject();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                stack.push((E) ("Item " + i));
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                stack.push((E) ("Item " + (i + 1000)));
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals("Stack size should be 2000", 2000, stack.size());
    }
}