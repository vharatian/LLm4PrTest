package org.apache.commons.compress.harmony.unpack200.bytecode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExceptionsAttributeLLM_Test {

    @Test
    public void testGetNestedClassFileEntries() {
        // Setup
        CPClass[] exceptions = {new CPClass("Exception1"), new CPClass("Exception2")};
        ExceptionsAttribute exceptionsAttribute = new ExceptionsAttribute(exceptions);
        CPUTF8 attributeName = new CPUTF8("Exceptions");
        ExceptionsAttribute.setAttributeName(attributeName);

        // Resolve the pool to set exceptionIndexes
        ClassConstantPool pool = new ClassConstantPool();
        exceptionsAttribute.resolve(pool);

        // Execute
        ClassFileEntry[] nestedEntries = exceptionsAttribute.getNestedClassFileEntries();

        // Verify
        assertEquals(exceptions.length + 1, nestedEntries.length);
        for (int i = 0; i < exceptions.length; i++) {
            assertEquals(exceptions[i], nestedEntries[i]);
        }
        assertEquals(attributeName, nestedEntries[exceptions.length]);
    }
}