package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

public class MapUtilsLLM_Test {

    @Test
    public void testDebugPrintWithNewArrayDeque() {
        final Map<Integer, String> inner = new HashMap<>(2, 1);
        inner.put(2, "B");
        inner.put(3, "C");
        final Map<Integer, Object> outer = new HashMap<>(2, 1);
        outer.put(0, inner);
        outer.put(1, "A");
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final PrintStream outPrint = new PrintStream(out);
        try {
            MapUtils.debugPrint(outPrint, "Print Map", outer);
        } catch (final ClassCastException e) {
            fail("No Casting should be occurring!");
        }
    }

    @Test
    public void testVerbosePrintWithNewArrayDeque() {
        final Map<Integer, String> inner = new HashMap<>(2, 1);
        inner.put(2, "B");
        inner.put(3, "C");
        final Map<Integer, Object> outer = new HashMap<>(2, 1);
        outer.put(0, inner);
        outer.put(1, "A");
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final PrintStream outPrint = new PrintStream(out);
        try {
            MapUtils.verbosePrint(outPrint, "Print Map", outer);
        } catch (final ClassCastException e) {
            fail("No Casting should be occurring!");
        }
    }

    @Test
    public void testDebugPrintNullStreamWithNewArrayDeque() {
        try {
            MapUtils.debugPrint(null, "Map", new HashMap<>());
            fail("Should generate NullPointerException");
        } catch (final NullPointerException expected) {
        }
    }

    @Test
    public void testVerbosePrintNullStreamWithNewArrayDeque() {
        try {
            MapUtils.verbosePrint(null, "Map", new HashMap<>());
            fail("Should generate NullPointerException");
        } catch (final NullPointerException expected) {
        }
    }
}