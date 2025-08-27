package org.apache.commons.compress.archivers.sevenz;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;

public class SevenZArchiveEntryLLM_Test {

    @Test
    public void testEqualSevenZMethodsWhenBothIterablesAreEmpty() {
        SevenZArchiveEntry entry1 = new SevenZArchiveEntry();
        SevenZArchiveEntry entry2 = new SevenZArchiveEntry();
        entry1.setContentMethods(Arrays.asList());
        entry2.setContentMethods(Arrays.asList());
        Assert.assertEquals(entry1, entry2);
    }

    @Test
    public void testEqualSevenZMethodsWhenFirstIterableIsEmpty() {
        SevenZArchiveEntry entry1 = new SevenZArchiveEntry();
        SevenZArchiveEntry entry2 = new SevenZArchiveEntry();
        entry1.setContentMethods(Arrays.asList());
        entry2.setContentMethods(Arrays.asList(new SevenZMethodConfiguration(SevenZMethod.COPY)));
        Assert.assertNotEquals(entry1, entry2);
    }

    @Test
    public void testEqualSevenZMethodsWhenSecondIterableIsEmpty() {
        SevenZArchiveEntry entry1 = new SevenZArchiveEntry();
        SevenZArchiveEntry entry2 = new SevenZArchiveEntry();
        entry1.setContentMethods(Arrays.asList(new SevenZMethodConfiguration(SevenZMethod.COPY)));
        entry2.setContentMethods(Arrays.asList());
        Assert.assertNotEquals(entry1, entry2);
    }

    @Test
    public void testEqualSevenZMethodsWhenBothIterablesHaveSameElements() {
        SevenZArchiveEntry entry1 = new SevenZArchiveEntry();
        SevenZArchiveEntry entry2 = new SevenZArchiveEntry();
        entry1.setContentMethods(Arrays.asList(new SevenZMethodConfiguration(SevenZMethod.COPY)));
        entry2.setContentMethods(Arrays.asList(new SevenZMethodConfiguration(SevenZMethod.COPY)));
        Assert.assertEquals(entry1, entry2);
    }

    @Test
    public void testEqualSevenZMethodsWhenBothIterablesHaveDifferentElements() {
        SevenZArchiveEntry entry1 = new SevenZArchiveEntry();
        SevenZArchiveEntry entry2 = new SevenZArchiveEntry();
        entry1.setContentMethods(Arrays.asList(new SevenZMethodConfiguration(SevenZMethod.COPY)));
        entry2.setContentMethods(Arrays.asList(new SevenZMethodConfiguration(SevenZMethod.LZMA2)));
        Assert.assertNotEquals(entry1, entry2);
    }
}