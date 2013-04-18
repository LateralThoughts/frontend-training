package com.github.lateralthoughts.repositories;

import com.github.lateralthoughts.domain.Company;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomEmployeesRepository {
    List<Company> findNonEmployersByEmployeeId(@Param("employee_id") Long employeeId);
}
