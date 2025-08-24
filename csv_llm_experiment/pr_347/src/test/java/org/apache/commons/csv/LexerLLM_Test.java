package org.apache.commons.csv;

import static org.apache.commons.csv.Token.Type.TOKEN;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.IOException;
import java.io.StringReader;
import org.junit.jupiter.api.Test;

public class LexerLLM_Test {

    private Lexer createLexer(final String input, final CSVFormat format) {
        return new Lexer(format, new ExtendedBufferedReader(new StringReader(input)));
    }

    @Test
    public void testInvalidCharBetweenEncapsulatedTokenAndDelimiter() {
        final String code = "\"foo\"x,bar";
        final CSVFormat format = CSVFormat.DEFAULT.withQuote('"');
        try (final Lexer lexer = createLexer(code, format)) {
            assertThrows(IOException.class, () -> lexer.nextToken(new Token()), 
                "Invalid char between encapsulated token and delimiter at line: 1, position: 5");
        }
    }
}