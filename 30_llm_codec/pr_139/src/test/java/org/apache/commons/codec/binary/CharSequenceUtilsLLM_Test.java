package org.apache.commons.codec.binary;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CharSequenceUtilsLLM_Test {

    // Test data class to encapsulate test parameters and expected results
    static class TestData {
        final String source;
        final boolean ignoreCase;
        final int toffset;
        final String other;
        final int ooffset;
        final int len;
        final boolean expected;
        final Class<? extends Throwable> throwable;

        TestData(final String source, final boolean ignoreCase, final int toffset,
                 final String other, final int ooffset, final int len, final boolean expected) {
            this.source = source;
            this.ignoreCase = ignoreCase;
            this.toffset = toffset;
            this.other = other;
            this.ooffset = ooffset;
            this.len = len;
            this.expected = expected;
            this.throwable = null;
        }

        TestData(final String source, final boolean ignoreCase, final int toffset,
                 final String other, final int ooffset, final int len, final Class<? extends Throwable> throwable) {
            this.source = source;
            this.ignoreCase = ignoreCase;
            this.toffset = toffset;
            this.other = other;
            this.ooffset = ooffset;
            this.len = len;
            this.expected = false;
            this.throwable = throwable;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append(source).append("[").append(toffset).append("]");
            sb.append(ignoreCase ? " caseblind " : " samecase ");
            sb.append(other).append("[").append(ooffset).append("]");
            sb.append(" ").append(len).append(" => ");
            if (throwable != null) {
                sb.append(throwable);
            } else {
                sb.append(expected);
            }
            return sb.toString();
        }
    }

    // Test data array with various scenarios
    private static final TestData[] TEST_DATA = {
        new TestData("a", true, 0, "abc", 0, 0, true),
        new TestData("a", true, 0, "abc", 0, 1, true),
        new TestData("Abc", true, 0, "abc", 0, 3, true),
        new TestData("Abc", false, 0, "abc", 0, 3, false),
        new TestData("Abc", true, 1, "abc", 1, 2, true),
        new TestData("Abc", false, 1, "abc", 1, 2, true),
        new TestData("Abcd", true, 1, "abcD", 1, 2, true),
        new TestData("Abcd", false, 1, "abcD", 1, 2, true),
    };

    // Abstract class to run tests
    private abstract static class RunTest {
        abstract boolean invoke();

        void run(final TestData data, final String id) {
            if (data.throwable != null) {
                final String msg = id + " Expected " + data.throwable;
                try {
                    invoke();
                    fail(msg + " but nothing was thrown.");
                } catch (final Exception ex) {
                    assertTrue(data.throwable.isAssignableFrom(ex.getClass()),
                            msg + " but was " + ex.getClass().getSimpleName());
                }
            } else {
                final boolean stringCheck = invoke();
                assertEquals(data.expected, stringCheck, id + " Failed test " + data);
            }
        }
    }

    // Test method for regionMatches
    @Test
    public void testRegionMatches() {
        for (final TestData data : TEST_DATA) {
            new RunTest() {
                @Override
                boolean invoke() {
                    return data.source.regionMatches(data.ignoreCase, data.toffset, data.other, data.ooffset, data.len);
                }
            }.run(data, "String");
            new RunTest() {
                @Override
                boolean invoke() {
                    return CharSequenceUtils.regionMatches(data.source, data.ignoreCase, data.toffset, data.other, data.ooffset, data.len);
                }
            }.run(data, "CSString");
            new RunTest() {
                @Override
                boolean invoke() {
                    return CharSequenceUtils.regionMatches(new StringBuilder(data.source), data.ignoreCase, data.toffset, data.other, data.ooffset, data.len);
                }
            }.run(data, "CSNonString");
        }
    }

    // Test constructor
    @SuppressWarnings("unused")
    @Test
    public void testConstructor() {
        new CharSequenceUtils();
    }
}