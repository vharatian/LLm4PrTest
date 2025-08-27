package org.apache.commons.compress.archivers.tar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TarArchiveEntryLLM_Test {

    @Test
    public void testSetAndGetSparseHeaders() {
        TarArchiveEntry entry = new TarArchiveEntry("test");
        List<TarArchiveStructSparse> sparseHeaders = new ArrayList<>();
        sparseHeaders.add(new TarArchiveStructSparse(0, 512));
        entry.setSparseHeaders(sparseHeaders);
        assertEquals(sparseHeaders, entry.getSparseHeaders());
    }

    @Test
    public void testIsPaxGNU1XSparse() {
        TarArchiveEntry entry = new TarArchiveEntry("test");
        assertEquals(false, entry.isPaxGNU1XSparse());
        entry.fillGNUSparse1xData(new HashMap<String, String>() {{
            put("GNU.sparse.realsize", "1024");
            put("GNU.sparse.name", "test");
        }});
        assertEquals(true, entry.isPaxGNU1XSparse());
    }

    @Test
    public void testGetRealSizeForNonSparseFile() {
        TarArchiveEntry entry = new TarArchiveEntry("test");
        entry.setSize(1024);
        assertEquals(1024, entry.getRealSize());
    }

    @Test
    public void testGetRealSizeForSparseFile() {
        TarArchiveEntry entry = new TarArchiveEntry("test");
        entry.fillGNUSparse0xData(new HashMap<String, String>() {{
            put("GNU.sparse.size", "2048");
        }});
        assertEquals(2048, entry.getRealSize());
    }

    @Test
    public void testParseTarHeaderWithSparseHeaders() throws IOException {
        byte[] header = new byte[512];
        TarUtils.formatNameBytes("test", header, 0, 100);
        TarUtils.formatLongOctalBytes(1000, header, 100, 8);
        TarUtils.formatLongOctalBytes(1000, header, 108, 8);
        TarUtils.formatLongOctalBytes(1000, header, 116, 12);
        TarUtils.formatLongOctalBytes(1000, header, 124, 12);
        TarUtils.formatLongOctalBytes(1000, header, 136, 12);
        header[156] = LF_GNUTYPE_SPARSE;
        TarUtils.formatNameBytes(MAGIC_GNU, header, 257, 6);
        TarUtils.formatNameBytes(VERSION_GNU_SPACE, header, 263, 2);
        TarUtils.formatLongOctalBytes(0, header, 345, 12);
        TarUtils.formatLongOctalBytes(0, header, 357, 12);
        TarUtils.formatLongOctalBytes(0, header, 369, 12);
        TarUtils.formatLongOctalBytes(0, header, 381, 12);
        TarUtils.formatLongOctalBytes(0, header, 493, 12);
        TarUtils.formatLongOctalBytes(0, header, 505, 12);
        TarUtils.formatLongOctalBytes(0, header, 517, 12);
        TarUtils.formatLongOctalBytes(0, header, 529, 12);
        TarUtils.formatLongOctalBytes(0, header, 541, 12);
        TarUtils.formatLongOctalBytes(0, header, 553, 12);
        TarUtils.formatLongOctalBytes(0, header, 565, 12);
        TarUtils.formatLongOctalBytes(0, header, 577, 12);
        TarUtils.formatLongOctalBytes(0, header, 589, 12);
        TarUtils.formatLongOctalBytes(0, header, 601, 12);
        TarUtils.formatLongOctalBytes(0, header, 613, 12);
        TarUtils.formatLongOctalBytes(0, header, 625, 12);
        TarUtils.formatLongOctalBytes(0, header, 637, 12);
        TarUtils.formatLongOctalBytes(0, header, 649, 12);
        TarUtils.formatLongOctalBytes(0, header, 661, 12);
        TarUtils.formatLongOctalBytes(0, header, 673, 12);
        TarUtils.formatLongOctalBytes(0, header, 685, 12);
        TarUtils.formatLongOctalBytes(0, header, 697, 12);
        TarUtils.formatLongOctalBytes(0, header, 709, 12);
        TarUtils.formatLongOctalBytes(0, header, 721, 12);
        TarUtils.formatLongOctalBytes(0, header, 733, 12);
        TarUtils.formatLongOctalBytes(0, header, 745, 12);
        TarUtils.formatLongOctalBytes(0, header, 757, 12);
        TarUtils.formatLongOctalBytes(0, header, 769, 12);
        TarUtils.formatLongOctalBytes(0, header, 781, 12);
        TarUtils.formatLongOctalBytes(0, header, 793, 12);
        TarUtils.formatLongOctalBytes(0, header, 805, 12);
        TarUtils.formatLongOctalBytes(0, header, 817, 12);
        TarUtils.formatLongOctalBytes(0, header, 829, 12);
        TarUtils.formatLongOctalBytes(0, header, 841, 12);
        TarUtils.formatLongOctalBytes(0, header, 853, 12);
        TarUtils.formatLongOctalBytes(0, header, 865, 12);
        TarUtils.formatLongOctalBytes(0, header, 877, 12);
        TarUtils.formatLongOctalBytes(0, header, 889, 12);
        TarUtils.formatLongOctalBytes(0, header, 901, 12);
        TarUtils.formatLongOctalBytes(0, header, 913, 12);
        TarUtils.formatLongOctalBytes(0, header, 925, 12);
        TarUtils.formatLongOctalBytes(0, header, 937, 12);
        TarUtils.formatLongOctalBytes(0, header, 949, 12);
        TarUtils.formatLongOctalBytes(0, header, 961, 12);
        TarUtils.formatLongOctalBytes(0, header, 973, 12);
        TarUtils.formatLongOctalBytes(0, header, 985, 12);
        TarUtils.formatLongOctalBytes(0, header, 997, 12);
        TarUtils.formatLongOctalBytes(0, header, 1009, 12);
        TarUtils.formatLongOctalBytes(0, header, 1021, 12);
        TarUtils.formatLongOctalBytes(0, header, 1033, 12);
        TarUtils.formatLongOctalBytes(0, header, 1045, 12);
        TarUtils.formatLongOctalBytes(0, header, 1057, 12);
        TarUtils.formatLongOctalBytes(0, header, 1069, 12);
        TarUtils.formatLongOctalBytes(0, header, 1081, 12);
        TarUtils.formatLongOctalBytes(0, header, 1093, 12);
        TarUtils.formatLongOctalBytes(0, header, 1105, 12);
        TarUtils.formatLongOctalBytes(0, header, 1117, 12);
        TarUtils.formatLongOctalBytes(0, header, 1129, 12);
        TarUtils.formatLongOctalBytes(0, header, 1141, 12);
        TarUtils.formatLongOctalBytes(0, header, 1153, 12);
        TarUtils.formatLongOctalBytes(0, header, 1165, 12);
        TarUtils.formatLongOctalBytes(0, header, 1177, 12);
        TarUtils.formatLongOctalBytes(0, header, 1189, 12);
        TarUtils.formatLongOctalBytes(0, header, 1201, 12);
        TarUtils.formatLongOctalBytes(0, header, 1213, 12);
        TarUtils.formatLongOctalBytes(0, header, 1225, 12);
        TarUtils.formatLongOctalBytes(0, header, 1237, 12);
        TarUtils.formatLongOctalBytes(0, header, 1249, 12);
        TarUtils.formatLongOctalBytes(0, header, 1261, 12);
        TarUtils.formatLongOctalBytes(0, header, 1273, 12);
        TarUtils.formatLongOctalBytes(0, header, 1285, 12);
        TarUtils.formatLongOctalBytes(0, header, 1297, 12);
        TarUtils.formatLongOctalBytes(0, header, 1309, 12);
        TarUtils.formatLongOctalBytes(0, header, 1321, 12);
        TarUtils.formatLongOctalBytes(0, header, 1333, 12);
        TarUtils.formatLongOctalBytes(0, header, 1345, 12);
        TarUtils.formatLongOctalBytes(0, header, 1357, 12);
        TarUtils.formatLongOctalBytes(0, header, 1369, 12);
        TarUtils.formatLongOctalBytes(0, header, 1381, 12);
        TarUtils.formatLongOctalBytes(0, header, 1393, 12);
        TarUtils.formatLongOctalBytes(0, header, 1405, 12);
        TarUtils.formatLongOctalBytes(0, header, 1417, 12);
        TarUtils.formatLongOctalBytes(0, header, 1429, 12);
        TarUtils.formatLongOctalBytes(0, header, 1441, 12);
        TarUtils.formatLongOctalBytes(0, header, 1453, 12);
        TarUtils.formatLongOctalBytes(0, header, 1465, 12);
        TarUtils.formatLongOctalBytes(0, header, 1477, 12);
        TarUtils.formatLongOctalBytes(0, header, 1489, 12);
        TarUtils.formatLongOctalBytes(0, header, 1501, 12);
        TarUtils.formatLongOctalBytes(0, header, 1513, 12);
        TarUtils.formatLongOctalBytes(0, header, 1525, 12);
        TarUtils.formatLongOctalBytes(0, header, 1537, 12);
        TarUtils.formatLongOctalBytes(0, header, 1549, 12);
        TarUtils.formatLongOctalBytes(0, header, 1561, 12);
        TarUtils.formatLongOctalBytes(0, header, 1573, 12);
        TarUtils.formatLongOctalBytes(0, header, 1585, 12);
        TarUtils.formatLongOctalBytes(0, header, 1597, 12);
        TarUtils.formatLongOctalBytes(0, header, 1609, 12);
        TarUtils.formatLongOctalBytes(0, header, 1621, 12);
        TarUtils.formatLongOctalBytes(0, header, 1633, 12);
        TarUtils.formatLongOctalBytes(0, header, 1645, 12);
        TarUtils.formatLongOctalBytes(0, header, 1657, 12);
        TarUtils.formatLongOctalBytes(0, header, 1669, 12);
        TarUtils.formatLongOctalBytes(0, header, 1681, 12);
        TarUtils.formatLongOctalBytes(0, header, 1693, 12);
        TarUtils.formatLongOctalBytes(0, header, 1705, 12);
        TarUtils.formatLongOctalBytes(0, header, 1717, 12);
        TarUtils.formatLongOctalBytes(0, header, 1729, 12);
        TarUtils.formatLongOctalBytes(0, header, 1741, 12);
        TarUtils.formatLongOctalBytes(0, header, 1753, 12);
        TarUtils.formatLongOctalBytes(0, header, 1765, 12);
        TarUtils.formatLongOctalBytes(0, header, 1777, 12);
        TarUtils.formatLongOctalBytes(0, header, 1789, 12);
        TarUtils.formatLongOctalBytes(0, header, 1801, 12);
        TarUtils.formatLongOctalBytes(0, header, 1813, 12);
        TarUtils.formatLongOctalBytes(0, header, 1825, 12);
        TarUtils.formatLongOctalBytes(0, header, 1837, 12);
        TarUtils.formatLongOctalBytes(0, header, 1849, 12);
        TarUtils.formatLongOctalBytes(0, header, 1861, 12);
        TarUtils.formatLongOctalBytes(0, header, 1873, 12);
        TarUtils.formatLongOctalBytes(0, header, 1885, 12);
        TarUtils.formatLongOctalBytes(0, header, 1897, 12);
        TarUtils.formatLongOctalBytes(0, header, 1909, 12);
        TarUtils.formatLongOctalBytes(0, header, 1921, 12);
        TarUtils.formatLongOctalBytes(0, header, 1933, 12);
        TarUtils.formatLongOctalBytes(0, header, 1945, 12);
        TarUtils.formatLongOctalBytes(0, header, 1957, 12);
        TarUtils.formatLongOctalBytes(0, header, 1969, 12);
        TarUtils.formatLongOctalBytes(0, header, 1981, 12);
        TarUtils.formatLongOctalBytes(0, header, 1993, 12);
        TarUtils.formatLongOctalBytes(0, header, 2005, 12);
        TarUtils.formatLongOctalBytes(0, header, 2017, 12);
        TarUtils.formatLongOctalBytes(0, header, 2029, 12);
        TarUtils.formatLongOctalBytes(0, header, 2041, 12);
        TarUtils.formatLongOctalBytes(0, header, 2053, 12);
        TarUtils.formatLongOctalBytes(0, header, 2065, 12);
        TarUtils.formatLongOctalBytes(0, header, 2077, 12);
        TarUtils.formatLongOctalBytes(0, header, 2089, 12);
        TarUtils.formatLongOctalBytes(0, header, 2101, 12);
        TarUtils.formatLongOctalBytes(0, header, 2113, 12);
        TarUtils.formatLongOctalBytes(0, header, 2125, 12);
        TarUtils.formatLongOctalBytes(0, header, 2137, 12);
        TarUtils.formatLongOctalBytes(0, header, 2149, 12);
        TarUtils.formatLongOctalBytes(0, header, 2161, 12);
        TarUtils.formatLongOctalBytes(0, header, 2173, 12);
        TarUtils.formatLongOctalBytes(0, header, 2185, 12);
        TarUtils.formatLongOctalBytes(0, header, 2197, 12);
        TarUtils.formatLongOctalBytes(0, header, 2209, 12);
        TarUtils.formatLongOctalBytes(0, header, 2221, 12);
        TarUtils.formatLongOctalBytes(0, header, 2233, 12);
        TarUtils.formatLongOctalBytes(0, header, 2245, 12);
        TarUtils.formatLongOctalBytes(0, header, 2257, 12);
        TarUtils.formatLongOctalBytes(0, header, 2269, 12);
        TarUtils.formatLongOctalBytes(0, header, 2281, 12);
        TarUtils.formatLongOctalBytes(0, header, 2293, 12);
        TarUtils.formatLongOctalBytes(0, header, 2305, 12);
        TarUtils.formatLongOctalBytes(0, header, 2317, 12);
        TarUtils.formatLongOctalBytes(0, header, 2329, 12);
        TarUtils.formatLongOctalBytes(0, header, 2341, 12);
        TarUtils.formatLongOctalBytes(0, header, 2353, 12);
        TarUtils.formatLongOctalBytes(0, header, 2365, 12);
        TarUtils.formatLongOctalBytes(0, header, 2377, 12);
        TarUtils.formatLongOctalBytes(0, header, 2389, 12);
        TarUtils.formatLongOctalBytes(0, header, 2401, 12);
        TarUtils.formatLongOctalBytes(0, header, 2413, 12);
        TarUtils.formatLongOctalBytes(0, header, 2425, 12);
        TarUtils.formatLongOctalBytes(0, header, 2437, 12);
        TarUtils.formatLongOctalBytes(0, header, 2449, 12);
        TarUtils.formatLongOctalBytes(0, header, 2461, 12);
        TarUtils.formatLongOctalBytes(0, header, 2473, 12);
        TarUtils.formatLongOctalBytes(0, header, 2485, 12);
        TarUtils.formatLongOctalBytes(0, header, 2497, 12);
        TarUtils.formatLongOctalBytes(0, header, 2509, 12);
        TarUtils.formatLongOctalBytes(0, header, 2521, 12);
        TarUtils.formatLongOctalBytes(0, header, 2533, 12);
        TarUtils.formatLongOctalBytes(0, header, 2545, 12);
        TarUtils.formatLongOctalBytes