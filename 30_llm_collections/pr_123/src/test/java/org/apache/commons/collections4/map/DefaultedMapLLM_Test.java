package org.apache.commons.collections4.map;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.FactoryUtils;
import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.ConstantFactory;
import org.junit.Test;
import static org.junit.Assert.*;

public class DefaultedMapLLM_Test<K, V> extends AbstractIterableMapTest<K, V> {

    protected final Factory<V> nullFactory = FactoryUtils.<V>nullFactory();

    public DefaultedMapTest2(final String testName) {
        super(testName);
    }

    @Override
    public IterableMap<K, V> makeObject() {
        return DefaultedMap.defaultedMap(new HashMap<K, V>(), nullFactory);
    }

    /**
     * Test for null factory in defaultedMap method.
     */
    @Test(expected = NullPointerException.class)
    public void testDefaultedMapWithNullFactory() {
        DefaultedMap.defaultedMap(new HashMap<K, V>(), (Factory<V>) null);
    }

    /**
     * Test for null transformer in defaultedMap method.
     */
    @Test(expected = NullPointerException.class)
    public void testDefaultedMapWithNullTransformer() {
        DefaultedMap.defaultedMap(new HashMap<K, V>(), (Transformer<K, V>) null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void testMapGet() {
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

    @SuppressWarnings("unchecked")
    public void testMapGet2() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, (V) "NULL");
        assertEquals(0, map.size());
        assertEquals(0, base.size());
        assertEquals(false, map.containsKey("NotInMap"));
        assertEquals("NULL", map.get("NotInMap"));
        map.put((K) "Key", (V) "Value");
        assertEquals(1, map.size());
        assertEquals(1, base.size());
        assertEquals(true, map.containsKey("Key"));
        assertEquals("Value", map.get("Key"));
        assertEquals(false, map.containsKey("NotInMap"));
        assertEquals("NULL", map.get("NotInMap"));
    }

    @SuppressWarnings("unchecked")
    public void testMapGet3() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, ConstantFactory.constantFactory((V) "NULL"));
        assertEquals(0, map.size());
        assertEquals(0, base.size());
        assertEquals(false, map.containsKey("NotInMap"));
        assertEquals("NULL", map.get("NotInMap"));
        map.put((K) "Key", (V) "Value");
        assertEquals(1, map.size());
        assertEquals(1, base.size());
        assertEquals(true, map.containsKey("Key"));
        assertEquals("Value", map.get("Key"));
        assertEquals(false, map.containsKey("NotInMap"));
        assertEquals("NULL", map.get("NotInMap"));
    }

    @SuppressWarnings("unchecked")
    public void testMapGet4() {
        final HashMap<K, V> base = new HashMap<>();
        final Map<K, V> map = DefaultedMap.defaultedMap(base, (Transformer<K, V>) input -> {
            if (input instanceof String) {
                return (V) "NULL";
            }
            return (V) "NULL_OBJECT";
        });
        assertEquals(0, map.size());
        assertEquals(0, base.size());
        assertEquals(false, map.containsKey("NotInMap"));
        assertEquals("NULL", map.get("NotInMap"));
        assertEquals("NULL_OBJECT", map.get(Integer.valueOf(0)));
        map.put((K) "Key", (V) "Value");
        assertEquals(1, map.size());
        assertEquals(1, base.size());
        assertEquals(true, map.containsKey("Key"));
        assertEquals("Value", map.get("Key"));
        assertEquals(false, map.containsKey("NotInMap"));
        assertEquals("NULL", map.get("NotInMap"));
        assertEquals("NULL_OBJECT", map.get(Integer.valueOf(0)));
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }
}