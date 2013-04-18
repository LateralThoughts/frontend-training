package com.github.lateralthoughts.collections.sort;

import org.springframework.data.domain.Sort;

import static org.springframework.data.domain.Sort.Direction.ASC;

public class CompanySort {
    public static Sort sortByAscName() {
        return new Sort(new Sort.Order(ASC, "name"));
    }
}
