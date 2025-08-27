package org.apache.commons.compress.archivers.zip;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Date;
import org.junit.jupiter.api.Test;

public class X000A_NTFSLLM_Test {

    @Test
    public void testHeaderId() {
        // Verify that the HEADER_ID is correctly set and accessible
        assertEquals(new ZipShort(0x000a), X000A_NTFS.HEADER_ID);
    }

    @Test
    public void testModifyHeaderId() {
        // Ensure that the HEADER_ID is not modifiable
        final X000A_NTFS xf = new X000A_NTFS();
        assertEquals(new ZipShort(0x000a), xf.getHeaderId());
    }
}