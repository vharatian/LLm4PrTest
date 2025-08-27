package org.apache.commons.collections4.bloomfilter.hasher;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShapeLLM_Test {

    @Test
    public void testConstructorWithProbability() {
        HashFunctionIdentity hashFunctionIdentity = new HashFunctionIdentityImpl("TestHash", "1.0", 64);
        int numberOfItems = 10;
        double probability = 0.01;

        Shape shape = new Shape(hashFunctionIdentity, numberOfItems, probability);

        assertEquals(hashFunctionIdentity, shape.getHashFunctionIdentity());
        assertEquals(numberOfItems, shape.getNumberOfItems());
        assertTrue(shape.getNumberOfBits() > 0);
        assertTrue(shape.getNumberOfHashFunctions() > 0);
    }

    @Test
    public void testConstructorWithNumberOfBits() {
        HashFunctionIdentity hashFunctionIdentity = new HashFunctionIdentityImpl("TestHash", "1.0", 64);
        int numberOfItems = 10;
        int numberOfBits = 128;

        Shape shape = new Shape(hashFunctionIdentity, numberOfItems, numberOfBits);

        assertEquals(hashFunctionIdentity, shape.getHashFunctionIdentity());
        assertEquals(numberOfItems, shape.getNumberOfItems());
        assertEquals(numberOfBits, shape.getNumberOfBits());
        assertTrue(shape.getNumberOfHashFunctions() > 0);
    }

    @Test
    public void testConstructorWithNumberOfHashFunctions() {
        HashFunctionIdentity hashFunctionIdentity = new HashFunctionIdentityImpl("TestHash", "1.0", 64);
        int numberOfItems = 10;
        int numberOfBits = 128;
        int numberOfHashFunctions = 5;

        Shape shape = new Shape(hashFunctionIdentity, numberOfItems, numberOfBits, numberOfHashFunctions);

        assertEquals(hashFunctionIdentity, shape.getHashFunctionIdentity());
        assertEquals(numberOfItems, shape.getNumberOfItems());
        assertEquals(numberOfBits, shape.getNumberOfBits());
        assertEquals(numberOfHashFunctions, shape.getNumberOfHashFunctions());
    }

    @Test
    public void testConstructorWithProbabilityAndNumberOfBits() {
        HashFunctionIdentity hashFunctionIdentity = new HashFunctionIdentityImpl("TestHash", "1.0", 64);
        double probability = 0.01;
        int numberOfBits = 128;
        int numberOfHashFunctions = 5;

        Shape shape = new Shape(hashFunctionIdentity, probability, numberOfBits, numberOfHashFunctions);

        assertEquals(hashFunctionIdentity, shape.getHashFunctionIdentity());
        assertTrue(shape.getNumberOfItems() > 0);
        assertEquals(numberOfBits, shape.getNumberOfBits());
        assertEquals(numberOfHashFunctions, shape.getNumberOfHashFunctions());
    }

    @Test
    public void testGetProbability() {
        HashFunctionIdentity hashFunctionIdentity = new HashFunctionIdentityImpl("TestHash", "1.0", 64);
        int numberOfItems = 10;
        double probability = 0.01;

        Shape shape = new Shape(hashFunctionIdentity, numberOfItems, probability);

        assertTrue(shape.getProbability() > 0.0);
        assertTrue(shape.getProbability() < 1.0);
    }

    @Test
    public void testGetNumberOfBytes() {
        HashFunctionIdentity hashFunctionIdentity = new HashFunctionIdentityImpl("TestHash", "1.0", 64);
        int numberOfItems = 10;
        double probability = 0.01;

        Shape shape = new Shape(hashFunctionIdentity, numberOfItems, probability);

        assertTrue(shape.getNumberOfBytes() > 0);
    }

    @Test
    public void testEqualsAndHashCode() {
        HashFunctionIdentity hashFunctionIdentity1 = new HashFunctionIdentityImpl("TestHash1", "1.0", 64);
        HashFunctionIdentity hashFunctionIdentity2 = new HashFunctionIdentityImpl("TestHash2", "1.0", 64);
        int numberOfItems = 10;
        double probability = 0.01;

        Shape shape1 = new Shape(hashFunctionIdentity1, numberOfItems, probability);
        Shape shape2 = new Shape(hashFunctionIdentity1, numberOfItems, probability);
        Shape shape3 = new Shape(hashFunctionIdentity2, numberOfItems, probability);

        assertEquals(shape1, shape2);
        assertNotEquals(shape1, shape3);
        assertEquals(shape1.hashCode(), shape2.hashCode());
        assertNotEquals(shape1.hashCode(), shape3.hashCode());
    }

    @Test
    public void testToString() {
        HashFunctionIdentity hashFunctionIdentity = new HashFunctionIdentityImpl("TestHash", "1.0", 64);
        int numberOfItems = 10;
        double probability = 0.01;

        Shape shape = new Shape(hashFunctionIdentity, numberOfItems, probability);

        String expectedString = String.format("Shape[ %s n=%s m=%s k=%s ]",
            HashFunctionIdentity.asCommonString(hashFunctionIdentity),
            numberOfItems, shape.getNumberOfBits(), shape.getNumberOfHashFunctions());

        assertEquals(expectedString, shape.toString());
    }
}