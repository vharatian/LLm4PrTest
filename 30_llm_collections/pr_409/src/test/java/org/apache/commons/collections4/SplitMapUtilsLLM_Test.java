package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.collections4.splitmap.TransformedSplitMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SplitMapUtilsLLM_Test {

    private Map<String, Integer> backingMap;
    private TransformedSplitMap<String, String, String, Integer> transformedMap;
    private final Transformer<String, Integer> stringToInt = Integer::valueOf;

    @BeforeEach
    public void setUp() throws Exception {
        backingMap = new HashMap<>();
        transformedMap = TransformedSplitMap.transformingMap(backingMap, NOPTransformer.<String>nopTransformer(), stringToInt);
        for (int i = 0; i < 10; i++) {
            transformedMap.put(String.valueOf(i), String.valueOf(i));
        }
    }

    @Test
    public void testReadableMapWithNullGet() {
        assertThrows(NullPointerException.class, () -> {
            SplitMapUtils.readableMap(null);
        });
    }

    @Test
    public void testWritableMapWithNullPut() {
        assertThrows(NullPointerException.class, () -> {
            SplitMapUtils.writableMap(null);
        });
    }

    @Test
    public void testReadableMapWithNonIterableMap() {
        final Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(String.valueOf(i), i);
        }
        final IterableMap<String, Integer> readableMap = SplitMapUtils.readableMap(map::get);
        for (int i = 0; i < 10; i++) {
            assertEquals(i, readableMap.get(String.valueOf(i)).intValue());
        }
    }

    @Test
    public void testWritableMapWithNonMapPut() {
        final Put<String, String> put = new Put<String, String>() {
            private final Map<String, String> internalMap = new HashMap<>();

            @Override
            public String put(String key, String value) {
                return internalMap.put(key, value);
            }

            @Override
            public void putAll(Map<? extends String, ? extends String> t) {
                internalMap.putAll(t);
            }

            @Override
            public void clear() {
                internalMap.clear();
            }
        };

        final Map<String, String> writableMap = SplitMapUtils.writableMap(put);
        writableMap.put("foo", "bar");
        assertEquals("bar", writableMap.get("foo"));
    }
}