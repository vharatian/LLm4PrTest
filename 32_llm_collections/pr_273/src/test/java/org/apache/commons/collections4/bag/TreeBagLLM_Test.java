package org.apache.commons.collections4.bag;

import junit.framework.Test;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.BulkTest;
import org.apache.commons.collections4.SortedBag;

public class TreeBagLLM_Test<T> extends AbstractSortedBagTest<T> {

    public TreeBagTest2(final String testName) {
        super(testName);
    }

    public static Test suite() {
        return BulkTest.makeSuite(TreeBagTest2.class);
    }

    @Override
    public SortedBag<T> makeObject() {
        return new TreeBag<>();
    }

    /**
     * Test to ensure the TreeBag constructors work correctly with the new diamond operator syntax.
     */
    public void testTreeBagConstructors() {
        // Test default constructor
        TreeBag<String> bag1 = new TreeBag<>();
        assertNotNull("TreeBag should be created", bag1);

        // Test constructor with comparator
        TreeBag<String> bag2 = new TreeBag<>(String::compareTo);
        assertNotNull("TreeBag with comparator should be created", bag2);

        // Test constructor with collection
        TreeBag<String> bag3 = new TreeBag<>(bag2);
        assertNotNull("TreeBag with collection should be created", bag3);
    }

    /**
     * Test to ensure the readObject method works correctly with the new diamond operator syntax.
     */
    public void testReadObject() throws Exception {
        // Create a TreeBag and serialize it
        TreeBag<String> originalBag = new TreeBag<>(String::compareTo);
        originalBag.add("a");
        originalBag.add("b");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(originalBag);
        oos.close();

        // Deserialize the TreeBag
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        TreeBag<String> deserializedBag = (TreeBag<String>) ois.readObject();
        ois.close();

        // Verify the contents of the deserialized TreeBag
        assertEquals("Deserialized TreeBag should have the same size", originalBag.size(), deserializedBag.size());
        assertTrue("Deserialized TreeBag should contain 'a'", deserializedBag.contains("a"));
        assertTrue("Deserialized TreeBag should contain 'b'", deserializedBag.contains("b"));
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }
}