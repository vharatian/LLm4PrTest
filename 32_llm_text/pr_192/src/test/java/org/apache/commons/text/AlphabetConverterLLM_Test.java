package org.apache.commons.text;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class AlphabetConverterLLM_Test {

    private static Character[] lowerCaseEnglish = {' ', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static Character[] englishAndNumbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c',
            'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
            'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
            'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' '};
    private static Character[] lowerCaseEnglishAndNumbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
            'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', ' '};
    private static Character[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static Character[] binary = {'0', '1'};
    private static Character[] hebrew = {'_', ' ', '\u05e7', '\u05e8', '\u05d0', '\u05d8', '\u05d5', '\u05df', '\u05dd',
            '\u05e4', '\u05e9', '\u05d3', '\u05d2', '\u05db', '\u05e2', '\u05d9', '\u05d7', '\u05dc', '\u05da',
            '\u05e3', '\u05d6', '\u05e1', '\u05d1', '\u05d4', '\u05e0', '\u05de', '\u05e6', '\u05ea', '\u05e5'};
    private static Character[] empty = {};
    private static Integer[] unicode = {32, 35395, 35397, 36302, 36291, 35203, 35201, 35215, 35219, 35268, 97, 98, 99,
            100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 1001, 1002, 1003, 1004, 1005};
    private static Integer[] lowerCaseEnglishCodepoints = {32, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107,
            108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122};
    private static Integer[] doNotEncodeCodepoints = {32, 97, 98, 99};

    @Test
    public void testCreateConverterWithFinalEncodedLetterLength() {
        // Test to ensure that the encodedLetterLength is correctly set as final
        final AlphabetConverter ac = AlphabetConverter.createConverter(lowerCaseEnglishCodepoints, lowerCaseEnglishCodepoints, doNotEncodeCodepoints);
        assertThat(ac.getEncodedCharLength()).isEqualTo(1);
    }

    @Test
    public void testCreateConverterWithFinalEncodedLetterLengthAndEncoding() {
        // Test to ensure that the encodedLetterLength is correctly set as final with different encoding
        final AlphabetConverter ac = AlphabetConverter.createConverter(lowerCaseEnglishCodepoints, numbers, doNotEncodeCodepoints);
        assertThat(ac.getEncodedCharLength()).isEqualTo(2);
    }

    @Test
    public void testCreateConverterWithFinalEncodedLetterLengthAndEmptyDoNotEncode() {
        // Test to ensure that the encodedLetterLength is correctly set as final with empty doNotEncode
        final AlphabetConverter ac = AlphabetConverter.createConverter(lowerCaseEnglishCodepoints, numbers, empty);
        assertThat(ac.getEncodedCharLength()).isEqualTo(2);
    }

    @Test
    public void testCreateConverterWithFinalEncodedLetterLengthAndEmptyEncoding() {
        // Test to ensure that the encodedLetterLength is correctly set as final with empty encoding
        assertThatThrownBy(() -> {
            AlphabetConverter.createConverter(lowerCaseEnglishCodepoints, empty, doNotEncodeCodepoints);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage(
                "Must have at least two encoding characters (excluding those in the 'do not encode' list), but has 0");
    }
}