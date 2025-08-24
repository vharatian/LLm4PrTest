package org.apache.commons.csv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class CSVPrinterLLM_Test {

    @Test
    public void testPrintRecordVarArgs() throws IOException {
        StringWriter sw = new StringWriter();
        try (CSVPrinter printer = new CSVPrinter(sw, CSVFormat.DEFAULT)) {
            printer.printRecord("a", "b", "c");
            assertEquals("a,b,c\r\n", sw.toString());
        }
    }

    @Test
    public void testPrintRecordVarArgsWithSpecialCharacters() throws IOException {
        StringWriter sw = new StringWriter();
        try (CSVPrinter printer = new CSVPrinter(sw, CSVFormat.DEFAULT)) {
            printer.printRecord("a,b", "c\nd", "e\"f\"");
            assertEquals("\"a,b\",\"c\nd\",\"e\"\"f\"\"\r\n", sw.toString());
        }
    }

    @Test
    public void testPrintRecordVarArgsWithNullValues() throws IOException {
        StringWriter sw = new StringWriter();
        try (CSVPrinter printer = new CSVPrinter(sw, CSVFormat.DEFAULT)) {
            printer.printRecord("a", null, "c");
            assertEquals("a,,c\r\n", sw.toString());
        }
    }

    @Test
    public void testPrintRecordVarArgsWithEmptyValues() throws IOException {
        StringWriter sw = new StringWriter();
        try (CSVPrinter printer = new CSVPrinter(sw, CSVFormat.DEFAULT)) {
            printer.printRecord("", "", "");
            assertEquals(",,\r\n", sw.toString());
        }
    }

    @Test
    public void testPrintRecordVarArgsWithMixedValues() throws IOException {
        StringWriter sw = new StringWriter();
        try (CSVPrinter printer = new CSVPrinter(sw, CSVFormat.DEFAULT)) {
            printer.printRecord("a", "", null, "d");
            assertEquals("a,,d\r\n", sw.toString());
        }
    }
}