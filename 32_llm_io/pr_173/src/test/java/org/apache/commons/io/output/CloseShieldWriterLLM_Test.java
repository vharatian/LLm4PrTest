package org.apache.commons.io.output;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.spy;
import java.io.Writer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CloseShieldWriterLLM_Test {

    private StringBuilderWriter original;
    private Writer shielded;

    @BeforeEach
    public void setUp() {
        original = spy(new StringBuilderWriter());
        shielded = new CloseShieldWriter(original);
    }

    /**
     * Test the deprecated constructor.
     */
    @Test
    public void testDeprecatedConstructor() {
        CloseShieldWriter writer = new CloseShieldWriter(original);
        assertNotNull(writer);
    }

    /**
     * Test the new wrap method.
     */
    @Test
    public void testWrapMethod() {
        CloseShieldWriter writer = CloseShieldWriter.wrap(original);
        assertNotNull(writer);
    }
}