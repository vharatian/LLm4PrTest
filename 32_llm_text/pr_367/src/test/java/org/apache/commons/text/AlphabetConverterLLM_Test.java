package org.apache.commons.text;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

public class AlphabetConverterLLM_Test {

    private static final Character[] LOWER_CASE_ENGLISH = {' ', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final Character[] ENGLISH_AND_NUMBERS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' '};
    private static final Character[] LOWER_CASE_ENGLISH_AND_NUMBERS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' '};
    private static final Character[] NUMBERS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final Character[] BINARY = {'0', '1'};
    private static final Character[] HEBREW = {'_', ' ', '\u05e7', '\u05e8', '\u05d0', '\u05d8', '\u05d5', '\u05df', '\u05dd', '\u05e4', '\u05e9', '\u05d3',
            '\u05d2', '\u05db', '\u05e2', '\u05d9', '\u05d7', '\u05dc', '\u05da', '\u05e3', '\u05d6', '\u05e1', '\u05d1', '\u05d4', '\u05e0', '\u05de', '\u05e6',
            '\u05ea', '\u05e5'};
    private static final Integer[] UNICODE = {32, 35395, 35397, 36302, 36291, 35203, 35201, 35215, 35219, 35268, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106,
            107, 108, 109, 110, 1001, 1002, 1003, 1004, 1005};
    private static final Integer[] LOWER_CASE_ENGLISH_CODEPOINTS = {32, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114,
            115, 116, 117, 118, 119, 120, 121, 122};
    private static final Integer[] DO_NOT_ENCODE_CODEPOINTS = {32, 97, 98, 99};

    @Test
    public void testToStringFormat() throws UnsupportedEncodingException {
        final AlphabetConverter ac = AlphabetConverter.createConverterFromChars(LOWER_CASE_ENGLISH, BINARY, ArrayUtils.EMPTY_CHARACTER_OBJECT_ARRAY);
        final String expectedToString = "  -> 32\n" +
                "a -> 97\n" +
                "b -> 98\n" +
                "c -> 99\n" +
                "d -> 100\n" +
                "e -> 101\n" +
                "f -> 102\n" +
                "g -> 103\n" +
                "h -> 104\n" +
                "i -> 105\n" +
                "j -> 106\n" +
                "k -> 107\n" +
                "l -> 108\n" +
                "m -> 109\n" +
                "n -> 110\n" +
                "o -> 111\n" +
                "p -> 112\n" +
                "q -> 113\n" +
                "r -> 114\n" +
                "s -> 115\n" +
                "t -> 116\n" +
                "u -> 117\n" +
                "v -> 118\n" +
                "w -> 119\n" +
                "x -> 120\n" +
                "y -> 121\n" +
                "z -> 122\n";
        assertThat(ac.toString()).isEqualTo(expectedToString);
    }
}