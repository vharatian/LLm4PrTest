package org.apache.commons.imaging.common.itu_t4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class T4_T6_TablesLLM_Test {

    @Test
    public void testEntryConstructor() {
        T4_T6_Tables.Entry entry = new T4_T6_Tables.Entry("1010", 5);
        assertEquals("1010", entry.bitString);
        assertEquals(5, entry.value);
    }

    @Test
    public void testWriteBits() {
        final BitArrayOutputStream bitArrayOutputStream = new BitArrayOutputStream(2309);
        T4_T6_Tables.Entry entry = new T4_T6_Tables.Entry("1010", 5);
        entry.writeBits(bitArrayOutputStream);
        assertEquals(4, bitArrayOutputStream.size());
        assertEquals("[10]", Arrays.toString(bitArrayOutputStream.toByteArray()));
    }
}