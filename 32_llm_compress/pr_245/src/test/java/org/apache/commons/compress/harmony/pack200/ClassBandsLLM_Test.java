package org.apache.commons.compress.harmony.pack200;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassBandsLLM_Test {

    private ClassBands classBands;
    private List<NewAttributeBands> classAttributeBands;
    private List<NewAttributeBands> methodAttributeBands;
    private List<NewAttributeBands> fieldAttributeBands;
    private List<NewAttributeBands> codeAttributeBands;

    @BeforeEach
    public void setUp() throws IOException {
        Segment segment = new Segment();
        classBands = new ClassBands(segment, 1, 1, false);
        classAttributeBands = new ArrayList<>();
        methodAttributeBands = new ArrayList<>();
        fieldAttributeBands = new ArrayList<>();
        codeAttributeBands = new ArrayList<>();
    }

    @Test
    public void testFinaliseBandsSorting() {
        // Prepare test data
        NewAttributeBands band1 = new NewAttributeBands(1, null, null, null);
        NewAttributeBands band2 = new NewAttributeBands(2, null, null, null);
        NewAttributeBands band3 = new NewAttributeBands(3, null, null, null);

        classAttributeBands.add(band3);
        classAttributeBands.add(band1);
        classAttributeBands.add(band2);

        methodAttributeBands.add(band3);
        methodAttributeBands.add(band1);
        methodAttributeBands.add(band2);

        fieldAttributeBands.add(band3);
        fieldAttributeBands.add(band1);
        fieldAttributeBands.add(band2);

        codeAttributeBands.add(band3);
        codeAttributeBands.add(band1);
        codeAttributeBands.add(band2);

        // Sort the bands
        Comparator<NewAttributeBands> comparator = (arg0, arg1) -> arg0.getFlagIndex() - arg1.getFlagIndex();
        classAttributeBands.sort(comparator);
        methodAttributeBands.sort(comparator);
        fieldAttributeBands.sort(comparator);
        codeAttributeBands.sort(comparator);

        // Verify the sorting
        assertEquals(band1, classAttributeBands.get(0));
        assertEquals(band2, classAttributeBands.get(1));
        assertEquals(band3, classAttributeBands.get(2));

        assertEquals(band1, methodAttributeBands.get(0));
        assertEquals(band2, methodAttributeBands.get(1));
        assertEquals(band3, methodAttributeBands.get(2));

        assertEquals(band1, fieldAttributeBands.get(0));
        assertEquals(band2, fieldAttributeBands.get(1));
        assertEquals(band3, fieldAttributeBands.get(2));

        assertEquals(band1, codeAttributeBands.get(0));
        assertEquals(band2, codeAttributeBands.get(1));
        assertEquals(band3, codeAttributeBands.get(2));
    }
}