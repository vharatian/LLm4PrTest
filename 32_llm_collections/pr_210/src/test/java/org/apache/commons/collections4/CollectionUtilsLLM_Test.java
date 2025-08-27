package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CollectionUtilsLLM_Test {

    @Test
    public void testIndexNotFoundConstant() {
        assertEquals(-1, CollectionUtils.INDEX_NOT_FOUND, "INDEX_NOT_FOUND should be -1");
    }
}