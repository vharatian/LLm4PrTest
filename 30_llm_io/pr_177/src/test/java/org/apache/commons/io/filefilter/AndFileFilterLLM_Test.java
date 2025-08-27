package org.apache.commons.io.filefilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class AndFileFilterLLM_Test {

    @Test
    public void testToString() {
        IOFileFilter filter1 = new NameFileFilter("test1");
        IOFileFilter filter2 = new NameFileFilter("test2");
        AndFileFilter andFileFilter = new AndFileFilter(filter1, filter2);
        
        String expected = "org.apache.commons.io.filefilter.AndFileFilter(" + filter1 + "," + filter2 + ")";
        assertEquals(expected, andFileFilter.toString());
    }

    @Test
    public void testToStringWithEmptyFilter() {
        AndFileFilter andFileFilter = new AndFileFilter();
        
        String expected = "org.apache.commons.io.filefilter.AndFileFilter()";
        assertEquals(expected, andFileFilter.toString());
    }

    @Test
    public void testToStringWithSingleFilter() {
        IOFileFilter filter1 = new NameFileFilter("test1");
        AndFileFilter andFileFilter = new AndFileFilter(filter1);
        
        String expected = "org.apache.commons.io.filefilter.AndFileFilter(" + filter1 + ")";
        assertEquals(expected, andFileFilter.toString());
    }
}