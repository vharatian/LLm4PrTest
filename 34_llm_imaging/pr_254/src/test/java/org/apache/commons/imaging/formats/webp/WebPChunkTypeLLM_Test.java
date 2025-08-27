package org.apache.commons.imaging.formats.webp;

import org.apache.commons.imaging.ImagingException;
import org.apache.commons.imaging.formats.webp.chunks.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class WebPChunkTypeLLM_Test {

    @Test
    public void testFindType() {
        assertEquals(WebPChunkType.ALPH, WebPChunkType.findType(WebPChunkType.ALPH.value));
        assertEquals(WebPChunkType.VP8, WebPChunkType.findType(WebPChunkType.VP8.value));
        assertEquals(WebPChunkType.VP8L, WebPChunkType.findType(WebPChunkType.VP8L.value));
        assertEquals(WebPChunkType.VP8X, WebPChunkType.findType(WebPChunkType.VP8X.value));
        assertEquals(WebPChunkType.ANIM, WebPChunkType.findType(WebPChunkType.ANIM.value));
        assertEquals(WebPChunkType.ANMF, WebPChunkType.findType(WebPChunkType.ANMF.value));
        assertEquals(WebPChunkType.ICCP, WebPChunkType.findType(WebPChunkType.ICCP.value));
        assertEquals(WebPChunkType.EXIF, WebPChunkType.findType(WebPChunkType.EXIF.value));
        assertEquals(WebPChunkType.XMP, WebPChunkType.findType(WebPChunkType.XMP.value));
        assertNull(WebPChunkType.findType(0));
    }

    @Test
    public void testMakeChunk() throws IOException, ImagingException {
        byte[] dummyBytes = new byte[10];
        WebPChunk chunk = WebPChunkType.makeChunk(WebPChunkType.ALPH.value, 10, dummyBytes);
        assertTrue(chunk instanceof WebPChunkAlph);

        chunk = WebPChunkType.makeChunk(WebPChunkType.VP8.value, 10, dummyBytes);
        assertTrue(chunk instanceof WebPChunkVp8);

        chunk = WebPChunkType.makeChunk(WebPChunkType.VP8L.value, 10, dummyBytes);
        assertTrue(chunk instanceof WebPChunkVp8l);

        chunk = WebPChunkType.makeChunk(WebPChunkType.VP8X.value, 10, dummyBytes);
        assertTrue(chunk instanceof WebPChunkVp8x);

        chunk = WebPChunkType.makeChunk(WebPChunkType.ANIM.value, 10, dummyBytes);
        assertTrue(chunk instanceof WebPChunkAnim);

        chunk = WebPChunkType.makeChunk(WebPChunkType.ANMF.value, 10, dummyBytes);
        assertTrue(chunk instanceof WebPChunkAnmf);

        chunk = WebPChunkType.makeChunk(WebPChunkType.ICCP.value, 10, dummyBytes);
        assertTrue(chunk instanceof WebPChunkIccp);

        chunk = WebPChunkType.makeChunk(WebPChunkType.EXIF.value, 10, dummyBytes);
        assertTrue(chunk instanceof WebPChunkExif);

        chunk = WebPChunkType.makeChunk(WebPChunkType.XMP.value, 10, dummyBytes);
        assertTrue(chunk instanceof WebPChunkXml);

        chunk = WebPChunkType.makeChunk(0, 10, dummyBytes);
        assertTrue(chunk instanceof WebPChunkXyzw);
    }
}