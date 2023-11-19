package com.ssafy.trip.global.util;

import com.ssafy.trip.global.dto.SearchRequest;

import java.util.HashMap;
import java.util.Map;

public class SearchUtil {

    public static Map<String, String> getKeyAndWord(SearchRequest searchRequest) {
        Map<String, String> map = new HashMap<>();
        String key = searchRequest.getKey();
        if (key != null && !key.isEmpty()) {
            if ("nickname".equals(key)) {
                map.put("key", "u.nickname");
            } else {
                map.put("key", "p." + key);
            }
            map.put("word", searchRequest.getWord());
        }
        return map;
    }

}
