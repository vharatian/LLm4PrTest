package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FilenameUtilsLLM_Test {

    @Test
    public void testIsIPv6Address() {
        // Test case for valid IPv6 address with compressed zeroes
        assertTrue(FilenameUtils.isIPv6Address("2001:0db8:85a3::8a2e:0370:7334"));
        
        // Test case for valid IPv6 address without compressed zeroes
        assertTrue(FilenameUtils.isIPv6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334"));
        
        // Test case for invalid IPv6 address with too many groups
        assertFalse(FilenameUtils.isIPv6Address("2001:0db8:85a3:0000:0000:8a2e:0370:7334:1234"));
        
        // Test case for invalid IPv6 address with too few groups and no compressed zeroes
        assertFalse(FilenameUtils.isIPv6Address("2001:0db8:85a3:0000:0000:8a2e:0370"));
        
        // Test case for valid IPv6 address with IPv4 ending
        assertTrue(FilenameUtils.isIPv6Address("::ffff:192.168.1.1"));
        
        // Test case for invalid IPv6 address with invalid characters
        assertFalse(FilenameUtils.isIPv6Address("2001:0db8:85a3:0000:0000:8a2e:0370:zzzz"));
    }
}