package org.apache.commons.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CSVParserLLM_Test {

    @Test
    public void testCreateHeadersWithDuplicateHeaderNamesAllowed() throws IOException {
        final String input = "a,b,a\n1,2,3\nx,y,z";
        final CSVFormat format = CSVFormat.DEFAULT.withHeader().withAllowDuplicateHeaderNames(true);
        try (final CSVParser parser = CSVParser.parse(input, format)) {
            final List<String> headerNames = parser.getHeaderNames();
            assertEquals(Arrays.asList("a", "b", "a"), headerNames);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateHeadersWithDuplicateHeaderNamesNotAllowed() throws IOException {
        final String input = "a,b,a\n1,2,3\nx,y,z";
        final CSVFormat format = CSVFormat.DEFAULT.withHeader().withAllowDuplicateHeaderNames(false);
        try (final CSVParser parser = CSVParser.parse(input, format)) {
            fail("Expected IllegalArgumentException due to duplicate header names");
        }
    }

    @Test
    public void testCreateHeadersWithEmptyHeaderNamesAllowed() throws IOException {
        final String input = "a,,c\n1,2,3\nx,y,z";
        final CSVFormat format = CSVFormat.DEFAULT.withHeader().withAllowMissingColumnNames(true);
        try (final CSVParser parser = CSVParser.parse(input, format)) {
            final List<String> headerNames = parser.getHeaderNames();
            assertEquals(Arrays.asList("a", "", "c"), headerNames);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateHeadersWithEmptyHeaderNamesNotAllowed() throws IOException {
        final String input = "a,,c\n1,2,3\nx,y,z";
        final CSVFormat format = CSVFormat.DEFAULT.withHeader().withAllowMissingColumnNames(false);
        try (final CSVParser parser = CSVParser.parse(input, format)) {
            fail("Expected IllegalArgumentException due to missing header names");
        }
    }

    @Test
    public void testCreateHeadersWithIgnoreHeaderCase() throws IOException {
        final String input = "A,b,C\n1,2,3\nx,y,z";
        final CSVFormat format = CSVFormat.DEFAULT.withHeader().withIgnoreHeaderCase(true);
        try (final CSVParser parser = CSVParser.parse(input, format)) {
            final List<String> headerNames = parser.getHeaderNames();
            assertTrue(headerNames.contains("a"));
            assertTrue(headerNames.contains("b"));
            assertTrue(headerNames.contains("c"));
        }
    }
}