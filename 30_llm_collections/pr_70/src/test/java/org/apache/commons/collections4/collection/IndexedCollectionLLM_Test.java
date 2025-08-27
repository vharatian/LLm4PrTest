package org.apache.commons.collections4.collection;

import static java.util.Arrays.asList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import org.apache.commons.collections4.Transformer;
import junit.framework.TestCase;

@SuppressWarnings("boxing")
public class IndexedCollectionLLM_Test extends TestCase {

    public IndexedCollectionTest2(final String name) {
        super(name);
    }

    protected Collection<String> decorateCollection(final Collection<String> collection) {
        return IndexedCollection.nonUniqueIndexedCollection(collection, new IntegerTransformer());
    }

    protected IndexedCollection<Integer, String> decorateUniqueCollection(final Collection<String> collection) {
        return IndexedCollection.uniqueIndexedCollection(collection, new IntegerTransformer());
    }

    private static final class IntegerTransformer implements Transformer<String, Integer>, Serializable {
        private static final long serialVersionUID = 809439581555072949L;

        @Override
        public Integer transform(final String input) {
            return Integer.valueOf(input);
        }
    }

    public void testRemoveIfWithNullPredicate() {
        final Collection<String> coll = decorateCollection(new ArrayList<>());
        coll.addAll(asList("1", "2", "3"));
        @SuppressWarnings("unchecked")
        final IndexedCollection<Integer, String> indexed = (IndexedCollection<Integer, String>) coll;
        assertFalse(indexed.removeIf(null));
        assertEquals(3, indexed.size());
    }

    public void testRemoveIfWithMatchingPredicate() {
        final Collection<String> coll = decorateCollection(new ArrayList<>());
        coll.addAll(asList("1", "2", "3"));
        @SuppressWarnings("unchecked")
        final IndexedCollection<Integer, String> indexed = (IndexedCollection<Integer, String>) coll;
        Predicate<String> predicate = s -> s.equals("2");
        assertTrue(indexed.removeIf(predicate));
        assertEquals(2, indexed.size());
        assertNull(indexed.get(2));
    }

    public void testRemoveIfWithNonMatchingPredicate() {
        final Collection<String> coll = decorateCollection(new ArrayList<>());
        coll.addAll(asList("1", "2", "3"));
        @SuppressWarnings("unchecked")
        final IndexedCollection<Integer, String> indexed = (IndexedCollection<Integer, String>) coll;
        Predicate<String> predicate = s -> s.equals("4");
        assertFalse(indexed.removeIf(predicate));
        assertEquals(3, indexed.size());
        assertEquals("1", indexed.get(1));
        assertEquals("2", indexed.get(2));
        assertEquals("3", indexed.get(3));
    }
}