package org.apache.commons.csv;

import static org.apache.commons.csv.Constants.CR;
import static org.apache.commons.csv.Constants.LF;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CSVFormatLLM_Test {

    @Test
    public void testSetAllowEofWithoutClosingQuote() {
        CSVFormat format = CSVFormat.DEFAULT.builder().setAllowEofWithoutClosingQuote(true).build();
        assertTrue(format.getAllowEofWithoutClosingQuote());

        format = CSVFormat.DEFAULT.builder().setAllowEofWithoutClosingQuote(false).build();
        assertFalse(format.getAllowEofWithoutClosingQuote());
    }

    @Test
    public void testSetAllowTrailingText() {
        CSVFormat format = CSVFormat.DEFAULT.builder().setAllowTrailingText(true).build();
        assertTrue(format.getAllowTrailingText());

        format = CSVFormat.DEFAULT.builder().setAllowTrailingText(false).build();
        assertFalse(format.getAllowTrailingText());
    }

    @Test
    public void testEqualsAllowEofWithoutClosingQuote() {
        CSVFormat right = CSVFormat.DEFAULT.builder().setAllowEofWithoutClosingQuote(true).build();
        CSVFormat left = right.builder().setAllowEofWithoutClosingQuote(false).build();
        assertNotEquals(right, left);
    }

    @Test
    public void testEqualsAllowTrailingText() {
        CSVFormat right = CSVFormat.DEFAULT.builder().setAllowTrailingText(true).build();
        CSVFormat left = right.builder().setAllowTrailingText(false).build();
        assertNotEquals(right, left);
    }

    @Test
    public void testHashCodeWithAllowEofWithoutClosingQuote() {
        CSVFormat format = CSVFormat.DEFAULT.builder().setAllowEofWithoutClosingQuote(true).build();
        int hashCode1 = format.hashCode();

        format = format.builder().setAllowEofWithoutClosingQuote(false).build();
        int hashCode2 = format.hashCode();

        assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    public void testHashCodeWithAllowTrailingText() {
        CSVFormat format = CSVFormat.DEFAULT.builder().setAllowTrailingText(true).build();
        int hashCode1 = format.hashCode();

        format = format.builder().setAllowTrailingText(false).build();
        int hashCode2 = format.hashCode();

        assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    public void testToStringWithAllowEofWithoutClosingQuote() {
        CSVFormat format = CSVFormat.DEFAULT.builder().setAllowEofWithoutClosingQuote(true).build();
        String toString1 = format.toString();

        format = format.builder().setAllowEofWithoutClosingQuote(false).build();
        String toString2 = format.toString();

        assertNotEquals(toString1, toString2);
    }

    @Test
    public void testToStringWithAllowTrailingText() {
        CSVFormat format = CSVFormat.DEFAULT.builder().setAllowTrailingText(true).build();
        String toString1 = format.toString();

        format = format.builder().setAllowTrailingText(false).build();
        String toString2 = format.toString();

        assertNotEquals(toString1, toString2);
    }
}