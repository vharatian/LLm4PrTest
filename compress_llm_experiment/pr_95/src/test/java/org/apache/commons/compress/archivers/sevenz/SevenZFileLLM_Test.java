package org.apache.commons.compress.archivers.sevenz;

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;

public class SevenZFileLLM_Test {

    @Test
    public void testSequentialAccessWithNewBuildDecodingStream() throws Exception {
        final Map<String, byte[]> entriesByName = new HashMap<>();
        try (SevenZFile archive = new SevenZFile(getFile("COMPRESS-320/Copy.7z"))) {
            SevenZArchiveEntry entry;
            while ((entry = archive.getNextEntry()) != null) {
                if (entry.hasStream()) {
                    entriesByName.put(entry.getName(), readFully(archive));
                }
            }
        }

        final String[] variants = {
            "BZip2-solid.7z",
            "BZip2.7z",
            "Copy-solid.7z",
            "Copy.7z",
            "Deflate-solid.7z",
            "Deflate.7z",
            "LZMA-solid.7z",
            "LZMA.7z",
            "LZMA2-solid.7z",
            "LZMA2.7z",
        };

        for (final String fileName : variants) {
            try (SevenZFile archive = new SevenZFile(getFile("COMPRESS-320/" + fileName))) {
                SevenZArchiveEntry entry;
                while ((entry = archive.getNextEntry()) != null) {
                    if (entry.hasStream()) {
                        assertTrue(entriesByName.containsKey(entry.getName()));
                        final byte[] content = readFully(archive);
                        assertTrue("Content mismatch on: " + fileName + "!" + entry.getName(),
                            Arrays.equals(content, entriesByName.get(entry.getName())));
                    }
                }
            }
        }
    }

    @Test
    public void testRandomAccessWithNewBuildDecodingStream() throws Exception {
        final Map<String, byte[]> entriesByName = new HashMap<>();
        try (SevenZFile archive = new SevenZFile(getFile("COMPRESS-320/Copy.7z"))) {
            SevenZArchiveEntry entry;
            while ((entry = archive.getNextEntry()) != null) {
                if (entry.hasStream()) {
                    entriesByName.put(entry.getName(), readFully(archive));
                }
            }
        }

        final String[] variants = {
            "BZip2-solid.7z",
            "BZip2.7z",
            "Copy-solid.7z",
            "Copy.7z",
            "Deflate-solid.7z",
            "Deflate.7z",
            "LZMA-solid.7z",
            "LZMA.7z",
            "LZMA2-solid.7z",
            "LZMA2.7z",
        };

        final Random rnd = new Random(0xdeadbeef);
        for (final String fileName : variants) {
            try (SevenZFile archive = new SevenZFile(getFile("COMPRESS-320/" + fileName))) {
                SevenZArchiveEntry entry;
                while ((entry = archive.getNextEntry()) != null) {
                    if (rnd.nextBoolean()) {
                        continue;
                    }
                    if (entry.hasStream()) {
                        assertTrue(entriesByName.containsKey(entry.getName()));
                        final byte[] content = readFully(archive);
                        assertTrue("Content mismatch on: " + fileName + "!" + entry.getName(),
                            Arrays.equals(content, entriesByName.get(entry.getName())));
                    }
                }
            }
        }
    }

    private byte[] readFully(final SevenZFile archive) throws IOException {
        final byte[] buf = new byte[1024];
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (int len = 0; (len = archive.read(buf)) > 0;) {
            baos.write(buf, 0, len);
        }
        return baos.toByteArray();
    }

    private File getFile(String name) {
        // Implement this method to return the correct file based on the name
        return new File(name);
    }
}