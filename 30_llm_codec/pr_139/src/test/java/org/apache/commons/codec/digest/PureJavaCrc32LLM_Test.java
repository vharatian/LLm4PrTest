package org.apache.commons.codec.digest;

import org.junit.jupiter.api.Test;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.zip.CRC32;
import java.util.zip.Checksum;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PureJavaCrc32LLM_Test {
    private final CRC32 theirs = new CRC32();
    private final PureJavaCrc32 ours = new PureJavaCrc32();

    @Test
    public void testCorrectness() throws Exception {
        checkSame();
        theirs.update(104);
        ours.update(104);
        checkSame();
        checkOnBytes(new byte[] {40, 60, 97, -70}, false);
        checkOnBytes("hello world!".getBytes(StandardCharsets.UTF_8), false);
        final Random random1 = new Random();
        final Random random2 = new Random();
        for (int i = 0; i < 10000; i++) {
            final byte randomBytes[] = new byte[random1.nextInt(2048)];
            random2.nextBytes(randomBytes);
            checkOnBytes(randomBytes, false);
        }
    }

    private void checkOnBytes(final byte[] bytes, final boolean print) {
        theirs.reset();
        ours.reset();
        checkSame();
        for (final byte b : bytes) {
            ours.update(b);
            theirs.update(b);
            checkSame();
        }
        if (print) {
            System.out.println("theirs:\t" + Long.toHexString(theirs.getValue()) +
                    "\nours:\t" + Long.toHexString(ours.getValue()));
        }
        theirs.reset();
        ours.reset();
        ours.update(bytes, 0, bytes.length);
        theirs.update(bytes, 0, bytes.length);
        if (print) {
            System.out.println("theirs:\t" + Long.toHexString(theirs.getValue()) +
                    "\nours:\t" + Long.toHexString(ours.getValue()));
        }
        checkSame();
        if (bytes.length >= 10) {
            ours.update(bytes, 5, 5);
            theirs.update(bytes, 5, 5);
            checkSame();
        }
    }

    private void checkSame() {
        assertEquals(theirs.getValue(), ours.getValue());
    }
}