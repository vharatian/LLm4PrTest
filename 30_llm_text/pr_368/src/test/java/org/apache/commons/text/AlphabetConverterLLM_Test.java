package org.apache.commons.text;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class AlphabetConverterLLM_Test {

    @Test
    public void testToStringFormat() {
        // Create a simple AlphabetConverter instance
        Map<Integer, String> originalToEncoded = new HashMap<>();
        originalToEncoded.put(97, "a");
        originalToEncoded.put(98, "b");
        AlphabetConverter ac = AlphabetConverter.createConverterFromMap(originalToEncoded);

        // Expected toString format
        String expected = "a -> 97" + System.lineSeparator() + "b -> 98" + System.lineSeparator();

        // Assert the toString method produces the expected format
        assertThat(ac.toString()).isEqualTo(expected);
    }
}