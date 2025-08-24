package org.apache.commons.lang3.reflect;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MemberUtilsLLM_Test {

    @Test
    public void testIsAccessible_withNullMember() {
        assertFalse(MemberUtils.isAccessible(null));
    }

    @Test
    public void testIsAccessible_withPublicMember() {
        Member member = mock(Member.class);
        when(member.getModifiers()).thenReturn(Modifier.PUBLIC);
        when(member.isSynthetic()).thenReturn(false);

        assertTrue(MemberUtils.isAccessible(member));
    }

    @Test
    public void testIsAccessible_withSyntheticMember() {
        Member member = mock(Member.class);
        when(member.getModifiers()).thenReturn(Modifier.PUBLIC);
        when(member.isSynthetic()).thenReturn(true);

        assertFalse(MemberUtils.isAccessible(member));
    }

    @Test
    public void testIsAccessible_withNonPublicMember() {
        Member member = mock(Member.class);
        when(member.getModifiers()).thenReturn(Modifier.PRIVATE);
        when(member.isSynthetic()).thenReturn(false);

        assertFalse(MemberUtils.isAccessible(member));
    }
}