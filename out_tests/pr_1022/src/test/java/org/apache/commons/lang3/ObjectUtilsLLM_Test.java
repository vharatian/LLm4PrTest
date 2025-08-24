package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.util.Comparator;
import org.apache.commons.lang3.text.StrBuilder;
import org.junit.jupiter.api.Test;

public class ObjectUtilsLLM_Test {

    @Test
    public void testIdentityToStringAppendable_NullObject() {
        assertThrows(NullPointerException.class, () -> ObjectUtils.identityToString(new StringBuilder(), null));
    }

    @Test
    public void testIdentityToStringStrBuilder_NullObject() {
        assertThrows(NullPointerException.class, () -> ObjectUtils.identityToString(new StrBuilder(), null));
    }

    @Test
    public void testIdentityToStringStringBuffer_NullObject() {
        assertThrows(NullPointerException.class, () -> ObjectUtils.identityToString(new StringBuffer(), null));
    }

    @Test
    public void testIdentityToStringStringBuilder_NullObject() {
        assertThrows(NullPointerException.class, () -> ObjectUtils.identityToString(new StringBuilder(), null));
    }

    @Test
    public void testMedian_NullComparator() {
        assertThrows(NullPointerException.class, () -> ObjectUtils.median((Comparator<Object>) null, new Object()));
    }
}