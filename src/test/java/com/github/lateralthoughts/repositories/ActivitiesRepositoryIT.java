package com.github.lateralthoughts.repositories;

import com.github.lateralthoughts.config.TestConfiguration;
import com.github.lateralthoughts.domain.Activity;
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

import javax.inject.Inject;
import javax.sql.DataSource;
import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.Date;

import static com.ninja_squad.dbsetup.Operations.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.rules.ExpectedException.none;

@ContextConfiguration(classes = TestConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class ActivitiesRepositoryIT {

    @Rule
    public ExpectedException exception = none();
    private CompaniesRepository companiesRepository;
    private EmployeesRepository employeesRepository;
    private ActivitiesRepository activitiesRepository;
    private DataSource dataSource;

    @Before
    public void prepare() throws Exception {
        Operation operation =
            sequenceOf(
                deleteAllFrom("activities", "employees", "companies"),
                insertInto("companies")
                    .columns("id", "name", "address")
                    .values(1L, "Lateral Thoughts", "Paris, FRANCE")
                    .build(),
                insertInto("employees")
                    .columns("id", "firstName", "lastName", "employer_id")
                    .values(1L, "Florent", "Biville", 1L)
                    .build());
        new DbSetup(new DataSourceDestination(dataSource), operation).launch();
    }

    @Inject
    public void setCompaniesRepository(CompaniesRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }

    @Inject
    public void setEmployeesRepository(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    @Inject
    public void setActivitiesRepository(ActivitiesRepository activitiesRepository) {
        this.activitiesRepository = activitiesRepository;
    }

    @Inject
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Test
    public void should_persist_a_valid_activity() {
        Employee employee = employeesRepository.findOne(1L);
        Company company = companiesRepository.findOne(1L);

        Activity activity = activitiesRepository.save(activity(employee, company, new BigDecimal(450)));
        assertThat(activity.getId()).isPositive();
    }

    @Test
    public void should_not_persist_an_activity_without_employee() {
        exception.expect(ConstraintViolationException.class);
        exception.expectMessage("may not be null");

        Company company = companiesRepository.findOne(1L);

        activitiesRepository.save(activity(null, company, new BigDecimal(450)));
    }

    @Test
    public void should_not_persist_an_activity_without_client() {
        exception.expect(ConstraintViolationException.class);
        exception.expectMessage("may not be null");

        Employee employee = employeesRepository.findOne(1L);

        activitiesRepository.save(activity(employee, null, new BigDecimal(450)));
    }

    @Test
    public void should_not_persist_an_activity_with_negative_amount() {
        exception.expect(ConstraintViolationException.class);
        exception.expectMessage("must be greater than or equal to 0");

        Employee employee = employeesRepository.findOne(1L);
        Company company = companiesRepository.findOne(1L);

        activitiesRepository.save(activity(employee, company, new BigDecimal(-450)));
    }

    @Test
    public void should_delete_related_activities_when_deleting_employee() {
        Employee employee = employeesRepository.findOne(1L);
        Company company = companiesRepository.findOne(1L);
        Activity activity = activitiesRepository.save(activity(employee, company, new BigDecimal(450)));
        long id = activity.getId();
        assertThat(id).isPositive();

        employeesRepository.delete(employee);
        assertThat(activitiesRepository.findOne(id)).isNull();
    }

    @Test
    public void should_delete_related_activities_when_deleting_client() {
        Employee employee = employeesRepository.findOne(1L);
        Company company = companiesRepository.findOne(1L);
        Activity activity = activitiesRepository.save(activity(employee, company, new BigDecimal(450)));
        long id = activity.getId();
        assertThat(id).isPositive();

        companiesRepository.delete(company);
        assertThat(activitiesRepository.findOne(id)).isNull();
    }

    private Activity activity(Employee employee, Company company, BigDecimal rate) {
        Activity activity = new Activity();
        activity.setEmployee(employee);
        activity.setClient(company);
        activity.setDay(new Date());
        activity.setRate(rate);
        return activity;
    }
}
