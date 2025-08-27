package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MapUtilsLLM_Test {

    @Test
    public void testGetIntegerWithNullSafe() {
        Map<String, Object> map = new HashMap<>();
        map.put("key1", 123);
        map.put("key2", "456");
        map.put("key3", null);

        assertEquals(Integer.valueOf(123), MapUtils.getInteger(map, "key1"));
        assertEquals(Integer.valueOf(456), MapUtils.getInteger(map, "key2"));
        assertEquals(null, MapUtils.getInteger(map, "key3"));
        assertEquals(null, MapUtils.getInteger(map, "key4"));
    }

    @Test
    public void testMultiValueMapWithArrayList() {
        Map<String, Collection<String>> map = new HashMap<>();
        MultiValueMap<String, String> multiValueMap = MapUtils.multiValueMap(map);

        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");

        assertEquals(2, multiValueMap.get("key1").size());
        assertTrue(multiValueMap.get("key1").contains("value1"));
        assertTrue(multiValueMap.get("key1").contains("value2"));
    }

    @Test
    public void testVerbosePrintInternalAncestor() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> childMap = new HashMap<>();
        map.put("child", childMap);
        childMap.put("parent", map);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream outPrint = new PrintStream(out);

        MapUtils.verbosePrint(outPrint, "Test Map", map);

        String expectedOutput = "Test Map = \n{\n child = \n {\n  parent = (ancestor[0] Map)\n }\n}\n";
        assertEquals(expectedOutput, out.toString().replaceAll("\r\n", "\n"));
    }
}