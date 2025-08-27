package org.apache.commons.io.function;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.function.Function;
import java.util.function.Supplier;
import static org.junit.jupiter.api.Assertions.*;

class IOFunctionLLM_Test {

    @Test
    void testApply() throws IOException {
        IOFunction<String, Integer> function = String::length;
        assertEquals(5, function.apply("Hello"));
    }

    @Test
    void testComposeWithIOFunction() throws IOException {
        IOFunction<String, Integer> function1 = String::length;
        IOFunction<Integer, String> function2 = Object::toString;
        IOFunction<String, String> composedFunction = function2.compose(function1);
        assertEquals("5", composedFunction.apply("Hello"));
    }

    @Test
    void testComposeWithFunction() throws IOException {
        IOFunction<String, Integer> function1 = String::length;
        Function<String, String> function2 = String::toUpperCase;
        IOFunction<String, Integer> composedFunction = function1.compose(function2);
        assertEquals(5, composedFunction.apply("hello"));
    }

    @Test
    void testComposeWithIOSupplier() throws IOException {
        IOFunction<String, Integer> function = String::length;
        IOSupplier<String> supplier = () -> "Hello";
        IOSupplier<Integer> composedSupplier = function.compose(supplier);
        assertEquals(5, composedSupplier.get());
    }

    @Test
    void testComposeWithSupplier() throws IOException {
        IOFunction<String, Integer> function = String::length;
        Supplier<String> supplier = () -> "Hello";
        IOSupplier<Integer> composedSupplier = function.compose(supplier);
        assertEquals(5, composedSupplier.get());
    }

    @Test
    void testAndThenWithIOFunction() throws IOException {
        IOFunction<String, Integer> function1 = String::length;
        IOFunction<Integer, String> function2 = Object::toString;
        IOFunction<String, String> composedFunction = function1.andThen(function2);
        assertEquals("5", composedFunction.apply("Hello"));
    }

    @Test
    void testAndThenWithFunction() throws IOException {
        IOFunction<String, Integer> function1 = String::length;
        Function<Integer, String> function2 = Object::toString;
        IOFunction<String, String> composedFunction = function1.andThen(function2);
        assertEquals("5", composedFunction.apply("Hello"));
    }

    @Test
    void testAndThenWithIOConsumer() throws IOException {
        IOFunction<String, Integer> function = String::length;
        IOConsumer<Integer> consumer = i -> assertEquals(5, i);
        IOConsumer<String> composedConsumer = function.andThen(consumer);
        composedConsumer.accept("Hello");
    }

    @Test
    void testAndThenWithConsumer() throws IOException {
        IOFunction<String, Integer> function = String::length;
        Consumer<Integer> consumer = i -> assertEquals(5, i);
        IOConsumer<String> composedConsumer = function.andThen(consumer);
        composedConsumer.accept("Hello");
    }

    @Test
    void testIdentity() throws IOException {
        IOFunction<String, String> identityFunction = IOFunction.identity();
        assertEquals("Hello", identityFunction.apply("Hello"));
    }
}