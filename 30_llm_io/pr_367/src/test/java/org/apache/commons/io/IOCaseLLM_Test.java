package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.jupiter.api.Test;

public class IOCaseLLM_Test {

    private static final boolean WINDOWS = File.separatorChar == '\\';

    private IOCase serialize(final IOCase value) throws Exception {
        final ByteArrayOutputStream buf = new ByteArrayOutputStream();
        try (final ObjectOutputStream out = new ObjectOutputStream(buf)) {
            out.writeObject(value);
            out.flush();
        }
        final ByteArrayInputStream bufin = new ByteArrayInputStream(buf.toByteArray());
        final ObjectInputStream in = new ObjectInputStream(bufin);
        return (IOCase) in.readObject();
    }

    @Test
    public void test_isCaseSensitive_javadoc() {
        // Testing the javadoc changes for isCaseSensitive method
        assertTrue(IOCase.SENSITIVE.isCaseSensitive(), "SENSITIVE should be case-sensitive");
        assertFalse(IOCase.INSENSITIVE.isCaseSensitive(), "INSENSITIVE should not be case-sensitive");
        assertEquals(!WINDOWS, IOCase.SYSTEM.isCaseSensitive(), "SYSTEM should be case-sensitive on Unix and case-insensitive on Windows");
    }

    @Test
    public void test_enumConstants_javadoc() {
        // Testing the javadoc changes for enum constants
        assertEquals("Sensitive", IOCase.SENSITIVE.getName(), "SENSITIVE name should be 'Sensitive'");
        assertEquals("Insensitive", IOCase.INSENSITIVE.getName(), "INSENSITIVE name should be 'Insensitive'");
        assertEquals("System", IOCase.SYSTEM.getName(), "SYSTEM name should be 'System'");
    }
}