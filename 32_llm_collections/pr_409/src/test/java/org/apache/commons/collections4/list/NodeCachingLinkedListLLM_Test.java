package org.apache.commons.collections4.list;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NodeCachingLinkedListLLM_Test<E> extends AbstractLinkedListTest<E> {

    public NodeCachingLinkedListTest2() {
        super(NodeCachingLinkedListTest2.class.getSimpleName());
    }

    @Override
    public NodeCachingLinkedList<E> makeObject() {
        return new NodeCachingLinkedList<>();
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testAddNodeToCache() {
        if (!isRemoveSupported() || !isAddSupported()) {
            return;
        }
        resetEmpty();
        final NodeCachingLinkedList<E> list = getCollection();
        list.addAll(Arrays.asList((E[]) new String[] { "1", "2", "3", "4" }));
        list.removeAllNodes();
        list.setMaximumCacheSize(2);
        list.addAll(Arrays.asList((E[]) new String[] { "1", "2", "3", "4" }));
        checkNodes();
        list.removeNode(list.getNode(0, false));
        list.removeNode(list.getNode(0, false));
        list.removeNode(list.getNode(0, false));
        checkNodes();
        list.addAll(Arrays.asList((E[]) new String[] { "1", "2", "3", "4" }));
        checkNodes();
    }

    @Test
    public void testIsCacheFull() {
        final NodeCachingLinkedList<E> list = new NodeCachingLinkedList<>(2);
        assertFalse(list.isCacheFull());
        list.addNodeToCache(list.createNode(null));
        assertFalse(list.isCacheFull());
        list.addNodeToCache(list.createNode(null));
        assertTrue(list.isCacheFull());
    }

    @Test
    public void testCacheSizeAfterShrink() {
        final NodeCachingLinkedList<E> list = new NodeCachingLinkedList<>(5);
        list.addNodeToCache(list.createNode(null));
        list.addNodeToCache(list.createNode(null));
        list.addNodeToCache(list.createNode(null));
        list.setMaximumCacheSize(2);
        assertEquals(2, list.cacheSize);
    }

    @Override
    public NodeCachingLinkedList<E> getCollection() {
        return (NodeCachingLinkedList<E>) super.getCollection();
    }
}