package org.apache.commons.compress.compressors.bzip2;

import static org.apache.commons.compress.AbstractTestCase.getFile;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Assert;
import org.junit.Test;

public class BZip2CompressorInputStreamLLM_Test {

    @Test
    public void testNSelectorsOverflow() throws Exception {
        // Create a BZip2 compressed stream with nSelectors > MAX_SELECTORS
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

        // Manually corrupt the stream to have nSelectors > MAX_SELECTORS
        byte[] compressedData = baos.toByteArray();
        compressedData[10] = (byte) (BZip2Constants.MAX_SELECTORS + 1);

        final ByteArrayInputStream bais = new ByteArrayInputStream(compressedData);
        final BZip2CompressorInputStream bzipIn = new BZip2CompressorInputStream(bais);

        // Read the data and ensure no exception is thrown
        final byte[] buffer = new byte[1024];
        while (bzipIn.read(buffer) != -1) {
            // Do nothing, just read through the stream
        }

        bzipIn.close();
    }

    @Test
    public void testNSelectorsWithinBounds() throws Exception {
        // Create a BZip2 compressed stream with nSelectors within bounds
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

        // Read the data and ensure no exception is thrown
        final byte[] buffer = new byte[1024];
        while (bzipIn.read(buffer) != -1) {
            // Do nothing, just read through the stream
        }

        bzipIn.close();
    }
}