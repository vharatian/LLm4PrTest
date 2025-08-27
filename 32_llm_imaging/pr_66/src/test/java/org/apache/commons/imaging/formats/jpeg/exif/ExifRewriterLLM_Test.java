package org.apache.commons.imaging.formats.jpeg.exif;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.common.bytesource.ByteSourceArray;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputSet;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExifRewriterLLM_Test {

    @Test
    public void testUpdateExifMetadataLosslessWithEmptyExifPieces() throws ImageReadException, IOException, ImageWriteException {
        ExifRewriter exifRewriter = new ExifRewriter(ByteOrder.BIG_ENDIAN);
        ByteSource byteSource = new ByteSourceArray(new byte[]{});
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        TiffOutputSet outputSet = new TiffOutputSet(ByteOrder.BIG_ENDIAN);

        ExifRewriter.JFIFPieces jfifPieces = new ExifRewriter.JFIFPieces(new ArrayList<>(), new ArrayList<>());
        List<ExifRewriter.JFIFPiece> pieces = jfifPieces.pieces;

        TiffImageWriterBase writer = new TiffImageWriterLossy(outputSet.byteOrder);
        byte[] newBytes = exifRewriter.writeExifSegment(writer, outputSet, true);

        exifRewriter.writeSegmentsReplacingExif(os, pieces, newBytes);

        assertEquals(0, jfifPieces.exifPieces.size());
    }

    @Test
    public void testUpdateExifMetadataLosslessWithNonEmptyExifPieces() throws ImageReadException, IOException, ImageWriteException {
        ExifRewriter exifRewriter = new ExifRewriter(ByteOrder.BIG_ENDIAN);
        ByteSource byteSource = new ByteSourceArray(new byte[]{});
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        TiffOutputSet outputSet = new TiffOutputSet(ByteOrder.BIG_ENDIAN);

        byte[] segmentData = new byte[]{1, 2, 3, 4, 5, 6};
        ExifRewriter.JFIFPieceSegmentExif exifPiece = new ExifRewriter.JFIFPieceSegmentExif(0xE1, new byte[]{(byte) 0xFF, (byte) 0xE1}, new byte[]{0, 6}, segmentData);
        List<ExifRewriter.JFIFPiece> pieces = new ArrayList<>();
        pieces.add(exifPiece);
        List<ExifRewriter.JFIFPiece> exifPieces = new ArrayList<>();
        exifPieces.add(exifPiece);
        ExifRewriter.JFIFPieces jfifPieces = new ExifRewriter.JFIFPieces(pieces, exifPieces);

        TiffImageWriterBase writer = new TiffImageWriterLossless(outputSet.byteOrder, segmentData);
        byte[] newBytes = exifRewriter.writeExifSegment(writer, outputSet, true);

        exifRewriter.writeSegmentsReplacingExif(os, pieces, newBytes);

        assertEquals(1, jfifPieces.exifPieces.size());
    }

    @Test
    public void testUpdateExifMetadataLosslessWithException() throws ImageReadException, IOException, ImageWriteException {
        ExifRewriter exifRewriter = new ExifRewriter(ByteOrder.BIG_ENDIAN);
        ByteSource byteSource = new ByteSourceArray(new byte[]{});
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        TiffOutputSet outputSet = new TiffOutputSet(ByteOrder.BIG_ENDIAN);

        byte[] segmentData = new byte[0xFFFF + 1];
        ExifRewriter.JFIFPieceSegmentExif exifPiece = new ExifRewriter.JFIFPieceSegmentExif(0xE1, new byte[]{(byte) 0xFF, (byte) 0xE1}, new byte[]{0, 6}, segmentData);
        List<ExifRewriter.JFIFPiece> pieces = new ArrayList<>();
        pieces.add(exifPiece);
        List<ExifRewriter.JFIFPiece> exifPieces = new ArrayList<>();
        exifPieces.add(exifPiece);
        ExifRewriter.JFIFPieces jfifPieces = new ExifRewriter.JFIFPieces(pieces, exifPieces);

        TiffImageWriterBase writer = new TiffImageWriterLossless(outputSet.byteOrder, segmentData);

        assertThrows(ExifRewriter.ExifOverflowException.class, () -> {
            byte[] newBytes = exifRewriter.writeExifSegment(writer, outputSet, true);
            exifRewriter.writeSegmentsReplacingExif(os, pieces, newBytes);
        });
    }
}