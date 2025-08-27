package org.apache.commons.compress.java.util.jar;

import junit.framework.TestCase;
import java.util.SortedMap;

public class Pack200LLM_Test extends TestCase {

    // Test to ensure that the Packer interface constants are correctly defined
    public void testPackerConstants() {
        assertEquals("pack.class.attribute.", Pack200.Packer.CLASS_ATTRIBUTE_PFX);
        assertEquals("pack.code.attribute.", Pack200.Packer.CODE_ATTRIBUTE_PFX);
        assertEquals("pack.deflate.hint", Pack200.Packer.DEFLATE_HINT);
        assertEquals("pack.effort", Pack200.Packer.EFFORT);
        assertEquals("error", Pack200.Packer.ERROR);
        assertEquals("false", Pack200.Packer.FALSE);
        assertEquals("pack.field.attribute.", Pack200.Packer.FIELD_ATTRIBUTE_PFX);
        assertEquals("keep", Pack200.Packer.KEEP);
        assertEquals("pack.keep.file.order", Pack200.Packer.KEEP_FILE_ORDER);
        assertEquals("latest", Pack200.Packer.LATEST);
        assertEquals("pack.method.attribute.", Pack200.Packer.METHOD_ATTRIBUTE_PFX);
        assertEquals("pack.modification.time", Pack200.Packer.MODIFICATION_TIME);
        assertEquals("pass", Pack200.Packer.PASS);
        assertEquals("pack.pass.file.", Pack200.Packer.PASS_FILE_PFX);
        assertEquals("pack.progress", Pack200.Packer.PROGRESS);
        assertEquals("pack.segment.limit", Pack200.Packer.SEGMENT_LIMIT);
        assertEquals("strip", Pack200.Packer.STRIP);
        assertEquals("true", Pack200.Packer.TRUE);
        assertEquals("pack.unknown.attribute", Pack200.Packer.UNKNOWN_ATTRIBUTE);
    }

    // Test to ensure that the Unpacker interface constants are correctly defined
    public void testUnpackerConstants() {
        assertEquals("unpack.deflate.hint", Pack200.Unpacker.DEFLATE_HINT);
        assertEquals("false", Pack200.Unpacker.FALSE);
        assertEquals("keep", Pack200.Unpacker.KEEP);
        assertEquals("unpack.progress", Pack200.Unpacker.PROGRESS);
        assertEquals("true", Pack200.Unpacker.TRUE);
    }

    // Test to ensure that the Packer properties method returns a non-null SortedMap
    public void testPackerProperties() {
        Pack200.Packer packer = Pack200.newPacker();
        SortedMap<String, String> properties = packer.properties();
        assertNotNull(properties);
    }

    // Test to ensure that the Unpacker properties method returns a non-null SortedMap
    public void testUnpackerProperties() {
        Pack200.Unpacker unpacker = Pack200.newUnpacker();
        SortedMap<String, String> properties = unpacker.properties();
        assertNotNull(properties);
    }
}