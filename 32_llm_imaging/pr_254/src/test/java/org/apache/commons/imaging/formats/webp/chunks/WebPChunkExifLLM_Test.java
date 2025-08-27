package org.apache.commons.imaging.formats.webp.chunks;

import org.apache.commons.imaging.ImagingException;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.formats.tiff.TiffImageMetadata;
import org.apache.commons.imaging.formats.tiff.TiffImageParser;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

public class WebPChunkExifLLM_Test {

    @Test
    public void testWebPChunkExifCreation() throws ImagingException {
        int type = 1;
        int size = 10;
        byte[] bytes = new byte[size];
        WebPChunkExif chunk = new WebPChunkExif(type, size, bytes);
        assertNotNull(chunk);
    }

    @Test
    public void testDump() throws ImagingException, IOException {
        int type = 1;
        int size = 10;
        byte[] bytes = new byte[size];
        WebPChunkExif chunk = new WebPChunkExif(type, size, bytes);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintWriter pw = new PrintWriter(baos);
        chunk.dump(pw, 0);
        pw.flush();

        String output = baos.toString();
        assertNotNull(output);
        assertFalse(output.isEmpty());
    }

    @Test
    public void testInvalidSize() {
        int type = 1;
        int size = 10;
        byte[] bytes = new byte[size - 1];

        assertThrows(ImagingException.class, () -> {
            new WebPChunkExif(type, size, bytes);
        });
    }
}