package org.apache.commons.collections4.multiset;

import junit.framework.Test;
import org.apache.commons.collections4.BulkTest;
import org.apache.commons.collections4.MultiSet;

public class HashMultiSetLLM_Test<T> extends AbstractMultiSetTest<T> {

    public HashMultiSetTest2(final String testName) {
        super(testName);
    }

    public static Test suite() {
        return BulkTest.makeSuite(HashMultiSetTest2.class);
    }

    @Override
    public MultiSet<T> makeObject() {
        return new HashMultiSet<>();
    }

    @Override
    public String getCompatibilityVersion() {
        return "4.1";
    }

    // Test to ensure the constructor initializes the map correctly
    public void testConstructorInitializesMap() {
        HashMultiSet<T> multiSet = new HashMultiSet<>();
        assertNotNull("Map should be initialized", multiSet.getMap());
    }

    // Test to ensure the readObject method sets the map correctly
    public void testReadObjectInitializesMap() throws IOException, ClassNotFoundException {
        HashMultiSet<T> multiSet = new HashMultiSet<>();
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(multiSet);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        HashMultiSet<T> deserializedMultiSet = (HashMultiSet<T>) in.readObject();

        assertNotNull("Map should be initialized after deserialization", deserializedMultiSet.getMap());
    }
}