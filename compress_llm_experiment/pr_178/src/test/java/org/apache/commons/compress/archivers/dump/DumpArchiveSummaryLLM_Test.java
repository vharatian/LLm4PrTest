package org.apache.commons.compress.archivers.dump;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DumpArchiveSummaryLLM_Test {

    @Test
    public void testEqualsWithDifferentDevname() {
        DumpArchiveSummary summary1 = new DumpArchiveSummary();
        summary1.setDumpDate(new Date(1000L));
        summary1.setHostname("host1");
        summary1.setDevname("dev1");

        DumpArchiveSummary summary2 = new DumpArchiveSummary();
        summary2.setDumpDate(new Date(1000L));
        summary2.setHostname("host1");
        summary2.setDevname("dev2");

        assertFalse(summary1.equals(summary2));
    }

    @Test
    public void testEqualsWithSameDevname() {
        DumpArchiveSummary summary1 = new DumpArchiveSummary();
        summary1.setDumpDate(new Date(1000L));
        summary1.setHostname("host1");
        summary1.setDevname("dev1");

        DumpArchiveSummary summary2 = new DumpArchiveSummary();
        summary2.setDumpDate(new Date(1000L));
        summary2.setHostname("host1");
        summary2.setDevname("dev1");

        assertTrue(summary1.equals(summary2));
    }

    @Test
    public void testEqualsWithNullDevname() {
        DumpArchiveSummary summary1 = new DumpArchiveSummary();
        summary1.setDumpDate(new Date(1000L));
        summary1.setHostname("host1");
        summary1.setDevname(null);

        DumpArchiveSummary summary2 = new DumpArchiveSummary();
        summary2.setDumpDate(new Date(1000L));
        summary2.setHostname("host1");
        summary2.setDevname(null);

        assertTrue(summary1.equals(summary2));
    }

    @Test
    public void testEqualsWithNonNullAndNullDevname() {
        DumpArchiveSummary summary1 = new DumpArchiveSummary();
        summary1.setDumpDate(new Date(1000L));
        summary1.setHostname("host1");
        summary1.setDevname("dev1");

        DumpArchiveSummary summary2 = new DumpArchiveSummary();
        summary2.setDumpDate(new Date(1000L));
        summary2.setHostname("host1");
        summary2.setDevname(null);

        assertFalse(summary1.equals(summary2));
    }
}