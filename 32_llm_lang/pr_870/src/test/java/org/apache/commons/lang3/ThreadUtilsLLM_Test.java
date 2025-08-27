package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ThreadUtilsLLM_Test {

    @Test
    public void testNamePredicateNullName() {
        assertThrows(NullPointerException.class, () -> new ThreadUtils.NamePredicate(null));
    }

    @Test
    public void testFindThreadByIdWithNullGroupName() {
        assertThrows(NullPointerException.class, () -> ThreadUtils.findThreadById(1L, (String) null));
    }

    @Test
    public void testFindThreadByIdWithInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> ThreadUtils.findThreadById(0L, "groupName"));
    }

    @Test
    public void testFindThreadByIdWithNullThreadGroup() {
        assertThrows(NullPointerException.class, () -> ThreadUtils.findThreadById(1L, (ThreadGroup) null));
    }

    @Test
    public void testFindThreadGroupsWithNullGroup() {
        assertThrows(NullPointerException.class, () -> ThreadUtils.findThreadGroups(null, true, ThreadUtils.ALWAYS_TRUE_PREDICATE));
    }

    @Test
    public void testFindThreadGroupsWithNullPredicate() {
        assertThrows(NullPointerException.class, () -> ThreadUtils.findThreadGroups(ThreadUtils.getSystemThreadGroup(), true, null));
    }

    @Test
    public void testFindThreadGroupsByNameWithNullName() {
        assertThrows(NullPointerException.class, () -> ThreadUtils.findThreadGroupsByName(null));
    }

    @Test
    public void testFindThreadsWithNullGroup() {
        assertThrows(NullPointerException.class, () -> ThreadUtils.findThreads(null, true, ThreadUtils.ALWAYS_TRUE_PREDICATE));
    }

    @Test
    public void testFindThreadsWithNullPredicate() {
        assertThrows(NullPointerException.class, () -> ThreadUtils.findThreads(ThreadUtils.getSystemThreadGroup(), true, null));
    }

    @Test
    public void testFindThreadsByNameWithNullName() {
        assertThrows(NullPointerException.class, () -> ThreadUtils.findThreadsByName(null));
    }

    @Test
    public void testFindThreadsByNameWithNullGroupName() {
        assertThrows(NullPointerException.class, () -> ThreadUtils.findThreadsByName("threadName", (String) null));
    }

    @Test
    public void testFindThreadsByNameWithNullThreadGroup() {
        assertThrows(NullPointerException.class, () -> ThreadUtils.findThreadsByName("threadName", (ThreadGroup) null));
    }
}