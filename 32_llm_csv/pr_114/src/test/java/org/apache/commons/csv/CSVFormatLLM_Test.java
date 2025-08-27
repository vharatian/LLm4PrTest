package org.apache.commons.csv;

import static org.apache.commons.csv.CSVFormat.RFC4180;
import static org.apache.commons.csv.Constants.CR;
import static org.apache.commons.csv.Constants.CRLF;
import static org.apache.commons.csv.Constants.LF;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CSVFormatLLM_Test {

    @Test
    public void testSetDuplicateHeaderMode() {
        CSVFormat format = CSVFormat.DEFAULT.builder()
            .setDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_ALL)
            .setHeader("A", "A")
            .build();
        assertEquals(DuplicateHeaderMode.ALLOW_ALL, format.getDuplicateHeaderMode());
        assertArrayEquals(new String[]{"A", "A"}, format.getHeader());

        format = CSVFormat.DEFAULT.builder()
            .setDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_EMPTY)
            .setHeader("A", "A")
            .build();
        assertEquals(DuplicateHeaderMode.ALLOW_EMPTY, format.getDuplicateHeaderMode());
        assertArrayEquals(new String[]{"A", "A"}, format.getHeader());

        assertThrows(IllegalArgumentException.class, () -> {
            CSVFormat.DEFAULT.builder()
                .setDuplicateHeaderMode(DuplicateHeaderMode.DISALLOW)
                .setHeader("A", "A")
                .build();
        });
    }

    @Test
    public void testGetDuplicateHeaderMode() {
        CSVFormat format = CSVFormat.DEFAULT.builder()
            .setDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_ALL)
            .build();
        assertEquals(DuplicateHeaderMode.ALLOW_ALL, format.getDuplicateHeaderMode());

        format = CSVFormat.DEFAULT.builder()
            .setDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_EMPTY)
            .build();
        assertEquals(DuplicateHeaderMode.ALLOW_EMPTY, format.getDuplicateHeaderMode());

        format = CSVFormat.DEFAULT.builder()
            .setDuplicateHeaderMode(DuplicateHeaderMode.DISALLOW)
            .build();
        assertEquals(DuplicateHeaderMode.DISALLOW, format.getDuplicateHeaderMode());
    }

    @Test
    public void testDeprecatedSetAllowDuplicateHeaderNames() {
        CSVFormat format = CSVFormat.DEFAULT.builder()
            .setAllowDuplicateHeaderNames(true)
            .setHeader("A", "A")
            .build();
        assertEquals(DuplicateHeaderMode.ALLOW_ALL, format.getDuplicateHeaderMode());
        assertArrayEquals(new String[]{"A", "A"}, format.getHeader());

        format = CSVFormat.DEFAULT.builder()
            .setAllowDuplicateHeaderNames(false)
            .setHeader("A", "A")
            .build();
        assertEquals(DuplicateHeaderMode.ALLOW_EMPTY, format.getDuplicateHeaderMode());
        assertArrayEquals(new String[]{"A", "A"}, format.getHeader());
    }

    @Test
    public void testDeprecatedGetAllowDuplicateHeaderNames() {
        CSVFormat format = CSVFormat.DEFAULT.builder()
            .setDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_ALL)
            .build();
        assertTrue(format.getAllowDuplicateHeaderNames());

        format = CSVFormat.DEFAULT.builder()
            .setDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_EMPTY)
            .build();
        assertFalse(format.getAllowDuplicateHeaderNames());

        format = CSVFormat.DEFAULT.builder()
            .setDuplicateHeaderMode(DuplicateHeaderMode.DISALLOW)
            .build();
        assertFalse(format.getAllowDuplicateHeaderNames());
    }

    @Test
    public void testValidateDuplicateHeaderMode() {
        assertThrows(IllegalArgumentException.class, () -> {
            CSVFormat.DEFAULT.builder()
                .setDuplicateHeaderMode(DuplicateHeaderMode.DISALLOW)
                .setHeader("A", "A")
                .build();
        });

        CSVFormat format = CSVFormat.DEFAULT.builder()
            .setDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_ALL)
            .setHeader("A", "A")
            .build();
        assertArrayEquals(new String[]{"A", "A"}, format.getHeader());

        format = CSVFormat.DEFAULT.builder()
            .setDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_EMPTY)
            .setHeader("A", "A")
            .build();
        assertArrayEquals(new String[]{"A", "A"}, format.getHeader());
    }
}