package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListUtilsLLM_Test {

    private static final String a = "a";
    private static final String b = "b";
    private static final String c = "c";
    private static final String d = "d";
    private static final String e = "e";
    private String[] fullArray;
    private List<String> fullList;

    @BeforeEach
    public void setUp() {
        fullArray = new String[]{a, b, c, d, e};
        fullList = new ArrayList<>(Arrays.asList(fullArray));
    }

    @Test
    public void testIndexOfWithCollectionUtilsIndexNotFound() {
        Predicate<String> testPredicate = EqualPredicate.equalPredicate("de");
        int index = ListUtils.indexOf(fullList, testPredicate);
        assertEquals(CollectionUtils.INDEX_NOT_FOUND, index);

        assertEquals(CollectionUtils.INDEX_NOT_FOUND, ListUtils.indexOf(null, testPredicate));
        assertEquals(CollectionUtils.INDEX_NOT_FOUND, ListUtils.indexOf(fullList, null));
    }
}