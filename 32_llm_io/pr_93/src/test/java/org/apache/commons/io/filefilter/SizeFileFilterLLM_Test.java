package org.apache.commons.io.filefilter;

import org.junit.Test;
import java.io.File;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SizeFileFilterLLM_Test {

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNegativeSize() {
        new SizeFileFilter(-1);
    }

    @Test
    public void testAcceptSmallerFiles() {
        SizeFileFilter filter = new SizeFileFilter(100, false);
        File smallFile = new File("smallFile.txt") {
            @Override
            public long length() {
                return 50;
            }
        };
        assertTrue(filter.accept(smallFile));
    }

    @Test
    public void testRejectLargerFiles() {
        SizeFileFilter filter = new SizeFileFilter(100, false);
        File largeFile = new File("largeFile.txt") {
            @Override
            public long length() {
                return 150;
            }
        };
        assertFalse(filter.accept(largeFile));
    }

    @Test
    public void testAcceptLargerFiles() {
        SizeFileFilter filter = new SizeFileFilter(100, true);
        File largeFile = new File("largeFile.txt") {
            @Override
            public long length() {
                return 150;
            }
        };
        assertTrue(filter.accept(largeFile));
    }

    @Test
    public void testRejectSmallerFiles() {
        SizeFileFilter filter = new SizeFileFilter(100, true);
        File smallFile = new File("smallFile.txt") {
            @Override
            public long length() {
                return 50;
            }
        };
        assertFalse(filter.accept(smallFile));
    }

    @Test
    public void testAcceptEqualSizeFiles() {
        SizeFileFilter filter = new SizeFileFilter(100, true);
        File equalFile = new File("equalFile.txt") {
            @Override
            public long length() {
                return 100;
            }
        };
        assertTrue(filter.accept(equalFile));
    }

    @Test
    public void testRejectEqualSizeFiles() {
        SizeFileFilter filter = new SizeFileFilter(100, false);
        File equalFile = new File("equalFile.txt") {
            @Override
            public long length() {
                return 100;
            }
        };
        assertFalse(filter.accept(equalFile));
    }
}