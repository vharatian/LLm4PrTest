package org.apache.commons.imaging.icc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IccProfileInfoLLM_Test {

    @Test
    public void testGetDataReturnsClonedArray() {
        byte[] data = {1, 2, 3, 4};
        IccProfileInfo profileInfo = new IccProfileInfo(data, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, new byte[0], new IccTag[0]);
        byte[] returnedData = profileInfo.getData();
        assertArrayEquals(data, returnedData);
        assertNotSame(data, returnedData);
    }

    @Test
    public void testGetProfileIdReturnsClonedArray() {
        byte[] profileId = {5, 6, 7, 8};
        IccProfileInfo profileInfo = new IccProfileInfo(new byte[0], 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, profileId, new IccTag[0]);
        byte[] returnedProfileId = profileInfo.getProfileId();
        assertArrayEquals(profileId, returnedProfileId);
        assertNotSame(profileId, returnedProfileId);
    }
}