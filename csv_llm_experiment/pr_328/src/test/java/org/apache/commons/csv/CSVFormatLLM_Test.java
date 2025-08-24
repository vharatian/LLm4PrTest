package org.apache.commons.csv;

import static org.apache.commons.csv.CSVFormat.RFC4180;
import static org.apache.commons.csv.Constants.CR;
import static org.apache.commons.csv.Constants.CRLF;
import static org.apache.commons.csv.Constants.LF;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

public class CSVFormatLLM_Test {

    @Test
    public void testPrintToFileWithCharset() throws IOException {
        Path tempFile = Files.createTempFile("csvTest", ".csv");
        CSVFormat format = CSVFormat.RFC4180;
        try (CSVPrinter printer = format.print(tempFile.toFile(), Charset.forName("UTF-8"))) {
            printer.printRecord("a", "b", "c");
        }
        String content = Files.readString(tempFile, Charset.forName("UTF-8"));
        assertEquals("a,b,c" + format.getRecordSeparator(), content);
    }

    @Test
    public void testPrintToPathWithCharset() throws IOException {
        Path tempFile = Files.createTempFile("csvTest", ".csv");
        CSVFormat format = CSVFormat.RFC4180;
        try (CSVPrinter printer = format.print(tempFile, Charset.forName("UTF-8"))) {
            printer.printRecord("a", "b", "c");
        }
        String content = Files.readString(tempFile, Charset.forName("UTF-8"));
        assertEquals("a,b,c" + format.getRecordSeparator(), content);
    }

    @Test
    public void testGetIgnoreHeaderCaseDocumentation() {
        CSVFormat format = CSVFormat.DEFAULT.builder().setIgnoreHeaderCase(true).build();
        assertEquals(true, format.getIgnoreHeaderCase());
    }

    @Test
    public void testConstructorDocumentation() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CSVFormat(",", '"', QuoteMode.MINIMAL, '#', '\\', true, true, "\n", null, null, null, false, false, true, true, true, true, DuplicateHeaderMode.ALLOW_ALL);
        });
    }
}