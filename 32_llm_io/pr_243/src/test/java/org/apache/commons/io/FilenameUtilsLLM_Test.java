package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FilenameUtilsLLM_Test {

    @Test
    public void testIsIPv6Address() {
        // Test valid IPv6 address
        assertTrue(FilenameUtils.isIPv6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334"));
        
        // Test invalid IPv6 address with too many groups
        assertFalse(FilenameUtils.isIPv6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334:1234"));
        
        // Test invalid IPv6 address with invalid hex group
        assertFalse(FilenameUtils.isIPv6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334g"));
        
        // Test valid compressed IPv6 address
        assertTrue(FilenameUtils.isIPv6Address("2001:db8::8a2e:370:7334"));
        
        // Test invalid compressed IPv6 address with multiple "::"
        assertFalse(FilenameUtils.isIPv6Address("2001:db8::8a2e::7334"));
        
        // Test valid IPv6 address with embedded IPv4
        assertTrue(FilenameUtils.isIPv6Address("::ffff:192.168.1.1"));
        
        // Test invalid IPv6 address with embedded invalid IPv4
        assertFalse(FilenameUtils.isIPv6Address("::ffff:999.999.999.999"));
    }
}