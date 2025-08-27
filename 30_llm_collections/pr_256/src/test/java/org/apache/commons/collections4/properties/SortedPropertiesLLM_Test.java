package org.apache.commons.collections4.properties;

import java.util.Map;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

public class SortedPropertiesLLM_Test {

    @Test
    public void testEntrySet() {
        final SortedProperties sortedProperties = new SortedProperties();
        for (char ch = 'Z'; ch >= 'A'; ch--) {
            sortedProperties.put(String.valueOf(ch), "Value" + ch);
        }
        Set<Map.Entry<Object, Object>> entrySet = sortedProperties.entrySet();
        char expectedKey = 'A';
        for (Map.Entry<Object, Object> entry : entrySet) {
            Assert.assertEquals(String.valueOf(expectedKey), entry.getKey());
            Assert.assertEquals("Value" + expectedKey, entry.getValue());
            expectedKey++;
        }
    }
}