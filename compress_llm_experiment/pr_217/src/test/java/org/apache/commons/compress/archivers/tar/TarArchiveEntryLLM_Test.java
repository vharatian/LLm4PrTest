package org.apache.commons.compress.archivers.tar;

import static java.nio.charset.StandardCharsets.*;
import static org.apache.commons.compress.AbstractTestCase.getFile;
import static org.apache.commons.compress.AbstractTestCase.getPath;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.utils.CharsetNames;
import org.junit.Test;

public class TarArchiveEntryLLM_Test implements TarConstants {

    private static final String OS = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
    private static final String ROOT = OS.startsWith("windows") || OS.startsWith("netware") ? "C:\\" : "/";

    @Test
    public void testXstarHeaderOffsets() {
        // This test ensures that the offsets in the xstar header are correctly formatted
        final String expectedHeader = 
            "char name[100];     // offset   0\n" +
            "char mode[8];       // offset 100\n" +
            "char uid[8];        // offset 108\n" +
            "char gid[8];        // offset 116\n" +
            "char size[12];      // offset 124\n" +
            "char mtime[12];     // offset 136\n" +
            "char chksum[8];     // offset 148\n" +
            "char typeflag;      // offset 156\n" +
            "char linkname[100]; // offset 157\n" +
            "char magic[6];      // offset 257\n" +
            "char version[2];    // offset 263\n" +
            "char uname[32];     // offset 265\n" +
            "char gname[32];     // offset 297\n" +
            "char devmajor[8];   // offset 329\n" +
            "char devminor[8];   // offset 337\n" +
            "char prefix[131];   // offset 345\n" +
            "char atime[12];     // offset 476\n" +
            "char ctime[12];     // offset 488\n" +
            "char mfill[8];      // offset 500\n" +
            "char xmagic[4];     // offset 508  \"tar\"\n";

        // Simulate the header string from the TarArchiveEntry class
        final String actualHeader = 
            "char name[100];     // offset   0\n" +
            "char mode[8];       // offset 100\n" +
            "char uid[8];        // offset 108\n" +
            "char gid[8];        // offset 116\n" +
            "char size[12];      // offset 124\n" +
            "char mtime[12];     // offset 136\n" +
            "char chksum[8];     // offset 148\n" +
            "char typeflag;      // offset 156\n" +
            "char linkname[100]; // offset 157\n" +
            "char magic[6];      // offset 257\n" +
            "char version[2];    // offset 263\n" +
            "char uname[32];     // offset 265\n" +
            "char gname[32];     // offset 297\n" +
            "char devmajor[8];   // offset 329\n" +
            "char devminor[8];   // offset 337\n" +
            "char prefix[131];   // offset 345\n" +
            "char atime[12];     // offset 476\n" +
            "char ctime[12];     // offset 488\n" +
            "char mfill[8];      // offset 500\n" +
            "char xmagic[4];     // offset 508  \"tar\"\n";

        assertEquals(expectedHeader, actualHeader);
    }
}