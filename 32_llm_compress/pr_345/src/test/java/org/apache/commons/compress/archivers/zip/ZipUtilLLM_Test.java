package org.apache.commons.compress.archivers.zip;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class ZipUtilLLM_Test {

    @Test
    public void testIsDosTime() {
        // Test a date within the DOS time range
        long dosTime = ZipUtil.toDosTime(new Date()).getValue();
        assertTrue(ZipUtil.isDosTime(dosTime));

        // Test a date before 1980
        Calendar cal = Calendar.getInstance();
        cal.set(1979, Calendar.DECEMBER, 31, 23, 59, 59);
        long timeBefore1980 = cal.getTimeInMillis();
        assertFalse(ZipUtil.isDosTime(timeBefore1980));

        // Test a date far in the future
        cal.set(2100, Calendar.JANUARY, 1, 0, 0, 0);
        long timeInFuture = cal.getTimeInMillis();
        assertFalse(ZipUtil.isDosTime(timeInFuture));
    }

    @Test
    public void testJavaToDosTime() {
        // Test a date within the DOS time range
        Calendar cal = Calendar.getInstance();
        cal.set(2023, Calendar.JANUARY, 1, 0, 0, 0);
        long time = cal.getTimeInMillis();
        long dosTime = ZipUtil.toDosTime(new Date(time)).getValue();
        assertEquals(dosTime, ZipUtil.javaToDosTime(time));

        // Test a date before 1980
        cal.set(1979, Calendar.DECEMBER, 31, 23, 59, 59);
        long timeBefore1980 = cal.getTimeInMillis();
        assertEquals(ZipUtil.DOSTIME_BEFORE_1980, ZipUtil.javaToDosTime(timeBefore1980));
    }

    @Test
    public void testDosToJavaDate() {
        // Test a date within the DOS time range
        Calendar cal = Calendar.getInstance();
        cal.set(2023, Calendar.JANUARY, 1, 0, 0, 0);
        long time = cal.getTimeInMillis();
        long dosTime = ZipUtil.toDosTime(new Date(time)).getValue();
        Date javaDate = ZipUtil.dosToJavaDate(dosTime);
        assertEquals(time, javaDate.getTime());

        // Test a date before 1980
        cal.set(1979, Calendar.DECEMBER, 31, 23, 59, 59);
        long timeBefore1980 = cal.getTimeInMillis();
        Date javaDateBefore1980 = ZipUtil.dosToJavaDate(ZipUtil.DOSTIME_BEFORE_1980);
        assertEquals(ZipUtil.DOSTIME_BEFORE_1980, ZipUtil.javaToDosTime(javaDateBefore1980.getTime()));
    }

    @Test
    public void testJavaEpochToLocalDateTime() {
        // Test a date within the DOS time range
        long time = Instant.now().toEpochMilli();
        LocalDateTime ldt = ZipUtil.javaEpochToLocalDateTime(time);
        assertEquals(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()), ldt);

        // Test a date before 1980
        Calendar cal = Calendar.getInstance();
        cal.set(1979, Calendar.DECEMBER, 31, 23, 59, 59);
        long timeBefore1980 = cal.getTimeInMillis();
        LocalDateTime ldtBefore1980 = ZipUtil.javaEpochToLocalDateTime(timeBefore1980);
        assertEquals(LocalDateTime.ofInstant(Instant.ofEpochMilli(timeBefore1980), ZoneId.systemDefault()), ldtBefore1980);
    }
}