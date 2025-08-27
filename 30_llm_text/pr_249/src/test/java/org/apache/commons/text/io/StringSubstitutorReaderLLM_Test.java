package org.apache.commons.text.io;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.Reader;

import org.apache.commons.text.StringSubstitutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StringSubstitutorReaderLLM_Test {

    private Reader mockReader;
    private StringSubstitutor mockStringSubstitutor;

    @BeforeEach
    public void setUp() {
        mockReader = mock(Reader.class);
        mockStringSubstitutor = mock(StringSubstitutor.class);
    }

    @Test
    public void testConstructorWithNonNullStringSubstitutor() {
        when(mockStringSubstitutor.getEscapeChar()).thenReturn('\\');
        when(mockStringSubstitutor.getVariablePrefixMatcher()).thenReturn(StringMatcherFactory.INSTANCE.stringMatcher("${"));
        
        StringSubstitutorReader reader = new StringSubstitutorReader(mockReader, mockStringSubstitutor);
        assertNotNull(reader);
    }
}