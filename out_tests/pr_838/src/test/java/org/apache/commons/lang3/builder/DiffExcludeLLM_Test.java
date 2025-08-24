package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.*;

public class DiffExcludeLLM_Test {

    @Test
    public void testDiffExcludeAnnotationPresence() throws NoSuchFieldException {
        // Create a test class with a field annotated with @DiffExclude
        class TestClass {
            @DiffExclude
            private String excludedField;
        }

        // Retrieve the field and check if it has the @DiffExclude annotation
        Field field = TestClass.class.getDeclaredField("excludedField");
        Annotation annotation = field.getAnnotation(DiffExclude.class);
        assertNotNull(annotation, "Field should be annotated with @DiffExclude");
    }

    @Test
    public void testDiffExcludeAnnotationRetentionPolicy() {
        // Check the retention policy of the @DiffExclude annotation
        Retention retention = DiffExclude.class.getAnnotation(Retention.class);
        assertNotNull(retention, "Retention annotation should be present on @DiffExclude");
        assertEquals(RetentionPolicy.RUNTIME, retention.value(), "Retention policy should be RUNTIME");
    }

    @Test
    public void testDiffExcludeAnnotationTarget() {
        // Check the target of the @DiffExclude annotation
        Target target = DiffExclude.class.getAnnotation(Target.class);
        assertNotNull(target, "Target annotation should be present on @DiffExclude");
        ElementType[] expectedTargets = {ElementType.FIELD};
        assertArrayEquals(expectedTargets, target.value(), "Target should be FIELD");
    }
}