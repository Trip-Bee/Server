package com.ssafy.trip.global.util;

import com.ssafy.trip.global.dto.PageRequest;

import java.util.HashMap;
import java.util.Map;

public class PageUtil {

    public static Map<String, String> getStartAndSize(PageRequest pageRequest) {
        Map<String, String> map = new HashMap<>();

        int page = pageRequest.getPage();
        int size = pageRequest.getSize();

        int currentPage = page == 0 ? 1 : page;
        int sizePerPage = size == 0 ? 10 : size;

        int start = currentPage * sizePerPage - sizePerPage;
        map.put("page", String.valueOf(currentPage));
        map.put("size", String.valueOf(sizePerPage));
        map.put("start", String.valueOf(start));

        return map;
    }

}
