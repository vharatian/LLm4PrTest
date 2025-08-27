package org.apache.commons.io.build;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import org.junit.jupiter.api.Test;

public abstract class AbstractOriginLLM_Test<T, B extends AbstractOrigin<T, B>> extends AbstractOriginTest<T, B> {

    @Test
    public void testCharSequenceOriginGetReader() throws IOException {
        // Setup a CharSequenceOrigin instance
        CharSequenceOrigin charSequenceOrigin = new CharSequenceOrigin("Test CharSequence");
        
        // Get the reader using the modified getReader method
        try (Reader reader = charSequenceOrigin.getReader(Charset.defaultCharset())) {
            assertNotNull(reader);
        }
    }
}