package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class EqualsBuilderLLM_Test {

    @Test
    public void testSetTestRecursiveWithBypassReflectionClasses() {
        TestRecursiveInnerObject i1_1 = new TestRecursiveInnerObject(1);
        TestRecursiveInnerObject i1_2 = new TestRecursiveInnerObject(1);
        TestRecursiveInnerObject i2_1 = new TestRecursiveInnerObject(2);

        List<Class<?>> bypassClasses = new ArrayList<>();
        bypassClasses.add(TestRecursiveInnerObject.class);

        EqualsBuilder builder = new EqualsBuilder()
            .setTestRecursive(true)
            .setBypassReflectionClasses(bypassClasses);

        assertFalse(builder.append(i1_1, i2_1).isEquals());
        assertTrue(builder.append(i1_1, i1_2).isEquals());
    }

    @Test
    public void testSetBypassReflectionClassesWithTestRecursive() {
        TestRecursiveInnerObject i1_1 = new TestRecursiveInnerObject(1);
        TestRecursiveInnerObject i1_2 = new TestRecursiveInnerObject(1);
        TestRecursiveInnerObject i2_1 = new TestRecursiveInnerObject(2);

        List<Class<?>> bypassClasses = new ArrayList<>();
        bypassClasses.add(TestRecursiveInnerObject.class);

        EqualsBuilder builder = new EqualsBuilder()
            .setBypassReflectionClasses(bypassClasses)
            .setTestRecursive(true);

        assertFalse(builder.append(i1_1, i2_1).isEquals());
        assertTrue(builder.append(i1_1, i1_2).isEquals());
    }
}