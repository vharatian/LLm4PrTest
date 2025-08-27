package org.apache.commons.collections4.map;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.FactoryUtils;
import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.TransformerUtils;
import org.apache.commons.collections4.functors.ConstantFactory;
import org.junit.Test;
import static org.junit.Assert.*;

public class DefaultedMapLLM_Test<K, V> extends AbstractIterableMapTest<K, V> {
    protected final Factory<V> nullFactory = FactoryUtils.<V>nullFactory();
    protected final Transformer<K, V> nullTransformer = TransformerUtils.<K, V>nullTransformer();

    public DefaultedMapTest2(final String testName) {
        super(testName);
    }

    @Override
    public IterableMap<K, V> makeObject() {
        return DefaultedMap.defaultedMap(new HashMap<K, V>(), nullFactory);
    }

    /**
     * Test to ensure the final modifier added to the variable 'v' in the get method works correctly.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testMapGetWithFinalVariable() {
        final Map<K, V> map = new DefaultedMap<>((V) "NULL");
        assertEquals(0, map.size());
        assertEquals(false, map.containsKey("NotInMap"));
        assertEquals("NULL", map.get("NotInMap"));
        map.put((K) "Key", (V) "Value");
        assertEquals(1, map.size());
        assertEquals(true, map.containsKey("Key"));
        assertEquals("Value", map.get("Key"));
        assertEquals(false, map.containsKey("NotInMap"));
        assertEquals("NULL", map.get("NotInMap"));
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }
}