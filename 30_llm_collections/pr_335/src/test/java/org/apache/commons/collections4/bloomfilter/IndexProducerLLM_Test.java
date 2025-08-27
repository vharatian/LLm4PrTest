package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

public class IndexProducerLLM_Test {

    @Test
    public void fromIndexArray_asIndexArrayTest() {
        int[] input = {1, 2, 3, 4, 5};
        IndexProducer producer = IndexProducer.fromIndexArray(input);
        int[] output = producer.asIndexArray();
        assertArrayEquals(input, output);
    }

    @Test
    public void fromIndexArray_asIndexArrayEmptyTest() {
        int[] input = {};
        IndexProducer producer = IndexProducer.fromIndexArray(input);
        int[] output = producer.asIndexArray();
        assertArrayEquals(input, output);
    }
}