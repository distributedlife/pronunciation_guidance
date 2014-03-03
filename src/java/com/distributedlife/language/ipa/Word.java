package com.distributedlife.language.ipa;

import java.util.ArrayList;
import java.util.List;

public class Word {
    private final String word;
    private List<String> meanings;
    private List<PronunciationVariation> pronunciationVariations = new ArrayList<PronunciationVariation>();

    public Word(String word, List<String> meanings) {
        this.word = word;
        this.meanings = meanings;
    }

    public String getWord() {
        return word;
    }

    public List<String> getMeanings() { return meanings; }

    public void addPronunciationVariation(PronunciationVariation pronunciationVariation) {
        pronunciationVariations.add(pronunciationVariation);
    }

    public List<PronunciationVariation> getPronunciationVariations() {
        return pronunciationVariations;
    }
}
