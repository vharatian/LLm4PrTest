package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.testtools.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class TailerLLM_Test {

    @TempDir
    public static File temporaryFolder;

    private Tailer tailer;

    @AfterEach
    public void tearDown() throws Exception {
        if (tailer != null) {
            tailer.stop();
        }
    }

    @Test
    public void testInbufArraySyntaxChange() throws Exception {
        final long delay = 50;
        final File file = new File(temporaryFolder, "testInbufArraySyntaxChange.txt");
        createFile(file, 0);
        writeString(file, "Test line\n");
        final TestTailerListener listener = new TestTailerListener();
        tailer = new Tailer(file, listener, delay, false);
        final Thread thread = new Thread(tailer);
        thread.start();
        List<String> lines = listener.getLines();
        while (lines.isEmpty() || !lines.get(lines.size() - 1).equals("Test line")) {
            lines = listener.getLines();
        }
        listener.clear();
    }

    protected void createFile(final File file, final long size) throws IOException {
        if (!file.getParentFile().exists()) {
            throw new IOException("Cannot create file " + file + " as the parent directory does not exist");
        }
        try (final BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file))) {
            TestUtils.generateTestData(output, size);
        }
        RandomAccessFile reader = null;
        try {
            while (reader == null) {
                try {
                    reader = new RandomAccessFile(file.getPath(), "r");
                } catch (final FileNotFoundException ignore) {
                }
                try {
                    TestUtils.sleep(200L);
                } catch (final InterruptedException ignore) {
                }
            }
        } finally {
            IOUtils.closeQuietly(reader);
        }
    }

    private void writeString(final File file, final String... strings) throws Exception {
        try (FileWriter writer = new FileWriter(file, true)) {
            for (final String string : strings) {
                writer.write(string);
            }
        }
    }

    private static class TestTailerListener extends TailerListenerAdapter {
        private final List<String> lines = Collections.synchronizedList(new ArrayList<String>());
        volatile Exception exception = null;
        volatile int notFound = 0;
        volatile int rotated = 0;
        volatile int initialised = 0;
        volatile int reachedEndOfFile = 0;

        @Override
        public void handle(final String line) {
            lines.add(line);
        }

        public List<String> getLines() {
            return lines;
        }

        public void clear() {
            lines.clear();
        }

        @Override
        public void handle(final Exception e) {
            exception = e;
        }

        @Override
        public void init(final Tailer tailer) {
            initialised++;
        }

        @Override
        public void fileNotFound() {
            notFound++;
        }

        @Override
        public void fileRotated() {
            rotated++;
        }

        @Override
        public void endOfFileReached() {
            reachedEndOfFile++;
        }
    }
}