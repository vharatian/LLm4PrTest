package org.apache.commons.csv;

import static org.apache.commons.csv.Constants.CR;
import static org.apache.commons.csv.Constants.LF;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.StringReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.jupiter.api.Test;

public class CSVFormatLLM_Test {

    @Test
    public void testPrintWithQuotesNoEndVariable() throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.withQuote('"').withDelimiter(',');
        StringWriter out = new StringWriter();
        format.print("value", out, true);
        assertEquals("\"value\"", out.toString());
    }

    @Test
    public void testPrintWithQuotesWhileLoopChange() throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.withQuote('"').withDelimiter(',');
        StringWriter out = new StringWriter();
        format.print("value,with,commas", out, true);
        assertEquals("\"value,with,commas\"", out.toString());
    }

    @Test
    public void testPrintWithQuotesPosChange() throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.withQuote('"').withDelimiter(',');
        StringWriter out = new StringWriter();
        format.print("value ends with space ", out, true);
        assertEquals("\"value ends with space \"", out.toString());
    }

    @Test
    public void testPrintWithQuotesAppendChange() throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.withQuote('"').withDelimiter(',');
        StringWriter out = new StringWriter();
        format.print("value with \"quote\"", out, true);
        assertEquals("\"value with \"\"quote\"\"\"", out.toString());
    }

    @Test
    public void testPrintWithQuotesReader() throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.withQuote('"').withDelimiter(',');
        Reader in = new StringReader("value with \"quote\"");
        StringWriter out = new StringWriter();
        format.print(in, out, true);
        assertEquals("\"value with \"\"quote\"\"\"", out.toString());
    }
}