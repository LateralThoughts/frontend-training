package com.github.lateralthoughts.repositories;

import com.github.lateralthoughts.config.TestConfiguration;
import com.github.lateralthoughts.domain.Company;
import com.github.lateralthoughts.domain.Employee;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.sql.DataSource;
import javax.validation.ConstraintViolationException;
import java.util.List;

import static com.ninja_squad.dbsetup.Operations.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.extractProperty;
import static org.junit.rules.ExpectedException.none;

@ContextConfiguration(classes = TestConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class EmployeesRepositoryIT {

    @Rule
    public ExpectedException exception = none();
    private EmployeesRepository employeesRepository;
    private CompaniesRepository companiesRepository;
    private DataSource dataSource;

    @Before
    public void prepare() throws Exception {
        Operation operation =
            sequenceOf(
                deleteAllFrom("employees", "companies"),
                insertInto("companies")
                    .columns("id", "name", "address")
                    .values(1L, "Lateral Thoughts", "Paris, FRANCE")
                    .build());
        new DbSetup(new DataSourceDestination(dataSource), operation).launch();
    }

    @Inject
    public void setEmployeesRepository(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    @Inject
    public void setCompaniesRepository(CompaniesRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }

    @Inject
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Test
    public void should_persist_a_valid_employee() {
        Company lateralThoughts = companiesRepository.findOne(1L);
        Employee flo = employeesRepository.save(employee(lateralThoughts, "Florent", "Biville"));
        assertThat(flo.getId()).isPositive();
    }

    @Test
    public void should_retrieve_only_companies_not_employing_employee() {
        Company lateralThoughts = companiesRepository.findOne(1L);
        Employee flo = employeesRepository.save(employee(lateralThoughts, "Florent", "Biville"));
        companiesRepository.save(company("VIDAL", "Issy-Les-Moulineaux"));

        List<Company> nonEmployers = employeesRepository.findNonEmployersByEmployeeId(flo.getId());
        assertThat(extractProperty("name").from(nonEmployers)).hasSize(1).containsExactly("VIDAL");
    }

    @Test
    public void should_not_persist_an_unemployed_employee() {
        exception.expect(ConstraintViolationException.class);
        exception.expectMessage("may not be null");
        employeesRepository.save(employee(null, "Florent", "Biville"));
    }

    @Test
    public void should_not_persist_an_employee_without_first_name() {
        exception.expect(ConstraintViolationException.class);
        exception.expectMessage("may not be empty");
        Company lateralThoughts = companiesRepository.findOne(1L);
        employeesRepository.save(employee(lateralThoughts, "", "Biville"));
    }

    @Test
    public void should_not_persist_an_employee_without_last_name() {
        exception.expect(ConstraintViolationException.class);
        exception.expectMessage("may not be empty");
        Company lateralThoughts = companiesRepository.findOne(1L);
        employeesRepository.save(employee(lateralThoughts, "Florent", ""));
    }

    @Test
    public void should_delete_related_employees_when_deleting_employer() {
        Company lateralThoughts = companiesRepository.findOne(1L);
        Employee employee = employeesRepository.save(employee(lateralThoughts, "Florent", "Biville"));
        long id = employee.getId();
        assertThat(id).isPositive();

        companiesRepository.delete(lateralThoughts);
        assertThat(employeesRepository.findOne(id)).isNull();
    }

    private Employee employee(Company company, String firstName, String lastName) {
        Employee employee = new Employee();
        employee.setEmployer(company);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        return employee;
    }

    private Company company(String name, String address) {
        Company entity = new Company();
        entity.setName(name);
        entity.setAddress(address);
        return entity;
    }
}
