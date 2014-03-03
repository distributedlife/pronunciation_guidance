package com.distributedlife.language.search;

import com.distributedlife.language.ipa.Ipa;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WordMatcherTest {
    @Test
    public void shouldGetTheReceivedPronunciationIpaForAWord() {
        String rp = "hello";
        String ipa = "həˈləʊ̯";

        assertThat(WordMatcher.search(rp, Ipa.ReceivedPronunciation).get(0).getPronunciationVariations().get(0).getIpa(), is(ipa));
    }
}
