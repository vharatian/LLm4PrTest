package org.apache.commons.compress.harmony.unpack200.bytecode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.TreeSet;
import static org.junit.jupiter.api.Assertions.*;

public class ClassConstantPoolLLM_Test {

    private ClassConstantPool classConstantPool;

    @BeforeEach
    public void setUp() {
        classConstantPool = new ClassConstantPool();
    }

    @Test
    public void testInitialSortWithComparator() {
        // Create mock entries
        CPUTF8 utf8Entry1 = new CPUTF8("a");
        CPUTF8 utf8Entry2 = new CPUTF8("b");
        CPClass classEntry1 = new CPClass(utf8Entry1);
        CPClass classEntry2 = new CPClass(utf8Entry2);
        ConstantPoolEntry entry1 = new ConstantPoolEntry(1) {};
        ConstantPoolEntry entry2 = new ConstantPoolEntry(2) {};

        // Add entries to the class constant pool
        classConstantPool.add(entry1);
        classConstantPool.add(entry2);
        classConstantPool.add(utf8Entry1);
        classConstantPool.add(utf8Entry2);
        classConstantPool.add(classEntry1);
        classConstantPool.add(classEntry2);

        // Call the initialSort method
        classConstantPool.resolve(new Segment());

        // Verify the sorting order using the new Comparator
        TreeSet<ConstantPoolEntry> inCpAll = new TreeSet<>(Comparator.comparingInt(ConstantPoolEntry::getGlobalIndex));
        TreeSet<CPUTF8> cpUtf8sNotInCpAll = new TreeSet<>(Comparator.comparing(CPUTF8::underlyingString));
        TreeSet<CPClass> cpClassesNotInCpAll = new TreeSet<>(Comparator.comparing(CPClass::getName));

        for (Object entry : classConstantPool.entries()) {
            if (entry instanceof ConstantPoolEntry) {
                ConstantPoolEntry constantPoolEntry = (ConstantPoolEntry) entry;
                if (constantPoolEntry.getGlobalIndex() == -1) {
                    if (constantPoolEntry instanceof CPUTF8) {
                        cpUtf8sNotInCpAll.add((CPUTF8) constantPoolEntry);
                    } else if (constantPoolEntry instanceof CPClass) {
                        cpClassesNotInCpAll.add((CPClass) constantPoolEntry);
                    } else {
                        fail("Unexpected entry type");
                    }
                } else {
                    inCpAll.add(constantPoolEntry);
                }
            }
        }

        assertEquals(inCpAll.size() + cpUtf8sNotInCpAll.size() + cpClassesNotInCpAll.size(), classConstantPool.entries().size());
    }
}