package org.apache.commons.text.lookup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Test class for the DefaultStringLookup enum.
 * This class specifically tests the changes introduced in the latest update.
 */
public class DefaultStringLookupLLM_Test {

    /**
     * Tests that the keys of the DefaultStringLookup enum match the expected values.
     */
    @Test
    public void testEnumKeys() {
        assertEquals("base64Decoder", DefaultStringLookup.BASE64_DECODER.getKey());
        assertEquals("base64Encoder", DefaultStringLookup.BASE64_ENCODER.getKey());
        assertEquals("const", DefaultStringLookup.CONST.getKey());
        assertEquals("date", DefaultStringLookup.DATE.getKey());
        assertEquals("dns", DefaultStringLookup.DNS.getKey());
        assertEquals("env", DefaultStringLookup.ENVIRONMENT.getKey());
        assertEquals("file", DefaultStringLookup.FILE.getKey());
        assertEquals("java", DefaultStringLookup.JAVA.getKey());
        assertEquals("localhost", DefaultStringLookup.LOCAL_HOST.getKey());
        assertEquals("properties", DefaultStringLookup.PROPERTIES.getKey());
        assertEquals("resourceBundle", DefaultStringLookup.RESOURCE_BUNDLE.getKey());
        assertEquals("script", DefaultStringLookup.SCRIPT.getKey());
        assertEquals("sys", DefaultStringLookup.SYSTEM_PROPERTIES.getKey());
        assertEquals("url", DefaultStringLookup.URL.getKey());
        assertEquals("urlDecoder", DefaultStringLookup.URL_DECODER.getKey());
        assertEquals("urlEncoder", DefaultStringLookup.URL_ENCODER.getKey());
        assertEquals("xml", DefaultStringLookup.XML.getKey());
    }
}