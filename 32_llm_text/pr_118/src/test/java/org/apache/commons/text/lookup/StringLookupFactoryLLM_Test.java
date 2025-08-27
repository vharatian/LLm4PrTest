package org.apache.commons.text.lookup;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class StringLookupFactoryLLM_Test {

    @Test
    public void testAddDefaultStringLookupsMap() {
        final Map<String, StringLookup> stringLookupMap = new HashMap<>();
        StringLookupFactory.INSTANCE.addDefaultStringLookups(stringLookupMap);
        assertTrue(stringLookupMap.containsKey(StringLookupFactory.KEY_DNS));
    }

    @Test
    public void testSingletons() {
        final StringLookupFactory stringLookupFactory = StringLookupFactory.INSTANCE;
        Assertions.assertSame(DnsStringLookup.INSTANCE, stringLookupFactory.dnsStringLookup());
    }
}