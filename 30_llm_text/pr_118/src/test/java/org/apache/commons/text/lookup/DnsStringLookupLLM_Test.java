package org.apache.commons.text.lookup;

import org.junit.jupiter.api.Test;
import java.net.InetAddress;
import java.net.UnknownHostException;
import static org.junit.jupiter.api.Assertions.*;

class DnsStringLookupLLM_Test {

    @Test
    void testLookupHostName() throws UnknownHostException {
        String key = "name|127.0.0.1";
        InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
        String expected = inetAddress.getHostName();
        assertEquals(expected, DnsStringLookup.INSTANCE.lookup(key));
    }

    @Test
    void testLookupCanonicalHostName() throws UnknownHostException {
        String key = "canonical-name|127.0.0.1";
        InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
        String expected = inetAddress.getCanonicalHostName();
        assertEquals(expected, DnsStringLookup.INSTANCE.lookup(key));
    }

    @Test
    void testLookupHostAddress() throws UnknownHostException {
        String key = "address|localhost";
        InetAddress inetAddress = InetAddress.getByName("localhost");
        String expected = inetAddress.getHostAddress();
        assertEquals(expected, DnsStringLookup.INSTANCE.lookup(key));
    }

    @Test
    void testLookupDefaultAddress() throws UnknownHostException {
        String key = "localhost";
        InetAddress inetAddress = InetAddress.getByName("localhost");
        String expected = inetAddress.getHostAddress();
        assertEquals(expected, DnsStringLookup.INSTANCE.lookup(key));
    }

    @Test
    void testLookupNullKey() {
        assertNull(DnsStringLookup.INSTANCE.lookup(null));
    }

    @Test
    void testLookupUnknownHost() {
        String key = "name|unknown.host";
        assertNull(DnsStringLookup.INSTANCE.lookup(key));
    }
}