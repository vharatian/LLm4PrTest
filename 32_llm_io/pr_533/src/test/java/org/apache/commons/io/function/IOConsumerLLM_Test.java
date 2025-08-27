package org.apache.commons.io.function;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.Closeable;
import java.io.IOException;
import java.io.StringReader;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

import org.apache.commons.io.IOExceptionList;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.file.PathUtils;
import org.apache.commons.io.test.ThrowOnCloseReader;
import org.junit.jupiter.api.Test;

public class IOConsumerLLM_Test {
    @Test
    void testForAllArrayCommentChange() throws IOException {
        // This test ensures that the functionality of forAll with array input remains unchanged
        // after the comment modification in the source code.
        IOConsumer.forAll(TestUtils.throwingIOConsumer(), (String[]) null);
        IOConsumer.forAll(null, (String[]) null);
        assertThrows(IOExceptionList.class, () -> IOConsumer.forAll(TestUtils.throwingIOConsumer(), "1"));
        final AtomicReference<String> ref = new AtomicReference<>("0");
        final IOConsumer<String> consumer1 = s -> ref.set(ref.get() + s);
        IOConsumer.forAll(consumer1, "1");
        assertEquals("01", ref.get());
    }
}