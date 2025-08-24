package org.apache.commons.compress.archivers.zip;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class AsiExtraFieldLLM_Test implements UnixStat {

    @Test
    public void testDefaultValues() {
        // Test to ensure default values are set correctly
        AsiExtraField a = new AsiExtraField();
        assertEquals("default mode", 0, a.getMode());
        assertEquals("default uid", 0, a.getUserId());
        assertEquals("default gid", 0, a.getGroupId());
        assertEquals("default link", "", a.getLinkedFile());
        assertTrue("default dirFlag", !a.isDirectory());
    }

    @Test
    public void testSettersAndGetters() {
        // Test to ensure setters and getters work correctly
        AsiExtraField a = new AsiExtraField();
        a.setMode(0755);
        assertEquals("set mode", 0100755, a.getMode());

        a.setUserId(1000);
        assertEquals("set uid", 1000, a.getUserId());

        a.setGroupId(1000);
        assertEquals("set gid", 1000, a.getGroupId());

        a.setLinkedFile("link");
        assertEquals("set link", "link", a.getLinkedFile());

        a.setDirectory(true);
        assertTrue("set dirFlag", a.isDirectory());
    }

    @Test
    public void testParseFromLocalFileDataWithDefaultValues() throws Exception {
        // Test parsing from local file data with default values
        byte[] data = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        AsiExtraField a = new AsiExtraField();
        a.parseFromLocalFileData(data, 0, data.length);
        assertEquals("default mode", 0, a.getMode());
        assertEquals("default uid", 0, a.getUserId());
        assertEquals("default gid", 0, a.getGroupId());
        assertEquals("default link", "", a.getLinkedFile());
        assertTrue("default dirFlag", !a.isDirectory());
    }

    @Test
    public void testParseFromLocalFileDataWithNonDefaultValues() throws Exception {
        // Test parsing from local file data with non-default values
        byte[] data = {(byte)0x75, (byte)0x8E, 0x41, (byte)0xFD, 0755, (byte)0xA0, 4, 0, 0, 0, 1000, 0, 1000, 0, (byte)'l', (byte)'i', (byte)'n', (byte)'k'};
        AsiExtraField a = new AsiExtraField();
        a.parseFromLocalFileData(data, 0, data.length);
        assertEquals("mode", LINK_FLAG | 0755, a.getMode());
        assertEquals("uid", 1000, a.getUserId());
        assertEquals("gid", 1000, a.getGroupId());
        assertEquals("link", "link", a.getLinkedFile());
        assertTrue("dirFlag", !a.isDirectory());
    }

    @Test
    public void testCloneWithDefaultValues() {
        // Test cloning with default values
        AsiExtraField s1 = new AsiExtraField();
        AsiExtraField s2 = (AsiExtraField) s1.clone();
        assertNotSame(s1, s2);
        assertEquals(s1.getUserId(), s2.getUserId());
        assertEquals(s1.getGroupId(), s2.getGroupId());
        assertEquals(s1.getLinkedFile(), s2.getLinkedFile());
        assertEquals(s1.getMode(), s2.getMode());
        assertEquals(s1.isDirectory(), s2.isDirectory());
    }
}