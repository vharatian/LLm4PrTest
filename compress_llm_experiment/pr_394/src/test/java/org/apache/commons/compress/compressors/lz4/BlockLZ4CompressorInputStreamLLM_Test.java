package org.apache.commons.compress.compressors.lz4;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.jupiter.api.Test;

public class BlockLZ4CompressorInputStreamLLM_Test extends AbstractTestCase {

    @Test
    public void testInitializeBackReferenceThrowsIOException() throws IOException {
        final File input = getFile("corrupt.block_lz4");
        try (InputStream is = Files.newInputStream(input.toPath());
             BlockLZ4CompressorInputStream in = new BlockLZ4CompressorInputStream(is)) {
            // Read the stream to the point where it should initialize a back reference
            byte[] buffer = new byte[1024];
            while (in.read(buffer) != -1) {
                // Keep reading until we hit the problematic part
            }
            // We expect an IOException due to the corrupt input
            assertThrows(IOException.class, () -> in.read(buffer));
        }
    }
}