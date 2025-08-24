package org.apache.commons.lang3.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.apache.commons.lang3.AbstractLangTest;
import org.junit.jupiter.api.Test;

public class IEEE754rUtilsLLM_Test extends AbstractLangTest {

    @Test
    public void testConstructorExists() {
        new IEEE754rUtils();
    }

    @Test
    public void testEnforceExceptions() {
        assertThrows(
            NullPointerException.class,
            () -> IEEE754rUtils.min((float[]) null),
            "IllegalArgumentException expected for null input"
        );
        assertThrows(
            IllegalArgumentException.class,
            IEEE754rUtils::min,
            "IllegalArgumentException expected for empty input"
        );
        assertThrows(
            NullPointerException.class,
            () -> IEEE754rUtils.max((float[]) null),
            "IllegalArgumentException expected for null input"
        );
        assertThrows(
            IllegalArgumentException.class,
            IEEE754rUtils::max,
            "IllegalArgumentException expected for empty input"
        );
        assertThrows(
            NullPointerException.class,
            () -> IEEE754rUtils.min((double[]) null),
            "IllegalArgumentException expected for null input"
        );
        assertThrows(
            IllegalArgumentException.class,
            IEEE754rUtils::min,
            "IllegalArgumentException expected for empty input"
        );
        assertThrows(
            NullPointerException.class,
            () -> IEEE754rUtils.max((double[]) null),
            "IllegalArgumentException expected for null input"
        );
        assertThrows(
            IllegalArgumentException.class,
            IEEE754rUtils::max,
            "IllegalArgumentException expected for empty input"
        );
    }

    @Test
    public void testLang381() {
        assertEquals(1.2, IEEE754rUtils.min(1.2, 2.5, Double.NaN), 0.01);
        assertEquals(2.5, IEEE754rUtils.max(1.2, 2.5, Double.NaN), 0.01);
        assertTrue(Double.isNaN(IEEE754rUtils.max(Double.NaN, Double.NaN, Double.NaN)));
        assertEquals(1.2f, IEEE754rUtils.min(1.2f, 2.5f, Float.NaN), 0.01);
        assertEquals(2.5f, IEEE754rUtils.max(1.2f, 2.5f, Float.NaN), 0.01);
        assertTrue(Float.isNaN(IEEE754rUtils.max(Float.NaN, Float.NaN, Float.NaN)));
        final double[] a = { 1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN };
        assertEquals(42.0, IEEE754rUtils.max(a), 0.01);
        assertEquals(1.2, IEEE754rUtils.min(a), 0.01);
        final double[] b = { Double.NaN, 1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN };
        assertEquals(42.0, IEEE754rUtils.max(b), 0.01);
        assertEquals(1.2, IEEE754rUtils.min(b), 0.01);
        final float[] aF = { 1.2f, Float.NaN, 3.7f, 27.0f, 42.0f, Float.NaN };
        assertEquals(1.2f, IEEE754rUtils.min(aF), 0.01);
        assertEquals(42.0f, IEEE754rUtils.max(aF), 0.01);
        final float[] bF = { Float.NaN, 1.2f, Float.NaN, 3.7f, 27.0f, 42.0f, Float.NaN };
        assertEquals(1.2f, IEEE754rUtils.min(bF), 0.01);
        assertEquals(42.0f, IEEE754rUtils.max(bF), 0.01);
    }
}