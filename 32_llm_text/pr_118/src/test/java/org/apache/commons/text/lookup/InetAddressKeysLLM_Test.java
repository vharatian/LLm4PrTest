package org.apache.commons.text.lookup;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InetAddressKeysLLM_Test {

    @Test
    void testKeyAddress() {
        assertEquals("address", InetAddressKeys.KEY_ADDRESS);
    }

    @Test
    void testKeyCanonicalName() {
        assertEquals("canonical-name", InetAddressKeys.KEY_CANONICAL_NAME);
    }

    @Test
    void testKeyName() {
        assertEquals("name", InetAddressKeys.KEY_NAME);
    }
}