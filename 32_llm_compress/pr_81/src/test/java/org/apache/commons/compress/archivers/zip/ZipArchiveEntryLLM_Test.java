package org.apache.commons.compress.archivers.zip;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class ZipArchiveEntryLLM_Test {

    @Test
    public void testSetExtraFieldsWithEmptyArray() {
        // Create some extra fields
        final AsiExtraField a = new AsiExtraField();
        a.setDirectory(true);
        a.setMode(0755);
        final UnrecognizedExtraField u = new UnrecognizedExtraField();
        u.setHeaderId(ExtraFieldUtilsTest.UNRECOGNIZED_HEADER);
        u.setLocalFileDataData(new byte[0]);

        // Create a ZipArchiveEntry and set extra fields
        final ZipArchiveEntry ze = new ZipArchiveEntry("test/");
        ze.setExtraFields(new ZipExtraField[] {a, u});

        // Verify the extra fields
        ZipExtraField[] result = ze.getExtraFields();
        assertEquals(2, result.length);
        assertSame(a, result[0]);
        assertSame(u, result[1]);

        // Set extra fields with an empty array
        ze.setExtraFields(new ZipExtraField[0]);

        // Verify the extra fields are now empty
        result = ze.getExtraFields();
        assertEquals(0, result.length);
    }

    @Test
    public void testRemoveExtraFieldWithEmptyArray() {
        // Create some extra fields
        final AsiExtraField a = new AsiExtraField();
        a.setDirectory(true);
        a.setMode(0755);
        final UnrecognizedExtraField u = new UnrecognizedExtraField();
        u.setHeaderId(ExtraFieldUtilsTest.UNRECOGNIZED_HEADER);
        u.setLocalFileDataData(new byte[0]);

        // Create a ZipArchiveEntry and set extra fields
        final ZipArchiveEntry ze = new ZipArchiveEntry("test/");
        ze.setExtraFields(new ZipExtraField[] {a, u});

        // Verify the extra fields
        ZipExtraField[] result = ze.getExtraFields();
        assertEquals(2, result.length);
        assertSame(a, result[0]);
        assertSame(u, result[1]);

        // Remove an extra field
        ze.removeExtraField(ExtraFieldUtilsTest.UNRECOGNIZED_HEADER);

        // Verify the extra field has been removed
        result = ze.getExtraFields();
        assertEquals(1, result.length);
        assertSame(a, result[0]);

        // Remove the last extra field
        ze.removeExtraField(a.getHeaderId());

        // Verify the extra fields are now empty
        result = ze.getExtraFields();
        assertEquals(0, result.length);
    }
}