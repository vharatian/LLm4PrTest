package org.apache.commons.collections4.iterators;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class NodeListIteratorLLM_Test extends AbstractIteratorTest<Node> {
    private Node[] nodes;
    private boolean createIteratorWithStandardConstr = true;

    public NodeListIteratorTest2() {
        super(NodeListIteratorTest2.class.getSimpleName());
    }

    @BeforeEach
    protected void setUp() throws Exception {
        createIteratorWithStandardConstr = true;
        final Node node1 = createMock(Element.class);
        final Node node2 = createMock(Element.class);
        final Node node3 = createMock(Text.class);
        final Node node4 = createMock(Element.class);
        nodes = new Node[] {node1, node2, node3, node4};
        replay(node1);
        replay(node2);
        replay(node3);
        replay(node4);
    }

    @Override
    public Iterator<Node> makeEmptyIterator() {
        final NodeList emptyNodeList = new NodeList() {
            @Override
            public Node item(final int index) {
                throw new IndexOutOfBoundsException();
            }

            @Override
            public int getLength() {
                return 0;
            }
        };
        if (createIteratorWithStandardConstr) {
            return new NodeListIterator(emptyNodeList);
        }
        final Node parentNode = createMock(Node.class);
        expect(parentNode.getChildNodes()).andStubReturn(emptyNodeList);
        replay(parentNode);
        return new NodeListIterator(parentNode);
    }

    @Override
    public Iterator<Node> makeObject() {
        final NodeList nodeList = new NodeList() {
            @Override
            public Node item(final int index) {
                return nodes[index];
            }

            @Override
            public int getLength() {
                return nodes.length;
            }
        };
        return new NodeListIterator(nodeList);
    }

    @Override
    public boolean supportsRemove() {
        return false;
    }

    @Test
    public void testNullConstructor() {
        assertThrows(NullPointerException.class, () -> new NodeListIterator((Node) null));
    }

    @Test
    public void testEmptyIteratorWithNodeConstructor(){
        createIteratorWithStandardConstr = false;
        testEmptyIterator();
    }

    @Test
    public void testFullIteratorWithNodeConstructor(){
        createIteratorWithStandardConstr = false;
        testFullIterator();
    }
}