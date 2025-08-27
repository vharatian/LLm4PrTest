package org.apache.commons.compress.archivers.arj;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainHeaderLLM_Test {

    @Test
    void testExtendedHeaderBytesInitialization() {
        MainHeader mainHeader = new MainHeader();
        assertNull(mainHeader.extendedHeaderBytes, "extendedHeaderBytes should be null by default");
    }

    @Test
    void testToString() {
        MainHeader mainHeader = new MainHeader();
        mainHeader.archiverVersionNumber = 1;
        mainHeader.minVersionToExtract = 2;
        mainHeader.hostOS = MainHeader.HostOS.UNIX;
        mainHeader.arjFlags = MainHeader.Flags.GARBLED;
        mainHeader.securityVersion = 3;
        mainHeader.fileType = 4;
        mainHeader.reserved = 5;
        mainHeader.dateTimeCreated = 123456789;
        mainHeader.dateTimeModified = 987654321;
        mainHeader.archiveSize = 123456789L;
        mainHeader.securityEnvelopeFilePosition = 6;
        mainHeader.fileSpecPosition = 7;
        mainHeader.securityEnvelopeLength = 8;
        mainHeader.encryptionVersion = 9;
        mainHeader.lastChapter = 10;
        mainHeader.arjProtectionFactor = 11;
        mainHeader.arjFlags2 = 12;
        mainHeader.name = "testName";
        mainHeader.comment = "testComment";
        mainHeader.extendedHeaderBytes = new byte[]{1, 2, 3};

        String expected = "MainHeader [archiverVersionNumber=1, minVersionToExtract=2, hostOS=2, arjFlags=1, securityVersion=3, fileType=4, reserved=5, dateTimeCreated=123456789, dateTimeModified=987654321, archiveSize=123456789, securityEnvelopeFilePosition=6, fileSpecPosition=7, securityEnvelopeLength=8, encryptionVersion=9, lastChapter=10, arjProtectionFactor=11, arjFlags2=12, name=testName, comment=testComment, extendedHeaderBytes=[1, 2, 3]]";
        assertEquals(expected, mainHeader.toString());
    }
}