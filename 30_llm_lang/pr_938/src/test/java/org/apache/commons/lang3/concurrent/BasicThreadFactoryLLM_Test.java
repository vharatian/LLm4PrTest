package org.apache.commons.lang3.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BasicThreadFactoryLLM_Test {

    private BasicThreadFactory.Builder builder;

    @BeforeEach
    public void setUp() {
        builder = new BasicThreadFactory.Builder();
    }

    @Test
    public void testBuildDefaults() {
        final BasicThreadFactory factory = builder.build();
        assertNull(factory.getNamingPattern(), "Got a naming pattern");
        assertNull(factory.getUncaughtExceptionHandler(), "Got an exception handler");
        assertNull(factory.getPriority(), "Got a priority");
        assertNull(factory.getDaemonFlag(), "Got a daemon flag");
        assertNotNull(factory.getWrappedFactory(), "No wrapped factory");
    }

    @Test
    public void testBuildNamingPatternNull() {
        assertThrows(NullPointerException.class, () -> builder.namingPattern(null));
    }

    @Test
    public void testBuildWrappedFactoryNull() {
        assertThrows(NullPointerException.class, () -> builder.wrappedFactory(null));
    }

    @Test
    public void testBuildUncaughtExceptionHandlerNull() {
        assertThrows(NullPointerException.class, () -> builder.uncaughtExceptionHandler(null));
    }

    @Test
    public void testBuilderReset() {
        final ThreadFactory wrappedFactory = EasyMock.createMock(ThreadFactory.class);
        final Thread.UncaughtExceptionHandler exHandler = EasyMock.createMock(Thread.UncaughtExceptionHandler.class);
        EasyMock.replay(wrappedFactory, exHandler);
        builder.namingPattern("testThread-%d").daemon(true).priority(Thread.MAX_PRIORITY)
                .uncaughtExceptionHandler(exHandler).wrappedFactory(wrappedFactory);
        builder.reset();
        final BasicThreadFactory factory = builder.build();
        assertNull(factory.getNamingPattern(), "Got a naming pattern");
        assertNull(factory.getUncaughtExceptionHandler(), "Got an exception handler");
        assertNull(factory.getPriority(), "Got a priority");
        assertNull(factory.getDaemonFlag(), "Got a daemon flag");
        assertNotNull(factory.getWrappedFactory(), "No wrapped factory");
        EasyMock.verify(wrappedFactory, exHandler);
    }

    @Test
    public void testBuilderResetAfterBuild() {
        builder.wrappedFactory(EasyMock.createNiceMock(ThreadFactory.class)).namingPattern("testThread-%d").daemon(true)
                .build();
        final BasicThreadFactory factory = builder.build();
        assertNull(factory.getNamingPattern(), "Got a naming pattern");
        assertNull(factory.getUncaughtExceptionHandler(), "Got an exception handler");
        assertNull(factory.getPriority(), "Got a priority");
        assertNull(factory.getDaemonFlag(), "Got a daemon flag");
        assertNotNull(factory.getWrappedFactory(), "No wrapped factory");
    }
}