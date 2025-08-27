package org.apache.commons.text.similarity;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;

public class IntersectionSimilarityLLM_Test {

    @Test
    public void testIntersectionUsingSetCharacter() {
        final IntersectionSimilarity<Character> similarity =
            new IntersectionSimilarity<>(IntersectionSimilarityTest2::toCharacterSet);
        assertIntersection(similarity, "", "", 0, 0, 0);
        assertIntersection(similarity, "a", "", 1, 0, 0);
        assertIntersection(similarity, "a", "a", 1, 1, 1);
        assertIntersection(similarity, "a", "b", 1, 1, 0);
        assertIntersection(similarity, "aa", "ab", 1, 2, 1);
        assertIntersection(similarity, "ab", "ab", 2, 2, 2);
        assertIntersection(similarity, "aaba", "abaa", 2, 2, 2);
        assertIntersection(similarity, "aaaa", "aa", 1, 1, 1);
        assertIntersection(similarity, "aa", "aaaa", 1, 1, 1);
        assertIntersection(similarity, "aaaa", "aaa", 1, 1, 1);
        assertIntersection(similarity, "aabab", "ababa", 2, 2, 2);
        assertIntersection(similarity, "the same", "the same", 7, 7, 7);
        assertIntersection(similarity, "abcdefghijklm", "ab_defg ijklm", 13, 13, 11);
    }

    private static Set<Character> toCharacterSet(final CharSequence sequence) {
        final int length = sequence.length();
        final Set<Character> set = new HashSet<>(length);
        for (int i = 0; i < length; i++) {
            set.add(sequence.charAt(i));
        }
        return set;
    }

    private static <T> void assertIntersection(final IntersectionSimilarity<T> similarity,
                                               final CharSequence cs1, final CharSequence cs2, final int sizeA, final int sizeB, final int intersection) {
        final IntersectionResult result = similarity.apply(cs1, cs2);
        assertEquals(sizeA, result.getSizeA(), "Size A error");
        assertEquals(sizeB, result.getSizeB(), "Size B error");
        assertEquals(intersection, result.getIntersection(), "Intersection error");
    }
}