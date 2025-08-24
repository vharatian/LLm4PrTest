package org.apache.commons.compress.archivers.zip;

import static org.apache.commons.compress.AbstractTestCase.getFile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ZipArchiveEntryLLM_Test {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void testMergeExtraFieldsWithFinalExisting() {
        // Setup initial extra fields
        final AsiExtraField a = new AsiExtraField();
        a.setDirectory(true);
        a.setMode(0755);
        final UnrecognizedExtraField u = new UnrecognizedExtraField();
        u.setHeaderId(ExtraFieldUtilsTest.UNRECOGNIZED_HEADER);
        u.setLocalFileDataData(new byte[0]);

        final ZipArchiveEntry ze = new ZipArchiveEntry("test/");
        ze.setExtraFields(new ZipExtraField[]{a, u});

        // New extra fields to merge
        final UnrecognizedExtraField u2 = new UnrecognizedExtraField();
        u2.setHeaderId(ExtraFieldUtilsTest.UNRECOGNIZED_HEADER);
        u2.setLocalFileDataData(new byte[]{1});

        // Merge extra fields
        ze.setExtraFields(new ZipExtraField[]{u2});

        // Validate the merge result
        final ZipExtraField[] result = ze.getExtraFields();
        assertEquals(1, result.length);
        assertSame(u2, result[0]);
    }
}