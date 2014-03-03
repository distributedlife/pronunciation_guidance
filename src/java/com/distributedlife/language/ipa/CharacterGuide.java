package com.distributedlife.language.ipa;

public class CharacterGuide {
    private String word;
    private Stress stress;

    public CharacterGuide(String word) {
        this.word = word;
        this.stress = Stress.Unstressed;
    }

    public void setStress(Stress stress) {
        this.stress = stress;
    }

    public enum Stress {Unstressed, Primary}

    public String getWord() {
        return word;
    }

    public Stress getStress() {
        return stress;
    }

    public String toString() {
        if (stress.equals(Stress.Primary)) {
            return word.toUpperCase();
        } else {
            return word;
        }
    }
}
