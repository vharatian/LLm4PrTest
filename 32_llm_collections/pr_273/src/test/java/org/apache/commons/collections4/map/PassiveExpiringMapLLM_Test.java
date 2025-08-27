package org.apache.commons.collections4.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import junit.framework.Test;
import org.apache.commons.collections4.BulkTest;
import org.apache.commons.collections4.map.PassiveExpiringMap.ExpirationPolicy;

public class PassiveExpiringMapLLM_Test<K, V> extends AbstractMapTest<K, V> {

    private static class TestExpirationPolicy implements ExpirationPolicy<Integer, String> {
        private static final long serialVersionUID = 1L;

        @Override
        public long expirationTime(final Integer key, final String value) {
            if (key == null) {
                return 0;
            }
            if (key.intValue() % 2 == 0) {
                return -1;
            }
            return 0;
        }
    }

    public static Test suite() {
        return BulkTest.makeSuite(PassiveExpiringMapTest2.class);
    }

    public PassiveExpiringMapTest2(final String testName) {
        super(testName);
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

    private Map<Integer, String> makeDecoratedTestMap() {
        final Map<Integer, String> m = new HashMap<>();
        m.put(Integer.valueOf(1), "one");
        m.put(Integer.valueOf(2), "two");
        m.put(Integer.valueOf(3), "three");
        m.put(Integer.valueOf(4), "four");
        m.put(Integer.valueOf(5), "five");
        m.put(Integer.valueOf(6), "six");
        return new PassiveExpiringMap<>(new TestExpirationPolicy(), m);
    }

    @Override
    public Map<K, V> makeObject() {
        return new PassiveExpiringMap<>();
    }

    private Map<Integer, String> makeTestMap() {
        final Map<Integer, String> m = new PassiveExpiringMap<>(new TestExpirationPolicy());
        m.put(Integer.valueOf(1), "one");
        m.put(Integer.valueOf(2), "two");
        m.put(Integer.valueOf(3), "three");
        m.put(Integer.valueOf(4), "four");
        m.put(Integer.valueOf(5), "five");
        m.put(Integer.valueOf(6), "six");
        return m;
    }

    public void testGenericConstructor() {
        // Test the constructor with generic type inference
        final ExpirationPolicy<Integer, String> policy = new TestExpirationPolicy();
        final PassiveExpiringMap<Integer, String> map = new PassiveExpiringMap<>(policy);
        assertNotNull(map);
    }

    public void testGenericConstructorWithTimeToLive() {
        // Test the constructor with generic type inference and time to live
        final PassiveExpiringMap<Integer, String> map = new PassiveExpiringMap<>(1000L);
        assertNotNull(map);
    }

    public void testGenericConstructorWithTimeToLiveAndMap() {
        // Test the constructor with generic type inference, time to live, and a map
        final Map<Integer, String> initialMap = new HashMap<>();
        initialMap.put(1, "one");
        final PassiveExpiringMap<Integer, String> map = new PassiveExpiringMap<>(1000L, initialMap);
        assertNotNull(map);
        assertEquals("one", map.get(1));
    }

    public void testGenericConstructorWithTimeToLiveAndTimeUnit() {
        // Test the constructor with generic type inference, time to live, and time unit
        final PassiveExpiringMap<Integer, String> map = new PassiveExpiringMap<>(1L, TimeUnit.SECONDS);
        assertNotNull(map);
    }

    public void testGenericConstructorWithTimeToLiveTimeUnitAndMap() {
        // Test the constructor with generic type inference, time to live, time unit, and a map
        final Map<Integer, String> initialMap = new HashMap<>();
        initialMap.put(1, "one");
        final PassiveExpiringMap<Integer, String> map = new PassiveExpiringMap<>(1L, TimeUnit.SECONDS, initialMap);
        assertNotNull(map);
        assertEquals("one", map.get(1));
    }
}