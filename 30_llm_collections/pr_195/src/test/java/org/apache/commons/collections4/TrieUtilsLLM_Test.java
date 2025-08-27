package org.apache.commons.collections4;

import static org.junit.Assert.*;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.apache.commons.collections4.trie.UnmodifiableTrie;
import org.junit.Test;

public class TrieUtilsLLM_Test {

    @Test
    public void testUnmodifiableTrie() {
        final Trie<String, Object> trie = TrieUtils.unmodifiableTrie(new PatriciaTrie<>());
        assertTrue("Returned object should be an UnmodifiableTrie.", trie instanceof UnmodifiableTrie);
        try {
            TrieUtils.unmodifiableTrie(null);
            fail("Expecting NullPointerException for null trie.");
        } catch (final NullPointerException ex) {
        }
        assertSame("UnmodifiableTrie shall not be decorated", trie, TrieUtils.unmodifiableTrie(trie));
    }
}