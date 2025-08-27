package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.Test;

public class MapUtilsLLM_Test {

    @Test
    public void testVerbosePrintAncestorFormatting() {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final PrintStream outPrint = new PrintStream(out);
        final String LABEL = "Print Map";
        final String INDENT = " ";
        final Map<Integer, Object> grandfather = new TreeMap<>();
        final Map<Integer, Object> father = new TreeMap<>();
        final Map<Integer, Object> son = new TreeMap<>();
        grandfather.put(0, "A");
        grandfather.put(1, father);
        father.put(2, "B");
        father.put(3, grandfather);
        father.put(4, son);
        son.put(5, "C");
        son.put(6, grandfather);
        son.put(7, father);
        outPrint.println(LABEL + " = ");
        outPrint.println("{");
        outPrint.println(INDENT + "0 = A");
        outPrint.println(INDENT + "1 = ");
        outPrint.println(INDENT + "{");
        outPrint.println(INDENT + INDENT + "2 = B");
        outPrint.println(INDENT + INDENT + "3 = (ancestor[0] Map)");
        outPrint.println(INDENT + INDENT + "4 = ");
        outPrint.println(INDENT + INDENT + "{");
        outPrint.println(INDENT + INDENT + INDENT + "5 = C");
        outPrint.println(INDENT + INDENT + INDENT + "6 = (ancestor[1] Map)");
        outPrint.println(INDENT + INDENT + INDENT + "7 = (ancestor[0] Map)");
        outPrint.println(INDENT + INDENT + "}");
        outPrint.println(INDENT + "}");
        outPrint.println("}");
        final String EXPECTED_OUT = out.toString();
        out.reset();
        MapUtils.verbosePrint(outPrint, "Print Map", grandfather);
        assertEquals(EXPECTED_OUT, out.toString());
    }

    @Test
    public void testDebugPrintAncestorFormatting() {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final PrintStream outPrint = new PrintStream(out);
        final String LABEL = "Print Map";
        final String INDENT = " ";
        final Map<Integer, Object> grandfather = new TreeMap<>();
        final Map<Integer, Object> father = new TreeMap<>();
        final Map<Integer, Object> son = new TreeMap<>();
        grandfather.put(0, "A");
        grandfather.put(1, father);
        father.put(2, "B");
        father.put(3, grandfather);
        father.put(4, son);
        son.put(5, "C");
        son.put(6, grandfather);
        son.put(7, father);
        outPrint.println(LABEL + " = ");
        outPrint.println("{");
        outPrint.println(INDENT + "0 = A " + String.class.getName());
        outPrint.println(INDENT + "1 = ");
        outPrint.println(INDENT + "{");
        outPrint.println(INDENT + INDENT + "2 = B " + String.class.getName());
        outPrint.println(INDENT + INDENT + "3 = (ancestor[0] Map) " + TreeMap.class.getName());
        outPrint.println(INDENT + INDENT + "4 = ");
        outPrint.println(INDENT + INDENT + "{");
        outPrint.println(INDENT + INDENT + INDENT + "5 = C " + String.class.getName());
        outPrint.println(INDENT + INDENT + INDENT + "6 = (ancestor[1] Map) " + TreeMap.class.getName());
        outPrint.println(INDENT + INDENT + INDENT + "7 = (ancestor[0] Map) " + TreeMap.class.getName());
        outPrint.println(INDENT + INDENT + "} " + TreeMap.class.getName());
        outPrint.println(INDENT + "} " + TreeMap.class.getName());
        outPrint.println("} " + TreeMap.class.getName());
        final String EXPECTED_OUT = out.toString();
        out.reset();
        MapUtils.debugPrint(outPrint, "Print Map", grandfather);
        assertEquals(EXPECTED_OUT, out.toString());
    }
}