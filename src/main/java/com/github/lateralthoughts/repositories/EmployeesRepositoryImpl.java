package com.github.lateralthoughts.repositories;

import com.github.lateralthoughts.domain.Company;
import com.github.lateralthoughts.domain.Employee;
import org.springframework.data.repository.query.Param;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Predicates.equalTo;
import static com.google.common.base.Predicates.not;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Lists.newArrayList;

class EmployeesRepositoryImpl implements CustomEmployeesRepository {

    private EntityManager entityManager;
    private CompaniesRepository companiesRepository;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Inject
    public void setCompaniesRepository(CompaniesRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }

    @Override
    public List<Company> findNonEmployersByEmployeeId(@Param("employee_id") Long employeeId) {
        checkArgument(employeeId > 0L);
        Employee employee = entityManager
            .createQuery("SELECT e FROM Employee e WHERE e.id = :employee_id", Employee.class)
            .setParameter("employee_id", employeeId)
            .getSingleResult();

        Iterable<Company> allCompanies = companiesRepository.findAll();
        return newArrayList(filter(allCompanies, not(equalTo(employee.getEmployer()))));
    }
}
