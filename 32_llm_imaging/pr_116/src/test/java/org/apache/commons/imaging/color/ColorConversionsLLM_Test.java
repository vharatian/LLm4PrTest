package org.apache.commons.imaging.color;

import static java.lang.Integer.toHexString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.apache.commons.imaging.internal.Debug;
import org.junit.jupiter.api.Test;

public class ColorConversionsLLM_Test {

    private static final int[] SAMPLE_RGBS = { 0xffffffff, 0xff000000,
            0xffff0000, 0xff00ff00, 0xff0000ff, 0xffff00ff, 0xfff0ff00,
            0xff00ffff, 0x00000000, 0xff7f7f7f, };

    @Test
    public void testConvertCIELabToDIN99oLabWithColorCieLab() {
        for (final int rgb : SAMPLE_RGBS) {
            final ColorXyz xyz = ColorConversions.convertRGBtoXYZ(rgb);
            final ColorCieLab cielab = ColorConversions.convertXYZtoCIELab(xyz);
            final ColorDin99Lab din99o = ColorConversions.convertCIELabToDIN99oLab(cielab);
            final ColorCieLab din99_cielab = ColorConversions.convertDIN99oLabToCIELab(din99o);
            final ColorXyz din99_cielab_xyz = ColorConversions.convertCIELabtoXYZ(din99_cielab);
            final int din99_cielab_xyz_rgb = ColorConversions.convertXYZtoRGB(din99_cielab_xyz);
            assertEquals(toHexString(0xffffff & rgb), toHexString(0xffffff & din99_cielab_xyz_rgb));
        }
    }

    @Test
    public void testConvertCIELabToDIN99oLabWithComponents() {
        for (final int rgb : SAMPLE_RGBS) {
            final ColorXyz xyz = ColorConversions.convertRGBtoXYZ(rgb);
            final ColorCieLab cielab = ColorConversions.convertXYZtoCIELab(xyz);
            final ColorDin99Lab din99o = ColorConversions.convertCIELabToDIN99oLab(cielab.L, cielab.a, cielab.b);
            final ColorCieLab din99_cielab = ColorConversions.convertDIN99oLabToCIELab(din99o.L99, din99o.a99, din99o.b99);
            final ColorXyz din99_cielab_xyz = ColorConversions.convertCIELabtoXYZ(din99_cielab);
            final int din99_cielab_xyz_rgb = ColorConversions.convertXYZtoRGB(din99_cielab_xyz);
            assertEquals(toHexString(0xffffff & rgb), toHexString(0xffffff & din99_cielab_xyz_rgb));
        }
    }
}