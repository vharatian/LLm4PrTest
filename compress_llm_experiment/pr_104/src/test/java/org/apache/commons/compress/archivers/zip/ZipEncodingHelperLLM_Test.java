package org.apache.commons.compress.archivers.zip;

import org.junit.jupiter.api.Test;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import static org.junit.jupiter.api.Assertions.*;

public class ZipEncodingHelperLLM_Test {

    @Test
    public void testGetZipEncodingWithNullName() {
        ZipEncoding encoding = ZipEncodingHelper.getZipEncoding(null);
        assertNotNull(encoding);
        assertTrue(encoding instanceof NioZipEncoding);
    }

    @Test
    public void testGetZipEncodingWithValidName() {
        ZipEncoding encoding = ZipEncodingHelper.getZipEncoding("UTF-8");
        assertNotNull(encoding);
        assertTrue(encoding instanceof NioZipEncoding);
    }

    @Test
    public void testGetZipEncodingWithInvalidName() {
        ZipEncoding encoding = ZipEncodingHelper.getZipEncoding("INVALID_CHARSET");
        assertNotNull(encoding);
        assertTrue(encoding instanceof NioZipEncoding);
        assertEquals(Charset.defaultCharset(), ((NioZipEncoding) encoding).getCharset());
    }

    @Test
    public void testIsUTF8WithNull() {
        assertTrue(ZipEncodingHelper.isUTF8(null));
    }

    @Test
    public void testIsUTF8WithUTF8Name() {
        assertTrue(ZipEncodingHelper.isUTF8("UTF-8"));
    }

    @Test
    public void testIsUTF8WithAlias() {
        for (String alias : Charset.forName("UTF-8").aliases()) {
            assertTrue(ZipEncodingHelper.isUTF8(alias));
        }
    }

    @Test
    public void testIsUTF8WithNonUTF8Name() {
        assertFalse(ZipEncodingHelper.isUTF8("ISO-8859-1"));
    }

    @Test
    public void testGrowBufferBy() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 1);
        ByteBuffer grownBuffer = ZipEncodingHelper.growBufferBy(buffer, 10);
        assertEquals(20, grownBuffer.capacity());
        assertEquals(1, grownBuffer.position());
    }
}