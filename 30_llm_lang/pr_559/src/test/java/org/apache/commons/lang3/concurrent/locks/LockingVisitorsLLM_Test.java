package org.apache.commons.lang3.concurrent.locks;

import org.apache.commons.lang3.function.FailableConsumer;
import org.apache.commons.lang3.function.FailableFunction;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class LockingVisitorsLLM_Test {

    @Test
    public void testStampedLockVisitorAcceptReadLocked() throws Throwable {
        PrintStream mockStream = mock(PrintStream.class);
        LockingVisitors.StampedLockVisitor<PrintStream> visitor = LockingVisitors.stampedLockVisitor(mockStream);

        visitor.acceptReadLocked(ps -> ps.println("Test Message"));

        verify(mockStream, times(1)).println("Test Message");
    }

    @Test
    public void testStampedLockVisitorAcceptWriteLocked() throws Throwable {
        PrintStream mockStream = mock(PrintStream.class);
        LockingVisitors.StampedLockVisitor<PrintStream> visitor = LockingVisitors.stampedLockVisitor(mockStream);

        visitor.acceptWriteLocked(ps -> ps.println("Test Message"));

        verify(mockStream, times(1)).println("Test Message");
    }

    @Test
    public void testStampedLockVisitorApplyReadLocked() throws Throwable {
        PrintStream mockStream = mock(PrintStream.class);
        LockingVisitors.StampedLockVisitor<PrintStream> visitor = LockingVisitors.stampedLockVisitor(mockStream);

        String result = visitor.applyReadLocked(ps -> {
            ps.println("Test Message");
            return "Result";
        });

        verify(mockStream, times(1)).println("Test Message");
        assertEquals("Result", result);
    }

    @Test
    public void testStampedLockVisitorApplyWriteLocked() throws Throwable {
        PrintStream mockStream = mock(PrintStream.class);
        LockingVisitors.StampedLockVisitor<PrintStream> visitor = LockingVisitors.stampedLockVisitor(mockStream);

        String result = visitor.applyWriteLocked(ps -> {
            ps.println("Test Message");
            return "Result";
        });

        verify(mockStream, times(1)).println("Test Message");
        assertEquals("Result", result);
    }

    @Test
    public void testReentrantReadWriteLockVisitorAcceptReadLocked() throws Throwable {
        PrintStream mockStream = mock(PrintStream.class);
        LockingVisitors.ReadWriteLockVisitor<PrintStream> visitor = LockingVisitors.reentrantReadWriteLockVisitor(mockStream);

        visitor.acceptReadLocked(ps -> ps.println("Test Message"));

        verify(mockStream, times(1)).println("Test Message");
    }

    @Test
    public void testReentrantReadWriteLockVisitorAcceptWriteLocked() throws Throwable {
        PrintStream mockStream = mock(PrintStream.class);
        LockingVisitors.ReadWriteLockVisitor<PrintStream> visitor = LockingVisitors.reentrantReadWriteLockVisitor(mockStream);

        visitor.acceptWriteLocked(ps -> ps.println("Test Message"));

        verify(mockStream, times(1)).println("Test Message");
    }

    @Test
    public void testReentrantReadWriteLockVisitorApplyReadLocked() throws Throwable {
        PrintStream mockStream = mock(PrintStream.class);
        LockingVisitors.ReadWriteLockVisitor<PrintStream> visitor = LockingVisitors.reentrantReadWriteLockVisitor(mockStream);

        String result = visitor.applyReadLocked(ps -> {
            ps.println("Test Message");
            return "Result";
        });

        verify(mockStream, times(1)).println("Test Message");
        assertEquals("Result", result);
    }

    @Test
    public void testReentrantReadWriteLockVisitorApplyWriteLocked() throws Throwable {
        PrintStream mockStream = mock(PrintStream.class);
        LockingVisitors.ReadWriteLockVisitor<PrintStream> visitor = LockingVisitors.reentrantReadWriteLockVisitor(mockStream);

        String result = visitor.applyWriteLocked(ps -> {
            ps.println("Test Message");
            return "Result";
        });

        verify(mockStream, times(1)).println("Test Message");
        assertEquals("Result", result);
    }

    @Test
    public void testStampedLockVisitorThrowsException() {
        PrintStream mockStream = mock(PrintStream.class);
        LockingVisitors.StampedLockVisitor<PrintStream> visitor = LockingVisitors.stampedLockVisitor(mockStream);

        assertThrows(RuntimeException.class, () -> {
            visitor.acceptReadLocked(ps -> {
                throw new RuntimeException("Test Exception");
            });
        });
    }

    @Test
    public void testReentrantReadWriteLockVisitorThrowsException() {
        PrintStream mockStream = mock(PrintStream.class);
        LockingVisitors.ReadWriteLockVisitor<PrintStream> visitor = LockingVisitors.reentrantReadWriteLockVisitor(mockStream);

        assertThrows(RuntimeException.class, () -> {
            visitor.acceptReadLocked(ps -> {
                throw new RuntimeException("Test Exception");
            });
        });
    }
}