package com.ajs.client.place;

import com.google.gwt.http.client.URL;
import com.google.gwt.place.shared.PlaceTokenizer;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public abstract class AppTokenizer<P extends ActivityPlace> implements PlaceTokenizer<P> {
    private static final Logger logger = Logger.getLogger(ActivityPlace.class.getName());

    private static final String COLON = ":";

    private static final String COMMA = ",";

    public AppTokenizer() {
    }

    @Override
    public String getToken(P place) {
        Map<String, String> parameters = place.getProperties();

        StringBuilder paramBuffer = new StringBuilder();
        for (String key : parameters.keySet()) {
            appendPair(paramBuffer, key, parameters.get(key));
        }

        StringBuilder b = new StringBuilder();
        b.append(paramBuffer.toString());

        return b.toString();
    }

    private void appendPair(StringBuilder b, String key, String value) {
        if (b.length() > 0) {
            b.append(COMMA);
        }
        b.append(encode(String.valueOf(key)));
        b.append(COLON);
        b.append(encode(value));
    }

    protected Map<String, String> parseProperties(String token) {
        if (token == null || token.trim().length() == 0)
            return new HashMap<String, String>();

        int lastParameterIndex = -1;

        if (lastParameterIndex == -1) {
            lastParameterIndex = token.length();
        }

        Map<String, String> properties = buildMap(token.substring(0, lastParameterIndex).split(COMMA));

        return properties;
    }

    private Map<String, String> buildMap(String[] split) {
        HashMap<String, String> properties = new HashMap<String, String>();
        for (String pair : split) {
            String[] splitPair = pair.split(COLON);
            if (splitPair.length == 2) {
                try {
                    properties.put(String.valueOf(decode(splitPair[0])),
                            decode(splitPair[1]));
                } catch (IllegalArgumentException e) {
                    System.out.println("Tokenizer error");
                }
            }
        }
        return properties;
    }

    static String encode(String value) {
        try {
            return URL.encodeQueryString(value);
        } catch (Throwable e) {
            // Out of native environment
            return value;
        }
    }

    static String decode(String value) {
        try {
            return URL.decodeQueryString(value);
        } catch (Throwable e) {
            // Out of native environment
            return value;
        }
    }
}
