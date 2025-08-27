package org.apache.commons.text.lookup;

import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;

public class DefaultStringLookupLLM_Test {

    @Test
    public void testNewEnumDNS() {
        assertSame(DefaultStringLookup.DNS.getStringLookup(), StringLookupFactory.INSTANCE.dnsStringLookup());
    }
}