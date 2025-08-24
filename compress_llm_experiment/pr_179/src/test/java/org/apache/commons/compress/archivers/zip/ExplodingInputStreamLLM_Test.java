package org.apache.commons.compress.archivers.zip;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.utils.CountingInputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExplodingInputStreamLLM_Test {

    private ExplodingInputStream explodingInputStream;

    @BeforeEach
    void setUp() {
        InputStream in = new ByteArrayInputStream(new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
        explodingInputStream = new ExplodingInputStream(4096, 2, in);
    }

    @Test
    void testUncompressedCountInitialization() {
        assertEquals(0, explodingInputStream.getUncompressedCount(), "Uncompressed count should be initialized to 0");
    }

    @Test
    void testTreeSizesInitialization() {
        assertEquals(0, explodingInputStream.getCompressedCount(), "Tree sizes should be initialized to 0");
    }

    @Test
    void testReadIncrementsUncompressedCount() throws IOException {
        explodingInputStream.read();
        assertEquals(1, explodingInputStream.getUncompressedCount(), "Uncompressed count should be incremented after read");
    }

    @Test
    void testClose() throws IOException {
        explodingInputStream.close();
        assertThrows(IOException.class, () -> explodingInputStream.read(), "Stream should be closed");
    }
}