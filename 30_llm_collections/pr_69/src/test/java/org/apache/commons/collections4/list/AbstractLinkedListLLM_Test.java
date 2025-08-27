package org.apache.commons.collections4.list;

import org.junit.Test;
import static org.junit.Assert.*;

public class AbstractLinkedListLLM_Test<E> extends AbstractLinkedListTest<E> {

    public AbstractLinkedListTest2(final String testName) {
        super(testName);
    }

    @Test
    public void testIsEqualValue() {
        AbstractLinkedList<String> list = new AbstractLinkedList<String>() {};
        
        // Test when both values are the same object
        assertTrue(list.isEqualValue("test", "test"));
        
        // Test when both values are null
        assertTrue(list.isEqualValue(null, null));
        
        // Test when one value is null and the other is not
        assertFalse(list.isEqualValue(null, "test"));
        assertFalse(list.isEqualValue("test", null));
        
        // Test when both values are equal but not the same object
        assertTrue(list.isEqualValue(new String("test"), new String("test")));
        
        // Test when values are different
        assertFalse(list.isEqualValue("test1", "test2"));
    }
}