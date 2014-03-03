package com.distributedlife.language.ipa;

import com.distributedlife.language.sorting.LongestFirst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class IpaMapper {
    public String map(String receivedPronunciation, Map<String, String> targetLanguage) {
        String mapped = receivedPronunciation;

        List<String> targetMapKeys = new ArrayList<String>(targetLanguage.keySet());
        Collections.sort(targetMapKeys, new LongestFirst());

        for (String mapping : targetMapKeys) {
            mapped = mapped.replace(mapping, targetLanguage.get(mapping));
        }

        return mapped;
    }
}
