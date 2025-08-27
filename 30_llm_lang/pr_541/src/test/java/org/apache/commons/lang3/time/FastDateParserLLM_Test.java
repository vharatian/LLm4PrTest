package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Comparator;
import java.util.Locale;
import java.util.TreeSet;
import org.junit.jupiter.api.Test;

public class FastDateParserLLM_Test {

    @Test
    public void testLongerFirstLowercaseComparator() {
        Comparator<String> comparator = Comparator.reverseOrder();
        TreeSet<String> set = new TreeSet<>(comparator);
        set.add("february");
        set.add("feb");
        set.add("march");
        set.add("mar");

        String[] expectedOrder = {"march", "mar", "february", "feb"};
        int i = 0;
        for (String s : set) {
            assertEquals(expectedOrder[i++], s);
        }
    }
}