package org.apache.commons.compress.harmony.unpack200;

import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertNotNull;

public class ClassBandsLLM_Test {

    private ClassBands classBands;
    private Segment segment;

    @Before
    public void setUp() {
        segment = new Segment();
        classBands = new ClassBands(segment);
    }

    @Test
    public void testParseFieldAttrBands() throws IOException, Pack200Exception {
        InputStream in = new ByteArrayInputStream(new byte[]{});
        classBands.read(in);
        assertNotNull(classBands.getFieldAttributes());
    }

    @Test
    public void testParseMethodAttrBands() throws IOException, Pack200Exception {
        InputStream in = new ByteArrayInputStream(new byte[]{});
        classBands.read(in);
        assertNotNull(classBands.getMethodAttributes());
    }
}