package com.github.lateralthoughts.collections.sort;

import org.springframework.data.domain.Sort;

import static org.springframework.data.domain.Sort.Direction.ASC;

public class EmployeeSort {
    public static Sort sortByAscLastName() {
        return new Sort(new Sort.Order(ASC, "lastName"));
    }
}
