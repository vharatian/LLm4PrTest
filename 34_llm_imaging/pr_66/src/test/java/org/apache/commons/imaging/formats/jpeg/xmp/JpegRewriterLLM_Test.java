package org.apache.commons.imaging.formats.jpeg.xmp;

import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.formats.jpeg.JpegConstants;
import org.apache.commons.imaging.formats.jpeg.xmp.JpegRewriter.JFIFPiece;
import org.apache.commons.imaging.formats.jpeg.xmp.JpegRewriter.JFIFPieceSegment;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class JpegRewriterLLM_Test {

    @Test
    public void testInsertAfterLastAppSegmentsWithEmptySegments() {
        JpegRewriter jpegRewriter = new JpegRewriter();
        List<JFIFPiece> segments = new ArrayList<>();
        List<JFIFPiece> newSegments = new ArrayList<>();

        assertThrows(ImageWriteException.class, () -> {
            jpegRewriter.insertAfterLastAppSegments(segments, newSegments);
        });
    }

    @Test
    public void testInsertAfterLastAppSegmentsWithNonEmptySegments() throws ImageWriteException {
        JpegRewriter jpegRewriter = new JpegRewriter();
        List<JFIFPiece> segments = new ArrayList<>();
        List<JFIFPiece> newSegments = new ArrayList<>();

        // Adding a dummy segment to avoid the exception
        segments.add(new JFIFPieceSegment(JpegConstants.JPEG_APP0_MARKER, new byte[0]));

        List<JFIFPiece> result = jpegRewriter.insertAfterLastAppSegments(segments, newSegments);
        // Check that the result contains the original segment plus the new segments
        assert(result.size() == segments.size() + newSegments.size());
    }
}