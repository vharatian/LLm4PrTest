package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ShapeLLM_Test {

    // Test for the corrected comments in the calculateNumberOfHashFunctions method
    @Test
    public void testCalculateNumberOfHashFunctions() {
        // Valid case
        int numberOfItems = 10;
        int numberOfBits = 100;
        int expectedHashFunctions = (int) Math.round(Math.log(2.0) * numberOfBits / numberOfItems);
        assertEquals(expectedHashFunctions, Shape.calculateNumberOfHashFunctions(numberOfItems, numberOfBits));

        // Edge case where number of items is 1
        numberOfItems = 1;
        numberOfBits = 100;
        expectedHashFunctions = (int) Math.round(Math.log(2.0) * numberOfBits / numberOfItems);
        assertEquals(expectedHashFunctions, Shape.calculateNumberOfHashFunctions(numberOfItems, numberOfBits));

        // Edge case where number of bits is 1
        numberOfItems = 10;
        numberOfBits = 1;
        assertThrows(IllegalArgumentException.class, () -> Shape.calculateNumberOfHashFunctions(numberOfItems, numberOfBits));

        // Edge case where number of items is greater than number of bits
        numberOfItems = 100;
        numberOfBits = 10;
        assertThrows(IllegalArgumentException.class, () -> Shape.calculateNumberOfHashFunctions(numberOfItems, numberOfBits));
    }
}