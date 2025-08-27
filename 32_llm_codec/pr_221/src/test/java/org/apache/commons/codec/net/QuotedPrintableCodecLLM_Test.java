package org.apache.commons.codec.net;

import static org.junit.jupiter.api.Assertions.assertNull;
import org.apache.commons.codec.DecoderException;
import org.junit.jupiter.api.Test;

public class QuotedPrintableCodecLLM_Test {

    @Test
    public void testEncodeQuotedPrintableWithStrictAndShortInput() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec(true);
        final byte[] shortInput = new byte[] {0x01, 0x02}; // Less than MIN_BYTES
        final byte[] result = QuotedPrintableCodec.encodeQuotedPrintable(null, shortInput, true);
        assertNull(result, "Encoding with strict mode and short input should return null");
    }
}