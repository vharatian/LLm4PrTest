package org.apache.commons.imaging.formats.tiff;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TiffElementLLM_Test {

    @Test
    public void testDataElementGetDataReturnsClone() {
        byte[] originalData = {1, 2, 3, 4, 5};
        TiffElement.DataElement dataElement = new TiffElement.DataElement(0, originalData.length, originalData) {};
        
        byte[] retrievedData = dataElement.getData();
        
        // Ensure the returned data is not the same reference as the original data
        assertNotSame(originalData, retrievedData);
        
        // Ensure the returned data is equal to the original data
        assertArrayEquals(originalData, retrievedData);
    }
}