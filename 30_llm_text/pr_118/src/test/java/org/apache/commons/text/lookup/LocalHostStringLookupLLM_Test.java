package org.apache.commons.text.lookup;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LocalHostStringLookupLLM_Test {

    @Test
    public void testAddress() throws UnknownHostException {
        Assertions.assertEquals(InetAddress.getLocalHost().getHostAddress(),
            LocalHostStringLookup.INSTANCE.lookup(InetAddressKeys.KEY_ADDRESS));
    }

    @Test
    public void testCanonicalName() throws UnknownHostException {
        Assertions.assertEquals(InetAddress.getLocalHost().getCanonicalHostName(),
            LocalHostStringLookup.INSTANCE.lookup(InetAddressKeys.KEY_CANONICAL_NAME));
    }

    @Test
    public void testName() throws UnknownHostException {
        Assertions.assertEquals(InetAddress.getLocalHost().getHostName(),
            LocalHostStringLookup.INSTANCE.lookup(InetAddressKeys.KEY_NAME));
    }

    @Test
    public void testNull() {
        Assertions.assertNull(LocalHostStringLookup.INSTANCE.lookup(null));
    }
}