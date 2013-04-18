package com.github.lateralthoughts.repositories;

import com.github.lateralthoughts.config.TestConfiguration;
import com.github.lateralthoughts.domain.Company;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.rules.ExpectedException.none;

@ContextConfiguration(classes = TestConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class CompaniesRepositoryIT {

    @Rule
    public ExpectedException exception = none();
    private CompaniesRepository companiesRepository;

    @Inject
    public void setCompaniesRepository(CompaniesRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }

    @Test
    public void should_persist_a_valid_company() {
        Company lt = companiesRepository.save(company("Lateral Thoughts", "Paris"));
        assertThat(lt.getId()).isPositive();
    }

    @Test
    public void should_not_persist_companies_with_too_short_name() {
        exception.expect(ConstraintViolationException.class);
        exception.expectMessage("length must be between 3 and 2147483647");
        companiesRepository.save(company("AB", "PARIS"));
    }

    @Test
    public void should_not_persist_companies_with_blank_address() {
        exception.expect(ConstraintViolationException.class);
        exception.expectMessage("may not be empty");
        companiesRepository.save(company("ABC", null));
    }

    private Company company(String name, String address) {
        Company company = new Company();
        company.setName(name);
        company.setAddress(address);
        return company;
    }
}
