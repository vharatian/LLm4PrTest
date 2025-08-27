package org.apache.commons.io.build;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.apache.commons.io.input.CharSequenceInputStream;
import org.junit.jupiter.api.Test;

public class AbstractOriginLLM_Test extends AbstractOriginTest<CharSequence, AbstractOrigin.CharSequenceOrigin> {

    @Test
    public void testCharSequenceOriginGetInputStream() throws IOException {
        // Setup
        CharSequence charSequence = "Test CharSequence";
        AbstractOrigin.CharSequenceOrigin charSequenceOrigin = new AbstractOrigin.CharSequenceOrigin(charSequence);
        setOriginRo(charSequenceOrigin);

        // Test
        try (InputStream inputStream = getOriginRo().getInputStream()) {
            assertNotNull(inputStream);
            // Ensure the InputStream is of type CharSequenceInputStream
            assertEquals(CharSequenceInputStream.class, inputStream.getClass());
        }
    }
}