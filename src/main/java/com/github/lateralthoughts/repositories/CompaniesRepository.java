package com.github.lateralthoughts.repositories;

import com.github.lateralthoughts.domain.Company;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.repository.annotation.RestResource;

@RestResource(path = "companies", rel = "companies")
public interface CompaniesRepository extends PagingAndSortingRepository<Company, Long> {
    Company findByName(@Param("name") String name);
}
