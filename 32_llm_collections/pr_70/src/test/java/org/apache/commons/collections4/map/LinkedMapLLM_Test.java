package org.apache.commons.collections4.map;

import java.util.function.Predicate;
import junit.framework.Test;
import org.apache.commons.collections4.BulkTest;
import org.apache.commons.collections4.list.AbstractListTest;

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

    public BulkTest bulkTestListView() {
        return new TestListView();
    }

    public class TestListView extends AbstractListTest<K> {
        TestListView() {
            super("TestListView");
        }

        @Override
        public List<K> makeObject() {
            return LinkedMapTest2.this.makeObject().asList();
        }

        @Override
        public List<K> makeFullCollection() {
            return LinkedMapTest2.this.makeFullMap().asList();
        }

        @Override
        public K[] getFullElements() {
            return LinkedMapTest2.this.getSampleKeys();
        }

        @Override
        public boolean isAddSupported() {
            return false;
        }

        @Override
        public boolean isRemoveSupported() {
            return false;
        }

        @Override
        public boolean isSetSupported() {
            return false;
        }

        @Override
        public boolean isNullSupported() {
            return LinkedMapTest2.this.isAllowNullKey();
        }

        @Override
        public boolean isTestSerialization() {
            return false;
        }
    }

    public void testRemoveIf() {
        LinkedMap<K, V> lm = makeFullMap();
        List<K> list = lm.asList();
        try {
            list.removeIf(new Predicate<K>() {
                @Override
                public boolean test(K k) {
                    return true;
                }
            });
            fail("UnsupportedOperationException expected");
        } catch (UnsupportedOperationException ex) {
            // expected
        }
    }
}