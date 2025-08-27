package org.apache.commons.compress.compressors.pack200;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;

public final class Pack200UtilsLLM_Test extends AbstractTestCase {

    @Test
    public void testNormalizeWithProperties() throws Throwable {
        final File input = getFile("bla.jar");
        final File[] output = createTempDirAndFile();
        try {
            HashMap<String, String> props = new HashMap<>();
            props.put(Pack200.Packer.EFFORT, "9");
            Pack200Utils.normalize(input, output[1], props);
            try (FileInputStream is = new FileInputStream(output[1])) {
                final ArchiveInputStream in = new ArchiveStreamFactory()
                        .createArchiveInputStream("jar", is);
                ArchiveEntry entry = in.getNextEntry();
                while (entry != null) {
                    final File archiveEntry = new File(dir, entry.getName());
                    archiveEntry.getParentFile().mkdirs();
                    if (entry.isDirectory()) {
                        archiveEntry.mkdir();
                        entry = in.getNextEntry();
                        continue;
                    }
                    final OutputStream out = new FileOutputStream(archiveEntry);
                    IOUtils.copy(in, out);
                    out.close();
                    entry = in.getNextEntry();
                }
                in.close();
            }
        } finally {
            output[1].delete();
            output[0].delete();
        }
    }

    @Test
    public void testNormalizeInPlaceWithProperties() throws Throwable {
        final File input = getFile("bla.jar");
        final File[] output = createTempDirAndFile();
        try {
            FileInputStream is = new FileInputStream(input);
            OutputStream os = null;
            try {
                os = new FileOutputStream(output[1]);
                IOUtils.copy(is, os);
            } finally {
                is.close();
                if (os != null) {
                    os.close();
                }
            }
            HashMap<String, String> props = new HashMap<>();
            props.put(Pack200.Packer.EFFORT, "9");
            Pack200Utils.normalize(output[1], props);
            is = new FileInputStream(output[1]);
            try {
                final ArchiveInputStream in = new ArchiveStreamFactory()
                        .createArchiveInputStream("jar", is);
                ArchiveEntry entry = in.getNextEntry();
                while (entry != null) {
                    final File archiveEntry = new File(dir, entry.getName());
                    archiveEntry.getParentFile().mkdirs();
                    if (entry.isDirectory()) {
                        archiveEntry.mkdir();
                        entry = in.getNextEntry();
                        continue;
                    }
                    final OutputStream out = new FileOutputStream(archiveEntry);
                    IOUtils.copy(in, out);
                    out.close();
                    entry = in.getNextEntry();
                }
                in.close();
            } finally {
                is.close();
            }
        } finally {
            output[1].delete();
            output[0].delete();
        }
    }
}