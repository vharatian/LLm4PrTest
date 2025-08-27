package org.apache.commons.collections4.map;

import org.apache.commons.collections4.CollectionUtils;
import junit.framework.Test;
import org.apache.commons.collections4.BulkTest;

public class LinkedMapLLM_Test<K, V> extends AbstractOrderedMapTest<K, V> {

    public LinkedMapTest2(final String testName) {
        super(testName);
    }

    public static Test suite() {
        return BulkTest.makeSuite(LinkedMapTest2.class);
    }

    @Override
    public LinkedMap<K, V> makeObject() {
        return new LinkedMap<>();
    }

    @Override
    public LinkedMap<K, V> makeFullMap() {
        return (LinkedMap<K, V>) super.makeFullMap();
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

    /**
     * Test the indexOf method to ensure it returns CollectionUtils.INDEX_NOT_FOUND
     * when the key is not found.
     */
    public void testIndexOfNotFound() {
        resetEmpty();
        LinkedMap<K, V> lm = getMap();
        assertEquals(CollectionUtils.INDEX_NOT_FOUND, lm.indexOf(getOtherKeys()));
    }
}