package org.apache.commons.collections4;

import static org.apache.commons.collections4.functors.EqualPredicate.equalPredicate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class IteratorUtilsLLM_Test {

    @Test
    public void testIndexOfWithCollectionUtilsIndexNotFound() {
        Predicate<Number> testPredicate = equalPredicate((Number) 45);
        int index = IteratorUtils.indexOf(null, testPredicate);
        assertEquals(CollectionUtils.INDEX_NOT_FOUND, index);

        index = IteratorUtils.indexOf(IteratorUtils.emptyIterator(), testPredicate);
        assertEquals(CollectionUtils.INDEX_NOT_FOUND, index);

        index = IteratorUtils.indexOf(IteratorUtils.singletonIterator(1), testPredicate);
        assertEquals(CollectionUtils.INDEX_NOT_FOUND, index);

        assertThrows(NullPointerException.class, () -> IteratorUtils.indexOf(IteratorUtils.singletonIterator(1), null));
    }
}