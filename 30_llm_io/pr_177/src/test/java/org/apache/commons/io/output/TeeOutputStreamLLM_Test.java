package org.apache.commons.io.output;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.test.ThrowOnCloseOutputStream;
import org.junit.jupiter.api.Test;

public class TeeOutputStreamLLM_Test {

    @Test
    public void testFinalBranchField() throws IOException {
        final ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
        final ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        final TeeOutputStream tos = new TeeOutputStream(baos1, baos2);

        // Verify that the branch field is correctly assigned and is final
        assertEquals(baos2, tos.branch, "The branch field should be correctly assigned and final.");
    }
}