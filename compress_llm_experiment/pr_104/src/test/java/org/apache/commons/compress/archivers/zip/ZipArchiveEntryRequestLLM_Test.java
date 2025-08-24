package org.apache.commons.compress.archivers.zip;

import org.apache.commons.compress.parallel.InputStreamSupplier;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ZipArchiveEntryRequestLLM_Test {

    @Test
    public void testCreateZipArchiveEntryRequest() {
        ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry("testEntry");
        InputStreamSupplier supplier = () -> new ByteArrayInputStream(new byte[0]);
        ZipArchiveEntryRequest request = ZipArchiveEntryRequest.createZipArchiveEntryRequest(zipArchiveEntry, supplier);

        assertNotNull(request);
        assertEquals(zipArchiveEntry, request.getZipArchiveEntry());
    }

    @Test
    public void testGetPayloadStream() {
        ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry("testEntry");
        InputStream expectedStream = new ByteArrayInputStream(new byte[0]);
        InputStreamSupplier supplier = () -> expectedStream;
        ZipArchiveEntryRequest request = ZipArchiveEntryRequest.createZipArchiveEntryRequest(zipArchiveEntry, supplier);

        assertEquals(expectedStream, request.getPayloadStream());
    }

    @Test
    public void testGetMethod() {
        ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry("testEntry");
        zipArchiveEntry.setMethod(ZipArchiveEntry.DEFLATED);
        InputStreamSupplier supplier = () -> new ByteArrayInputStream(new byte[0]);
        ZipArchiveEntryRequest request = ZipArchiveEntryRequest.createZipArchiveEntryRequest(zipArchiveEntry, supplier);

        assertEquals(ZipArchiveEntry.DEFLATED, request.getMethod());
    }

    @Test
    public void testGetZipArchiveEntry() {
        ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry("testEntry");
        InputStreamSupplier supplier = () -> new ByteArrayInputStream(new byte[0]);
        ZipArchiveEntryRequest request = ZipArchiveEntryRequest.createZipArchiveEntryRequest(zipArchiveEntry, supplier);

        assertEquals(zipArchiveEntry, request.getZipArchiveEntry());
    }
}