package org.apache.commons.compress.harmony.pack200;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CPLongLLM_Test {

    @Test
    public void testCompareTo() {
        CPLong cpLong1 = new CPLong(10L);
        CPLong cpLong2 = new CPLong(20L);
        CPLong cpLong3 = new CPLong(10L);

        // Test when theLong is less than theLong of the other object
        assertTrue(cpLong1.compareTo(cpLong2) < 0);

        // Test when theLong is equal to theLong of the other object
        assertEquals(0, cpLong1.compareTo(cpLong3));

        // Test when theLong is greater than theLong of the other object
        assertTrue(cpLong2.compareTo(cpLong1) > 0);
    }
}