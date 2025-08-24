package org.apache.commons.lang3.text.translate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.StringWriter;
import java.io.Writer;
import java.io.IOException;

public class AggregateTranslatorLLM_Test {

    @Test
    public void testTranslate() throws IOException {
        CharSequenceTranslator translator1 = new CharSequenceTranslator() {
            @Override
            public int translate(CharSequence input, int index, Writer out) throws IOException {
                if (input.charAt(index) == 'a') {
                    out.write('1');
                    return 1;
                }
                return 0;
            }
        };

        CharSequenceTranslator translator2 = new CharSequenceTranslator() {
            @Override
            public int translate(CharSequence input, int index, Writer out) throws IOException {
                if (input.charAt(index) == 'b') {
                    out.write('2');
                    return 1;
                }
                return 0;
            }
        };

        AggregateTranslator aggregateTranslator = new AggregateTranslator(translator1, translator2);
        StringWriter writer = new StringWriter();
        int result = aggregateTranslator.translate("abc", 0, writer);
        assertEquals(1, result);
        assertEquals("1", writer.toString());

        writer = new StringWriter();
        result = aggregateTranslator.translate("abc", 1, writer);
        assertEquals(1, result);
        assertEquals("2", writer.toString());

        writer = new StringWriter();
        result = aggregateTranslator.translate("abc", 2, writer);
        assertEquals(0, result);
        assertEquals("", writer.toString());
    }
}