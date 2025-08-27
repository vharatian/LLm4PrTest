package org.apache.commons.text;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.matcher.StringMatcher;
import org.apache.commons.text.matcher.StringMatcherFactory;
import org.junit.jupiter.api.Test;

public class TextStringBuilderLLM_Test {

    @Test
    public void testConstructorWithInvalidLength() {
        char[] initialBuffer = new char[10];
        assertThrows(IllegalArgumentException.class, () -> new TextStringBuilder(initialBuffer, -1));
        assertThrows(IllegalArgumentException.class, () -> new TextStringBuilder(initialBuffer, 11));
    }

    @Test
    public void testConstructorWithValidLength() {
        char[] initialBuffer = new char[10];
        TextStringBuilder tsb = new TextStringBuilder(initialBuffer, 5);
        assertEquals(5, tsb.size());
        assertEquals(10, tsb.capacity());
    }
}