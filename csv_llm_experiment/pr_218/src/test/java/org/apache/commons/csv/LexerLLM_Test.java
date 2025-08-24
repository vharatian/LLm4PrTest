package org.apache.commons.csv;

import static org.apache.commons.csv.Token.Type.EOF;
import static org.apache.commons.csv.Token.Type.TOKEN;
import static org.apache.commons.csv.TokenMatchers.matches;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    public void testIsLastTokenDelimiterSingleCharDelimiter() throws IOException {
        final String code = "a,b,c";
        final CSVFormat format = CSVFormat.DEFAULT.withDelimiter(',');
        try (final Lexer parser = createLexer(code, format)) {
            Token token = new Token();
            parser.nextToken(token);
            assertTrue(parser.isLastTokenDelimiter);
        }
    }

    @Test
    public void testIsLastTokenDelimiterMultiCharDelimiter() throws IOException {
        final String code = "a||b||c";
        final CSVFormat format = CSVFormat.DEFAULT.withDelimiter("||");
        try (final Lexer parser = createLexer(code, format)) {
            Token token = new Token();
            parser.nextToken(token);
            assertTrue(parser.isLastTokenDelimiter);
        }
    }

    @Test
    public void testNextTokenEOFWithLastTokenDelimiter() throws IOException {
        final String code = "a,";
        final CSVFormat format = CSVFormat.DEFAULT.withDelimiter(',');
        try (final Lexer parser = createLexer(code, format)) {
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "a"));
            assertThat(parser.nextToken(new Token()), matches(EOF, ""));
        }
    }

    @Test
    public void testNextTokenEOFWithoutLastTokenDelimiter() throws IOException {
        final String code = "a";
        final CSVFormat format = CSVFormat.DEFAULT.withDelimiter(',');
        try (final Lexer parser = createLexer(code, format)) {
            assertThat(parser.nextToken(new Token()), matches(TOKEN, "a"));
            assertThat(parser.nextToken(new Token()), matches(EOF, ""));
        }
    }
}