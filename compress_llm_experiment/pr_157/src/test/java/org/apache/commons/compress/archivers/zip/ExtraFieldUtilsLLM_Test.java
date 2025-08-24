package org.apache.commons.compress.archivers.zip;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import java.util.zip.ZipException;

public class ExtraFieldUtilsLLM_Test implements UnixStat {
    static final ZipShort UNRECOGNIZED_HEADER = new ZipShort(0x5555);
    static final ZipShort AIOB_HEADER = new ZipShort(0x1000);
    private AsiExtraField a;
    private UnrecognizedExtraField dummy;
    private byte[] data;
    private byte[] aLocal;

    @Before
    public void setUp() {
        a = new AsiExtraField();
        a.setMode(0755);
        a.setDirectory(true);
        dummy = new UnrecognizedExtraField();
        dummy.setHeaderId(UNRECOGNIZED_HEADER);
        dummy.setLocalFileDataData(new byte[] {0});
        dummy.setCentralDirectoryData(new byte[] {0});
        aLocal = a.getLocalFileDataData();
        final byte[] dummyLocal = dummy.getLocalFileDataData();
        data = new byte[4 + aLocal.length + 4 + dummyLocal.length];
        System.arraycopy(a.getHeaderId().getBytes(), 0, data, 0, 2);
        System.arraycopy(a.getLocalFileDataLength().getBytes(), 0, data, 2, 2);
        System.arraycopy(aLocal, 0, data, 4, aLocal.length);
        System.arraycopy(dummy.getHeaderId().getBytes(), 0, data, 4 + aLocal.length, 2);
        System.arraycopy(dummy.getLocalFileDataLength().getBytes(), 0, data, 4 + aLocal.length + 2, 2);
        System.arraycopy(dummyLocal, 0, data, 4 + aLocal.length + 4, dummyLocal.length);
    }

    @Test
    public void testParseWithDataLength() throws Exception {
        final ZipExtraField[] ze = ExtraFieldUtils.parse(data);
        assertEquals("number of fields", 2, ze.length);
        assertTrue("type field 1", ze[0] instanceof AsiExtraField);
        assertEquals("mode field 1", 040755, ((AsiExtraField) ze[0]).getMode());
        assertTrue("type field 2", ze[1] instanceof UnrecognizedExtraField);
        assertEquals("data length field 2", 1, ze[1].getLocalFileDataLength().getValue());
    }

    @Test
    public void testMergeLocalFileDataWithDataLength() {
        final byte[] local = ExtraFieldUtils.mergeLocalFileDataData(new ZipExtraField[] {a, dummy});
        assertEquals("local length", data.length, local.length);
        for (int i = 0; i < local.length; i++) {
            assertEquals("local byte " + i, data[i], local[i]);
        }
    }

    @Test
    public void testMergeCentralDirectoryDataWithDataLength() {
        final byte[] dummyCentral = dummy.getCentralDirectoryData();
        final byte[] data2 = new byte[4 + aLocal.length + 4 + dummyCentral.length];
        System.arraycopy(data, 0, data2, 0, 4 + aLocal.length + 2);
        System.arraycopy(dummy.getCentralDirectoryLength().getBytes(), 0, data2, 4 + aLocal.length + 2, 2);
        System.arraycopy(dummyCentral, 0, data2, 4 + aLocal.length + 4, dummyCentral.length);
        final byte[] central = ExtraFieldUtils.mergeCentralDirectoryData(new ZipExtraField[] {a, dummy});
        assertEquals("central length", data2.length, central.length);
        for (int i = 0; i < central.length; i++) {
            assertEquals("central byte " + i, data2[i], central[i]);
        }
    }
}