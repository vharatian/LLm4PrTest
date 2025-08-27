package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class RandomStringUtilsLLM_Test {

    @Test
    public void testUpdatedRNGLink() {
        // This test ensures that the updated link in the Javadoc is correct.
        String expectedLink = "https://commons.apache.org/proper/commons-rng/";
        String actualLink = RandomStringUtils.class.getAnnotation(Deprecated.class).toString();
        assertTrue(actualLink.contains(expectedLink), "The Javadoc link should be updated to the correct Commons RNG URL.");
    }
}