package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HasherCollectionLLM_Test {

    @Test
    public void testEmptyHasherCollection() {
        HasherCollection hasherCollection = new HasherCollection();
        assertNotNull(hasherCollection.indices(new Shape(10, 3)));
        assertNotNull(hasherCollection.uniqueIndices(new Shape(10, 3)));
    }

    @Test
    public void testAddHasher() {
        HasherCollection hasherCollection = new HasherCollection();
        Hasher hasher = new SimpleHasher(1, 2, 3);
        hasherCollection.add(hasher);
        assertEquals(1, hasherCollection.getHashers().size());
    }

    @Test
    public void testAddCollectionOfHashers() {
        HasherCollection hasherCollection = new HasherCollection();
        List<Hasher> hashers = Arrays.asList(new SimpleHasher(1, 2, 3), new SimpleHasher(4, 5, 6));
        hasherCollection.add(hashers);
        assertEquals(2, hasherCollection.getHashers().size());
    }

    @Test
    public void testIndices() {
        Hasher hasher1 = new SimpleHasher(1, 2, 3);
        Hasher hasher2 = new SimpleHasher(4, 5, 6);
        HasherCollection hasherCollection = new HasherCollection(hasher1, hasher2);
        IndexProducer indexProducer = hasherCollection.indices(new Shape(10, 3));
        int[] indices = indexProducer.asIndexArray();
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, indices);
    }

    @Test
    public void testUniqueIndices() {
        Hasher hasher1 = new SimpleHasher(1, 2, 3);
        Hasher hasher2 = new SimpleHasher(3, 4, 5);
        HasherCollection hasherCollection = new HasherCollection(hasher1, hasher2);
        IndexProducer indexProducer = hasherCollection.uniqueIndices(new Shape(10, 3));
        int[] indices = indexProducer.asIndexArray();
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, indices);
    }

    @Test
    public void testHasherCollectionConstructorWithCollection() {
        List<Hasher> hashers = Arrays.asList(new SimpleHasher(1, 2, 3), new SimpleHasher(4, 5, 6));
        HasherCollection hasherCollection = new HasherCollection(hashers);
        assertEquals(2, hasherCollection.getHashers().size());
    }

    @Test
    public void testHasherCollectionConstructorWithVarargs() {
        HasherCollection hasherCollection = new HasherCollection(new SimpleHasher(1, 2, 3), new SimpleHasher(4, 5, 6));
        assertEquals(2, hasherCollection.getHashers().size());
    }

    @Test
    public void testHasherCollectionIndexProducerForEachIndex() {
        Hasher hasher1 = new SimpleHasher(1, 2, 3);
        Hasher hasher2 = new SimpleHasher(4, 5, 6);
        HasherCollection hasherCollection = new HasherCollection(hasher1, hasher2);
        IndexProducer indexProducer = hasherCollection.indices(new Shape(10, 3));
        assertTrue(indexProducer.forEachIndex(index -> index >= 1 && index <= 6));
    }

    @Test
    public void testHasherCollectionIndexProducerAsIndexArray() {
        Hasher hasher1 = new SimpleHasher(1, 2, 3);
        Hasher hasher2 = new SimpleHasher(4, 5, 6);
        HasherCollection hasherCollection = new HasherCollection(hasher1, hasher2);
        IndexProducer indexProducer = hasherCollection.indices(new Shape(10, 3));
        int[] indices = indexProducer.asIndexArray();
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, indices);
    }
}