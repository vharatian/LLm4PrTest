package org.apache.commons.imaging.formats.jpeg.segments;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import static org.junit.jupiter.api.Assertions.*;

public class GenericSegmentLLM_Test {

    @Test
    public void testGetSegmentData() throws IOException {
        byte[] data = {0x01, 0x02, 0x03, 0x04};
        InputStream is = new ByteArrayInputStream(data);
        GenericSegment segment = new GenericSegment(0xff, data.length, is);
        assertArrayEquals(data, segment.getSegmentData());
    }

    @Test
    public void testGetSegmentDataWithOffset() throws IOException {
        byte[] data = {0x01, 0x02, 0x03, 0x04};
        InputStream is = new ByteArrayInputStream(data);
        GenericSegment segment = new GenericSegment(0xff, data.length, is);
        assertEquals(0x02, segment.getSegmentData(1));
    }

    @Test
    public void testGetSegmentDataAsString() throws UnsupportedEncodingException {
        byte[] data = "test".getBytes("UTF-8");
        GenericSegment segment = new GenericSegment(0xff, data);
        assertEquals("test", segment.getSegmentDataAsString("UTF-8"));
    }

    @Test
    public void testGetSegmentDataAsStringUnsupportedEncoding() {
        byte[] data = "test".getBytes();
        GenericSegment segment = new GenericSegment(0xff, data);
        assertThrows(UnsupportedEncodingException.class, () -> {
            segment.getSegmentDataAsString("unsupported-encoding");
        });
    }

    @Test
    public void testDump() throws IOException {
        byte[] data = {0x01, 0x02, 0x03, 0x04};
        InputStream is = new ByteArrayInputStream(data);
        GenericSegment segment = new GenericSegment(0xff, data.length, is);
        PrintWriter pw = new PrintWriter(System.out);
        segment.dump(pw);
        pw.flush();
    }
}