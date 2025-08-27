package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DiffableLLM_Test {

    @Test
    public void testDiffWithNonNullObject() {
        // Arrange
        Diffable<String> diffable = mock(Diffable.class);
        DiffResult<String> expectedDiffResult = new DiffResult<>(null, null, null);
        when(diffable.diff("test")).thenReturn(expectedDiffResult);

        // Act
        DiffResult<String> actualDiffResult = diffable.diff("test");

        // Assert
        assertEquals(expectedDiffResult, actualDiffResult);
    }

    @Test
    public void testDiffWithNullObject() {
        // Arrange
        Diffable<String> diffable = mock(Diffable.class);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> diffable.diff(null));
    }
}