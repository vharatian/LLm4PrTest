package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TableSwitchFormLLM_Test {

    private TableSwitchForm tableSwitchForm;
    private ByteCode byteCode;
    private OperandManager operandManager;

    @BeforeEach
    public void setUp() {
        tableSwitchForm = new TableSwitchForm(170, "tableswitch");
        byteCode = new ByteCode(170, "tableswitch");
        operandManager = new OperandManager() {
            private int caseCount = 3;
            private int labelIndex = 0;
            private int[] labels = {10, 20, 30, 40};
            private int[] caseValues = {100, 200, 300};

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
        tableSwitchForm.setByteCodeOperands(byteCode, operandManager, 5);
        int[] expectedTargets = {10, 20, 30, 40};
        assertArrayEquals(expectedTargets, byteCode.getByteCodeTargets());

        int[] rewrite = byteCode.getRewrite();
        assertNotNull(rewrite);
        assertEquals(1 + 3 + 4 + 4 + 4 + (4 * 3), rewrite.length);
    }
}