package com.distributedlife.language.json;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;

public class Json {
    public static JSONObject loadDocument(String filename) {
        JSONObject document;
        try {
            document = new JSONObject(IOUtils.toString(Json.class.getResourceAsStream(filename)));
        } catch (IOException e) {
            throw new RuntimeException("Unable to load IPA Example Word file 'ipaExampleWords.json'");
        }
        return document;
    }
}
