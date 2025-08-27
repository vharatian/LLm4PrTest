package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.TruePredicate;
import org.junit.jupiter.api.Test;

public class PredicatedCollectionLLM_Test<E> extends AbstractCollectionTest<E> {
    public PredicatedCollectionTest2() {
        super(PredicatedCollectionTest2.class.getSimpleName());
    }

    protected Predicate<E> truePredicate = TruePredicate.<E>truePredicate();

    protected Collection<E> decorateCollection(
        final Collection<E> collection, final Predicate<E> predicate) {
        return PredicatedCollection.predicatedCollection(collection, predicate);
    }

    @Override
    public Collection<E> makeObject() {
        return decorateCollection(new ArrayList<E>(), truePredicate);
    }

    @Override
    public Collection<E> makeConfirmedCollection() {
        return new ArrayList<>();
    }

    @Override
    @SuppressWarnings("unchecked")
    public E[] getFullElements() {
        return (E[]) new Object[] { "1", "3", "5", "7", "2", "4", "6" };
    }

    @Override
    public Collection<E> makeFullCollection() {
        final List<E> list = new ArrayList<>(Arrays.asList(getFullElements()));
        return decorateCollection(list, truePredicate);
    }

    @Override
    public Collection<E> makeConfirmedFullCollection() {
        return new ArrayList<>(Arrays.asList(getFullElements()));
    }

    protected Predicate<E> testPredicate =
        o -> o instanceof String;

    public Collection<E> makeTestCollection() {
        return decorateCollection(new ArrayList<E>(), testPredicate);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testIllegalAdd() {
        final Collection<E> c = makeTestCollection();
        final Integer i = 3;
        assertThrows(IllegalArgumentException.class, () -> c.add((E) i), "Integer should fail string predicate.");
        assertFalse(c.contains(i), "Collection shouldn't contain illegal element");
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testIllegalAddAll() {
        final Collection<E> c = makeTestCollection();
        final List<E> elements = new ArrayList<>();
        elements.add((E) "one");
        elements.add((E) "two");
        elements.add((E) Integer.valueOf(3));
        elements.add((E) "four");
        assertThrows(IllegalArgumentException.class, () -> c.addAll(elements), "Integer should fail string predicate.");
        assertFalse(c.contains("one"), "Collection shouldn't contain illegal element");
        assertFalse(c.contains("two"), "Collection shouldn't contain illegal element");
        assertFalse(c.contains(3), "Collection shouldn't contain illegal element");
        assertFalse(c.contains("four"), "Collection shouldn't contain illegal element");
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }
}