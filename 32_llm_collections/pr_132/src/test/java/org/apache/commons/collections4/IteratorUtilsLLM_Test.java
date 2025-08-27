package org.apache.commons.collections4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class IteratorUtilsLLM_Test {

    @Test
    public void testNodeListIteratorFormatting() {
        // This test ensures that the formatting change in the Javadoc does not affect functionality
        final Node[] nodes = createNodes();
        final NodeList nodeList = createNodeList(nodes);
        final Iterator<Node> iterator = IteratorUtils.nodeListIterator(nodeList);
        int expectedNodeIndex = 0;
        for (final Node actual : IteratorUtils.asIterable(iterator)) {
            assertEquals(nodes[expectedNodeIndex], actual);
            ++expectedNodeIndex;
        }
        assertEquals(nodes.length, expectedNodeIndex);
    }

    @Test
    public void testIndexOfFormatting() {
        // This test ensures that the formatting change in the method does not affect functionality
        Predicate<Number> testPredicate = equalPredicate((Number) 4);
        int index = IteratorUtils.indexOf(iterableA.iterator(), testPredicate);
        assertEquals(6, index);
        testPredicate = equalPredicate((Number) 45);
        index = IteratorUtils.indexOf(iterableA.iterator(), testPredicate);
        assertEquals(-1, index);
        assertEquals(-1, IteratorUtils.indexOf(null, testPredicate));
        try {
            IteratorUtils.indexOf(iterableA.iterator(), null);
            fail("expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }
    }

    @Test
    public void testToStringFormatting() {
        // This test ensures that the formatting change in the method does not affect functionality
        final List<Object> list = new ArrayList<>();
        list.add(Integer.valueOf(1));
        list.add("Two");
        list.add(null);
        final String result = IteratorUtils.toString(list.iterator());
        assertEquals("[1, Two, null]", result);
    }

    private NodeList createNodeList(final Node[] nodes) {
        return new NodeList() {
            @Override
            public int getLength() {
                return nodes.length;
            }

            @Override
            public Node item(final int index) {
                return nodes[index];
            }
        };
    }

    private Node[] createNodes() {
        final Node node1 = createMock(Node.class);
        final Node node2 = createMock(Node.class);
        final Node node3 = createMock(Node.class);
        final Node node4 = createMock(Node.class);
        replay(node1);
        replay(node2);
        replay(node3);
        replay(node4);
        return new Node[] { node1, node2, node3, node4 };
    }
}