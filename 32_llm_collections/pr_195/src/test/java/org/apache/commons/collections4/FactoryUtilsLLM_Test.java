package org.apache.commons.collections4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.TimeZone;
import org.apache.commons.collections4.functors.ConstantFactory;
import org.apache.commons.collections4.functors.ExceptionFactory;
import org.junit.Test;

public class FactoryUtilsLLM_Test {

    @Test
    public void testPrivateConstructor() {
        try {
            FactoryUtils.class.getDeclaredConstructor().setAccessible(true);
            FactoryUtils.class.getDeclaredConstructor().newInstance();
            fail("Expected an exception to be thrown");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalAccessException || e.getCause() instanceof IllegalAccessException);
        }
    }
}