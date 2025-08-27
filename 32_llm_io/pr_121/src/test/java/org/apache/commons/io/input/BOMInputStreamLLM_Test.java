package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.ByteOrderMark;
import org.junit.jupiter.api.Test;

public class BOMInputStreamLLM_Test {

    @Test
    public void testByteOrderMarkLengthComparator() {
        ByteOrderMark bom1 = new ByteOrderMark("UTF-8", 0xEF, 0xBB, 0xBF);
        ByteOrderMark bom2 = new ByteOrderMark("UTF-16BE", 0xFE, 0xFF);
        ByteOrderMark bom3 = new ByteOrderMark("UTF-32BE", 0x00, 0x00, 0xFE, 0xFF);

        List<ByteOrderMark> boms = Arrays.asList(bom1, bom2, bom3);
        boms.sort(BOMInputStream.ByteOrderMarkLengthComparator);

        assertEquals(bom3, boms.get(0));
        assertEquals(bom1, boms.get(1));
        assertEquals(bom2, boms.get(2));
    }
}