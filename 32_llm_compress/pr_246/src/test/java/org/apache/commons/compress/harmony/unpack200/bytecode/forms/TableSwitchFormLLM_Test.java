package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TableSwitchFormLLM_Test {

    @Test
    public void testSetByteCodeOperands() {
        // Arrange
        int opcode = 170; // example opcode for tableswitch
        String name = "tableswitch";
        TableSwitchForm tableSwitchForm = new TableSwitchForm(opcode, name);
        ByteCode byteCode = new ByteCode(opcode);
        OperandManager operandManager = new MockOperandManager();
        int codeLength = 10;

        // Act
        tableSwitchForm.setByteCodeOperands(byteCode, operandManager, codeLength);

        // Assert
        int[] expectedLabelsArray = {operandManager.getDefaultPc(), operandManager.getCasePc(0), operandManager.getCasePc(1)};
        assertArrayEquals(expectedLabelsArray, byteCode.getByteCodeTargets());
    }

    private class MockOperandManager extends OperandManager {
        private int caseCount = 2;
        private int defaultPc = 100;
        private int[] casePcs = {200, 300};
        private int caseValue = 1;

        @Override
        public int nextCaseCount() {
            return caseCount;
        }

        @Override
        public int nextLabel() {
            return defaultPc;
        }

        @Override
        public int nextCaseValues() {
            return caseValue;
        }

        @Override
        public int getDefaultPc() {
            return defaultPc;
        }

        @Override
        public int getCasePc(int index) {
            return casePcs[index];
        }
    }
}