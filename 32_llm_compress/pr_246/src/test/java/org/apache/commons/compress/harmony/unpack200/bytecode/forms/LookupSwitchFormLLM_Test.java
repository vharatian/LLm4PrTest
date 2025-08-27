package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LookupSwitchFormLLM_Test {

    @Test
    public void testSetByteCodeOperands() {
        // Arrange
        final int opcode = 171; // example opcode for lookupswitch
        final String name = "lookupswitch";
        LookupSwitchForm lookupSwitchForm = new LookupSwitchForm(opcode, name);
        ByteCode byteCode = new ByteCode(opcode, name);
        OperandManager operandManager = new OperandManager() {
            private int caseCount = 3;
            private int[] caseValues = {10, 20, 30};
            private int[] labels = {100, 200, 300, 400};
            private int index = 0;

            @Override
            public int nextCaseCount() {
                return caseCount;
            }

            @Override
            public int nextCaseValues() {
                return caseValues[index++];
            }

            @Override
            public int nextLabel() {
                return labels[index++];
            }
        };
        final int codeLength = 10;

        // Act
        lookupSwitchForm.setByteCodeOperands(byteCode, operandManager, codeLength);

        // Assert
        int[] expectedLabelsArray = {100, 200, 300, 400};
        assertArrayEquals(expectedLabelsArray, byteCode.getByteCodeTargets());

        int padLength = 3 - (codeLength % 4);
        int rewriteSize = 1 + padLength + 4 + 4 + (4 * 3) + (4 * 3);
        int[] newRewrite = byteCode.getRewrite();
        assertEquals(rewriteSize, newRewrite.length);
        assertEquals(opcode, newRewrite[0]);
        for (int i = 1; i <= padLength; i++) {
            assertEquals(0, newRewrite[i]);
        }
        assertEquals(-1, newRewrite[padLength + 1]);
        assertEquals(-1, newRewrite[padLength + 2]);
        assertEquals(-1, newRewrite[padLength + 3]);
        assertEquals(-1, newRewrite[padLength + 4]);
    }
}