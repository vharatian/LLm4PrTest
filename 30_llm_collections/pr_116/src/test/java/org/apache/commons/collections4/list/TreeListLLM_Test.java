package org.apache.commons.collections4.list;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import junit.framework.Test;
import org.apache.commons.collections4.BulkTest;

public class TreeListLLM_Test<E> extends AbstractListTest<E> {

    public TreeListTest2(final String name) {
        super(name);
    }

    public static Test suite() {
        return BulkTest.makeSuite(TreeListTest2.class);
    }

    @Override
    public TreeList<E> makeObject() {
        return new TreeList<>();
    }

    /**
     * Test to ensure the setOffset method correctly updates the relative position.
     */
    public void testSetOffset() {
        TreeList<String> treeList = new TreeList<>();
        treeList.add("A");
        treeList.add("B");
        treeList.add("C");

        TreeList.AVLNode<String> nodeA = treeList.root.get(0);
        TreeList.AVLNode<String> nodeB = treeList.root.get(1);
        TreeList.AVLNode<String> nodeC = treeList.root.get(2);

        // Initial relative positions
        assertEquals(0, nodeA.relativePosition);
        assertEquals(1, nodeB.relativePosition);
        assertEquals(1, nodeC.relativePosition);

        // Update relative position using setOffset
        nodeA.setOffset(nodeB, 2);
        assertEquals(2, nodeB.relativePosition);

        nodeB.setOffset(nodeC, 3);
        assertEquals(3, nodeC.relativePosition);
    }

    /**
     * Test to ensure the setOffset method handles null nodes correctly.
     */
    public void testSetOffsetWithNullNode() {
        TreeList<String> treeList = new TreeList<>();
        treeList.add("A");
        treeList.add("B");

        TreeList.AVLNode<String> nodeA = treeList.root.get(0);

        // Update relative position using setOffset with null node
        int oldOffset = nodeA.setOffset(null, 2);
        assertEquals(0, oldOffset); // old offset should be 0 as node is null
    }
}