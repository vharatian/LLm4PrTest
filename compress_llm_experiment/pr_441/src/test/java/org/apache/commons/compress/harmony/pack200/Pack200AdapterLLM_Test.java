package org.apache.commons.compress.harmony.pack200;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class Pack200AdapterLLM_Test {

    private Pack200Adapter adapter;

    @BeforeEach
    public void setUp() {
        adapter = new Pack200Adapter() {};
    }

    @Test
    public void testAddPropertyChangeListener() {
        PropertyChangeListener listener = evt -> {};
        adapter.addPropertyChangeListener(listener);
        // No direct way to assert listener addition, but no exception means success
    }

    @Test
    public void testRemovePropertyChangeListener() {
        PropertyChangeListener listener = evt -> {};
        adapter.addPropertyChangeListener(listener);
        adapter.removePropertyChangeListener(listener);
        // No direct way to assert listener removal, but no exception means success
    }

    @Test
    public void testProperties() {
        assertNotNull(adapter.properties());
    }

    @Test
    public void testCompleted() {
        PropertyChangeListener listener = evt -> {
            assertEquals("pack.progress", evt.getPropertyName());
            assertNull(evt.getOldValue());
            assertEquals("100", evt.getNewValue());
        };
        adapter.addPropertyChangeListener(listener);
        try {
            adapter.completed(1.0);
        } catch (IOException e) {
            fail("IOException should not be thrown");
        }
    }

    @Test
    public void testFirePropertyChange() {
        PropertyChangeListener listener = evt -> {
            assertEquals("testProperty", evt.getPropertyName());
            assertEquals("oldValue", evt.getOldValue());
            assertEquals("newValue", evt.getNewValue());
        };
        adapter.addPropertyChangeListener(listener);
        try {
            adapter.firePropertyChange("testProperty", "oldValue", "newValue");
        } catch (IOException e) {
            fail("IOException should not be thrown");
        }
    }

    @Test
    public void testCompletedThrowsIOException() {
        PropertyChangeListener listener = evt -> {};
        adapter.addPropertyChangeListener(listener);
        assertThrows(IOException.class, () -> adapter.completed(Double.NaN));
    }

    @Test
    public void testFirePropertyChangeThrowsIOException() {
        PropertyChangeListener listener = evt -> {};
        adapter.addPropertyChangeListener(listener);
        assertThrows(IOException.class, () -> adapter.firePropertyChange(null, "oldValue", "newValue"));
    }
}