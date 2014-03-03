package com.distributedlife.language;

import com.distributedlife.language.ipa.Ipa;
import com.distributedlife.language.ipa.IpaMapper;
import com.distributedlife.language.ipa.Word;
import com.distributedlife.language.ipa.WordGuide;
import com.distributedlife.language.search.WordMatcher;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RealWords {
    @Test
    public void shouldHandleAConversionToTheSameThing() {
        String word = "hello";

        List<Word> ipa = WordMatcher.search(word, Ipa.ReceivedPronunciation);

        IpaMapper ipaMapper = new IpaMapper();
        String locallyAdjustedIpa = ipaMapper.map(ipa.get(0).getPronunciationVariations().get(0).getIpa(), Ipa.AustralianEnglish);
        WordGuide wordGuide = Ipa.getHelpText(locallyAdjustedIpa);

        assertThat(wordGuide.getCharacter(0).getWord(), is("(h)am"));
        assertThat(wordGuide.getCharacter(1).getWord(), is("(a)bout"));
        assertThat(wordGuide.getCharacter(2).getWord(), is("(l)eft"));
        assertThat(wordGuide.getCharacter(3).getWord(), is("g(o)"));
        assertThat(wordGuide.getCharacter(4).getWord(), is("g(o)"));
        assertThat(wordGuide.getCharacterCount(), is(4));
    }

    @Test
    public void shouldHandleAConversionToAnotherDialect() {
        String word = "hello";

        List<Word> ipa = WordMatcher.search(word, Ipa.ReceivedPronunciation);

        IpaMapper ipaMapper = new IpaMapper();
        String locallyAdjustedIpa = ipaMapper.map(ipa.get(0).getPronunciationVariations().get(0).getIpa(), Ipa.GeneralAmerican);

        assertThat(locallyAdjustedIpa, is("həˈloʊ̯"));
    }

    @Test
    public void shouldHandleVietnamese() {
        String word = "bánh";

        List<Word> ipa = WordMatcher.search(word, Ipa.Vietnamese);

        IpaMapper ipaMapper = new IpaMapper();
        String locallyAdjustedIpa = ipaMapper.map(ipa.get(0).getPronunciationVariations().get(0).getIpa(), Ipa.AustralianEnglish);
        WordGuide wordGuide = Ipa.getHelpText(locallyAdjustedIpa);

        assertThat(wordGuide.getCharacter(0).getWord(), is("(b)ee"));
        assertThat(wordGuide.getCharacter(1).getWord(), is("h(a)t"));
        assertThat(wordGuide.getCharacter(2).getWord(), is("si(ng)"));
        assertThat(wordGuide.getCharacterCount(), is(3));
        assertThat(wordGuide.getTone(0), is(WordGuide.Tones.Mid));
        assertThat(wordGuide.getTone(1), is(WordGuide.Tones.High));
    }

    @Test
    public void shouldHandlePartialMatches() {
        String word = "bá";

        List<Word> ipa = WordMatcher.search(word, Ipa.Vietnamese);

        assertThat(ipa.get(0).getWord(), is("bánh"));
    }

    @Test
    public void shouldHandleStrippedAccents() {
        String word = "banh";

        List<Word> ipa = WordMatcher.search(word, Ipa.Vietnamese);

        assertThat(ipa.get(0).getWord(), is("bánh"));
    }

    @Test
    public void shouldHandlePhonenticWithDialect() {
        String word = "bang";

        List<Word> ipa = WordMatcher.search(word, Ipa.Vietnamese, Ipa.AustralianEnglish);

        assertThat(ipa.get(0).getWord(), is("bánh"));
    }
}
