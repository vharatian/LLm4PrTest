package org.apache.commons.codec.digest;

import org.junit.jupiter.api.Test;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import static org.junit.jupiter.api.Assertions.*;

public class Blake3LLM_Test {

    @Test
    public void testInitHash() {
        Blake3 hasher = Blake3.initHash();
        assertNotNull(hasher);
    }

    @Test
    public void testInitKeyedHash() {
        byte[] key = new byte[32];
        new SecureRandom().nextBytes(key);
        Blake3 hasher = Blake3.initKeyedHash(key);
        assertNotNull(hasher);
    }

    @Test
    public void testInitKeyDerivationFunction() {
        byte[] context = "context".getBytes(StandardCharsets.UTF_8);
        Blake3 kdf = Blake3.initKeyDerivationFunction(context);
        assertNotNull(kdf);
    }

    @Test
    public void testUpdateAndFinalizeHash() {
        Blake3 hasher = Blake3.initHash();
        hasher.update("Hello, world!".getBytes(StandardCharsets.UTF_8));
        byte[] hash = new byte[32];
        hasher.doFinalize(hash);
        assertEquals(32, hash.length);
    }

    @Test
    public void testUpdateAndFinalizeKeyedHash() {
        byte[] key = new byte[32];
        new SecureRandom().nextBytes(key);
        Blake3 hasher = Blake3.initKeyedHash(key);
        hasher.update("Hello, Alice!".getBytes(StandardCharsets.UTF_8));
        byte[] mac = new byte[32];
        hasher.doFinalize(mac);
        assertEquals(32, mac.length);
    }

    @Test
    public void testKeyDerivationFunction() {
        String context = "org.apache.commons.codec.digest.Blake3Example";
        byte[] sharedSecret = "sharedSecret".getBytes(StandardCharsets.UTF_8);
        byte[] senderId = "senderId".getBytes(StandardCharsets.UTF_8);
        byte[] recipientId = "recipientId".getBytes(StandardCharsets.UTF_8);
        Blake3 kdf = Blake3.initKeyDerivationFunction(context.getBytes(StandardCharsets.UTF_8));
        kdf.update(sharedSecret);
        kdf.update(senderId);
        kdf.update(recipientId);
        byte[] txKey = new byte[32];
        byte[] rxKey = new byte[32];
        kdf.doFinalize(txKey);
        kdf.doFinalize(rxKey);
        assertEquals(32, txKey.length);
        assertEquals(32, rxKey.length);
    }

    @Test
    public void testHash() {
        byte[] data = "data".getBytes(StandardCharsets.UTF_8);
        byte[] hash = Blake3.hash(data);
        assertEquals(32, hash.length);
    }

    @Test
    public void testKeyedHash() {
        byte[] key = new byte[32];
        new SecureRandom().nextBytes(key);
        byte[] data = "data".getBytes(StandardCharsets.UTF_8);
        byte[] mac = Blake3.keyedHash(key, data);
        assertEquals(32, mac.length);
    }

    @Test
    public void testDoFinalizeWithLength() {
        Blake3 hasher = Blake3.initHash();
        hasher.update("Hello, world!".getBytes(StandardCharsets.UTF_8));
        byte[] hash = hasher.doFinalize(64);
        assertEquals(64, hash.length);
    }

    @Test
    public void testReset() {
        Blake3 hasher = Blake3.initHash();
        hasher.update("Hello, world!".getBytes(StandardCharsets.UTF_8));
        hasher.reset();
        byte[] hash = new byte[32];
        hasher.doFinalize(hash);
        assertEquals(32, hash.length);
    }

    @Test
    public void testUpdateWithOffsetAndLength() {
        Blake3 hasher = Blake3.initHash();
        byte[] data = "Hello, world!".getBytes(StandardCharsets.UTF_8);
        hasher.update(data, 0, data.length);
        byte[] hash = new byte[32];
        hasher.doFinalize(hash);
        assertEquals(32, hash.length);
    }

    @Test
    public void testDoFinalizeWithOffsetAndLength() {
        Blake3 hasher = Blake3.initHash();
        hasher.update("Hello, world!".getBytes(StandardCharsets.UTF_8));
        byte[] hash = new byte[64];
        hasher.doFinalize(hash, 0, 64);
        assertEquals(64, hash.length);
    }

    @Test
    public void testDoFinalizeWithNegativeBytes() {
        Blake3 hasher = Blake3.initHash();
        hasher.update("Hello, world!".getBytes(StandardCharsets.UTF_8));
        assertThrows(IllegalArgumentException.class, () -> hasher.doFinalize(-1));
    }

    @Test
    public void testInitKeyedHashWithInvalidKeyLength() {
        byte[] key = new byte[16];
        assertThrows(IllegalArgumentException.class, () -> Blake3.initKeyedHash(key));
    }

    @Test
    public void testUpdateWithNull() {
        Blake3 hasher = Blake3.initHash();
        assertThrows(NullPointerException.class, () -> hasher.update(null));
    }

    @Test
    public void testDoFinalizeWithNull() {
        Blake3 hasher = Blake3.initHash();
        assertThrows(NullPointerException.class, () -> hasher.doFinalize(null));
    }

    @Test
    public void testUpdateWithInvalidOffsetAndLength() {
        Blake3 hasher = Blake3.initHash();
        byte[] data = "Hello, world!".getBytes(StandardCharsets.UTF_8);
        assertThrows(IndexOutOfBoundsException.class, () -> hasher.update(data, -1, data.length));
        assertThrows(IndexOutOfBoundsException.class, () -> hasher.update(data, 0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> hasher.update(data, data.length, 1));
    }

    @Test
    public void testDoFinalizeWithInvalidOffsetAndLength() {
        Blake3 hasher = Blake3.initHash();
        byte[] hash = new byte[32];
        assertThrows(IndexOutOfBoundsException.class, () -> hasher.doFinalize(hash, -1, hash.length));
        assertThrows(IndexOutOfBoundsException.class, () -> hasher.doFinalize(hash, 0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> hasher.doFinalize(hash, hash.length, 1));
    }
}