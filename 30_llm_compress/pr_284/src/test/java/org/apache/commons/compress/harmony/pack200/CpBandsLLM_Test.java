package org.apache.commons.compress.harmony.pack200;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CpBandsLLM_Test {

    private CpBands cpBands;
    private Segment segment;

    @BeforeEach
    public void setUp() {
        segment = new Segment();
        cpBands = new CpBands(segment, 1);
    }

    @Test
    public void testGetCPSignatureWithStringBuffer() {
        String signature = "Lcom/example/MyClass;";
        CPSignature cpSignature = cpBands.getCPSignature(signature);

        assertEquals(signature, cpSignature.getUnderlyingString());
        assertEquals(1, cpSignature.getClasses().size());
        assertEquals("com/example/MyClass", ((CPClass) cpSignature.getClasses().get(0)).getUnderlyingString());
    }

    @Test
    public void testGetCPSignatureWithStringBuilder() {
        String signature = "Ljava/util/List<Ljava/lang/String;>;";
        CPSignature cpSignature = cpBands.getCPSignature(signature);

        assertEquals(signature, cpSignature.getUnderlyingString());
        assertEquals(2, cpSignature.getClasses().size());
        assertEquals("java/util/List", ((CPClass) cpSignature.getClasses().get(0)).getUnderlyingString());
        assertEquals("java/lang/String", ((CPClass) cpSignature.getClasses().get(1)).getUnderlyingString());
    }
}