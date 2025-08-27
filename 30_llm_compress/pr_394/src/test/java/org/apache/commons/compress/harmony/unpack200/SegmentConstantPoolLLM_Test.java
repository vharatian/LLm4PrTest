package org.apache.commons.compress.harmony.unpack200;

import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SegmentConstantPoolLLM_Test {

    @Test
    public void testGetClassSpecificPoolEntry() throws Pack200Exception {
        CpBands mockBands = new CpBands();
        SegmentConstantPool pool = new SegmentConstantPool(mockBands);

        // Mock data setup
        mockBands.setCpFieldClass(new String[]{"fieldClass1", "fieldClass2"});
        mockBands.setCpMethodClass(new String[]{"methodClass1", "methodClass2"});
        mockBands.setCpIMethodClass(new String[]{"imethodClass1", "imethodClass2"});

        // Test CP_FIELD case
        ConstantPoolEntry entry = pool.getClassSpecificPoolEntry(SegmentConstantPool.CP_FIELD, 0, "fieldClass1");
        assertNotNull(entry);

        // Test CP_METHOD case
        entry = pool.getClassSpecificPoolEntry(SegmentConstantPool.CP_METHOD, 1, "methodClass2");
        assertNotNull(entry);

        // Test CP_IMETHOD case
        entry = pool.getClassSpecificPoolEntry(SegmentConstantPool.CP_IMETHOD, 0, "imethodClass1");
        assertNotNull(entry);

        // Test invalid cp value
        assertThrows(Error.class, () -> pool.getClassSpecificPoolEntry(99, 0, "unknownClass"));
    }
}