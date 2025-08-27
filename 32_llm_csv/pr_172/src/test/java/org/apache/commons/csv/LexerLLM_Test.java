package org.apache.commons.csv;

import static org.apache.commons.csv.Constants.BACKSPACE;
import static org.apache.commons.csv.Constants.CR;
import static org.apache.commons.csv.Constants.FF;
import static org.apache.commons.csv.Constants.LF;
import static org.apache.commons.csv.Constants.TAB;
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
    public void testParseSimpleTokenWithEscape() throws IOException {
        final String code = "a,\\b,c";
        final CSVFormat format = formatWithEscaping.withIgnoreEmptyLines(false);
        try (final Lexer parser = createLexer(code, format)) {
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "a"));
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "b"));
            assertThat(parser.nextToken(new Token()), matches(EORECORD, "c"));
            assertThat(parser.nextToken(new Token()), matches(EOF, ""));
        }
    }

    @Test
    public void testParseSimpleTokenWithEscapeAtEOF() throws IOException {
        final String code = "a,\\b,c\\";
        final CSVFormat format = formatWithEscaping.withIgnoreEmptyLines(false);
        try (final Lexer parser = createLexer(code, format)) {
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "a"));
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "b"));
            assertThat(parser.nextToken(new Token()), matches(EORECORD, "c"));
            assertThrows(IOException.class, () -> parser.nextToken(new Token()));
        }
    }
}