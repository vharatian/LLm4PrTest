package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ObjectGraphIteratorLLM_Test extends AbstractIteratorTest<Object> {

    public ObjectGraphIteratorTest2() {
        super(ObjectGraphIteratorTest2.class.getSimpleName());
    }

    @Override
    public ObjectGraphIterator<Object> makeEmptyIterator() {
        final ArrayList<Object> list = new ArrayList<>();
        return new ObjectGraphIterator<>(list.iterator());
    }

    @Override
    public ObjectGraphIterator<Object> makeObject() {
        final List<Iterator<String>> iteratorList = new ArrayList<>();
        iteratorList.add(new ArrayList<String>().iterator());
        return new ObjectGraphIterator<>(iteratorList.iterator());
    }

    /**
     * Test to ensure that remove() throws UnsupportedOperationException
     * if the underlying iterator does not support the remove operation.
     */
    @Test
    public void testRemoveUnsupportedOperationException() {
        final List<String> list = List.of("One", "Two", "Three");
        final Iterator<String> unmodifiableIterator = List.copyOf(list).iterator();
        final ObjectGraphIterator<Object> it = new ObjectGraphIterator<>(unmodifiableIterator);

        // Iterate through all elements
        while (it.hasNext()) {
            it.next();
        }

        // Attempt to remove an element, which should throw UnsupportedOperationException
        assertThrows(UnsupportedOperationException.class, it::remove);
    }
}