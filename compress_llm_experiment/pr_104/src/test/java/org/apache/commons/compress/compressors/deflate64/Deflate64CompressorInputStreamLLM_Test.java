package org.apache.commons.compress.compressors.deflate64;

import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class Deflate64CompressorInputStreamLLM_Test {

    private final HuffmanDecoder nullDecoder = null;

    @Mock
    private HuffmanDecoder decoder;

    @Test(expected = java.io.EOFException.class)
    public void throwsEOFExceptionOnTruncatedStreamsWithComment() throws Exception {
        byte[] data = {
            1, 11, 0, -12, -1,
            'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l',
        };
        try (Deflate64CompressorInputStream input = new Deflate64CompressorInputStream(new ByteArrayInputStream(data));
             BufferedReader br = new BufferedReader(new InputStreamReader(input))) {
            assertEquals("Hello World", br.readLine());
        }
    }
}