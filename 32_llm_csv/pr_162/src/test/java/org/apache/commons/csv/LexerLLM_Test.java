package org.apache.commons.csv;

import static org.apache.commons.csv.Constants.BACKSPACE;
import static org.apache.commons.csv.Constants.CR;
import static org.apache.commons.csv.Constants.FF;
import static org.apache.commons.csv.Constants.LF;
import static org.apache.commons.csv.Constants.TAB;
import static org.apache.commons.csv.Token.Type.EOF;
import static org.apache.commons.csv.Token.Type.TOKEN;
import static org.apache.commons.csv.TokenMatchers.matches;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void testIsDelimiterWithMultipleCharacters() throws IOException {
        final String code = "a|b|c";
        final CSVFormat format = CSVFormat.DEFAULT.withDelimiter('|');
        try (final Lexer lexer = createLexer(code, format)) {
            assertThat(lexer.nextToken(new Token()), matches(TOKEN, "a"));
            assertThat(lexer.nextToken(new Token()), matches(TOKEN, "b"));
            assertThat(lexer.nextToken(new Token()), matches(EOF, "c"));
        }
    }

    @Test
    public void testIsEscapeDelimiterWithMultipleCharacters() throws IOException {
        final String code = "a!|!b!|!c";
        final CSVFormat format = CSVFormat.DEFAULT.withDelimiter('|').withEscape('!');
        try (final Lexer lexer = createLexer(code, format)) {
            assertThat(lexer.nextToken(new Token()), matches(TOKEN, "a"));
            assertThat(lexer.nextToken(new Token()), matches(TOKEN, "b"));
            assertThat(lexer.nextToken(new Token()), matches(EOF, "c"));
        }
    }

    @Test
    public void testIsDelimiterWithLookAhead() throws IOException {
        final String code = "a,b,c";
        final CSVFormat format = CSVFormat.DEFAULT.withDelimiter(',');
        try (final Lexer lexer = createLexer(code, format)) {
            assertThat(lexer.nextToken(new Token()), matches(TOKEN, "a"));
            assertThat(lexer.nextToken(new Token()), matches(TOKEN, "b"));
            assertThat(lexer.nextToken(new Token()), matches(EOF, "c"));
        }
    }

    @Test
    public void testIsEscapeDelimiterWithLookAhead() throws IOException {
        final String code = "a!b!c";
        final CSVFormat format = CSVFormat.DEFAULT.withDelimiter('b').withEscape('!');
        try (final Lexer lexer = createLexer(code, format)) {
            assertThat(lexer.nextToken(new Token()), matches(TOKEN, "a"));
            assertThat(lexer.nextToken(new Token()), matches(EOF, "c"));
        }
    }

    @Test
    public void testReadEscapeWithDelimiter() throws IOException {
        final String code = "a\\,b\\,c";
        final CSVFormat format = CSVFormat.DEFAULT.withDelimiter(',').withEscape('\\');
        try (final Lexer lexer = createLexer(code, format)) {
            assertThat(lexer.nextToken(new Token()), matches(TOKEN, "a,b,c"));
        }
    }

    @Test
    public void testReadEscapeWithMultipleCharacters() throws IOException {
        final String code = "a\\|b\\|c";
        final CSVFormat format = CSVFormat.DEFAULT.withDelimiter('|').withEscape('\\');
        try (final Lexer lexer = createLexer(code, format)) {
            assertThat(lexer.nextToken(new Token()), matches(TOKEN, "a|b|c"));
        }
    }

    @Test
    public void testEscapeDelimiterAtEOF() throws IOException {
        final String code = "a!|!";
        final CSVFormat format = CSVFormat.DEFAULT.withDelimiter('|').withEscape('!');
        try (final Lexer lexer = createLexer(code, format)) {
            assertThrows(IOException.class, () -> lexer.nextToken(new Token()));
        }
    }
}