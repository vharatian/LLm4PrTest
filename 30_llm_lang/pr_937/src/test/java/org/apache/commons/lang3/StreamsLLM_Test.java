package org.apache.commons.lang3;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.Functions.FailableConsumer;
import org.apache.commons.lang3.Functions.FailablePredicate;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;
import org.xml.sax.SAXException;

public class StreamsLLM_Test extends AbstractLangTest {

    @Test
    public void testFailableStreamGenericType() {
        // Test to ensure that FailableStream can handle different generic types
        final List<Integer> intInput = Arrays.asList(1, 2, 3, 4, 5, 6);
        final List<Integer> intOutput = Functions.stream(intInput).collect(Collectors.toList());
        assertEquals(intInput, intOutput);

        final List<String> stringInput = Arrays.asList("a", "b", "c");
        final List<String> stringOutput = Functions.stream(stringInput).collect(Collectors.toList());
        assertEquals(stringInput, stringOutput);
    }

    @Test
    public void testToArrayGenericType() {
        // Test to ensure that toArray method can handle different generic types
        final Integer[] intArray = Arrays.asList(1, 2, 3).stream().collect(Streams.toArray(Integer.class));
        assertNotNull(intArray);
        assertEquals(3, intArray.length);
        assertEquals(1, intArray[0]);
        assertEquals(2, intArray[1]);
        assertEquals(3, intArray[2]);

        final String[] stringArray = Arrays.asList("a", "b", "c").stream().collect(Streams.toArray(String.class));
        assertNotNull(stringArray);
        assertEquals(3, stringArray.length);
        assertEquals("a", stringArray[0]);
        assertEquals("b", stringArray[1]);
        assertEquals("c", stringArray[2]);
    }
}