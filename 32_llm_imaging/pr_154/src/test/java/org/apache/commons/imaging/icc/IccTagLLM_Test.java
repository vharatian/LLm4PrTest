package org.apache.commons.imaging.icc;

import org.junit.jupiter.api.Test;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.*;

public class IccTagLLM_Test {

    private static final Logger LOGGER = Logger.getLogger(IccTagTest.class.getName());

    @Test
    public void testDumpWithNullData() throws Exception {
        IccTag iccTag = new IccTag(0x61626364, 0, 0, null);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        iccTag.dump(pw, "prefix");

        pw.flush();
        sw.flush();

        String result = sw.toString();
        assertTrue(result.contains("data: null"));
    }

    @Test
    public void testDumpWithData() throws Exception {
        IccTag iccTag = new IccTag(0x61626364, 0, 4, null);
        byte[] data = new byte[]{0x00, 0x01, 0x02, 0x03};
        iccTag.setData(data);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        iccTag.dump(pw, "prefix");

        pw.flush();
        sw.flush();

        String result = sw.toString();
        assertTrue(result.contains("data: 4"));
        assertTrue(result.contains("data type signature:"));
    }
}