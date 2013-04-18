package com.github.lateralthoughts.repositories;

import com.github.lateralthoughts.domain.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.repository.annotation.RestResource;

@RestResource(path = "employees", rel = "employees")
public interface EmployeesRepository extends PagingAndSortingRepository<Employee, Long>, CustomEmployeesRepository {

}
