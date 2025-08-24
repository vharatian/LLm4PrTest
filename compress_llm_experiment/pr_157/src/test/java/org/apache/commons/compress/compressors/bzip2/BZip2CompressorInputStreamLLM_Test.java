package org.apache.commons.compress.compressors.bzip2;

import static org.apache.commons.compress.AbstractTestCase.getFile;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Assert;
import org.junit.Test;

public class BZip2CompressorInputStreamLLM_Test {

    @Test
    public void testFinalByteArrayInitialization() throws Exception {
        final byte[] rawData = new byte[1048576];
        for (int i = 0; i < rawData.length; ++i) {
            rawData[i] = (byte) Math.floor(Math.random() * 256);
        }
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BZip2CompressorOutputStream bzipOut = new BZip2CompressorOutputStream(baos);
        bzipOut.write(rawData);
        bzipOut.flush();
        bzipOut.close();
        baos.flush();
        baos.close();
        final ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        final BZip2CompressorInputStream bzipIn = new BZip2CompressorInputStream(bais);
        final byte[] buffer = new byte[1024];
        Assert.assertEquals(1024, bzipIn.read(buffer, 0, 1024));
        Assert.assertEquals(0, bzipIn.read(buffer, 1024, 0));
        Assert.assertEquals(1024, bzipIn.read(buffer, 0, 1024));
        bzipIn.close();
    }

    @Test
    public void testReadAfterClose() throws Exception {
        final byte[] rawData = new byte[1048576];
        for (int i = 0; i < rawData.length; ++i) {
            rawData[i] = (byte) Math.floor(Math.random() * 256);
        }
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BZip2CompressorOutputStream bzipOut = new BZip2CompressorOutputStream(baos);
        bzipOut.write(rawData);
        bzipOut.flush();
        bzipOut.close();
        baos.flush();
        baos.close();
        final ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        final BZip2CompressorInputStream bzipIn = new BZip2CompressorInputStream(bais);
        bzipIn.close();
        try {
            bzipIn.read();
            Assert.fail("Expected IOException");
        } catch (IOException e) {
            Assert.assertEquals("Stream closed", e.getMessage());
        }
    }
}