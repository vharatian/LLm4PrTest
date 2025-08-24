package org.apache.commons.lang3.text;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StrBuilderLLM_Test {

    @Test
    public void testToCharArray() {
        StrBuilder sb = new StrBuilder();
        assertArrayEquals(new char[0], sb.toCharArray());

        sb.append("test");
        assertArrayEquals(new char[]{'t', 'e', 's', 't'}, sb.toCharArray());
    }

    @Test
    public void testToCharArrayWithRange() {
        StrBuilder sb = new StrBuilder("test");
        assertArrayEquals(new char[]{'e', 's'}, sb.toCharArray(1, 3));

        assertThrows(IndexOutOfBoundsException.class, () -> sb.toCharArray(-1, 3));
        assertThrows(IndexOutOfBoundsException.class, () -> sb.toCharArray(1, 5));
    }

    @Test
    public void testGetChars() {
        StrBuilder sb = new StrBuilder("test");
        char[] destination = new char[4];
        sb.getChars(0, 4, destination, 0);
        assertArrayEquals(new char[]{'t', 'e', 's', 't'}, destination);

        assertThrows(IndexOutOfBoundsException.class, () -> sb.getChars(-1, 4, destination, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> sb.getChars(0, 5, destination, 0));
    }

    @Test
    public void testInsertCharArray() {
        StrBuilder sb = new StrBuilder("test");
        sb.insert(2, new char[]{'X', 'Y'});
        assertEquals("teXYst", sb.toString());

        assertThrows(IndexOutOfBoundsException.class, () -> sb.insert(-1, new char[]{'X', 'Y'}));
        assertThrows(IndexOutOfBoundsException.class, () -> sb.insert(10, new char[]{'X', 'Y'}));
    }

    @Test
    public void testInsertCharArrayWithOffset() {
        StrBuilder sb = new StrBuilder("test");
        sb.insert(2, new char[]{'A', 'B', 'C', 'D'}, 1, 2);
        assertEquals("teBCst", sb.toString());

        assertThrows(IndexOutOfBoundsException.class, () -> sb.insert(2, new char[]{'A', 'B', 'C', 'D'}, -1, 2));
        assertThrows(IndexOutOfBoundsException.class, () -> sb.insert(2, new char[]{'A', 'B', 'C', 'D'}, 1, 5));
    }

    @Test
    public void testEqualsIgnoreCase() {
        StrBuilder sb1 = new StrBuilder("Test");
        StrBuilder sb2 = new StrBuilder("test");
        assertEquals(true, sb1.equalsIgnoreCase(sb2));

        sb2 = new StrBuilder("Test1");
        assertEquals(false, sb1.equalsIgnoreCase(sb2));
    }

    @Test
    public void testEquals() {
        StrBuilder sb1 = new StrBuilder("Test");
        StrBuilder sb2 = new StrBuilder("Test");
        assertEquals(true, sb1.equals(sb2));

        sb2 = new StrBuilder("test");
        assertEquals(false, sb1.equals(sb2));
    }

    @Test
    public void testHashCode() {
        StrBuilder sb1 = new StrBuilder("Test");
        StrBuilder sb2 = new StrBuilder("Test");
        assertEquals(sb1.hashCode(), sb2.hashCode());

        sb2 = new StrBuilder("test");
        assertEquals(false, sb1.hashCode() == sb2.hashCode());
    }

    @Test
    public void testStrBuilderReader() throws Exception {
        StrBuilder sb = new StrBuilder("test");
        Reader reader = sb.asReader();
        char[] buffer = new char[4];
        int len = reader.read(buffer);
        assertEquals(4, len);
        assertArrayEquals(new char[]{'t', 'e', 's', 't'}, buffer);

        assertThrows(IndexOutOfBoundsException.class, () -> reader.read(buffer, -1, 2));
        assertThrows(IndexOutOfBoundsException.class, () -> reader.read(buffer, 1, 5));
    }
}