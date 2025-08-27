package org.apache.commons.io.monitor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FileAlterationMonitorLLM_Test {

    private FileAlterationObserver observer1;
    private FileAlterationObserver observer2;

    @BeforeEach
    public void setUp() {
        observer1 = mock(FileAlterationObserver.class);
        observer2 = mock(FileAlterationObserver.class);
    }

    @Test
    public void testConstructorWithCollection() {
        FileAlterationMonitor monitor = new FileAlterationMonitor(5000, Arrays.asList(observer1, observer2));
        assertEquals(5000, monitor.getInterval());
        assertEquals(2, ((Collection<?>) monitor.getObservers()).size());
    }

    @Test
    public void testConstructorWithNullCollection() {
        FileAlterationMonitor monitor = new FileAlterationMonitor(5000, (Collection<FileAlterationObserver>) null);
        assertEquals(5000, monitor.getInterval());
        assertEquals(0, ((Collection<?>) monitor.getObservers()).size());
    }

    @Test
    public void testStartAndStop() throws Exception {
        FileAlterationMonitor monitor = new FileAlterationMonitor(5000, Arrays.asList(observer1, observer2));
        monitor.start();
        verify(observer1).initialize();
        verify(observer2).initialize();
        monitor.stop();
        verify(observer1).destroy();
        verify(observer2).destroy();
    }

    @Test
    public void testStartWhenAlreadyRunning() throws Exception {
        FileAlterationMonitor monitor = new FileAlterationMonitor(5000, Arrays.asList(observer1, observer2));
        monitor.start();
        assertThrows(IllegalStateException.class, monitor::start);
        monitor.stop();
    }

    @Test
    public void testStopWhenNotRunning() {
        FileAlterationMonitor monitor = new FileAlterationMonitor(5000, Arrays.asList(observer1, observer2));
        assertThrows(IllegalStateException.class, monitor::stop);
    }
}