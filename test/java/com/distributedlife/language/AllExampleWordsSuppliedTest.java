package com.distributedlife.language;

import com.distributedlife.language.ipa.*;
import com.distributedlife.language.search.WordMatcher;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class AllExampleWordsSuppliedTest {
    @Test
    public void shouldHaveAllExampleWords() {
        List<List<Word>> languages = Arrays.asList(Ipa.Vietnamese, Ipa.Cantonese, Ipa.Mandarin);

        for (List<Word> language : languages) {
            List<Word> all = WordMatcher.search("", language);

            for (Word word : all) {
                for (PronunciationVariation pronunciationVariation : word.getPronunciationVariations()) {
                    WordGuide wordGuide = Ipa.getHelpText(pronunciationVariation.getIpa());

                    for (CharacterGuide characterGuide : wordGuide.getCharacterGuides()) {
                        checkParens(characterGuide.getWord());
                    }
                }
            }
        }
    }

    private void checkParens(String word) {
        assertTrue(String.format("%s does not contain: (", word), word.contains("("));
        assertTrue(String.format("%s does not contain: )", word), word.contains(")"));
    }
}
