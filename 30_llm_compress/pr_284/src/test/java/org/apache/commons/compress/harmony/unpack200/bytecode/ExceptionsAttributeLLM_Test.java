package org.apache.commons.compress.harmony.unpack200.bytecode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExceptionsAttributeLLM_Test {

    @Test
    public void testToString() {
        CPClass[] exceptions = {new CPClass("Exception1"), new CPClass("Exception2")};
        ExceptionsAttribute attribute = new ExceptionsAttribute(exceptions);
        String expected = "Exceptions: Exception1 Exception2 ";
        assertEquals(expected, attribute.toString());
    }
}