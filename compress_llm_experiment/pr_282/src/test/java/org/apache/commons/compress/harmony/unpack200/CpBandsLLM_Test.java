package org.apache.commons.compress.harmony.unpack200;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class CpBandsLLM_Test {

    private CpBands cpBands;
    private Segment segment;

    @BeforeEach
    public void setUp() {
        segment = new Segment();
        cpBands = new CpBands(segment);
    }

    @Test
    public void testParseCpFloat() throws Exception {
        // Mock input stream for cpFloat
        int cpFloatCount = 3;
        byte[] mockData = new byte[]{0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        InputStream in = new ByteArrayInputStream(mockData);
        segment.setHeader(new SegmentHeader(cpFloatCount, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));

        cpBands.read(in);

        float[] expected = new float[cpFloatCount];
        Arrays.fill(expected, 0.0f);
        assertArrayEquals(expected, cpBands.getCpFloat());
    }

    @Test
    public void testParseCpUtf8() throws Exception {
        // Mock input stream for cpUtf8
        int cpUTF8Count = 3;
        byte[] mockData = new byte[]{0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        InputStream in = new ByteArrayInputStream(mockData);
        segment.setHeader(new SegmentHeader(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, cpUTF8Count));

        cpBands.read(in);

        String[] expected = new String[cpUTF8Count];
        Arrays.fill(expected, "");
        assertArrayEquals(expected, cpBands.getCpUTF8());
    }
}