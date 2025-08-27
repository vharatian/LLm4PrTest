package org.apache.commons.lang3.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExceptionUtilsLLM_Test {

    @Test
    @DisplayName("getStackTrace returns empty string when the argument is null")
    public void testGetStackTraceNullArg() {
        String actual = ExceptionUtils.getStackTrace(null);
        assertEquals("", actual);
    }

    @Test
    @DisplayName("getStackTrace returns the stack trace string when there is a real exception")
    public void testGetStackTraceHappyPath() {
        Throwable throwable = new Throwable() {
            @Override
            public void printStackTrace(final PrintWriter s) {
                s.write("org.apache.commons.lang3.exception.ExceptionUtilsTest2$1\n" +
                        "\tat org.apache.commons.lang3.exception.ExceptionUtilsTest2.testGetStackTraceHappyPath(ExceptionUtilsTest2.java:20)\n" +
                        "\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n" +
                        "\tat com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:230)\n" +
                        "\tat com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)\n");
            }
        };
        String actual = ExceptionUtils.getStackTrace(throwable);
        assertEquals("org.apache.commons.lang3.exception.ExceptionUtilsTest2$1\n" +
                     "\tat org.apache.commons.lang3.exception.ExceptionUtilsTest2.testGetStackTraceHappyPath(ExceptionUtilsTest2.java:20)\n" +
                     "\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n" +
                     "\tat com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:230)\n" +
                     "\tat com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)\n", actual);
    }
}