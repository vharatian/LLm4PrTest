package org.apache.commons.lang3.exception;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExceptionUtilsLLM_Test {

    @Test
    @DisplayName("getRootCauseStackTrace returns empty string array when throwable is null")
    public void testGetRootCauseStackTrace_NullThrowable() {
        String[] result = ExceptionUtils.getRootCauseStackTrace(null);
        assertArrayEquals(new String[0], result);
    }

    @Test
    @DisplayName("printRootCauseStackTrace with PrintStream does not throw exception when throwable is null")
    public void testPrintRootCauseStackTrace_PrintStream_NullThrowable() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        ExceptionUtils.printRootCauseStackTrace(null, printStream);
        assertEquals(0, out.toString().length());
    }

    @Test
    @DisplayName("printRootCauseStackTrace with PrintWriter does not throw exception when throwable is null")
    public void testPrintRootCauseStackTrace_PrintWriter_NullThrowable() {
        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ExceptionUtils.printRootCauseStackTrace(null, printWriter);
        assertEquals(0, writer.getBuffer().length());
    }

    @Test
    @DisplayName("printRootCauseStackTrace with PrintStream throws NullPointerException when PrintStream is null")
    public void testPrintRootCauseStackTrace_PrintStream_NullStream() {
        Throwable cause = new Throwable();
        assertThrows(NullPointerException.class, () -> ExceptionUtils.printRootCauseStackTrace(cause, (PrintStream) null));
    }

    @Test
    @DisplayName("printRootCauseStackTrace with PrintWriter throws NullPointerException when PrintWriter is null")
    public void testPrintRootCauseStackTrace_PrintWriter_NullWriter() {
        Throwable cause = new Throwable();
        assertThrows(NullPointerException.class, () -> ExceptionUtils.printRootCauseStackTrace(cause, (PrintWriter) null));
    }

    @Test
    @DisplayName("printRootCauseStackTrace with PrintStream prints stack trace correctly")
    public void testPrintRootCauseStackTrace_PrintStream() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        Throwable cause = new Throwable("Test Exception");
        ExceptionUtils.printRootCauseStackTrace(cause, printStream);
        String stackTrace = out.toString();
        assertTrue(stackTrace.contains("Test Exception"));
    }

    @Test
    @DisplayName("printRootCauseStackTrace with PrintWriter prints stack trace correctly")
    public void testPrintRootCauseStackTrace_PrintWriter() {
        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        Throwable cause = new Throwable("Test Exception");
        ExceptionUtils.printRootCauseStackTrace(cause, printWriter);
        String stackTrace = writer.toString();
        assertTrue(stackTrace.contains("Test Exception"));
    }
}