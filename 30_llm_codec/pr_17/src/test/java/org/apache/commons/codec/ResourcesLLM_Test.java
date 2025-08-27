package org.apache.commons.codec;

import org.junit.Test;
import java.io.InputStream;
import static org.junit.Assert.*;

public class ResourcesLLM_Test {

    @Test
    public void testGetInputStreamValidResource() {
        InputStream inputStream = Resources.getInputStream("validResourceName");
        assertNotNull("InputStream should not be null for a valid resource", inputStream);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetInputStreamInvalidResource() {
        Resources.getInputStream("invalidResourceName");
    }
}