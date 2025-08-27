package org.apache.commons.compress.harmony.unpack200;

import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SegmentConstantPoolLLM_Test {

    @Test
    public void testGetInitMethodPoolEntryWithCPMethod() throws Pack200Exception {
        CpBands mockBands = new CpBands();
        SegmentConstantPool pool = new SegmentConstantPool(mockBands);
        
        // Assuming mockBands.getCpMethodClass() and mockBands.getCpMethodDescriptor() are properly mocked
        // and REGEX_MATCH_INIT is properly handled in matchSpecificPoolEntryIndex method.
        
        // Test with valid CP_METHOD
        ConstantPoolEntry result = pool.getInitMethodPoolEntry(SegmentConstantPool.CP_METHOD, 0, "desiredClassName");
        assertNotNull(result);
        
        // Test with invalid CP_METHOD
        assertThrows(Error.class, () -> {
            pool.getInitMethodPoolEntry(SegmentConstantPool.CP_FIELD, 0, "desiredClassName");
        });
    }
}