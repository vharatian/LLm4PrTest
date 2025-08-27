package org.apache.commons.text;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class TextStringBuilderLLM_Test {

    @Test
    public void testEqualsWithDifferentSizes() {
        TextStringBuilder sb1 = new TextStringBuilder("abc");
        TextStringBuilder sb2 = new TextStringBuilder("abcd");
        assertFalse(sb1.equals(sb2));
    }

    @Test
    public void testEqualsWithSameSizesDifferentContent() {
        TextStringBuilder sb1 = new TextStringBuilder("abc");
        TextStringBuilder sb2 = new TextStringBuilder("abd");
        assertFalse(sb1.equals(sb2));
    }

    @Test
    public void testEqualsWithSameSizesSameContent() {
        TextStringBuilder sb1 = new TextStringBuilder("abc");
        TextStringBuilder sb2 = new TextStringBuilder("abc");
        assertTrue(sb1.equals(sb2));
    }

    @Test
    public void testEqualsWithSelf() {
        TextStringBuilder sb = new TextStringBuilder("abc");
        assertTrue(sb.equals(sb));
    }

    @Test
    public void testHashCodeConsistency() {
        TextStringBuilder sb = new TextStringBuilder("abc");
        int initialHashCode = sb.hashCode();
        assertEquals(initialHashCode, sb.hashCode());
        sb.append("def");
        assertNotEquals(initialHashCode, sb.hashCode());
    }

    @Test
    public void testHashCodeWithDifferentContent() {
        TextStringBuilder sb1 = new TextStringBuilder("abc");
        TextStringBuilder sb2 = new TextStringBuilder("abd");
        assertNotEquals(sb1.hashCode(), sb2.hashCode());
    }

    @Test
    public void testHashCodeWithSameContent() {
        TextStringBuilder sb1 = new TextStringBuilder("abc");
        TextStringBuilder sb2 = new TextStringBuilder("abc");
        assertEquals(sb1.hashCode(), sb2.hashCode());
    }
}