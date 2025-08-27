package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicReference;
import static org.junit.jupiter.api.Assertions.*;

public class IOUtilsLLM_Test {

    @Test
    public void testSkipByteBufferInitialization() throws Exception {
        // Access the private SKIP_BYTE_BUFFER field
        Method method = IOUtils.class.getDeclaredMethod("getByteArray");
        method.setAccessible(true);

        // Invoke the method and check the result
        byte[] byteArray = (byte[]) method.invoke(null);
        assertNotNull(byteArray);
        assertEquals(IOUtils.DEFAULT_BUFFER_SIZE, byteArray.length);
    }

    @Test
    public void testSkipCharBufferInitialization() throws Exception {
        // Access the private SKIP_CHAR_BUFFER field
        Method method = IOUtils.class.getDeclaredMethod("getCharArray");
        method.setAccessible(true);

        // Invoke the method and check the result
        char[] charArray = (char[]) method.invoke(null);
        assertNotNull(charArray);
        assertEquals(IOUtils.DEFAULT_BUFFER_SIZE, charArray.length);
    }
}