package com.distributedlife.language.search;

import com.distributedlife.language.ipa.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WordMatcher {
    public static List<Word> search(String wordToFind, List<Word> language) {
        List<Word> matches = new ArrayList<Word>();

        for (Word wordInLanguage : language) {
            if (match(wordInLanguage.getWord(), wordToFind)) {
                matches.add(wordInLanguage);
            }
        }

        return matches;
    }

    public static List<Word> search(String wordToFind, List<Word> language, Map<String, String> phoneticDialect) {
        List<Word> matches = new ArrayList<Word>();

        for (Word wordInLanguage : language) {
            IpaMapper ipaMapper = new IpaMapper();

            for (PronunciationVariation pronunciationVariation : wordInLanguage.getPronunciationVariations()) {
                String locallyAdjustedIpa = ipaMapper.map(pronunciationVariation.getIpa(), phoneticDialect);
                WordGuide wordGuide = Ipa.getHelpText(locallyAdjustedIpa);

                if (match(wordGuide.getPhoneticCompressed(), wordToFind)) {
                    matches.add(wordInLanguage);
                    break;
                }
                if (match(wordInLanguage.getWord(), wordToFind)) {
                    matches.add(wordInLanguage);
                    break;
                }
            }
        }

        return matches;
    }

    protected static boolean match(String wordInLanguage, String wordToFind) {
        if (wordInLanguage.contains(wordToFind)) {
            return true;
        }
        if (StringUtils.stripAccents(wordInLanguage).contains(wordToFind)) {
            return true;
        }
        return false;
    }
}
