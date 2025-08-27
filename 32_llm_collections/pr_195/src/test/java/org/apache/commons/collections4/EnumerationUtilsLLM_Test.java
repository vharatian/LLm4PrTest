package org.apache.commons.collections4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import org.junit.Test;

public class EnumerationUtilsLLM_Test {

    @Test
    public void testPrivateConstructor() {
        try {
            EnumerationUtils.class.getDeclaredConstructor().setAccessible(true);
            EnumerationUtils.class.getDeclaredConstructor().newInstance();
            fail("Expecting IllegalAccessException");
        } catch (IllegalAccessException e) {
            // Expected exception
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
    }
}