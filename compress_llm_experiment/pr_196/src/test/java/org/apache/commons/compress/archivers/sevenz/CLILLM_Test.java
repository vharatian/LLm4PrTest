package org.apache.commons.compress.archivers.sevenz;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class CLILLM_Test {

    @Test
    public void testModeListTakeActionDirectory() throws IOException {
        // Setup
        SevenZArchiveEntry entry = new SevenZArchiveEntry();
        entry.setName("testDir");
        entry.setDirectory(true);
        entry.setHasLastModifiedDate(false);

        SevenZFile archive = null; // Mock or create a dummy SevenZFile if necessary

        // Capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Execute
        CLI.Mode.LIST.takeAction(archive, entry);

        // Verify
        String expectedOutput = "testDir dir no last modified date\n";
        assertEquals(expectedOutput, outContent.toString());

        // Reset the System.out
        System.setOut(System.out);
    }

    @Test
    public void testModeListTakeActionFile() throws IOException {
        // Setup
        SevenZArchiveEntry entry = new SevenZArchiveEntry();
        entry.setName("testFile");
        entry.setDirectory(false);
        entry.setCompressedSize(100);
        entry.setSize(200);
        entry.setHasLastModifiedDate(true);
        entry.setLastModifiedDate(new java.util.Date());

        SevenZFile archive = null; // Mock or create a dummy SevenZFile if necessary

        // Capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Execute
        CLI.Mode.LIST.takeAction(archive, entry);

        // Verify
        String expectedOutput = "testFile 100/200 " + entry.getLastModifiedDate() + " \n";
        assertTrue(outContent.toString().startsWith(expectedOutput));

        // Reset the System.out
        System.setOut(System.out);
    }
}