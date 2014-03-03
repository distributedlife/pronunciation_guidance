package com.distributedlife.language.ipa;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WordGuideTest {
    @Test
    public void shouldGetPhoneticCompressed() {
        WordGuide wordGuide = new WordGuide();
        wordGuide.addCharacter(new CharacterGuide("(b)ee"));
        wordGuide.addCharacter(new CharacterGuide("h(a)t"));
        wordGuide.addCharacter(new CharacterGuide("si(ng)"));

        assertThat(wordGuide.getPhoneticCompressed(), is("bang"));
    }
}
