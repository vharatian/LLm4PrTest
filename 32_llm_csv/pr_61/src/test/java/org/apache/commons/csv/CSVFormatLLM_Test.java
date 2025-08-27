package org.apache.commons.csv;

import static org.apache.commons.csv.Constants.CR;
import static org.apache.commons.csv.Constants.LF;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.junit.jupiter.api.Test;

public class CSVFormatLLM_Test {

    @Test
    public void testPrintWithEscapesReader() throws IOException {
        // Setup
        CSVFormat format = CSVFormat.DEFAULT.withEscape('\\');
        String input = "value1,value2\nvalue3,value4";
        StringReader reader = new StringReader(input);
        StringWriter writer = new StringWriter();

        // Execute
        format.printWithEscapes(reader, writer);

        // Verify
        assertEquals("value1,value2\\nvalue3,value4", writer.toString());
    }

    @Test
    public void testPrintWithEscapesReaderWithLineBreaks() throws IOException {
        // Setup
        CSVFormat format = CSVFormat.DEFAULT.withEscape('\\');
        String input = "value1,value2\rvalue3,value4";
        StringReader reader = new StringReader(input);
        StringWriter writer = new StringWriter();

        // Execute
        format.printWithEscapes(reader, writer);

        // Verify
        assertEquals("value1,value2\\rvalue3,value4", writer.toString());
    }

    @Test
    public void testPrintWithEscapesReaderWithDelimiter() throws IOException {
        // Setup
        CSVFormat format = CSVFormat.DEFAULT.withEscape('\\').withDelimiter(',');
        String input = "value1,value2,value3";
        StringReader reader = new StringReader(input);
        StringWriter writer = new StringWriter();

        // Execute
        format.printWithEscapes(reader, writer);

        // Verify
        assertEquals("value1\\,value2\\,value3", writer.toString());
    }

    @Test
    public void testPrintWithEscapesReaderWithEscapeCharacter() throws IOException {
        // Setup
        CSVFormat format = CSVFormat.DEFAULT.withEscape('\\');
        String input = "value1\\value2";
        StringReader reader = new StringReader(input);
        StringWriter writer = new StringWriter();

        // Execute
        format.printWithEscapes(reader, writer);

        // Verify
        assertEquals("value1\\\\value2", writer.toString());
    }
}