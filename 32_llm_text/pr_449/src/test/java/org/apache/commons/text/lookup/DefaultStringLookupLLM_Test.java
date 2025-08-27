package org.apache.commons.text.lookup;

import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;

public class DefaultStringLookupLLM_Test {

    @Test
    public void testNewEnums() {
        // Test for XML_DECODER
        assertSame(DefaultStringLookup.XML_DECODER.getStringLookup(),
            StringLookupFactory.INSTANCE.xmlDecoderStringLookup());

        // Test for XML_ENCODER
        assertSame(DefaultStringLookup.XML_ENCODER.getStringLookup(),
            StringLookupFactory.INSTANCE.xmlEncoderStringLookup());
    }
}