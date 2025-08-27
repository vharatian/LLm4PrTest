package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ChainedTransformerLLM_Test {

    @Test
    public void testChainedTransformerWithEmptyCollection() {
        Transformer<Object, Object> transformer = ChainedTransformer.chainedTransformer(Collections.emptyList());
        assertNotNull(transformer);
        assertSame(NOPTransformer.nopTransformer(), transformer);
    }

    @Test
    public void testChainedTransformerWithNonEmptyCollection() {
        Transformer<Object, Object> transformer1 = input -> input + "1";
        Transformer<Object, Object> transformer2 = input -> input + "2";
        List<Transformer<Object, Object>> transformers = Arrays.asList(transformer1, transformer2);

        Transformer<Object, Object> chainedTransformer = ChainedTransformer.chainedTransformer(transformers);
        assertNotNull(chainedTransformer);
        assertEquals("test12", chainedTransformer.transform("test"));
    }
}