package org.apache.commons.compress.archivers.zip;

import org.apache.commons.compress.utils.TimeUtils;
import org.junit.jupiter.api.Test;

import java.nio.file.attribute.FileTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class X000A_NTFSLLM_Test {

    @Test
    public void testFileTimeRoundtrip() throws Exception {
        final X000A_NTFS xf = new X000A_NTFS();
        FileTime modifyTime = FileTime.fromMillis(0);
        FileTime accessTime = FileTime.fromMillis(-11644473601000L);
        FileTime createTime = null;

        xf.setModifyFileTime(modifyTime);
        xf.setAccessFileTime(accessTime);
        xf.setCreateFileTime(createTime);

        final byte[] b = xf.getLocalFileDataData();
        final X000A_NTFS xf2 = new X000A_NTFS();
        xf2.parseFromLocalFileData(b, 0, b.length);

        assertEquals(modifyTime, xf2.getModifyFileTime());
        assertEquals(accessTime, xf2.getAccessFileTime());
        assertNull(xf2.getCreateFileTime());
    }

    @Test
    public void testToStringWithFileTime() {
        final X000A_NTFS xf = new X000A_NTFS();
        FileTime modifyTime = FileTime.fromMillis(0);
        FileTime accessTime = FileTime.fromMillis(-11644473601000L);
        FileTime createTime = null;

        xf.setModifyFileTime(modifyTime);
        xf.setAccessFileTime(accessTime);
        xf.setCreateFileTime(createTime);

        String expected = "0x000A Zip Extra Field: Modify:[" + modifyTime + "] Access:[" + accessTime + "] Create:[" + createTime + "] ";
        assertEquals(expected, xf.toString());
    }
}