package com.ssafy.trip.global.util;

import com.ssafy.trip.global.dto.PageRequest;

import java.util.HashMap;
import java.util.Map;

public class PageUtil {

    public static Map<String, String> getStartAndSize(PageRequest pageRequest) {
        Map<String, String> map = new HashMap<>();

        String page = pageRequest.getPage();
        String size = pageRequest.getSize();

        int currentPage = (page == null || page.isEmpty() || "0".equals(page)) ? 1 : Integer.parseInt(page);
        int sizePerPage = (size == null || size.isEmpty() || "0".equals(page)) ? 10 : Integer.parseInt(size);

        int start = currentPage * sizePerPage - sizePerPage;
        map.put("page", String.valueOf(currentPage));
        map.put("size", String.valueOf(sizePerPage));
        map.put("start", String.valueOf(start));

        return map;
    }

}
