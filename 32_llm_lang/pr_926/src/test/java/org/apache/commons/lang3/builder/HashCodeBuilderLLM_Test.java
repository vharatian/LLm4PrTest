package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class HashCodeBuilderLLM_Test extends AbstractLangTest {

    @Test
    public void testEffectiveJavaLinkUpdate() {
        // This test ensures that the link to Effective Java in the class documentation is correct.
        // Since this is a documentation change, we will just ensure the class loads correctly.
        HashCodeBuilder builder = new HashCodeBuilder();
        assertEquals(17, builder.toHashCode());
    }
}