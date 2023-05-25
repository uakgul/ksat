package com.example.ksat.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Slf4j
public final class ControllerUtil {
    private static final Integer MIN_PAGE = 0;
    private static final Integer MAX_SIZE = 20;
    private static final Integer MIN_SIZE = 1;

    private ControllerUtil() {
    }

    public static Pageable validatePageable(Integer page, Integer size) {
        if (page == null || page < MIN_PAGE) {
            log.debug("Page number is not valid set default page: {}", page);
            page = MIN_PAGE;
        }

        if (size == null || size > MAX_SIZE || size < MIN_SIZE) {
            log.debug("Page size is not valid set default size: {}", size);
            size = MAX_SIZE;
        }
        return PageRequest.of(page, size);
    }
}
