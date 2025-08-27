package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ShapeLLM_Test {

    @Test
    public void testEstimateMaxN() {
        Shape shape = Shape.fromKM(3, 24);
        double expected = 24 * Math.log(2.0) / 3;
        assertEquals(expected, shape.estimateMaxN(), 0.000001, "Error in estimateMaxN calculation");

        shape = Shape.fromKM(5, 100);
        expected = 100 * Math.log(2.0) / 5;
        assertEquals(expected, shape.estimateMaxN(), 0.000001, "Error in estimateMaxN calculation");

        shape = Shape.fromKM(1, 1);
        expected = 1 * Math.log(2.0) / 1;
        assertEquals(expected, shape.estimateMaxN(), 0.000001, "Error in estimateMaxN calculation");
    }
}