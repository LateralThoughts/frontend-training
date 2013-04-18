package com.github.lateralthoughts.repositories;

import com.github.lateralthoughts.domain.Activity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.repository.annotation.RestResource;


@RestResource(path = "activities", rel = "activities")
public interface ActivitiesRepository extends PagingAndSortingRepository<Activity, Long> {
}
