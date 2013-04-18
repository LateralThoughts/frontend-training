package com.github.lateralthoughts.collections.sort;

import org.springframework.data.domain.Sort;

import static org.springframework.data.domain.Sort.Direction.ASC;

public class ActivitySort {

    public static Sort sortByAscClientAndEmployee() {
        return new Sort(ASC, "clientName").and(new Sort(ASC, "employeeLastName"));
    }
}
