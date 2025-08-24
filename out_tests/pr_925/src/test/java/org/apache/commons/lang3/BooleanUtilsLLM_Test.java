package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class BooleanUtilsLLM_Test extends AbstractLangTest {

    @Test
    public void testOneHot_primitive_validInput_2items() {
        // Test case for the modified oneHot method with 2 items
        assertFalse(BooleanUtils.oneHot(new boolean[]{true, true}), "both true");
        assertFalse(BooleanUtils.oneHot(new boolean[]{false, false}), "both false");
        assertTrue(BooleanUtils.oneHot(new boolean[]{true, false}), "first true");
        assertTrue(BooleanUtils.oneHot(new boolean[]{false, true}), "last true");
    }

    @Test
    public void testOneHot_primitive_validInput_3items() {
        // Test case for the modified oneHot method with 3 items
        assertFalse(BooleanUtils.oneHot(new boolean[]{false, false, false}), "all false");
        assertTrue(BooleanUtils.oneHot(new boolean[]{true, false, false}), "first true");
        assertTrue(BooleanUtils.oneHot(new boolean[]{false, true, false}), "middle true");
        assertTrue(BooleanUtils.oneHot(new boolean[]{false, false, true}), "last true");
        assertFalse(BooleanUtils.oneHot(new boolean[]{false, true, true}), "first false");
        assertFalse(BooleanUtils.oneHot(new boolean[]{true, false, true}), "middle false");
        assertFalse(BooleanUtils.oneHot(new boolean[]{true, true, false}), "last false");
        assertFalse(BooleanUtils.oneHot(new boolean[]{true, true, true}), "all true");
    }

    @Test
    public void testOneHot_primitive_emptyInput() {
        // Test case for the modified oneHot method with empty input
        assertThrows(IllegalArgumentException.class, () -> BooleanUtils.oneHot(new boolean[] {}));
    }

    @Test
    public void testOneHot_primitive_nullInput() {
        // Test case for the modified oneHot method with null input
        assertThrows(NullPointerException.class, () -> BooleanUtils.oneHot((boolean[]) null));
    }

    @Test
    public void testOneHot_primitive_validInput_1item() {
        // Test case for the modified oneHot method with 1 item
        assertTrue(BooleanUtils.oneHot(new boolean[]{true}), "true");
        assertFalse(BooleanUtils.oneHot(new boolean[]{false}), "false");
    }
}