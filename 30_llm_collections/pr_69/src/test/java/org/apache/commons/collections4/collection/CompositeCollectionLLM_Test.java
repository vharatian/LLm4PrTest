package org.apache.commons.collections4.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
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

    @Test
    public void testRemoveComposited() {
        setUpMutatorTest();
        one.add((E) "1");
        two.add((E) "2");
        two.add((E) "1");
        c.addComposited(one, two);
        c.removeComposited(one);
        Assert.assertTrue(c.contains("1"));
        Assert.assertEquals(2, c.size());
    }

    @Test
    public void testRemoveCompositedNull() {
        setUpMutatorTest();
        one.add((E) "1");
        two.add((E) "2");
        two.add((E) "1");
        c.addComposited(one, two);
        c.removeComposited(null);
        Assert.assertTrue(c.contains("1"));
        Assert.assertEquals(3, c.size());
    }

    @SuppressWarnings("serial")
    protected void setUpMutatorTest() {
        setUpTest();
        c.setMutator(new CompositeCollection.CollectionMutator<E>() {
            @Override
            public boolean add(final CompositeCollection<E> composite, final List<Collection<E>> collections, final E obj) {
                for (final Collection<E> coll : collections) {
                    coll.add(obj);
                }
                return true;
            }

            @Override
            public boolean addAll(final CompositeCollection<E> composite,
                                  final List<Collection<E>> collections, final Collection<? extends E> coll) {
                for (final Collection<E> collection : collections) {
                    collection.addAll(coll);
                }
                return true;
            }

            @Override
            public boolean remove(final CompositeCollection<E> composite,
                                  final List<Collection<E>> collections, final Object obj) {
                for (final Collection<E> collection : collections) {
                    collection.remove(obj);
                }
                return true;
            }
        });
    }

    protected void setUpTest() {
        c = new CompositeCollection<>();
        one = new HashSet<>();
        two = new HashSet<>();
    }
}