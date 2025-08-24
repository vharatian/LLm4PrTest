package org.apache.commons.compress.archivers.dump;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DumpArchiveSummaryLLM_Test {

    private DumpArchiveSummary summary1;
    private DumpArchiveSummary summary2;

    @BeforeEach
    public void setUp() throws Exception {
        byte[] buffer = new byte[1000];
        ZipEncoding encoding = null; // Mock or actual implementation of ZipEncoding
        summary1 = new DumpArchiveSummary(buffer, encoding);
        summary2 = new DumpArchiveSummary(buffer, encoding);
    }

    @Test
    public void testGetLabel() {
        String label1 = summary1.getLabel();
        String label2 = summary2.getLabel();
        assertEquals(label1, label2, "Labels should be equal");
    }

    @Test
    public void testEquals() {
        assertEquals(summary1, summary2, "Summaries should be equal");
    }

    @Test
    public void testHashCode() {
        assertEquals(summary1.hashCode(), summary2.hashCode(), "Hash codes should be equal");
    }

    @Test
    public void testNotEquals() {
        summary2.setLabel("Different Label");
        assertNotEquals(summary1, summary2, "Summaries should not be equal after changing label");
    }
}