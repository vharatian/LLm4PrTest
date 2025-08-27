package org.apache.commons.text;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AlphabetConverterLLM_Test {

    @Test
    public void testSampleUsageHeaderChange() {
        // This test ensures that the header change from <h1> to <h2> does not affect functionality.
        // Since this is a documentation change, no functional test is needed.
        // We can simply ensure that the class can be instantiated and used as expected.
        
        final Character[] original = {'a', 'b', 'c', 'd'};
        final Character[] encoding = {'0', '1', 'd'};
        final Character[] doNotEncode = {'d'};
        AlphabetConverter ac = AlphabetConverter.createConverterFromChars(original, encoding, doNotEncode);
        
        assertThat(ac).isNotNull();
    }
}