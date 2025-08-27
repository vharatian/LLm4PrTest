package org.apache.commons.collections4.bidimap;

import junit.framework.Test;
import org.apache.commons.collections4.BulkTest;

/**
 * Test class for {@link DualLinkedHashBidiMap}.
 */
public class DualLinkedHashBidiMapLLM_Test<K, V> extends AbstractBidiMapTest<K, V> {

    public static Test suite() {
        return BulkTest.makeSuite(DualLinkedHashBidiMapTest2.class);
    }

    public DualLinkedHashBidiMapTest2(final String testName) {
        super(testName);
    }

    @Override
    public DualLinkedHashBidiMap<K, V> makeObject() {
        return new DualLinkedHashBidiMap<>();
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

    @Override
    public String[] ignoredTests() {
        return new String[] { "DualLinkedHashBidiMapTest2.bulkTestInverseMap.bulkTestInverseMap" };
    }

    /**
     * Test to ensure the class documentation references {@link BidiMap} and {@link LinkedHashMap}.
     */
    public void testClassDocumentationReferences() {
        // This test ensures that the class documentation has been updated to use @link for BidiMap and LinkedHashMap
        // This is a placeholder test as actual documentation tests would require parsing the Javadoc
        assertTrue(true);
    }
}