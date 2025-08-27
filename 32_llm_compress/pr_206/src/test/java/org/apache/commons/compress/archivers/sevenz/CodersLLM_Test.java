package org.apache.commons.compress.archivers.sevenz;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class CodersLLM_Test {

    @Test
    void testCoderMapInitialization() {
        Map<SevenZMethod, CoderBase> expectedCoderMap = new HashMap<>();
        expectedCoderMap.put(SevenZMethod.COPY, new Coders.CopyDecoder());
        expectedCoderMap.put(SevenZMethod.LZMA, new LZMADecoder());
        expectedCoderMap.put(SevenZMethod.LZMA2, new LZMA2Decoder());
        expectedCoderMap.put(SevenZMethod.DEFLATE, new Coders.DeflateDecoder());
        expectedCoderMap.put(SevenZMethod.DEFLATE64, new Coders.Deflate64Decoder());
        expectedCoderMap.put(SevenZMethod.BZIP2, new Coders.BZIP2Decoder());
        expectedCoderMap.put(SevenZMethod.AES256SHA256, new AES256SHA256Decoder());
        expectedCoderMap.put(SevenZMethod.BCJ_X86_FILTER, new Coders.BCJDecoder(new X86Options()));
        expectedCoderMap.put(SevenZMethod.BCJ_PPC_FILTER, new Coders.BCJDecoder(new PowerPCOptions()));
        expectedCoderMap.put(SevenZMethod.BCJ_IA64_FILTER, new Coders.BCJDecoder(new IA64Options()));
        expectedCoderMap.put(SevenZMethod.BCJ_ARM_FILTER, new Coders.BCJDecoder(new ARMOptions()));
        expectedCoderMap.put(SevenZMethod.BCJ_ARM_THUMB_FILTER, new Coders.BCJDecoder(new ARMThumbOptions()));
        expectedCoderMap.put(SevenZMethod.BCJ_SPARC_FILTER, new Coders.BCJDecoder(new SPARCOptions()));
        expectedCoderMap.put(SevenZMethod.DELTA_FILTER, new DeltaDecoder());

        for (Map.Entry<SevenZMethod, CoderBase> entry : expectedCoderMap.entrySet()) {
            assertEquals(entry.getValue().getClass(), Coders.findByMethod(entry.getKey()).getClass());
        }
    }

    @Test
    void testFindByMethodUnsupportedMethod() {
        assertNull(Coders.findByMethod(SevenZMethod.byId(new byte[]{0x00})));
    }

    @Test
    void testAddDecoderUnsupportedMethod() {
        Coder unsupportedCoder = new Coder();
        unsupportedCoder.decompressionMethodId = new byte[]{0x00};
        assertThrows(IOException.class, () -> Coders.addDecoder("archive", null, 0, unsupportedCoder, null, 0));
    }

    @Test
    void testAddEncoderUnsupportedMethod() {
        assertThrows(IOException.class, () -> Coders.addEncoder(null, SevenZMethod.byId(new byte[]{0x00}), null));
    }
}