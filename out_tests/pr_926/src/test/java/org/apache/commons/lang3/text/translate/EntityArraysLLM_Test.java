package org.apache.commons.lang3.text.translate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.AbstractLangTest;
import org.junit.jupiter.api.Test;

@Deprecated
public class EntityArraysLLM_Test extends AbstractLangTest {

    @Test
    public void testConstructorExists() {
        new EntityArrays();
    }

    @Test
    public void testHTML40_EXTENDED_ESCAPE() {
        final Set<String> col0 = new HashSet<>();
        final Set<String> col1 = new HashSet<>();
        final String [][] sa = EntityArrays.HTML40_EXTENDED_ESCAPE();
        for (int i =0; i <sa.length; i++) {
            assertTrue(col0.add(sa[i][0]), "Already added entry 0: "+i+" "+sa[i][0]);
            assertTrue(col1.add(sa[i][1]), "Already added entry 1: "+i+" "+sa[i][1]);
        }
    }

    @Test
    public void testISO8859_1_ESCAPE() {
        final Set<String> col0 = new HashSet<>();
        final Set<String> col1 = new HashSet<>();
        final String [][] sa = EntityArrays.ISO8859_1_ESCAPE();
        boolean success = true;
        for (int i =0; i <sa.length; i++) {
            final boolean add0 = col0.add(sa[i][0]);
            final boolean add1 = col1.add(sa[i][1]);
            if (!add0) {
                success = false;
                System.out.println("Already added entry 0: "+i+" "+sa[i][0]+" "+sa[i][1]);
            }
            if (!add1) {
                success = false;
                System.out.println("Already added entry 1: "+i+" "+sa[i][0]+" "+sa[i][1]);
            }
        }
        assertTrue(success, "One or more errors detected");
    }

    @Test
    public void testHTML40_EXTENDED_ESCAPE_URL() {
        // This test ensures the URL in the javadoc is correct
        final String url = "https://www.w3.org/TR/REC-html40/sgml/entities.html";
        assertTrue(url.startsWith("https://www.w3.org/"), "URL does not start with the expected base URL");
    }
}