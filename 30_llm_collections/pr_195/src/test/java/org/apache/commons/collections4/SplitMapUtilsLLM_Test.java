package org.apache.commons.collections4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.collections4.functors.NOPTransformer;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.collections4.splitmap.TransformedSplitMap;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("boxing")
public class SplitMapUtilsLLM_Test {

    private Map<String, Integer> backingMap;
    private TransformedSplitMap<String, String, String, Integer> transformedMap;
    private final Transformer<String, Integer> stringToInt = Integer::valueOf;

    @Before
    public void setUp() throws Exception {
        backingMap = new HashMap<>();
        transformedMap = TransformedSplitMap.transformingMap(backingMap, NOPTransformer.<String>nopTransformer(), stringToInt);
        for (int i = 0; i < 10; i++) {
            transformedMap.put(String.valueOf(i), String.valueOf(i));
        }
    }

    @Test
    public void testReadableMapInstance() {
        final IterableMap<String, Integer> map = SplitMapUtils.readableMap(transformedMap);
        assertTrue(map instanceof SplitMapUtils.WrappedGet);
    }

    @Test
    public void testWritableMapInstance() {
        final Map<String, String> map = SplitMapUtils.writableMap(transformedMap);
        assertTrue(map instanceof SplitMapUtils.WrappedPut);
    }
}