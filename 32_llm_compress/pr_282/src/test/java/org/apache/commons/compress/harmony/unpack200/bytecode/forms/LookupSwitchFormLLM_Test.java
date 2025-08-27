package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LookupSwitchFormLLM_Test {

    private LookupSwitchForm lookupSwitchForm;
    private ByteCode byteCode;
    private OperandManager operandManager;

    @BeforeEach
    public void setUp() {
        lookupSwitchForm = new LookupSwitchForm(171, "lookupswitch");
        byteCode = new ByteCode(171, 0);
        operandManager = new OperandManager() {
            private int caseCount = 3;
            private int labelIndex = 0;
            private int[] caseValues = {10, 20, 30};
            private int[] labels = {100, 200, 300, 400};

            @Override
            public int nextCaseCount() {
                return caseCount;
            }

            @Override
            public int nextLabel() {
                return labels[labelIndex++];
            }

            @Override
            public int nextCaseValues() {
                return caseValues[labelIndex - 1];
            }
        };
    }

    @Test
    public void testSetByteCodeOperands() {
        lookupSwitchForm.setByteCodeOperands(byteCode, operandManager, 10);

        int[] expectedTargets = {100, 200, 300, 400};
        assertArrayEquals(expectedTargets, byteCode.getByteCodeTargets());

        int[] rewrite = byteCode.getRewrite();
        assertNotNull(rewrite);
        assertEquals(1 + 3 + 4 + 4 + (4 * 3) + (4 * 3), rewrite.length);
        assertEquals(171, rewrite[0]); // opcode
        assertEquals(0, rewrite[1]); // padding
        assertEquals(0, rewrite[2]); // padding
        assertEquals(0, rewrite[3]); // padding
        assertEquals(-1, rewrite[4]); // default pc
        assertEquals(-1, rewrite[5]);
        assertEquals(-1, rewrite[6]);
        assertEquals(-1, rewrite[7]);
        assertEquals(3, rewrite[8]); // npairs
        assertEquals(10, rewrite[9]); // case value 1
        assertEquals(-1, rewrite[10]); // case pc 1
        assertEquals(-1, rewrite[11]);
        assertEquals(-1, rewrite[12]);
        assertEquals(-1, rewrite[13]);
        assertEquals(20, rewrite[14]); // case value 2
        assertEquals(-1, rewrite[15]); // case pc 2
        assertEquals(-1, rewrite[16]);
        assertEquals(-1, rewrite[17]);
        assertEquals(-1, rewrite[18]);
        assertEquals(30, rewrite[19]); // case value 3
        assertEquals(-1, rewrite[20]); // case pc 3
        assertEquals(-1, rewrite[21]);
        assertEquals(-1, rewrite[22]);
        assertEquals(-1, rewrite[23]);
    }
}