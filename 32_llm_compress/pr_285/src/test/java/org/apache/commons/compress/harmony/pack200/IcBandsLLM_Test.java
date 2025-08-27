package org.apache.commons.compress.harmony.pack200;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IcBandsLLM_Test {

    @Test
    public void testIcTupleEquals() {
        CPClass cpClass1 = new CPClass("Class1");
        CPClass cpClass2 = new CPClass("Class2");
        CPUTF8 utf8_1 = new CPUTF8("Inner1");
        CPUTF8 utf8_2 = new CPUTF8("Inner2");

        IcBands.IcTuple tuple1 = new IcBands(null, null, 0).new IcTuple(cpClass1, 1, cpClass2, utf8_1);
        IcBands.IcTuple tuple2 = new IcBands(null, null, 0).new IcTuple(cpClass1, 1, cpClass2, utf8_1);
        IcBands.IcTuple tuple3 = new IcBands(null, null, 0).new IcTuple(cpClass1, 1, cpClass2, utf8_2);
        IcBands.IcTuple tuple4 = new IcBands(null, null, 0).new IcTuple(cpClass1, 1, null, utf8_1);
        IcBands.IcTuple tuple5 = new IcBands(null, null, 0).new IcTuple(cpClass1, 1, cpClass2, null);

        // Test equality with same values
        assertEquals(tuple1, tuple2);

        // Test inequality with different inner name
        assertNotEquals(tuple1, tuple3);

        // Test inequality with null outer class
        assertNotEquals(tuple1, tuple4);

        // Test inequality with null inner name
        assertNotEquals(tuple1, tuple5);
    }

    @Test
    public void testIcTupleEqualsWithNulls() {
        CPClass cpClass1 = new CPClass("Class1");

        IcBands.IcTuple tuple1 = new IcBands(null, null, 0).new IcTuple(cpClass1, 1, null, null);
        IcBands.IcTuple tuple2 = new IcBands(null, null, 0).new IcTuple(cpClass1, 1, null, null);

        // Test equality with null values
        assertEquals(tuple1, tuple2);
    }
}