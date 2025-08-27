package org.apache.commons.imaging.common;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class XmpImagingParametersLLM_Test {

    @Test
    public void testGetXmpXml() {
        XmpImagingParameters params = new XmpImagingParameters();
        params.setXmpXml("testXml");
        assertEquals("testXml", params.getXmpXml());
    }

    @Test
    public void testSetXmpXml() {
        XmpImagingParameters params = new XmpImagingParameters();
        params.setXmpXml("testXml");
        assertEquals("testXml", params.getXmpXml());
    }

    @Test
    public void testDefaultXmpXml() {
        XmpImagingParameters params = new XmpImagingParameters();
        assertNull(params.getXmpXml());
    }
}