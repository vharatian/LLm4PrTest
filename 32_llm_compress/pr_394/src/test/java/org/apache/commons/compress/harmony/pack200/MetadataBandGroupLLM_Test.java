package org.apache.commons.compress.harmony.pack200;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MetadataBandGroupLLM_Test {

    private MetadataBandGroup metadataBandGroup;
    private CpBands cpBands;
    private SegmentHeader segmentHeader;

    @BeforeEach
    public void setUp() {
        cpBands = new CpBands();
        segmentHeader = new SegmentHeader();
        metadataBandGroup = new MetadataBandGroup("testType", MetadataBandGroup.CONTEXT_CLASS, cpBands, segmentHeader, 5);
    }

    @Test
    public void testPackWithNonNullEncodedBand() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        assertDoesNotThrow(() -> {
            metadataBandGroup.pack(out);
        });
    }
}