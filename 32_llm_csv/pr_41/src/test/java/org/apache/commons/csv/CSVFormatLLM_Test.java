package org.apache.commons.csv;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class CSVFormatLLM_Test {

    @Test
    public void testWithAllowDuplicateHeaderNames() {
        CSVFormat format = CSVFormat.DEFAULT.withAllowDuplicateHeaderNames();
        assertTrue(format.getAllowDuplicateHeaderNames());
    }

    @Test
    public void testWithAllowDuplicateHeaderNamesFalse() {
        CSVFormat format = CSVFormat.DEFAULT.withAllowDuplicateHeaderNames(false);
        assertFalse(format.getAllowDuplicateHeaderNames());
    }

    @Test
    public void testDefaultAllowDuplicateHeaderNames() {
        assertTrue(CSVFormat.DEFAULT.getAllowDuplicateHeaderNames());
    }

    @Test
    public void testEqualsAllowDuplicateHeaderNames() {
        CSVFormat right = CSVFormat.DEFAULT.withAllowDuplicateHeaderNames(true);
        CSVFormat left = right.withAllowDuplicateHeaderNames(false);
        assertNotEquals(right, left);
    }

    @Test
    public void testNewFormatAllowDuplicateHeaderNames() {
        CSVFormat format = CSVFormat.newFormat(';');
        assertTrue(format.getAllowDuplicateHeaderNames());
    }

    @Test
    public void testWithHeaderAllowDuplicateHeaderNames() {
        String[] header = new String[]{"one", "two", "three"};
        CSVFormat format = CSVFormat.DEFAULT.withHeader(header).withAllowDuplicateHeaderNames();
        assertTrue(format.getAllowDuplicateHeaderNames());
    }

    @Test
    public void testWithHeaderCommentsAllowDuplicateHeaderNames() {
        Object[] headerComments = new Object[]{"comment1", "comment2"};
        CSVFormat format = CSVFormat.DEFAULT.withHeaderComments(headerComments).withAllowDuplicateHeaderNames();
        assertTrue(format.getAllowDuplicateHeaderNames());
    }

    @Test
    public void testWithNullStringAllowDuplicateHeaderNames() {
        CSVFormat format = CSVFormat.DEFAULT.withNullString("null").withAllowDuplicateHeaderNames();
        assertTrue(format.getAllowDuplicateHeaderNames());
    }

    @Test
    public void testWithQuoteAllowDuplicateHeaderNames() {
        CSVFormat format = CSVFormat.DEFAULT.withQuote('"').withAllowDuplicateHeaderNames();
        assertTrue(format.getAllowDuplicateHeaderNames());
    }

    @Test
    public void testWithRecordSeparatorAllowDuplicateHeaderNames() {
        CSVFormat format = CSVFormat.DEFAULT.withRecordSeparator('\n').withAllowDuplicateHeaderNames();
        assertTrue(format.getAllowDuplicateHeaderNames());
    }

    @Test
    public void testWithSkipHeaderRecordAllowDuplicateHeaderNames() {
        CSVFormat format = CSVFormat.DEFAULT.withSkipHeaderRecord().withAllowDuplicateHeaderNames();
        assertTrue(format.getAllowDuplicateHeaderNames());
    }

    @Test
    public void testWithTrailingDelimiterAllowDuplicateHeaderNames() {
        CSVFormat format = CSVFormat.DEFAULT.withTrailingDelimiter().withAllowDuplicateHeaderNames();
        assertTrue(format.getAllowDuplicateHeaderNames());
    }

    @Test
    public void testWithTrimAllowDuplicateHeaderNames() {
        CSVFormat format = CSVFormat.DEFAULT.withTrim().withAllowDuplicateHeaderNames();
        assertTrue(format.getAllowDuplicateHeaderNames());
    }
}