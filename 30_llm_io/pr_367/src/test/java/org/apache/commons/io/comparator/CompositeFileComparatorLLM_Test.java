package org.apache.commons.io.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.util.Comparator;
import org.junit.jupiter.api.Test;

public class CompositeFileComparatorLLM_Test extends ComparatorAbstractTest {

    @Test
    public void testToStringWithMultipleComparators() {
        Comparator<File> sizeComparator = SizeFileComparator.SIZE_COMPARATOR;
        Comparator<File> extensionComparator = ExtensionFileComparator.EXTENSION_COMPARATOR;
        Comparator<File> compositeComparator = new CompositeFileComparator(sizeComparator, extensionComparator);

        String expected = "CompositeFileComparator{" + sizeComparator.toString() + "," + extensionComparator.toString() + "}";
        assertEquals(expected, compositeComparator.toString(), "toString with multiple comparators");
    }

    @Test
    public void testToStringWithSingleComparator() {
        Comparator<File> sizeComparator = SizeFileComparator.SIZE_COMPARATOR;
        Comparator<File> compositeComparator = new CompositeFileComparator(sizeComparator);

        String expected = "CompositeFileComparator{" + sizeComparator.toString() + "}";
        assertEquals(expected, compositeComparator.toString(), "toString with single comparator");
    }

    @Test
    public void testToStringWithNoComparators() {
        Comparator<File> compositeComparator = new CompositeFileComparator();

        String expected = "CompositeFileComparator{}";
        assertEquals(expected, compositeComparator.toString(), "toString with no comparators");
    }
}