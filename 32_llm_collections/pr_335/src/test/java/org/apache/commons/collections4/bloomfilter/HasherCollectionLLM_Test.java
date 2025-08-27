package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HasherCollectionLLM_Test extends AbstractHasherTest {

    @Override
    protected HasherCollection createHasher() {
        return new HasherCollection(new IncrementingHasher(1, 1), new IncrementingHasher(2, 2));
    }

    @Override
    protected HasherCollection createEmptyHasher() {
        return new HasherCollection();
    }

    @Override
    protected int getBehaviour() {
        return 0;
    }

    @Override
    protected int getHasherSize(Hasher hasher) {
        return ((HasherCollection) hasher).getHashers().size();
    }

    /**
     * Test the absoluteUniqueIndices method.
     */
    @Test
    public void testAbsoluteUniqueIndices() {
        Shape shape = Shape.fromKM(12, 72);
        Hasher h1 = new IncrementingHasher(5, 12);
        Hasher h2 = new IncrementingHasher(7, 12);
        HasherCollection hasher = createEmptyHasher();
        hasher.add(h1);
        hasher.add(h2);

        List<Integer> expectedIndices = new ArrayList<>(Arrays.asList(5, 17, 29, 41, 53, 65, 7, 19, 31, 43, 55, 67));
        assertTrue(hasher.absoluteUniqueIndices(shape).forEachIndex(i -> expectedIndices.remove(Integer.valueOf(i))), "unable to remove value");
        assertEquals(0, expectedIndices.size());
    }

    /**
     * Test the modified asIndexArray method.
     */
    @Test
    public void testAsIndexArray() {
        Shape shape = Shape.fromKM(12, 72);
        Hasher h1 = new IncrementingHasher(5, 12);
        Hasher h2 = new IncrementingHasher(7, 12);
        HasherCollection hasher = createEmptyHasher();
        hasher.add(h1);
        hasher.add(h2);

        int[] expectedIndices = new int[] { 5, 17, 29, 41, 53, 65, 7, 19, 31, 43, 55, 67 };
        int[] actualIndices = hasher.indices(shape).asIndexArray();
        assertTrue(Arrays.equals(expectedIndices, actualIndices), "Indices array does not match expected values");
    }
}