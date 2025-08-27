package org.apache.commons.io.monitor;

import org.junit.Before;
import org.junit.Test;
import java.util.concurrent.ThreadFactory;
import static org.junit.Assert.*;

public class FileAlterationMonitorLLM_Test {

    private FileAlterationMonitor monitor;
    private FileAlterationObserver observer;

    @Before
    public void setUp() {
        monitor = new FileAlterationMonitor();
        observer = new FileAlterationObserver("testPath");
    }

    @Test
    public void testDefaultConstructor() {
        FileAlterationMonitor defaultMonitor = new FileAlterationMonitor();
        assertEquals(10000, defaultMonitor.getInterval());
    }

    @Test
    public void testIntervalConstructor() {
        FileAlterationMonitor intervalMonitor = new FileAlterationMonitor(5000);
        assertEquals(5000, intervalMonitor.getInterval());
    }

    @Test
    public void testAddObserver() {
        monitor.addObserver(observer);
        assertTrue(monitor.getObservers().iterator().hasNext());
    }

    @Test
    public void testRemoveObserver() {
        monitor.addObserver(observer);
        monitor.removeObserver(observer);
        assertFalse(monitor.getObservers().iterator().hasNext());
    }

    @Test
    public void testSetThreadFactory() {
        ThreadFactory threadFactory = r -> new Thread(r, "TestThread");
        monitor.setThreadFactory(threadFactory);
        assertNotNull(monitor.getThreadFactory());
    }

    @Test
    public void testStartAndStop() throws Exception {
        monitor.addObserver(observer);
        monitor.start();
        assertTrue(monitor.isRunning());
        monitor.stop();
        assertFalse(monitor.isRunning());
    }

    @Test(expected = IllegalStateException.class)
    public void testStartWhenAlreadyRunning() throws Exception {
        monitor.addObserver(observer);
        monitor.start();
        monitor.start();
    }

    @Test(expected = IllegalStateException.class)
    public void testStopWhenNotRunning() throws Exception {
        monitor.stop();
    }
}