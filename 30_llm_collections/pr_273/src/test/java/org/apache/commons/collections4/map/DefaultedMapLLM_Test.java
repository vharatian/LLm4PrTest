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
     * Test the constructor change to ensure it works with diamond operator.
     */
    @Test
    public void testConstructorWithDiamondOperator() {
        final Transformer<K, V> transformer = input -> (V) "DEFAULT";
        final DefaultedMap<K, V> map = new DefaultedMap<>(transformer);
        assertNotNull(map);
        assertEquals("DEFAULT", map.get("anyKey"));
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }
}