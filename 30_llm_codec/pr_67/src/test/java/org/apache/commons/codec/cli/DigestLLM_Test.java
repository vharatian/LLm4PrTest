package org.apache.commons.codec.cli;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DigestLLM_Test {

    @Test
    public void testConstructorWithNullArgs() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Digest(null);
        });
        assertEquals("args", thrown.getMessage());
    }

    @Test
    public void testConstructorWithEmptyArgs() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Digest(new String[0]);
        });
        assertTrue(thrown.getMessage().contains("Usage: java org.apache.commons.codec.cli.Digest [algorithm] [FILE|DIRECTORY|string] ..."));
    }

    @Test
    public void testConstructorWithSingleArg() {
        Digest digest = new Digest(new String[]{"MD5"});
        assertNotNull(digest);
    }

    @Test
    public void testConstructorWithMultipleArgs() {
        Digest digest = new Digest(new String[]{"MD5", "input1", "input2"});
        assertNotNull(digest);
    }

    @Test
    public void testToString() {
        Digest digest = new Digest(new String[]{"MD5", "input1", "input2"});
        String result = digest.toString();
        assertTrue(result.contains("MD5"));
        assertTrue(result.contains("input1"));
        assertTrue(result.contains("input2"));
    }
}