package org.apache.commons.io.filefilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class OrFileFilterLLM_Test {

    @Test
    public void testToString() {
        IOFileFilter filter1 = new NameFileFilter("test1");
        IOFileFilter filter2 = new NameFileFilter("test2");
        OrFileFilter orFileFilter = new OrFileFilter(filter1, filter2);

        String expected = "org.apache.commons.io.filefilter.OrFileFilter(" + filter1 + "," + filter2 + ")";
        assertEquals(expected, orFileFilter.toString());
    }

    @Test
    public void testToStringWithEmptyFilter() {
        OrFileFilter orFileFilter = new OrFileFilter();
        String expected = "org.apache.commons.io.filefilter.OrFileFilter()";
        assertEquals(expected, orFileFilter.toString());
    }

    @Test
    public void testToStringWithSingleFilter() {
        IOFileFilter filter1 = new NameFileFilter("test1");
        OrFileFilter orFileFilter = new OrFileFilter(filter1);

        String expected = "org.apache.commons.io.filefilter.OrFileFilter(" + filter1 + ")";
        assertEquals(expected, orFileFilter.toString());
    }
}