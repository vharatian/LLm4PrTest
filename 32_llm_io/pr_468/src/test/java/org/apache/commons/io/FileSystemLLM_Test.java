package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

public class FileSystemLLM_Test {

    @Test
    public void testTypoCorrectionInJavadoc() {
        // This test is to ensure that the typo correction in the Javadoc does not affect the functionality.
        // Since the change is purely in the comment, no functional test is needed.
        // However, we can ensure that the method `indexOf` still works as expected.
        
        CharSequence cs = "This is a test string.";
        int searchChar = 't';
        int start = 10;
        
        int expectedIndex = 10; // 't' at index 10
        int actualIndex = FileSystem.indexOf(cs, searchChar, start);
        
        assertEquals(expectedIndex, actualIndex);
    }
}