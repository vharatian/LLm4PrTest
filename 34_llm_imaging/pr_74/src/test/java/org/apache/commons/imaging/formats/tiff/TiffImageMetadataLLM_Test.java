package org.apache.commons.imaging.formats.tiff;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.RationalNumber;
import org.apache.commons.imaging.formats.tiff.constants.GpsTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.TiffDirectoryConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TiffImageMetadataLLM_Test {

    @Test
    public void testGPSInfoToString() throws ImageReadException {
        RationalNumber latitudeDegrees = new RationalNumber(8, 1);
        RationalNumber latitudeMinutes = new RationalNumber(40, 1);
        RationalNumber latitudeSeconds = new RationalNumber(422, 10);
        RationalNumber longitudeDegrees = new RationalNumber(115, 1);
        RationalNumber longitudeMinutes = new RationalNumber(26, 1);
        RationalNumber longitudeSeconds = new RationalNumber(218, 10);

        TiffImageMetadata.GPSInfo gpsInfo = new TiffImageMetadata.GPSInfo(
                "S", "E",
                latitudeDegrees, latitudeMinutes, latitudeSeconds,
                longitudeDegrees, longitudeMinutes, longitudeSeconds
        );

        String expected = "[GPS. Latitude: 8 degrees, 40 minutes, 42.2 seconds S, " +
                "Longitude: 115 degrees, 26 minutes, 21.8 seconds E]";
        assertEquals(expected, gpsInfo.toString());
    }

    @Test
    public void testGPSInfoToStringWithDifferentValues() throws ImageReadException {
        RationalNumber latitudeDegrees = new RationalNumber(37, 1);
        RationalNumber latitudeMinutes = new RationalNumber(48, 1);
        RationalNumber latitudeSeconds = new RationalNumber(1234, 100);
        RationalNumber longitudeDegrees = new RationalNumber(122, 1);
        RationalNumber longitudeMinutes = new RationalNumber(25, 1);
        RationalNumber longitudeSeconds = new RationalNumber(5678, 100);

        TiffImageMetadata.GPSInfo gpsInfo = new TiffImageMetadata.GPSInfo(
                "N", "W",
                latitudeDegrees, latitudeMinutes, latitudeSeconds,
                longitudeDegrees, longitudeMinutes, longitudeSeconds
        );

        String expected = "[GPS. Latitude: 37 degrees, 48 minutes, 12.34 seconds N, " +
                "Longitude: 122 degrees, 25 minutes, 56.78 seconds W]";
        assertEquals(expected, gpsInfo.toString());
    }
}