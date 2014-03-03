package com.distributedlife.language.ipa;

import com.distributedlife.language.json.Json;
import com.distributedlife.language.sorting.LongestFirst;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class Ipa {
    public static final String PRIMARY_STRESS_BEFORE = "PRIMARY_STRESS_BEFORE";
    public static final String TONE_MIDDLE = "TONE_MIDDLE";
    public static final String TONE_HIGH = "TONE_HIGH";
    private static final String TONE_LOW = "TONE_LOW";
    private static final String TONE_LOW_MID = "TONE_LOW_MID";
    private static final String TONE_MID_HIGH = "TONE_MID_HIGH";
    private static final String NEW_WORD = "NEW_WORD";
    private static final String NOT_SURE_HOW_TO_HANDLE = "NOT_SURE_HOW_TO_HANDLE";
    public static Map<String, String> GeneralAmerican = loadIpaMapping("/ipaMaps/genAmIpaMap.json");
    public static Map<String, String> AustralianEnglish = loadIpaMapping("/ipaMaps/auEngIpaMap.json");

    private static Map<String, String> IpaExampleWords = buildIpaExampleWordDictionary();
    private static List<String> IpaSymbolsByLength = buildIpaSymbolsSortedByLength();

    public static List<Word> ReceivedPronunciation = loadDictionary("/languages/english.json");
    public static List<Word> Vietnamese = loadDictionary("/languages/vietnamese.json");
    public static List<Word> Cantonese = loadDictionary("/languages/cantonese.json");
    public static List<Word> Mandarin = loadDictionary("/languages/mandarin.json");

    public static WordGuide getHelpText(String ipa) {
        for (String ipaSymbol : IpaSymbolsByLength) {
            String exampleWord = IpaExampleWords.get(ipaSymbol);
            if (exampleWord.isEmpty()) {
                throw new RuntimeException(String.format("Missing example word for IPA: %s", ipaSymbol));
            }

            String text = String.format("%s,", exampleWord).toUpperCase();

            ipa = ipa.replace(ipaSymbol, text);
        }
        ipa = ipa.replace(" ", "NEW_WORD,");

        String[] split = ipa.split(",");
        WordGuide wordGuide = new WordGuide();

        for (String wordSymbol : split) {
            wordSymbol = wordSymbol.trim();

            if (wordSymbol.isEmpty()) {
                continue;
            }
            if (wordSymbol.equals(PRIMARY_STRESS_BEFORE)) {
                wordGuide.applyPrimaryStress();
                continue;
            }
            if (wordSymbol.equals(TONE_LOW)) {
                wordGuide.addTone(WordGuide.Tones.Low);
                continue;
            }
            if (wordSymbol.equals(TONE_LOW_MID)) {
                wordGuide.addTone(WordGuide.Tones.LowMid);
                continue;
            }
            if (wordSymbol.equals(TONE_MIDDLE)) {
                wordGuide.addTone(WordGuide.Tones.Mid);
                continue;
            }
            if (wordSymbol.equals(TONE_MID_HIGH)) {
                wordGuide.addTone(WordGuide.Tones.MidHigh);
                continue;
            }
            if (wordSymbol.equals(TONE_HIGH)) {
                wordGuide.addTone(WordGuide.Tones.High);
                continue;
            }
            if (wordSymbol.equals(NEW_WORD)) {
                wordGuide.addTone(WordGuide.Tones.Break);
                continue;
            }
            if (wordSymbol.equals(NOT_SURE_HOW_TO_HANDLE)) {
                continue;
            }

            wordGuide.addCharacter(new CharacterGuide(wordSymbol.toLowerCase()));
        }

        return wordGuide;
    }

    private static List<Word> loadDictionary(String filename) {
        List<Word> dictionary = new ArrayList<Word>();

        JSONArray words = Json.loadDocument(filename).getJSONArray("words");
        for (int i = 0; i < words.length(); i++) {
            JSONObject wordAsJson = words.getJSONObject(i);
            String written = wordAsJson.getString("word");

            JSONArray meaningsAsJson = wordAsJson.getJSONArray("meaning");
            List<String> meanings = new ArrayList<String>();
            for (int m = 0; m < meaningsAsJson.length(); m++) {
                meanings.add(meaningsAsJson.getString(m));
            }

            Word word = new Word(written, meanings);

            JSONArray listOfPronunciation = wordAsJson.getJSONArray("pronunciation");
            for (int p = 0; p < listOfPronunciation.length(); p++) {
                JSONObject pronunciationAsJson = listOfPronunciation.getJSONObject(p);

                word.addPronunciationVariation(new PronunciationVariation(
                        pronunciationAsJson.getString("where"),
                        pronunciationAsJson.getString("ipa")
                ));
            }

            dictionary.add(word);
        }

        return dictionary;
    }

    private static List<String> buildIpaSymbolsSortedByLength() {
        List<String> ipaSymbols = new ArrayList<String>();

        for (String key : buildIpaExampleWordDictionary().keySet()) {
            ipaSymbols.add(key);
        }

        Collections.sort(ipaSymbols, new LongestFirst());

        return ipaSymbols;
    }

    private static Map<String, String> buildIpaExampleWordDictionary() {
        Map<String, String> dictionary = new HashMap<String, String>();

        JSONArray exampleWords = Json.loadDocument("/ipaExampleWords.json").getJSONArray("exampleWords");
        for (int i = 0; i < exampleWords.length(); i++) {
            String ipa = exampleWords.getJSONObject(i).getString("ipa");
            String word = exampleWords.getJSONObject(i).getString("word");

            dictionary.put(ipa, word);
        }

        return dictionary;
    }

    private static Map<String, String> loadIpaMapping(String filename) {
        Map<String, String> dictionary = new HashMap<String, String>();

        JSONArray mappings = Json.loadDocument(filename).getJSONArray("mappings");
        for (int i = 0; i < mappings.length(); i++) {
            String from = mappings.getJSONObject(i).getString("from");
            String to = mappings.getJSONObject(i).getString("to");

            dictionary.put(from, to);
        }

        return dictionary;
    }
}
