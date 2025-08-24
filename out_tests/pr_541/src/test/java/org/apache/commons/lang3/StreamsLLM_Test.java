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

class StreamsLLM_Test {

    @Test
    void testArrayCollectorAccumulator() {
        // Given
        List<String> input = Arrays.asList("a", "b", "c");
        Streams.ArrayCollector<String> arrayCollector = new Streams.ArrayCollector<>(String.class);
        List<String> resultContainer = arrayCollector.supplier().get();

        // When
        arrayCollector.accumulator().accept(resultContainer, "a");
        arrayCollector.accumulator().accept(resultContainer, "b");
        arrayCollector.accumulator().accept(resultContainer, "c");

        // Then
        assertEquals(input, resultContainer);
    }
}