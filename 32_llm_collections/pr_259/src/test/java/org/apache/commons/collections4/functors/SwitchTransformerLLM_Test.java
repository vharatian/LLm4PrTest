package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.SwitchTransformer;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SwitchTransformerLLM_Test {

    @Test
    public void testSwitchTransformerWithNullDefault() {
        Predicate<Object> predicate = input -> input.equals("test");
        Transformer<Object, String> transformer = input -> "transformed";
        Transformer<Object, String> defaultTransformer = null;

        Transformer<Object, String> switchTransformer = SwitchTransformer.switchTransformer(
                new Predicate[]{predicate},
                new Transformer[]{transformer},
                defaultTransformer
        );

        assertEquals("transformed", switchTransformer.transform("test"));
        assertEquals(null, switchTransformer.transform("other"));
    }

    @Test
    public void testSwitchTransformerWithNonNullDefault() {
        Predicate<Object> predicate = input -> input.equals("test");
        Transformer<Object, String> transformer = input -> "transformed";
        Transformer<Object, String> defaultTransformer = input -> "default";

        Transformer<Object, String> switchTransformer = SwitchTransformer.switchTransformer(
                new Predicate[]{predicate},
                new Transformer[]{transformer},
                defaultTransformer
        );

        assertEquals("transformed", switchTransformer.transform("test"));
        assertEquals("default", switchTransformer.transform("other"));
    }

    @Test
    public void testSwitchTransformerMapWithNullDefault() {
        Predicate<Object> predicate = input -> input.equals("test");
        Transformer<Object, String> transformer = input -> "transformed";

        Map<Predicate<Object>, Transformer<Object, String>> map = new HashMap<>();
        map.put(predicate, transformer);
        map.put(null, null);

        Transformer<Object, String> switchTransformer = SwitchTransformer.switchTransformer(map);

        assertEquals("transformed", switchTransformer.transform("test"));
        assertEquals(null, switchTransformer.transform("other"));
    }

    @Test
    public void testSwitchTransformerMapWithNonNullDefault() {
        Predicate<Object> predicate = input -> input.equals("test");
        Transformer<Object, String> transformer = input -> "transformed";
        Transformer<Object, String> defaultTransformer = input -> "default";

        Map<Predicate<Object>, Transformer<Object, String>> map = new HashMap<>();
        map.put(predicate, transformer);
        map.put(null, defaultTransformer);

        Transformer<Object, String> switchTransformer = SwitchTransformer.switchTransformer(map);

        assertEquals("transformed", switchTransformer.transform("test"));
        assertEquals("default", switchTransformer.transform("other"));
    }
}