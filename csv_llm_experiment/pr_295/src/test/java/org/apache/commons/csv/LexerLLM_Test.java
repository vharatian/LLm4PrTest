package org.apache.commons.csv;

import static org.apache.commons.csv.Token.Type.EOF;
import static org.apache.commons.csv.Token.Type.EORECORD;
import static org.apache.commons.csv.Token.Type.TOKEN;
import static org.apache.commons.csv.TokenMatchers.matches;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.StringReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LexerLLM_Test {

    private CSVFormat formatWithEscaping;

    @SuppressWarnings("resource")
    private Lexer createLexer(final String input, final CSVFormat format) {
        return new Lexer(format, new ExtendedBufferedReader(new StringReader(input)));
    }

    @BeforeEach
    public void setUp() {
        formatWithEscaping = CSVFormat.DEFAULT.withEscape('\\');
    }

    @Test
    public void testAllowTrailingText() throws IOException {
        final String code = "\"value\" trailing text";
        final CSVFormat format = CSVFormat.DEFAULT.withAllowTrailingText(true);
        try (final Lexer parser = createLexer(code, format)) {
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "value trailing text"));
            assertThat(parser.nextToken(new Token()), matches(EOF, ""));
        }
    }

    @Test
    public void testDisallowTrailingText() {
        final String code = "\"value\" trailing text";
        final CSVFormat format = CSVFormat.DEFAULT.withAllowTrailingText(false);
        try (final Lexer parser = createLexer(code, format)) {
            assertThrows(IOException.class, () -> parser.nextToken(new Token()));
        }
    }

    @Test
    public void testAllowEofWithoutClosingQuote() throws IOException {
        final String code = "\"value";
        final CSVFormat format = CSVFormat.DEFAULT.withAllowEofWithoutClosingQuote(true);
        try (final Lexer parser = createLexer(code, format)) {
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "value"));
            assertThat(parser.nextToken(new Token()), matches(EOF, ""));
        }
    }

    @Test
    public void testDisallowEofWithoutClosingQuote() {
        final String code = "\"value";
        final CSVFormat format = CSVFormat.DEFAULT.withAllowEofWithoutClosingQuote(false);
        try (final Lexer parser = createLexer(code, format)) {
            assertThrows(IOException.class, () -> parser.nextToken(new Token()));
        }
    }
}