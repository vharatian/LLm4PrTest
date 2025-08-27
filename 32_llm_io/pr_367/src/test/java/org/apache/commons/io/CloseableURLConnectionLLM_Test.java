package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CloseableURLConnectionLLM_Test {

    @Test
    void testSetAllowUserInteraction() throws IOException {
        URLConnection mockURLConnection = mock(URLConnection.class);
        CloseableURLConnection closeableURLConnection = new CloseableURLConnection(mockURLConnection);

        closeableURLConnection.setAllowUserInteraction(true);
        verify(mockURLConnection).setAllowUserInteraction(true);

        closeableURLConnection.setAllowUserInteraction(false);
        verify(mockURLConnection).setAllowUserInteraction(false);
    }

    @Test
    void testSetDefaultUseCaches() throws IOException {
        URLConnection mockURLConnection = mock(URLConnection.class);
        CloseableURLConnection closeableURLConnection = new CloseableURLConnection(mockURLConnection);

        closeableURLConnection.setDefaultUseCaches(true);
        verify(mockURLConnection).setDefaultUseCaches(true);

        closeableURLConnection.setDefaultUseCaches(false);
        verify(mockURLConnection).setDefaultUseCaches(false);
    }

    @Test
    void testSetDoInput() throws IOException {
        URLConnection mockURLConnection = mock(URLConnection.class);
        CloseableURLConnection closeableURLConnection = new CloseableURLConnection(mockURLConnection);

        closeableURLConnection.setDoInput(true);
        verify(mockURLConnection).setDoInput(true);

        closeableURLConnection.setDoInput(false);
        verify(mockURLConnection).setDoInput(false);
    }

    @Test
    void testSetDoOutput() throws IOException {
        URLConnection mockURLConnection = mock(URLConnection.class);
        CloseableURLConnection closeableURLConnection = new CloseableURLConnection(mockURLConnection);

        closeableURLConnection.setDoOutput(true);
        verify(mockURLConnection).setDoOutput(true);

        closeableURLConnection.setDoOutput(false);
        verify(mockURLConnection).setDoOutput(false);
    }

    @Test
    void testSetIfModifiedSince() throws IOException {
        URLConnection mockURLConnection = mock(URLConnection.class);
        CloseableURLConnection closeableURLConnection = new CloseableURLConnection(mockURLConnection);

        long time = System.currentTimeMillis();
        closeableURLConnection.setIfModifiedSince(time);
        verify(mockURLConnection).setIfModifiedSince(time);
    }

    @Test
    void testSetUseCaches() throws IOException {
        URLConnection mockURLConnection = mock(URLConnection.class);
        CloseableURLConnection closeableURLConnection = new CloseableURLConnection(mockURLConnection);

        closeableURLConnection.setUseCaches(true);
        verify(mockURLConnection).setUseCaches(true);

        closeableURLConnection.setUseCaches(false);
        verify(mockURLConnection).setUseCaches(false);
    }
}