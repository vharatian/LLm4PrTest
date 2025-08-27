package org.apache.commons.compress.harmony.unpack200;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IcBandsLLM_Test {

    private IcBands icBands;
    private Segment segment;

    @BeforeEach
    public void setUp() {
        segment = new Segment();
        icBands = new IcBands(segment);
    }

    @Test
    public void testRead() throws IOException, Pack200Exception {
        String input = "some input data";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        icBands.read(in);
        assertNotNull(icBands.getIcTuples());
    }

    @Test
    public void testGetRelevantIcTuples() throws IOException, Pack200Exception {
        String input = "some input data";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        icBands.read(in);
        ClassConstantPool cp = new ClassConstantPool();
        IcTuple[] relevantIcTuples = icBands.getRelevantIcTuples("someClassName", cp);
        assertNotNull(relevantIcTuples);
        assertEquals(0, relevantIcTuples.length);
    }

    @Test
    public void testSortRelevantIcTuples() throws IOException, Pack200Exception {
        String input = "some input data";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        icBands.read(in);
        ClassConstantPool cp = new ClassConstantPool();
        IcTuple[] relevantIcTuples = icBands.getRelevantIcTuples("someClassName", cp);
        assertNotNull(relevantIcTuples);
        for (int i = 0; i < relevantIcTuples.length - 1; i++) {
            assertEquals(true, relevantIcTuples[i].getTupleIndex() <= relevantIcTuples[i + 1].getTupleIndex());
        }
    }
}