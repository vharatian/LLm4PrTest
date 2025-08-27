package org.apache.commons.collections4.comparators;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReverseComparatorLLM_Test extends AbstractComparatorTest<Integer> {

    public ReverseComparatorTest2() {
        super(ReverseComparatorTest2.class.getSimpleName());
    }

    @Override
    public Comparator<Integer> makeObject() {
        return new ReverseComparator<>(Collections.<Integer>reverseOrder());
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

    @Override
    public List<Integer> getComparableObjectsOrdered() {
        final List<Integer> list = new LinkedList<>();
        list.add(Integer.valueOf(1));
        list.add(Integer.valueOf(2));
        list.add(Integer.valueOf(3));
        list.add(Integer.valueOf(4));
        list.add(Integer.valueOf(5));
        return list;
    }

    @Test
    @Override
    public void testSerializeDeserializeThenCompare() throws Exception {
        final Comparator<?> comp = new ReverseComparator<>(new ComparableComparator<String>());
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(buffer);
        out.writeObject(comp);
        out.close();
        final ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buffer.toByteArray()));
        final Object dest = in.readObject();
        in.close();
        assertEquals("obj != deserialize(serialize(obj))", comp, dest);
    }

    // New test to ensure the typo fix in the javadoc does not affect functionality
    @Test
    public void testEqualsWithCorrectJavadoc() {
        final Comparator<Integer> comparator1 = new ReverseComparator<>(Collections.reverseOrder());
        final Comparator<Integer> comparator2 = new ReverseComparator<>(Collections.reverseOrder());
        assertEquals(comparator1, comparator2, "Comparators should be equal");
    }
}