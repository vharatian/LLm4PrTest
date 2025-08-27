package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testJaroWinklerDistanceDocumentationLink() {
        // Test to ensure the Jaro-Winkler distance documentation link is correct
        String expectedLink = "https://en.wikipedia.org/wiki/Jaro%E2%80%93Winkler_distance";
        String actualLink = "https://en.wikipedia.org/wiki/Jaro%E2%80%93Winkler_distance";
        assertEquals(expectedLink, actualLink);
    }

    @Test
    public void testLevenshteinDistanceDocumentationLink() {
        // Test to ensure the Levenshtein distance documentation link is correct
        String expectedLink1 = "https://blog.softwx.net/2014/12/optimizing-levenshtein-algorithm-in-c.html";
        String actualLink1 = "https://blog.softwx.net/2014/12/optimizing-levenshtein-algorithm-in-c.html";
        assertEquals(expectedLink1, actualLink1);

        String expectedLink2 = "https://web.archive.org/web/20120212021906/http%3A//www.merriampark.com/ld.htm";
        String actualLink2 = "https://web.archive.org/web/20120212021906/http%3A//www.merriampark.com/ld.htm";
        assertEquals(expectedLink2, actualLink2);
    }

    @Test
    public void testNormalizeSpaceDocumentationLink() {
        // Test to ensure the normalize-space documentation link is correct
        String expectedLink1 = "https://www.w3.org/TR/xpath/#function-normalize-space";
        String actualLink1 = "https://www.w3.org/TR/xpath/#function-normalize-space";
        assertEquals(expectedLink1, actualLink1);

        String expectedLink2 = "https://www.w3.org/TR/REC-xml/#NT-S";
        String actualLink2 = "https://www.w3.org/TR/REC-xml/#NT-S";
        assertEquals(expectedLink2, actualLink2);
    }

    @Test
    public void testUnicodeSupplementaryCharactersLink() {
        // Test to ensure the Unicode Supplementary Characters documentation link is correct
        String expectedLink = "https://www.unicode.org/glossary/#supplementary_character";
        String actualLink = "https://www.unicode.org/glossary/#supplementary_character";
        assertEquals(expectedLink, actualLink);
    }

    @Test
    public void testLFDocumentationLink() {
        // Test to ensure the LF documentation link is correct
        String expectedLink = "https://docs.oracle.com/javase/specs/jls/se8/html/jls-3.html#jls-3.10.6";
        String actualLink = "https://docs.oracle.com/javase/specs/jls/se8/html/jls-3.html#jls-3.10.6";
        assertEquals(expectedLink, actualLink);
    }

    @Test
    public void testCRDocumentationLink() {
        // Test to ensure the CR documentation link is correct
        String expectedLink = "https://docs.oracle.com/javase/specs/jls/se8/html/jls-3.html#jls-3.10.6";
        String actualLink = "https://docs.oracle.com/javase/specs/jls/se8/html/jls-3.html#jls-3.10.6";
        assertEquals(expectedLink, actualLink);
    }
}