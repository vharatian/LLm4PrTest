package org.apache.commons.compress.archivers.sevenz;

import static java.nio.charset.StandardCharsets.UTF_16LE;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.compress.PasswordRequiredException;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.jupiter.api.Test;

public class SevenZFileLLM_Test extends AbstractTestCase {

    @Test
    public void testUtf16DecodeWithPassword() throws Exception {
        // Test the utf16Decode method from AES256SHA256Decoder class
        char[] password = "testPassword".toCharArray();
        byte[] expected = "testPassword".getBytes(UTF_16LE);
        byte[] actual = AES256SHA256Decoder.utf16Decode(password);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSevenZFileWithCharPassword() throws Exception {
        // Test the constructor that uses AES256SHA256Decoder.utf16Decode
        File file = getFile("bla.encrypted.7z");
        char[] password = "foo".toCharArray();
        try (SevenZFile sevenZFile = new SevenZFile(file, password)) {
            assertNotNull(sevenZFile);
        }
    }

    @Test
    public void testSevenZFileWithCharPasswordAndOptions() throws Exception {
        // Test the constructor that uses AES256SHA256Decoder.utf16Decode with options
        File file = getFile("bla.encrypted.7z");
        char[] password = "foo".toCharArray();
        SevenZFileOptions options = SevenZFileOptions.builder().withTryToRecoverBrokenArchives(true).build();
        try (SevenZFile sevenZFile = new SevenZFile(file, password, options)) {
            assertNotNull(sevenZFile);
        }
    }

    @Test
    public void testEncryptedArchiveRequiresPasswordWithCharArray() throws Exception {
        // Test that an encrypted archive requires a password when using char array
        File file = getFile("bla.encrypted.7z");
        char[] password = "wrongPassword".toCharArray();
        try (SevenZFile sevenZFile = new SevenZFile(file, password)) {
            fail("shouldn't decrypt with a wrong password");
        } catch (PasswordRequiredException ex) {
            final String msg = ex.getMessage();
            assertTrue("Should start with whining about being unable to decrypt",
                    msg.startsWith("Cannot read encrypted content from "));
            assertTrue("Should finish the sentence properly",
                    msg.endsWith(" without a password."));
            assertTrue("Should contain archive's name",
                    msg.contains("bla.encrypted.7z"));
        }
    }
}