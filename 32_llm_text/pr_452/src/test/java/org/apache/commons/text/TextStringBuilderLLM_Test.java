package org.apache.commons.text;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Locale;

import org.apache.commons.io.output.NullAppendable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.matcher.StringMatcher;
import org.apache.commons.text.matcher.StringMatcherFactory;
import org.junit.jupiter.api.Test;

public class TextStringBuilderLLM_Test {

    @Test
    public void testEnsureCapacityInternal() {
        TextStringBuilder sb = new TextStringBuilder();
        sb.ensureCapacity(2);
        assertTrue(sb.capacity() >= 2);

        sb.ensureCapacityInternal(2);
        assertTrue(sb.capacity() >= 2);

        sb.ensureCapacityInternal(-1);
        assertTrue(sb.capacity() >= 0);

        sb.append("HelloWorld");
        sb.ensureCapacityInternal(40);
        assertTrue(sb.capacity() >= 40);
    }

    @Test
    public void testResizeBuffer() {
        TextStringBuilder sb = new TextStringBuilder();
        sb.ensureCapacityInternal(2);
        assertTrue(sb.capacity() >= 2);

        sb.ensureCapacityInternal(100);
        assertTrue(sb.capacity() >= 100);

        sb.ensureCapacityInternal(Integer.MAX_VALUE - 8);
        assertTrue(sb.capacity() >= Integer.MAX_VALUE - 8);
    }

    @Test
    public void testCreatePositiveCapacity() {
        assertThrows(OutOfMemoryError.class, () -> TextStringBuilder.createPositiveCapacity(-1));

        int capacity = TextStringBuilder.createPositiveCapacity(10);
        assertEquals(10, capacity);

        capacity = TextStringBuilder.createPositiveCapacity(Integer.MAX_VALUE - 8);
        assertEquals(Integer.MAX_VALUE - 8, capacity);
    }

    @Test
    public void testAppendWithEnsureCapacityInternal() {
        TextStringBuilder sb = new TextStringBuilder();
        sb.append(true);
        assertEquals("true", sb.toString());

        sb = new TextStringBuilder();
        sb.append(false);
        assertEquals("false", sb.toString());

        sb = new TextStringBuilder();
        sb.append('a');
        assertEquals("a", sb.toString());

        sb = new TextStringBuilder();
        sb.append(new char[]{'a', 'b', 'c'});
        assertEquals("abc", sb.toString());

        sb = new TextStringBuilder();
        sb.append(new char[]{'a', 'b', 'c'}, 1, 2);
        assertEquals("bc", sb.toString());

        sb = new TextStringBuilder();
        sb.append(CharBuffer.wrap("abc"));
        assertEquals("abc", sb.toString());

        sb = new TextStringBuilder();
        sb.append(CharBuffer.wrap("abc"), 1, 2);
        assertEquals("bc", sb.toString());

        sb = new TextStringBuilder();
        sb.append("abc", 1, 2);
        assertEquals("bc", sb.toString());

        sb = new TextStringBuilder();
        sb.append(new StringBuffer("abc"), 1, 2);
        assertEquals("bc", sb.toString());

        sb = new TextStringBuilder();
        sb.append(new StringBuilder("abc"), 1, 2);
        assertEquals("bc", sb.toString());

        sb = new TextStringBuilder();
        sb.append(new TextStringBuilder("abc"), 1, 2);
        assertEquals("bc", sb.toString());

        sb = new TextStringBuilder();
        sb.appendFixedWidthPadLeft(123, 5, '0');
        assertEquals("00123", sb.toString());

        sb = new TextStringBuilder();
        sb.appendFixedWidthPadRight(123, 5, '0');
        assertEquals("12300", sb.toString());

        sb = new TextStringBuilder();
        sb.appendPadding(5, '0');
        assertEquals("00000", sb.toString());
    }

    @Test
    public void testInsertWithEnsureCapacityInternal() {
        TextStringBuilder sb = new TextStringBuilder("abc");
        sb.insert(1, true);
        assertEquals("atruebc", sb.toString());

        sb = new TextStringBuilder("abc");
        sb.insert(1, false);
        assertEquals("afalsebc", sb.toString());

        sb = new TextStringBuilder("abc");
        sb.insert(1, 'd');
        assertEquals("adbc", sb.toString());

        sb = new TextStringBuilder("abc");
        sb.insert(1, new char[]{'d', 'e', 'f'});
        assertEquals("adefbc", sb.toString());

        sb = new TextStringBuilder("abc");
        sb.insert(1, new char[]{'d', 'e', 'f'}, 1, 2);
        assertEquals("aefbc", sb.toString());

        sb = new TextStringBuilder("abc");
        sb.insert(1, 1.23);
        assertEquals("a1.23bc", sb.toString());

        sb = new TextStringBuilder("abc");
        sb.insert(1, 1.23f);
        assertEquals("a1.23bc", sb.toString());

        sb = new TextStringBuilder("abc");
        sb.insert(1, 123);
        assertEquals("a123bc", sb.toString());

        sb = new TextStringBuilder("abc");
        sb.insert(1, 123L);
        assertEquals("a123bc", sb.toString());

        sb = new TextStringBuilder("abc");
        sb.insert(1, "def");
        assertEquals("adefbc", sb.toString());
    }

    @Test
    public void testReadFromWithEnsureCapacityInternal() throws IOException {
        TextStringBuilder sb = new TextStringBuilder();
        sb.readFrom(new StringReader("abc"));
        assertEquals("abc", sb.toString());

        sb = new TextStringBuilder();
        sb.readFrom(new StringReader("abc"), 2);
        assertEquals("ab", sb.toString());

        sb = new TextStringBuilder();
        sb.readFrom(CharBuffer.wrap("abc"));
        assertEquals("abc", sb.toString());

        sb = new TextStringBuilder();
        sb.readFrom(CharBuffer.wrap("abc"), 2);
        assertEquals("ab", sb.toString());

        sb = new TextStringBuilder();
        sb.readFrom(new MockReadable("abc"));
        assertEquals("abc", sb.toString());
    }
}