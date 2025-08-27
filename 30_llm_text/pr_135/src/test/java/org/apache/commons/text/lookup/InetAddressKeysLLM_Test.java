package org.apache.commons.text.lookup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;

public class InetAddressKeysLLM_Test {

    @Test
    public void testKeyAddress() {
        assertEquals("address", InetAddressKeys.KEY_ADDRESS);
    }

    @Test
    public void testKeyCanonicalName() {
        assertEquals("canonical-name", InetAddressKeys.KEY_CANONICAL_NAME);
    }

    @Test
    public void testKeyName() {
        assertEquals("name", InetAddressKeys.KEY_NAME);
    }
}