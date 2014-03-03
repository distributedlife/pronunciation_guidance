package com.distributedlife.language.ipa;

import java.util.ArrayList;
import java.util.List;

public class WordGuide {
    List<CharacterGuide> characterGuides = new ArrayList<CharacterGuide>();
    List<Tones> tones = new ArrayList<Tones>();

    public CharacterGuide getCharacter(int index) {
        return characterGuides.get(index);
    }

    public Tones getTone(int index) {
        return tones.get(index);
    }

    public void addCharacter(CharacterGuide characterGuide) {
        characterGuides.add(characterGuide);
    }

    public void addTone(Tones tone) {
        tones.add(tone);
    }

    public void applyPrimaryStress() {
        for (CharacterGuide guide : characterGuides) {
            guide.setStress(CharacterGuide.Stress.Primary);
        }
    }

    public Integer getCharacterCount() {
        return characterGuides.size();
    }

    public List<CharacterGuide> getCharacterGuides() {
        return characterGuides;
    }

    public List<Tones> getTones() {
        return tones;
    }

    public String getPhoneticCompressed() {
        StringBuilder phoneticCompressed = new StringBuilder();

        for (CharacterGuide characterGuide : characterGuides) {
            String guide = characterGuide.getWord();

            int start = guide.indexOf("(");
            int end = guide.indexOf(")");

            String key = guide.substring(start + 1, end);

            phoneticCompressed.append(key);
        }

        return phoneticCompressed.toString();
    }

    public enum Tones {High, Low, LowMid, MidHigh, Break, Mid}
}
