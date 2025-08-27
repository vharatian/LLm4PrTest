package org.apache.commons.imaging.formats.jpeg.xmp;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.common.bytesource.ByteSourceArray;
import org.apache.commons.imaging.common.bytesource.ByteSourceFile;
import org.apache.commons.imaging.common.bytesource.ByteSourceInputStream;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JpegXmpRewriterLLM_Test {

    @Test
    public void testRemoveXmpXml_File_ThrowsImageReadException() throws IOException {
        File mockFile = mock(File.class);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        JpegXmpRewriter rewriter = new JpegXmpRewriter();

        assertThrows(ImageReadException.class, () -> rewriter.removeXmpXml(mockFile, os));
    }

    @Test
    public void testRemoveXmpXml_ByteArray_ThrowsImageReadException() throws IOException {
        byte[] src = new byte[0];
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        JpegXmpRewriter rewriter = new JpegXmpRewriter();

        assertThrows(ImageReadException.class, () -> rewriter.removeXmpXml(src, os));
    }

    @Test
    public void testRemoveXmpXml_InputStream_ThrowsImageReadException() throws IOException {
        ByteArrayInputStream src = new ByteArrayInputStream(new byte[0]);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        JpegXmpRewriter rewriter = new JpegXmpRewriter();

        assertThrows(ImageReadException.class, () -> rewriter.removeXmpXml(src, os));
    }

    @Test
    public void testRemoveXmpXml_ByteSource_ThrowsImageReadException() throws IOException {
        ByteSource byteSource = new ByteSourceArray(new byte[0]);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        JpegXmpRewriter rewriter = new JpegXmpRewriter();

        assertThrows(ImageReadException.class, () -> rewriter.removeXmpXml(byteSource, os));
    }

    @Test
    public void testUpdateXmpXml_ByteArray_ThrowsImageReadException() throws IOException, ImageWriteException {
        byte[] src = new byte[0];
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        String xmpXml = "<xmp></xmp>";
        JpegXmpRewriter rewriter = new JpegXmpRewriter();

        assertThrows(ImageReadException.class, () -> rewriter.updateXmpXml(src, os, xmpXml));
    }

    @Test
    public void testUpdateXmpXml_InputStream_ThrowsImageReadException() throws IOException, ImageWriteException {
        ByteArrayInputStream src = new ByteArrayInputStream(new byte[0]);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        String xmpXml = "<xmp></xmp>";
        JpegXmpRewriter rewriter = new JpegXmpRewriter();

        assertThrows(ImageReadException.class, () -> rewriter.updateXmpXml(src, os, xmpXml));
    }

    @Test
    public void testUpdateXmpXml_File_ThrowsImageReadException() throws IOException, ImageWriteException {
        File mockFile = mock(File.class);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        String xmpXml = "<xmp></xmp>";
        JpegXmpRewriter rewriter = new JpegXmpRewriter();

        assertThrows(ImageReadException.class, () -> rewriter.updateXmpXml(mockFile, os, xmpXml));
    }

    @Test
    public void testUpdateXmpXml_ByteSource_ThrowsImageReadException() throws IOException, ImageWriteException {
        ByteSource byteSource = new ByteSourceArray(new byte[0]);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        String xmpXml = "<xmp></xmp>";
        JpegXmpRewriter rewriter = new JpegXmpRewriter();

        assertThrows(ImageReadException.class, () -> rewriter.updateXmpXml(byteSource, os, xmpXml));
    }
}