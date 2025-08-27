package org.apache.commons.compress.archivers.zip;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.compress.parallel.InputStreamSupplier;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ZipArchiveEntryRequestLLM_Test {

    @Test
    public void testGetZipArchiveEntry() {
        ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry("testEntry");
        InputStreamSupplier supplier = () -> new ByteArrayInputStream(new byte[0]);
        ZipArchiveEntryRequest request = ZipArchiveEntryRequest.createZipArchiveEntryRequest(zipArchiveEntry, supplier);

        assertSame(zipArchiveEntry, request.getZipArchiveEntry(), "The returned ZipArchiveEntry should be the same as the one provided");
    }

    @Test
    public void testGetMethod() {
        ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry("testEntry");
        zipArchiveEntry.setMethod(ZipArchiveEntry.DEFLATED);
        InputStreamSupplier supplier = () -> new ByteArrayInputStream(new byte[0]);
        ZipArchiveEntryRequest request = ZipArchiveEntryRequest.createZipArchiveEntryRequest(zipArchiveEntry, supplier);

        assertEquals(ZipArchiveEntry.DEFLATED, request.getMethod(), "The method should be DEFLATED");
    }

    @Test
    public void testGetPayloadStream() throws Exception {
        byte[] data = "test data".getBytes();
        InputStreamSupplier supplier = () -> new ByteArrayInputStream(data);
        ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry("testEntry");
        ZipArchiveEntryRequest request = ZipArchiveEntryRequest.createZipArchiveEntryRequest(zipArchiveEntry, supplier);

        try (InputStream stream = request.getPayloadStream()) {
            byte[] buffer = new byte[data.length];
            int bytesRead = stream.read(buffer);
            assertEquals(data.length, bytesRead, "The number of bytes read should match the length of the data");
            assertArrayEquals(data, buffer, "The data read should match the original data");
        }
    }
}