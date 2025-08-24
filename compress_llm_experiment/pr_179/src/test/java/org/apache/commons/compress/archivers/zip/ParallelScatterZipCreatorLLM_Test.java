package org.apache.commons.compress.archivers.zip;

import org.apache.commons.compress.parallel.FileBasedScatterGatherBackingStore;
import org.apache.commons.compress.parallel.InputStreamSupplier;
import org.apache.commons.compress.parallel.ScatterGatherBackingStoreSupplier;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.After;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;

import static org.apache.commons.compress.AbstractTestCase.getFile;
import static org.apache.commons.compress.AbstractTestCase.tryHardToDelete;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ParallelScatterZipCreatorLLM_Test {

    private final int NUMITEMS = 5000;
    private static final long EXPECTED_FILE_SIZE = 1024 * 1024;
    private static final int EXPECTED_FILES_NUMBER = 50;
    private File result;
    private File tmp;

    @After
    public void cleanup() {
        tryHardToDelete(result);
        tryHardToDelete(tmp);
    }

    @Test
    public void testCompressionDoneAtInitialization() throws Exception {
        result = File.createTempFile("parallelScatterGather6", "");
        final ZipArchiveOutputStream zos = new ZipArchiveOutputStream(result);
        zos.setEncoding("UTF-8");
        final ParallelScatterZipCreator zipCreator = new ParallelScatterZipCreator();
        final Map<String, byte[]> entries = writeEntries(zipCreator);
        zipCreator.writeTo(zos);
        zos.close();
        removeEntriesFoundInZipFile(result, entries);
        assertTrue(entries.isEmpty());
        assertNotNull(zipCreator.getStatisticsMessage());

        // Ensure that compressionDoneAt is initialized correctly
        assertTrue(zipCreator.getStatisticsMessage().getCompressionTime() >= 0);
    }

    private Map<String, byte[]> writeEntries(final ParallelScatterZipCreator zipCreator) {
        final Map<String, byte[]> entries = new HashMap<>();
        for (int i = 0; i < NUMITEMS; i++) {
            final byte[] payloadBytes = ("content" + i).getBytes();
            final ZipArchiveEntry za = createZipArchiveEntry(entries, i, payloadBytes);
            final InputStreamSupplier iss = () -> new ByteArrayInputStream(payloadBytes);
            if (i % 2 == 0) {
                zipCreator.addArchiveEntry(za, iss);
            } else {
                final ZipArchiveEntryRequestSupplier zaSupplier = () -> ZipArchiveEntryRequest.createZipArchiveEntryRequest(za, iss);
                zipCreator.addArchiveEntry(zaSupplier);
            }
        }
        return entries;
    }

    private void removeEntriesFoundInZipFile(final File result, final Map<String, byte[]> entries) throws IOException {
        final ZipFile zf = new ZipFile(result);
        final Enumeration<ZipArchiveEntry> entriesInPhysicalOrder = zf.getEntriesInPhysicalOrder();
        int i = 0;
        while (entriesInPhysicalOrder.hasMoreElements()) {
            final ZipArchiveEntry zipArchiveEntry = entriesInPhysicalOrder.nextElement();
            final InputStream inputStream = zf.getInputStream(zipArchiveEntry);
            final byte[] actual = IOUtils.toByteArray(inputStream);
            final byte[] expected = entries.remove(zipArchiveEntry.getName());
            assertArrayEquals("For " + zipArchiveEntry.getName(), expected, actual);
            assertEquals("For " + zipArchiveEntry.getName(), "file" + i++, zipArchiveEntry.getName());
        }
        zf.close();
    }

    private ZipArchiveEntry createZipArchiveEntry(final Map<String, byte[]> entries, final int i, final byte[] payloadBytes) {
        final ZipArchiveEntry za = new ZipArchiveEntry("file" + i);
        entries.put(za.getName(), payloadBytes);
        za.setMethod(ZipEntry.DEFLATED);
        za.setSize(payloadBytes.length);
        za.setUnixMode(UnixStat.FILE_FLAG | 0664);
        return za;
    }
}