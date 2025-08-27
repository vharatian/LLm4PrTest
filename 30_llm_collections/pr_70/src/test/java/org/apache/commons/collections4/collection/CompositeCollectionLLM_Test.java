package org.apache.commons.collections4.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.function.Predicate;
import org.junit.Assert;
import org.junit.Test;

public class CompositeCollectionLLM_Test<E> extends AbstractCollectionTest<E> {
    protected CompositeCollection<E> c;
    protected Collection<E> one;
    protected Collection<E> two;

    public CompositeCollectionTest2(final String name) {
        super(name);
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

    @Override
    @SuppressWarnings("unchecked")
    public E[] getFullElements() {
        return (E[]) new Object[] { "1", "2", "3", "4" };
    }

    @Override
    public boolean isAddSupported() {
        return false;
    }

    @Override
    public boolean isRemoveSupported() {
        return false;
    }

    @Override
    public Collection<E> makeConfirmedCollection() {
        return new HashSet<>();
    }

    @Override
    public Collection<E> makeConfirmedFullCollection() {
        final Collection<E> collection = new HashSet<>();
        collection.addAll(Arrays.asList(getFullElements()));
        return collection;
    }

    @Override
    public Collection<E> makeFullCollection() {
        final CompositeCollection<E> compositeCollection = new CompositeCollection<>();
        final E[] elements = getFullElements();
        for (final E element : elements) {
            final Collection<E> summand = new HashSet<>();
            summand.add(element);
            compositeCollection.addComposited(summand);
        }
        return compositeCollection;
    }

    @Override
    public Collection<E> makeObject() {
        return new CompositeCollection<>();
    }

    protected void setUpTest() {
        c = new CompositeCollection<>();
        one = new HashSet<>();
        two = new HashSet<>();
    }

    @Test
    public void testRemoveIf() {
        setUpTest();
        one.add((E) "1");
        one.add((E) "2");
        two.add((E) "3");
        two.add((E) "4");
        c.addComposited(one, two);

        Predicate<E> filter = e -> e.equals("2") || e.equals("3");
        boolean result = c.removeIf(filter);

        Assert.assertTrue(result);
        Assert.assertFalse(c.contains("2"));
        Assert.assertFalse(c.contains("3"));
        Assert.assertTrue(c.contains("1"));
        Assert.assertTrue(c.contains("4"));
    }

    @Test
    public void testRemoveIfNullPredicate() {
        setUpTest();
        one.add((E) "1");
        one.add((E) "2");
        two.add((E) "3");
        two.add((E) "4");
        c.addComposited(one, two);

        boolean result = c.removeIf(null);

        Assert.assertFalse(result);
        Assert.assertTrue(c.contains("1"));
        Assert.assertTrue(c.contains("2"));
        Assert.assertTrue(c.contains("3"));
        Assert.assertTrue(c.contains("4"));
    }
}