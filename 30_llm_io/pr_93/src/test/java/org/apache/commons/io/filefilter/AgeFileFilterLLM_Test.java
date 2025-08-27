package org.apache.commons.io.filefilter;

import org.junit.Test;
import java.io.File;
import java.util.Date;
import static org.junit.Assert.*;

public class AgeFileFilterLLM_Test {

    @Test
    public void testAcceptOlderTrue() {
        long cutoff = System.currentTimeMillis() - 1000;
        AgeFileFilter filter = new AgeFileFilter(cutoff, true);
        File olderFile = new File("olderFile.txt") {
            @Override
            public long lastModified() {
                return cutoff - 1000;
            }
        };
        File newerFile = new File("newerFile.txt") {
            @Override
            public long lastModified() {
                return cutoff + 1000;
            }
        };
        assertTrue(filter.accept(olderFile));
        assertFalse(filter.accept(newerFile));
    }

    @Test
    public void testAcceptOlderFalse() {
        long cutoff = System.currentTimeMillis() - 1000;
        AgeFileFilter filter = new AgeFileFilter(cutoff, false);
        File olderFile = new File("olderFile.txt") {
            @Override
            public long lastModified() {
                return cutoff - 1000;
            }
        };
        File newerFile = new File("newerFile.txt") {
            @Override
            public long lastModified() {
                return cutoff + 1000;
            }
        };
        assertFalse(filter.accept(olderFile));
        assertTrue(filter.accept(newerFile));
    }

    @Test
    public void testAcceptWithDate() {
        Date cutoffDate = new Date(System.currentTimeMillis() - 1000);
        AgeFileFilter filter = new AgeFileFilter(cutoffDate, true);
        File olderFile = new File("olderFile.txt") {
            @Override
            public long lastModified() {
                return cutoffDate.getTime() - 1000;
            }
        };
        File newerFile = new File("newerFile.txt") {
            @Override
            public long lastModified() {
                return cutoffDate.getTime() + 1000;
            }
        };
        assertTrue(filter.accept(olderFile));
        assertFalse(filter.accept(newerFile));
    }

    @Test
    public void testAcceptWithFile() {
        File cutoffReference = new File("cutoffReference.txt") {
            @Override
            public long lastModified() {
                return System.currentTimeMillis() - 1000;
            }
        };
        AgeFileFilter filter = new AgeFileFilter(cutoffReference, true);
        File olderFile = new File("olderFile.txt") {
            @Override
            public long lastModified() {
                return cutoffReference.lastModified() - 1000;
            }
        };
        File newerFile = new File("newerFile.txt") {
            @Override
            public long lastModified() {
                return cutoffReference.lastModified() + 1000;
            }
        };
        assertTrue(filter.accept(olderFile));
        assertFalse(filter.accept(newerFile));
    }
}