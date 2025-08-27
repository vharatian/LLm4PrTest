package org.apache.commons.compress.archivers.zip;

import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Test;

public class BinaryTreeLLM_Test {

    @Test
    public void testDecodeWithMaxBitLengthCommentChange() throws IOException {
        final InputStream in = new ByteArrayInputStream(new byte[] { 0x02, 0x42, 0x01, 0x13 });
        final BinaryTree tree = BinaryTree.decode(in, 8);
        assertNotNull(tree);
        final BitStream stream = new BitStream(new ByteArrayInputStream(new byte[] { (byte) 0x8D, (byte) 0xC5, (byte) 0x11, 0x00 }));
        assertEquals(0, tree.read(stream));
        assertEquals(1, tree.read(stream));
        assertEquals(2, tree.read(stream));
        assertEquals(3, tree.read(stream));
        assertEquals(4, tree.read(stream));
        assertEquals(5, tree.read(stream));
        assertEquals(6, tree.read(stream));
        assertEquals(7, tree.read(stream));
    }

    @Test
    public void testDecodeWithEmptyStream() {
        InputStream is = new ByteArrayInputStream(new byte[]{});
        try {
            BinaryTree.decode(is, 8);
            fail("should have thrown IOException");
        } catch (IOException e) {
            // Expected exception
        }
    }

    @Test
    public void testDecodeWithNegativeTotalNumberOfValues() {
        InputStream is = new ByteArrayInputStream(new byte[]{0x02, 0x42, 0x01, 0x13});
        try {
            BinaryTree.decode(is, -1);
            fail("should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }
}