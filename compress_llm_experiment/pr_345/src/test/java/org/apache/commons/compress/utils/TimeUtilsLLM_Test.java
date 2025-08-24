package org.apache.commons.compress.utils;

import static org.apache.commons.compress.utils.TimeUtils.javaTimeToUnixTime;
import static org.apache.commons.compress.utils.TimeUtils.toUnixTime;
import static org.apache.commons.compress.utils.TimeUtils.unixTimeToFileTime;
import static org.apache.commons.compress.utils.TimeUtils.isUnixTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TimeUtilsLLM_Test {

    public static Stream<Arguments> unixTimeProvider() {
        return Stream.of(
            Arguments.of(0L, "1970-01-01T00:00:00Z"),
            Arguments.of(1L, "1970-01-01T00:00:01Z"),
            Arguments.of(-1L, "1969-12-31T23:59:59Z"),
            Arguments.of(1609459200L, "2021-01-01T00:00:00Z"),
            Arguments.of(2147483647L, "2038-01-19T03:14:07Z"),
            Arguments.of(-2147483648L, "1901-12-13T20:45:52Z")
        );
    }

    @ParameterizedTest
    @MethodSource("unixTimeProvider")
    public void shouldConvertUnixTimeToFileTime(final long unixTime, final String expectedInstant) {
        final FileTime fileTime = unixTimeToFileTime(unixTime);
        assertEquals(Instant.parse(expectedInstant), fileTime.toInstant());
    }

    @ParameterizedTest
    @MethodSource("unixTimeProvider")
    public void shouldConvertFileTimeToUnixTime(final long expectedUnixTime, final String instant) {
        final FileTime fileTime = FileTime.from(Instant.parse(instant));
        assertEquals(expectedUnixTime, toUnixTime(fileTime));
    }

    @ParameterizedTest
    @MethodSource("unixTimeProvider")
    public void shouldConvertJavaTimeToUnixTime(final long expectedUnixTime, final String instant) {
        final long javaTime = Instant.parse(instant).toEpochMilli();
        assertEquals(expectedUnixTime, javaTimeToUnixTime(javaTime));
    }

    public static Stream<Arguments> isUnixTimeProvider() {
        return Stream.of(
            Arguments.of(FileTime.from(0, TimeUnit.SECONDS), true),
            Arguments.of(FileTime.from(1, TimeUnit.SECONDS), true),
            Arguments.of(FileTime.from(-1, TimeUnit.SECONDS), true),
            Arguments.of(FileTime.from(2147483647L, TimeUnit.SECONDS), true),
            Arguments.of(FileTime.from(-2147483648L, TimeUnit.SECONDS), true),
            Arguments.of(FileTime.from(2147483648L, TimeUnit.SECONDS), false),
            Arguments.of(FileTime.from(-2147483649L, TimeUnit.SECONDS), false),
            Arguments.of(null, true)
        );
    }

    @ParameterizedTest
    @MethodSource("isUnixTimeProvider")
    public void shouldTestIfFileTimeIsUnixTime(final FileTime fileTime, final boolean expected) {
        assertEquals(expected, isUnixTime(fileTime));
    }

    public static Stream<Arguments> isUnixTimeSecondsProvider() {
        return Stream.of(
            Arguments.of(0L, true),
            Arguments.of(1L, true),
            Arguments.of(-1L, true),
            Arguments.of(2147483647L, true),
            Arguments.of(-2147483648L, true),
            Arguments.of(2147483648L, false),
            Arguments.of(-2147483649L, false)
        );
    }

    @ParameterizedTest
    @MethodSource("isUnixTimeSecondsProvider")
    public void shouldTestIfSecondsIsUnixTime(final long seconds, final boolean expected) {
        assertEquals(expected, isUnixTime(seconds));
    }
}