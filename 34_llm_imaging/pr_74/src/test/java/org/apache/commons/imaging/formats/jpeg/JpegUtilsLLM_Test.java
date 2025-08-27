package org.apache.commons.imaging.formats.jpeg;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.internal.Debug;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import static org.mockito.Mockito.*;

public class JpegUtilsLLM_Test {

    @Test
    public void testTraverseJFIF_DebugMarkerCount() throws ImageReadException, IOException {
        // Mock ByteSource and InputStream
        ByteSource byteSource = mock(ByteSource.class);
        InputStream inputStream = mock(InputStream.class);

        // Mock behavior for InputStream
        when(byteSource.getInputStream()).thenReturn(inputStream);
        when(inputStream.read(any(byte[].class), anyInt(), anyInt())).thenAnswer(invocation -> {
            byte[] buffer = invocation.getArgument(0);
            int offset = invocation.getArgument(1);
            int length = invocation.getArgument(2);
            if (length >= 2) {
                buffer[offset] = (byte) 0xff;
                buffer[offset + 1] = (byte) 0xd8; // SOI marker
                return 2;
            }
            return -1;
        });

        // Mock behavior for readByte
        when(inputStream.read()).thenReturn(0xff, 0xd9); // EOI marker

        // Create an instance of JpegUtils
        JpegUtils jpegUtils = new JpegUtils();

        // Create a Visitor
        JpegUtils.Visitor visitor = new JpegUtils.Visitor() {
            @Override
            public boolean beginSOS() {
                return true;
            }

            @Override
            public void visitSOS(int marker, byte[] markerBytes, byte[] imageData) {
                // No-op
            }

            @Override
            public boolean visitSegment(int marker, byte[] markerBytes, int segmentLength, byte[] segmentLengthBytes, byte[] segmentData) {
                return true;
            }
        };

        // Capture debug output
        Debug debug = mock(Debug.class);
        Debug.setDebug(debug);

        // Call traverseJFIF
        jpegUtils.traverseJFIF(byteSource, visitor);

        // Verify that the debug output for marker count is correct
        verify(debug).debug("1 markers");
    }
}