package org.apache.commons.compress.archivers.zip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipException;

import org.junit.jupiter.api.Test;

public class ZipArchiveEntryLLM_Test {

    @Test
    public void testGetExtraFieldsWithNullInitialization() throws Exception {
        // Create a ZipArchiveEntry with no extra fields
        final ZipArchiveEntry ze = new ZipArchiveEntry("foo");

        // Ensure that getExtraFields with BEST_EFFORT returns an empty array
        final ZipExtraField[] read = ze.getExtraFields(ZipArchiveEntry.ExtraFieldParsingMode.BEST_EFFORT);
        assertEquals(0, read.length);
    }

    @Test
    public void testGetExtraFieldsWithEmptyCentralDirectory() throws Exception {
        // Create a ZipArchiveEntry with some extra fields
        final ZipExtraField[] extraFields = parsingModeBehaviorTestData();
        final ZipArchiveEntry ze = new ZipArchiveEntry("foo");
        ze.setExtraFields(extraFields);

        // Set an empty central directory extra field
        ze.setCentralDirectoryExtra(new byte[0]);

        // Ensure that getExtraFields with BEST_EFFORT returns the original extra fields
        final ZipExtraField[] read = ze.getExtraFields(ZipArchiveEntry.ExtraFieldParsingMode.BEST_EFFORT);
        assertEquals(extraFields.length, read.length);
    }

    @Test
    public void testGetExtraFieldsWithUnparseableCentralDirectory() throws Exception {
        // Create a ZipArchiveEntry with some extra fields
        final ZipExtraField[] extraFields = parsingModeBehaviorTestData();
        final ZipArchiveEntry ze = new ZipArchiveEntry("foo");
        ze.setExtraFields(extraFields);

        // Set an unparseable central directory extra field
        final byte[] unparseable = {0, 0, (byte) 0xff, (byte) 0xff, 0, 0, 0};
        ze.setCentralDirectoryExtra(unparseable);

        // Ensure that getExtraFields with DRACONIC throws a ZipException
        assertThrows(ZipException.class, () -> ze.getExtraFields(ZipArchiveEntry.ExtraFieldParsingMode.DRACONIC));
    }

    private ZipExtraField[] parsingModeBehaviorTestData() {
        final AsiExtraField a = new AsiExtraField();
        a.setDirectory(true);
        a.setMode(0755);
        final UnrecognizedExtraField u = new UnrecognizedExtraField();
        u.setHeaderId(ExtraFieldUtilsTest.UNRECOGNIZED_HEADER);
        u.setLocalFileDataData(new byte[0]);
        final UnparseableExtraFieldData x = new UnparseableExtraFieldData();
        final byte[] unparseable = {0, 0, (byte) 0xff, (byte) 0xff, 0, 0, 0};
        x.parseFromLocalFileData(unparseable, 0, unparseable.length);
        return new ZipExtraField[]{a, u, x};
    }
}