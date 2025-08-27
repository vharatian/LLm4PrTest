package org.apache.commons.compress.compressors;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.pack200.Pack200CompressorInputStream;
import org.apache.commons.compress.compressors.snappy.FramedSnappyCompressorInputStream;
import org.apache.commons.compress.compressors.z.ZCompressorInputStream;
import org.apache.commons.compress.compressors.deflate.DeflateCompressorInputStream;
import org.apache.commons.compress.compressors.xz.XZUtils;
import org.apache.commons.compress.compressors.lzma.LZMAUtils;
import org.apache.commons.compress.compressors.lz4.FramedLZ4CompressorInputStream;
import org.apache.commons.compress.compressors.zstandard.ZstdUtils;
import org.apache.commons.compress.utils.Sets;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CompressorStreamFactoryLLM_Test {

    @Test
    public void testDetectWithLimitedCompressorNames() throws Exception {
        byte[] bzip2Signature = new byte[]{'B', 'Z', 'h'};
        InputStream bzip2Stream = new ByteArrayInputStream(bzip2Signature);
        bzip2Stream.mark(bzip2Signature.length);

        Set<String> compressorNames = Sets.newHashSet(CompressorStreamFactory.BZIP2, CompressorStreamFactory.GZIP);
        String detected = CompressorStreamFactory.detect(bzip2Stream, compressorNames);
        assertEquals(CompressorStreamFactory.BZIP2, detected);
    }

    @Test
    public void testDetectWithEmptyCompressorNames() {
        byte[] bzip2Signature = new byte[]{'B', 'Z', 'h'};
        InputStream bzip2Stream = new ByteArrayInputStream(bzip2Signature);
        bzip2Stream.mark(bzip2Signature.length);

        Set<String> compressorNames = Sets.newHashSet();
        Executable executable = () -> CompressorStreamFactory.detect(bzip2Stream, compressorNames);
        assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    public void testCreateCompressorInputStreamWithLimitedCompressorNames() throws Exception {
        byte[] gzipSignature = new byte[]{(byte) 0x1f, (byte) 0x8b, (byte) 0x08};
        InputStream gzipStream = new ByteArrayInputStream(gzipSignature);
        gzipStream.mark(gzipSignature.length);

        Set<String> compressorNames = Sets.newHashSet(CompressorStreamFactory.GZIP, CompressorStreamFactory.BZIP2);
        CompressorInputStream cis = new CompressorStreamFactory().createCompressorInputStream(gzipStream, compressorNames);
        assertTrue(cis instanceof GzipCompressorInputStream);
    }
}