package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class IOUtilsLLM_Test {

    @Test
    public void testByteArrayNegativeSize() {
        assertThrows(NegativeArraySizeException.class, () -> IOUtils.byteArray(-1));
    }

    @Test
    public void testDeprecatedConstructor() {
        assertDoesNotThrow(() -> new IOUtils());
    }
}