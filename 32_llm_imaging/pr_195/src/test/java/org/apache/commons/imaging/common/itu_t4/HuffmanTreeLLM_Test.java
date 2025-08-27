package org.apache.commons.imaging.common.itu_t4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HuffmanTreeLLM_Test {

    @Test
    void testGrowAndGetNode() throws HuffmanTreeException {
        HuffmanTree<String> tree = new HuffmanTree<>();
        String pattern = "010";
        String value = "testValue";
        tree.insert(pattern, value);

        // Verify that the node at the expected position is not empty and has the correct value
        int position = 0;
        for (char c : pattern.toCharArray()) {
            if (c == '0') {
                position = (position << 1) + 1;
            } else {
                position = (position + 1) << 1;
            }
        }
        HuffmanTree.Node<String> node = tree.growAndGetNode(position);
        assertFalse(node.empty);
        assertEquals(value, node.value);
    }

    @Test
    void testInsertAndDecode() throws HuffmanTreeException, IOException {
        HuffmanTree<String> tree = new HuffmanTree<>();
        String pattern = "010";
        String value = "testValue";
        tree.insert(pattern, value);

        BitInputStreamFlexible bitStream = new BitInputStreamFlexible(new byte[]{0b01000000});
        String decodedValue = tree.decode(bitStream);
        assertEquals(value, decodedValue);
    }

    @Test
    void testInsertThrowsExceptionForLeaf() {
        HuffmanTree<String> tree = new HuffmanTree<>();
        String pattern = "0";
        String value = "testValue";
        assertThrows(HuffmanTreeException.class, () -> {
            tree.insert(pattern, value);
            tree.insert(pattern, "anotherValue");
        });
    }

    @Test
    void testDecodeThrowsExceptionForInvalidBitPattern() {
        HuffmanTree<String> tree = new HuffmanTree<>();
        String pattern = "0";
        String value = "testValue";
        assertThrows(HuffmanTreeException.class, () -> {
            tree.insert(pattern, value);
            BitInputStreamFlexible bitStream = new BitInputStreamFlexible(new byte[]{0b10000000});
            tree.decode(bitStream);
        });
    }
}