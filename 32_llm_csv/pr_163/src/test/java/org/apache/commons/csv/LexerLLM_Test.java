package org.apache.commons.csv;

import static org.apache.commons.csv.Token.Type.EOF;
import static org.apache.commons.csv.Token.Type.TOKEN;
import static org.apache.commons.csv.TokenMatchers.matches;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.StringReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LexerLLM_Test {

    private CSVFormat formatWithEscaping;

    @BeforeEach
    public void setUp() {
        formatWithEscaping = CSVFormat.DEFAULT.withEscape('\\');
    }

    @SuppressWarnings("resource")
    private Lexer createLexer(final String input, final CSVFormat format) {
        return new Lexer(format, new ExtendedBufferedReader(new StringReader(input)));
    }

    @Test
    public void testSingleCharacterDelimiter() throws IOException {
        final String code = "a,b,c";
        final CSVFormat format = CSVFormat.DEFAULT.withDelimiter(',');
        try (final Lexer parser = createLexer(code, format)) {
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "a"));
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "b"));
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "c"));
            assertThat(parser.nextToken(new Token()), matches(EOF, ""));
        }
    }

    @Test
    public void testMultiCharacterDelimiter() throws IOException {
        final String code = "a||b||c";
        final CSVFormat format = CSVFormat.DEFAULT.withDelimiter("||");
        try (final Lexer parser = createLexer(code, format)) {
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "a"));
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "b"));
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "c"));
            assertThat(parser.nextToken(new Token()), matches(EOF, ""));
        }
    }

    @Test
    public void testSingleCharacterDelimiterWithSpaces() throws IOException {
        final String code = "a , b , c ";
        final CSVFormat format = CSVFormat.DEFAULT.withDelimiter(',').withIgnoreSurroundingSpaces();
        try (final Lexer parser = createLexer(code, format)) {
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "a"));
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "b"));
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "c"));
            assertThat(parser.nextToken(new Token()), matches(EOF, ""));
        }
    }

    @Test
    public void testMultiCharacterDelimiterWithSpaces() throws IOException {
        final String code = "a || b || c ";
        final CSVFormat format = CSVFormat.DEFAULT.withDelimiter("||").withIgnoreSurroundingSpaces();
        try (final Lexer parser = createLexer(code, format)) {
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "a"));
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "b"));
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "c"));
            assertThat(parser.nextToken(new Token()), matches(EOF, ""));
        }
    }
}