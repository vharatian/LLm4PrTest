package org.apache.commons.codec.binary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BinaryCodecLLM_Test {
    private static final Charset CHARSET_UTF8 = StandardCharsets.UTF_8;
    private static final int BIT_0 = 0x01;
    private static final int BIT_1 = 0x02;
    private static final int BIT_2 = 0x04;
    private static final int BIT_3 = 0x08;
    private static final int BIT_4 = 0x10;
    private static final int BIT_5 = 0x20;
    private static final int BIT_6 = 0x40;
    private static final int BIT_7 = 0x80;
    BinaryCodec instance = null;

    @Before
    public void setUp() throws Exception {
        this.instance = new BinaryCodec();
    }

    @After
    public void tearDown() throws Exception {
        this.instance = null;
    }

    @Test
    public void testFromAsciiByteArrayWithLength() {
        byte[] bits = new byte[1];
        byte[] decoded = BinaryCodec.fromAscii("00000000".getBytes(CHARSET_UTF8));
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[1];
        bits[0] = BIT_0;
        decoded = BinaryCodec.fromAscii("00000001".getBytes(CHARSET_UTF8));
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[1];
        bits[0] = BIT_0 | BIT_1;
        decoded = BinaryCodec.fromAscii("00000011".getBytes(CHARSET_UTF8));
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[1];
        bits[0] = BIT_0 | BIT_1 | BIT_2;
        decoded = BinaryCodec.fromAscii("00000111".getBytes(CHARSET_UTF8));
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[1];
        bits[0] = BIT_0 | BIT_1 | BIT_2 | BIT_3;
        decoded = BinaryCodec.fromAscii("00001111".getBytes(CHARSET_UTF8));
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[1];
        bits[0] = BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4;
        decoded = BinaryCodec.fromAscii("00011111".getBytes(CHARSET_UTF8));
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[1];
        bits[0] = BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5;
        decoded = BinaryCodec.fromAscii("00111111".getBytes(CHARSET_UTF8));
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[1];
        bits[0] = BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5 | BIT_6;
        decoded = BinaryCodec.fromAscii("01111111".getBytes(CHARSET_UTF8));
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[1];
        bits[0] = (byte) (BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5 | BIT_6 | BIT_7);
        decoded = BinaryCodec.fromAscii("11111111".getBytes(CHARSET_UTF8));
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[2];
        bits[0] = (byte) (BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5 | BIT_6 | BIT_7);
        decoded = BinaryCodec.fromAscii("0000000011111111".getBytes(CHARSET_UTF8));
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[2];
        bits[1] = BIT_0;
        bits[0] = (byte) (BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5 | BIT_6 | BIT_7);
        decoded = BinaryCodec.fromAscii("0000000111111111".getBytes(CHARSET_UTF8));
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[2];
        bits[1] = BIT_0 | BIT_1;
        bits[0] = (byte) (BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5 | BIT_6 | BIT_7);
        decoded = BinaryCodec.fromAscii("0000001111111111".getBytes(CHARSET_UTF8));
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[2];
        bits[1] = BIT_0 | BIT_1 | BIT_2;
        bits[0] = (byte) (BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5 | BIT_6 | BIT_7);
        decoded = BinaryCodec.fromAscii("0000011111111111".getBytes(CHARSET_UTF8));
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[2];
        bits[1] = BIT_0 | BIT_1 | BIT_2 | BIT_3;
        bits[0] = (byte) (BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5 | BIT_6 | BIT_7);
        decoded = BinaryCodec.fromAscii("0000111111111111".getBytes(CHARSET_UTF8));
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[2];
        bits[1] = BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4;
        bits[0] = (byte) (BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5 | BIT_6 | BIT_7);
        decoded = BinaryCodec.fromAscii("0001111111111111".getBytes(CHARSET_UTF8));
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[2];
        bits[1] = BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5;
        bits[0] = (byte) (BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5 | BIT_6 | BIT_7);
        decoded = BinaryCodec.fromAscii("0011111111111111".getBytes(CHARSET_UTF8));
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[2];
        bits[1] = BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5 | BIT_6;
        bits[0] = (byte) (BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5 | BIT_6 | BIT_7);
        decoded = BinaryCodec.fromAscii("0111111111111111".getBytes(CHARSET_UTF8));
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[2];
        bits[1] = (byte) (BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5 | BIT_6 | BIT_7);
        bits[0] = (byte) (BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5 | BIT_6 | BIT_7);
        decoded = BinaryCodec.fromAscii("1111111111111111".getBytes(CHARSET_UTF8));
        assertEquals(new String(bits), new String(decoded));
    }

    @Test
    public void testFromAsciiCharArrayWithLength() {
        byte[] bits = new byte[1];
        byte[] decoded = BinaryCodec.fromAscii("00000000".toCharArray());
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[1];
        bits[0] = BIT_0;
        decoded = BinaryCodec.fromAscii("00000001".toCharArray());
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[1];
        bits[0] = BIT_0 | BIT_1;
        decoded = BinaryCodec.fromAscii("00000011".toCharArray());
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[1];
        bits[0] = BIT_0 | BIT_1 | BIT_2;
        decoded = BinaryCodec.fromAscii("00000111".toCharArray());
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[1];
        bits[0] = BIT_0 | BIT_1 | BIT_2 | BIT_3;
        decoded = BinaryCodec.fromAscii("00001111".toCharArray());
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[1];
        bits[0] = BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4;
        decoded = BinaryCodec.fromAscii("00011111".toCharArray());
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[1];
        bits[0] = BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5;
        decoded = BinaryCodec.fromAscii("00111111".toCharArray());
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[1];
        bits[0] = BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5 | BIT_6;
        decoded = BinaryCodec.fromAscii("01111111".toCharArray());
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[1];
        bits[0] = (byte) (BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5 | BIT_6 | BIT_7);
        decoded = BinaryCodec.fromAscii("11111111".toCharArray());
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[2];
        bits[0] = (byte) (BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5 | BIT_6 | BIT_7);
        decoded = BinaryCodec.fromAscii("0000000011111111".toCharArray());
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[2];
        bits[1] = BIT_0;
        bits[0] = (byte) (BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5 | BIT_6 | BIT_7);
        decoded = BinaryCodec.fromAscii("0000000111111111".toCharArray());
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[2];
        bits[1] = BIT_0 | BIT_1;
        bits[0] = (byte) (BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5 | BIT_6 | BIT_7);
        decoded = BinaryCodec.fromAscii("0000001111111111".toCharArray());
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[2];
        bits[1] = BIT_0 | BIT_1 | BIT_2;
        bits[0] = (byte) (BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5 | BIT_6 | BIT_7);
        decoded = BinaryCodec.fromAscii("0000011111111111".toCharArray());
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[2];
        bits[1] = BIT_0 | BIT_1 | BIT_2 | BIT_3;
        bits[0] = (byte) (BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5 | BIT_6 | BIT_7);
        decoded = BinaryCodec.fromAscii("0000111111111111".toCharArray());
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[2];
        bits[1] = BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4;
        bits[0] = (byte) (BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5 | BIT_6 | BIT_7);
        decoded = BinaryCodec.fromAscii("0001111111111111".toCharArray());
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[2];
        bits[1] = BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5;
        bits[0] = (byte) (BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5 | BIT_6 | BIT_7);
        decoded = BinaryCodec.fromAscii("0011111111111111".toCharArray());
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[2];
        bits[1] = BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5 | BIT_6;
        bits[0] = (byte) (BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5 | BIT_6 | BIT_7);
        decoded = BinaryCodec.fromAscii("0111111111111111".toCharArray());
        assertEquals(new String(bits), new String(decoded));
        
        bits = new byte[2];
        bits[1] = (byte) (BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5 | BIT_6 | BIT_7);
        bits[0] = (byte) (BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5 | BIT_6 | BIT_7);
        decoded = BinaryCodec.fromAscii("1111111111111111".toCharArray());
        assertEquals(new String(bits), new String(decoded));
    }

    @Test
    public void testToAsciiBytesWithLength() {
        byte[] bits = new byte[1];
        String l_encoded = new String(BinaryCodec.toAsciiBytes(bits));
        assertEquals("00000000", l_encoded);
        
        bits = new byte[1];
        bits[0] = BIT_0;
        l_encoded = new String(BinaryCodec.toAsciiBytes(bits));
        assertEquals("00000001", l_encoded);
        
        bits = new byte[1];
        bits[0] = BIT_0 | BIT_1;
        l_encoded = new String(BinaryCodec.toAsciiBytes(bits));
        assertEquals("00000011", l_encoded);
        
        bits = new byte[1];
        bits[0] = BIT_0 | BIT_1 | BIT_2;
        l_encoded = new String(BinaryCodec.toAsciiBytes(bits));
        assertEquals("00000111", l_encoded);
        
        bits = new byte[1];
        bits[0] = BIT_0 | BIT_1 | BIT_2 | BIT_3;
        l_encoded = new String(BinaryCodec.toAsciiBytes(bits));
        assertEquals("00001111", l_encoded);
        
        bits = new byte[1];
        bits[0] = BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4;
        l_encoded = new String(BinaryCodec.toAsciiBytes(bits));
        assertEquals("00011111", l_encoded);
        
        bits = new byte[1];
        bits[0] = BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5;
        l_encoded = new String(BinaryCodec.toAsciiBytes(bits));
        assertEquals("00111111", l_encoded);
        
        bits = new byte[1];
        bits[0] = BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5 | BIT_6;
        l_encoded = new String(BinaryCodec.toAsciiBytes(bits));
        assertEquals("01111111", l_encoded);
        
        bits = new byte[1];
        bits[0] = (byte) (BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5 | BIT_6 | BIT_7);
        l_encoded = new String(BinaryCodec.toAsciiBytes(bits));
        assertEquals("11111111", l_encoded);
        
        bits = new byte[2];
        l_encoded = new String(BinaryCodec.toAsciiBytes(bits));
        assertEquals("0000000000000000", l_encoded);
        
        bits = new byte[2];
        bits[0] = BIT_0;
        l_encoded = new String(BinaryCodec.toAsciiBytes(bits));
        assertEquals("0000000000000001", l_encoded);
        
        bits = new byte[2];
        bits[0] = BIT_0 | BIT_1;
        l_encoded = new String(BinaryCodec.toAsciiBytes(bits));
        assertEquals("0000000000000011", l_encoded);
        
        bits = new byte[2];
        bits[0] = BIT_0 | BIT_1 | BIT_2;
        l_encoded = new String(BinaryCodec.toAsciiBytes(bits));
        assertEquals("0000000000000111", l_encoded);
        
        bits = new byte[2];
        bits[0] = BIT_0 | BIT_1 | BIT_2 | BIT_3;
        l_encoded = new String(BinaryCodec.toAsciiBytes(bits));
        assertEquals("0000000000001111", l_encoded);
        
        bits = new byte[2];
        bits[0] = BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4;
        l_encoded = new String(BinaryCodec.toAsciiBytes(bits));
        assertEquals("0000000000011111", l_encoded);
        
        bits = new byte[2];
        bits[0] = BIT_0