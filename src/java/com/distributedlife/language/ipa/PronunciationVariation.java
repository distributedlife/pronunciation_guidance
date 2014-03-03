package com.distributedlife.language.ipa;

public class PronunciationVariation {
    private final String where;
    private final String ipa;

    public PronunciationVariation(String where, String ipa) {
        this.where = where;
        this.ipa = ipa;
    }

    public String getIpa() {
        return ipa;
    }

    public String getWhere() {
        return where;
    }
}
