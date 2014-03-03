package com.distributedlife.language.ipa;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class IpaTest {
    @Test
    public void shouldGetHelpTextForIpa() {
        String ipa = "həˈləʊ̯";

        assertThat(Ipa.getHelpText(ipa).getCharacter(0).getWord(), is("(h)am"));
        assertThat(Ipa.getHelpText(ipa).getCharacter(0).getStress(), is(CharacterGuide.Stress.Primary));
        assertThat(Ipa.getHelpText(ipa).getCharacter(1).getWord(), is("(a)bout"));
        assertThat(Ipa.getHelpText(ipa).getCharacter(1).getStress(), is(CharacterGuide.Stress.Primary));
        assertThat(Ipa.getHelpText(ipa).getCharacter(2).getWord(), is("(l)eft"));
        assertThat(Ipa.getHelpText(ipa).getCharacter(2).getStress(), is(CharacterGuide.Stress.Unstressed));
        assertThat(Ipa.getHelpText(ipa).getCharacter(3).getWord(), is("g(o)"));
        assertThat(Ipa.getHelpText(ipa).getCharacter(3).getStress(), is(CharacterGuide.Stress.Unstressed));
    }

    @Test
    public void shouldHandleAnnotatedLettersCorrectly() {
        String ipa = "m̩";

        assertThat(Ipa.getHelpText(ipa).getCharacter(0).getWord(), is("rhyth(m)"));
    }

    @Test
    public void shouldHandleUnannotatedLettersCorrectly() {
        String ipa = "m";

        assertThat(Ipa.getHelpText(ipa).getCharacter(0).getWord(), is("(m)y"));
    }
}
