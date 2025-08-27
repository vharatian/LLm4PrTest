package org.apache.commons.compress.archivers.zip;

import static org.apache.commons.compress.AbstractTestCase.getFile;
import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.util.Collections;
import java.util.Enumeration;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.SeekableInMemoryByteChannel;
import org.junit.After;
import org.junit.Test;

public class ZipFileLLM_Test {
    private ZipFile zf = null;

    @After
    public void tearDown() {
        ZipFile.closeQuietly(zf);
    }

    /**
     * Test to ensure that the new IOException message is thrown correctly
     * when there is an error during the ZipFile initialization.
     */
    @Test
    public void testIOExceptionMessageOnInitialization() {
        try {
            // Attempt to open a non-existent file to trigger an IOException
            zf = new ZipFile(new File("non-existent.zip"));
            fail("Expected IOException to be thrown");
        } catch (IOException e) {
            assertTrue(e.getMessage().contains("Error on ZipFile non-existent.zip"));
        }
    }

    /**
     * Test to ensure that the new IOException message is thrown correctly
     * when there is an error during the ZipFile initialization with a SeekableByteChannel.
     */
    @Test
    public void testIOExceptionMessageOnInitializationWithChannel() {
        try {
            // Attempt to open a non-existent file to trigger an IOException
            SeekableByteChannel channel = new SeekableInMemoryByteChannel(new byte[0]);
            zf = new ZipFile(channel, "non-existent.zip", "UTF-8", true);
            fail("Expected IOException to be thrown");
        } catch (IOException e) {
            assertTrue(e.getMessage().contains("Error on ZipFile non-existent.zip"));
        }
    }
}