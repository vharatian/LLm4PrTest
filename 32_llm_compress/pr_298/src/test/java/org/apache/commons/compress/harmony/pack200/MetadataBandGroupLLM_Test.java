package org.apache.commons.compress.harmony.pack200;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

public class MetadataBandGroupLLM_Test {

    private MetadataBandGroup metadataBandGroup;
    private CpBands cpBands;
    private SegmentHeader segmentHeader;

    @BeforeEach
    public void setUp() {
        cpBands = new CpBands();
        segmentHeader = new SegmentHeader();
        metadataBandGroup = new MetadataBandGroup("testType", MetadataBandGroup.CONTEXT_CLASS, cpBands, segmentHeader, 5);
    }

    @Test
    public void testAddParameterAnnotation() {
        int numParams = 2;
        int[] annoN = {1, 2};
        IntList pairN = new IntList();
        pairN.add(1);
        List<String> typeRS = new ArrayList<>();
        typeRS.add("type1");
        List<String> nameRU = new ArrayList<>();
        nameRU.add("name1");
        List<String> t = new ArrayList<>();
        t.add("B");
        List<Object> values = new ArrayList<>();
        values.add(1);
        List<Integer> caseArrayN = new ArrayList<>();
        caseArrayN.add(1);
        List<String> nestTypeRS = new ArrayList<>();
        nestTypeRS.add("nestType1");
        List<String> nestNameRU = new ArrayList<>();
        nestNameRU.add("nestName1");
        List<Integer> nestPairN = new ArrayList<>();
        nestPairN.add(1);

        metadataBandGroup.addParameterAnnotation(numParams, annoN, pairN, typeRS, nameRU, t, values, caseArrayN, nestTypeRS, nestNameRU, nestPairN);

        assertEquals(1, metadataBandGroup.param_NB.size());
        assertEquals(2, metadataBandGroup.anno_N.size());
        assertEquals(1, metadataBandGroup.pair_N.size());
        assertEquals(1, metadataBandGroup.type_RS.size());
        assertEquals(1, metadataBandGroup.name_RU.size());
        assertEquals(1, metadataBandGroup.T.size());
        assertEquals(1, metadataBandGroup.caseI_KI.size());
        assertEquals(1, metadataBandGroup.casearray_N.size());
        assertEquals(1, metadataBandGroup.nesttype_RS.size());
        assertEquals(1, metadataBandGroup.nestname_RU.size());
        assertEquals(1, metadataBandGroup.nestpair_N.size());
    }

    @Test
    public void testAddAnnotation() {
        String desc = "desc";
        List<String> nameRU = new ArrayList<>();
        nameRU.add("name1");
        List<String> tags = new ArrayList<>();
        tags.add("D");
        List<Object> values = new ArrayList<>();
        values.add(1.0);
        List<Integer> caseArrayN = new ArrayList<>();
        caseArrayN.add(1);
        List<String> nestTypeRS = new ArrayList<>();
        nestTypeRS.add("nestType1");
        List<String> nestNameRU = new ArrayList<>();
        nestNameRU.add("nestName1");
        List<Integer> nestPairN = new ArrayList<>();
        nestPairN.add(1);

        metadataBandGroup.addAnnotation(desc, nameRU, tags, values, caseArrayN, nestTypeRS, nestNameRU, nestPairN);

        assertEquals(1, metadataBandGroup.type_RS.size());
        assertEquals(1, metadataBandGroup.pair_N.size());
        assertEquals(1, metadataBandGroup.name_RU.size());
        assertEquals(1, metadataBandGroup.T.size());
        assertEquals(1, metadataBandGroup.caseD_KD.size());
        assertEquals(1, metadataBandGroup.casearray_N.size());
        assertEquals(1, metadataBandGroup.nesttype_RS.size());
        assertEquals(1, metadataBandGroup.nestname_RU.size());
        assertEquals(1, metadataBandGroup.nestpair_N.size());
    }

    @Test
    public void testRemoveOnePair() {
        List<String> tags = new ArrayList<>();
        tags.add("I");
        List<Object> values = new ArrayList<>();
        values.add(1);
        metadataBandGroup.T.addAll(tags);
        metadataBandGroup.caseI_KI.add(cpBands.getConstant(1));

        metadataBandGroup.removeOnePair();

        assertTrue(metadataBandGroup.T.isEmpty());
        assertTrue(metadataBandGroup.caseI_KI.isEmpty());
    }
}