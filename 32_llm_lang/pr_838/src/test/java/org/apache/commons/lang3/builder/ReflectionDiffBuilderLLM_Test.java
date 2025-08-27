package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.apache.commons.lang3.AbstractLangTest;
import org.junit.jupiter.api.Test;

public class ReflectionDiffBuilderLLM_Test extends AbstractLangTest {

    private static final ToStringStyle SHORT_STYLE = ToStringStyle.SHORT_PREFIX_STYLE;

    @SuppressWarnings("unused")
    private static class TypeTestClass implements Diffable<TypeTestClass> {
        private final ToStringStyle style = SHORT_STYLE;
        private final boolean booleanField = true;
        private final boolean[] booleanArrayField = {true};
        private final byte byteField = (byte) 0xFF;
        private final byte[] byteArrayField = {(byte) 0xFF};
        private char charField = 'a';
        private char[] charArrayField = {'a'};
        private final double doubleField = 1.0;
        private final double[] doubleArrayField = {1.0};
        private final float floatField = 1.0f;
        private final float[] floatArrayField = {1.0f};
        int intField = 1;
        private final int[] intArrayField = {1};
        private final long longField = 1L;
        private final long[] longArrayField = {1L};
        private final short shortField = 1;
        private final short[] shortArrayField = {1};
        private final Object objectField = null;
        private final Object[] objectArrayField = {null};
        private static int staticField;
        private transient String transientField;
        private String password = "secret";
        private String lastModificationDate = "2023-10-01";

        @Override
        public DiffResult diff(final TypeTestClass obj) {
            return new ReflectionDiffBuilder(this, obj, style).build();
        }

        @Override
        public int hashCode() {
            return HashCodeBuilder.reflectionHashCode(this, false);
        }

        @Override
        public boolean equals(final Object obj) {
            return EqualsBuilder.reflectionEquals(this, obj, false);
        }
    }

    @Test
    public void test_exclude_field_names() {
        final TypeTestClass firstObject = new TypeTestClass();
        final TypeTestClass secondObject = new TypeTestClass();
        secondObject.password = "newSecret";
        secondObject.lastModificationDate = "2023-10-02";

        final DiffResult list = new ReflectionDiffBuilder<>(firstObject, secondObject, SHORT_STYLE)
                .setExcludeFieldNames("password", "lastModificationDate")
                .build();

        assertEquals(0, list.getNumberOfDiffs());
    }

    @Test
    public void test_include_excluded_fields() {
        final TypeTestClass firstObject = new TypeTestClass();
        final TypeTestClass secondObject = new TypeTestClass();
        secondObject.password = "newSecret";
        secondObject.lastModificationDate = "2023-10-02";

        final DiffResult list = new ReflectionDiffBuilder<>(firstObject, secondObject, SHORT_STYLE)
                .build();

        assertEquals(2, list.getNumberOfDiffs());
    }
}