package org.apache.commons.imaging;

import org.apache.commons.imaging.formats.webp.WebPImageParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AbstractImageParserLLM_Test {

    @Test
    public void testGetAllImageParsersIncludesWebPImageParser() {
        // Get the list of all image parsers
        List<AbstractImageParser<?>> parsers = AbstractImageParser.getAllImageParsers();

        // Check if WebPImageParser is included in the list
        boolean found = false;
        for (AbstractImageParser<?> parser : parsers) {
            if (parser instanceof WebPImageParser) {
                found = true;
                break;
            }
        }

        // Assert that WebPImageParser is found in the list
        assertTrue(found, "WebPImageParser should be included in the list of all image parsers");
    }
}