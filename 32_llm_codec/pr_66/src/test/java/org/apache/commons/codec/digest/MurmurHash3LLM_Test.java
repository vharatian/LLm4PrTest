package org.apache.commons.codec.digest;

import org.junit.Assert;
import org.junit.Test;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.codec.binary.StringUtils;

public class MurmurHash3LLM_Test {

    private static final byte[] RANDOM_BYTES;
    static {
        int[] randomInts = {
            46, 246, 249, 184, 247, 84, 99, 144, 62, 77, 195, 220, 92, 20, 150, 159, 38, 40, 124, 252, 185, 28, 63, 13, 213, 172, 85, 198, 118, 74, 109, 157, 132, 216, 76, 177, 173, 23, 140, 86, 146, 95, 54, 176, 114, 179, 234, 174, 183, 141, 122, 12, 60, 116, 200, 142, 6, 167, 59, 240, 33, 29, 165, 111, 243, 30, 219, 110, 255, 53, 32, 35, 64, 225, 96, 152, 70, 41, 133, 80, 244, 127, 57, 199, 5, 164, 151, 49, 26, 180, 203, 83, 108, 39, 126, 208, 42, 206, 178, 19, 69, 223, 71, 231, 250, 125, 211, 232, 189, 55, 44, 82, 48, 221, 43, 192, 241, 103, 155, 27, 51, 163, 21, 169, 91, 94, 217, 191, 78, 72, 93, 102, 104, 105, 8, 113, 100, 143, 89, 245, 227, 120, 160, 251, 153, 145, 45, 218, 168, 233, 229, 253, 67, 22, 182, 98, 137, 128, 135, 11, 214, 66, 73, 171, 188, 170, 131, 207, 79, 106, 24, 75, 237, 194, 7, 129, 215, 81, 248, 242, 16, 25, 136, 147, 156, 97, 52, 10, 181, 17, 205, 58, 101, 68, 230, 1, 37, 0, 222, 88, 130, 148, 224, 47, 50, 197, 34, 212, 196, 209, 14, 36, 139, 228, 154, 31, 175, 202, 236, 161, 3, 162, 190, 254, 134, 119, 4, 61, 65, 117, 186, 107, 204, 9, 187, 201, 90, 149, 226, 56, 239, 238, 235, 112, 87, 18, 121, 115, 138, 123, 210, 2, 193, 166, 158, 15
        };
        RANDOM_BYTES = new byte[randomInts.length];
        for (int i = 0; i < RANDOM_BYTES.length; i++) {
            RANDOM_BYTES[i] = (byte) randomInts[i];
        }
    }

    @Test
    public void testHash32WithCorrectedComments() {
        // Test to ensure that the corrected comments do not affect the functionality
        Assert.assertEquals(1905657630, MurmurHash3.hash32(RANDOM_BYTES));
        final int[] answers = {-965378730, 418246248, 1175981702, -616767012, -12304673, 1697005142, -1212417875,
                -420043393, -826068069, -1721451528, -544986914, 892942691, 27535194, 974863697, 1835661694, -894915836,
                1826914566, -677571679, 1218764493, -375719050, -1320048170, -503583763, 1321750696, -175065786, -496878386,
                -12065683, 512351473, 716560510, -1944803590, 10253199, 1105638211, 525704533,};
        for (int i = 0; i < answers.length; i++) {
            final byte[] bytes = Arrays.copyOf(RANDOM_BYTES, i);
            if (i % 4 == 0 || !negativeBytes(bytes, (i / 4) * 4, i % 4)) {
                Assert.assertEquals(answers[i], MurmurHash3.hash32(bytes));
            } else {
                Assert.assertNotEquals(answers[i], MurmurHash3.hash32(bytes));
            }
        }
    }

    private static boolean negativeBytes(final byte[] bytes, final int start, final int length) {
        for (int i = start; i < start + length; i++) {
            if (bytes[i] < 0) {
                return true;
            }
        }
        return false;
    }
}