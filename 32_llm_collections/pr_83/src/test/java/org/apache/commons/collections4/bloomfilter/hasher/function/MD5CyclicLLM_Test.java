package org.apache.commons.collections4.bloomfilter.hasher.function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5CyclicLLM_Test {

    private MD5Cyclic md5Cyclic;

    @BeforeEach
    public void setUp() {
        md5Cyclic = new MD5Cyclic();
    }

    @Test
    public void testApplyWithSeedZero() {
        byte[] buffer = "test".getBytes();
        long result = md5Cyclic.apply(buffer, 0);
        
        // Manually compute expected result
        byte[] hash;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            synchronized (messageDigest) {
                messageDigest.update(buffer);
                hash = messageDigest.digest();
                messageDigest.reset();
            }
            LongBuffer lb = ByteBuffer.wrap(hash).asLongBuffer();
            long expected = lb.get(0);
            assertEquals(expected, result);
        } catch (NoSuchAlgorithmException e) {
            fail("MD5 algorithm not found");
        }
    }

    @Test
    public void testApplyWithNonZeroSeed() {
        byte[] buffer = "test".getBytes();
        md5Cyclic.apply(buffer, 0);
        long result = md5Cyclic.apply(buffer, 1);
        
        // Manually compute expected result
        byte[] hash;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            synchronized (messageDigest) {
                messageDigest.update(buffer);
                hash = messageDigest.digest();
                messageDigest.reset();
            }
            LongBuffer lb = ByteBuffer.wrap(hash).asLongBuffer();
            long expected = lb.get(0) + lb.get(1);
            assertEquals(expected, result);
        } catch (NoSuchAlgorithmException e) {
            fail("MD5 algorithm not found");
        }
    }

    @Test
    public void testGetName() {
        assertEquals("MD5", md5Cyclic.getName());
    }

    @Test
    public void testGetProvider() {
        assertEquals("Apache Commons Collections", md5Cyclic.getProvider());
    }

    @Test
    public void testGetSignedness() {
        assertEquals(HashFunction.Signedness.SIGNED, md5Cyclic.getSignedness());
    }

    @Test
    public void testGetProcessType() {
        assertEquals(HashFunction.ProcessType.CYCLIC, md5Cyclic.getProcessType());
    }

    @Test
    public void testGetSignature() {
        long signature = md5Cyclic.getSignature();
        assertEquals(signature, md5Cyclic.getSignature());
    }
}