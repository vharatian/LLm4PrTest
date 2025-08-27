package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Calendar;
import org.apache.commons.lang3.AbstractLangTest;
import org.junit.jupiter.api.Test;

public class CalendarUtilsLLM_Test extends AbstractLangTest {

    /**
     * Test for the newly added getDayOfYear method.
     */
    @Test
    public void testGetDayOfYear() {
        assertEquals(Calendar.getInstance().get(Calendar.DAY_OF_YEAR), CalendarUtils.INSTANCE.getDayOfYear());
    }
}