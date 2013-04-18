package com.github.lateralthoughts.controllers;

import com.github.lateralthoughts.domain.Company;
import com.github.lateralthoughts.domain.Employee;
import com.github.lateralthoughts.exceptions.ResourceNotFoundException;
import com.github.lateralthoughts.repositories.CompaniesRepository;
import com.github.lateralthoughts.repositories.EmployeesRepository;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Collection;

import static com.github.lateralthoughts.collections.sort.CompanySort.sortByAscName;
import static com.google.common.collect.Lists.newArrayList;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/employees")
public class EmployeesController {

    private static final int PAGE_SIZE = 5;
    private final EmployeesRepository employeesRepository;
    private final CompaniesRepository companiesRepository;
    private final DomainClassConverter entityConverter;

    @Inject
    public EmployeesController(EmployeesRepository employeesRepository,
                               CompaniesRepository companiesRepository,
                               DomainClassConverter entityConverter) {
        this.employeesRepository = employeesRepository;
        this.companiesRepository = companiesRepository;
        this.entityConverter = entityConverter;
    }

    @RequestMapping(method = GET)
    public ModelAndView all(@RequestParam(value = "page", defaultValue = "1") int page) {
        ModelAndView view = new ModelAndView("employees/index");
        Page<Employee> pagedResult = employeesRepository.findAll(new PageRequest(page - 1, PAGE_SIZE, ASC, "lastName"));
        view.addObject("employees", pagedResult.getContent());
        view.addObject("lastPage", 1 + pagedResult.getTotalElements() / (1 + PAGE_SIZE));
        return view;
    }

    @RequestMapping(value = "/new", method = GET)
    public ModelAndView form(@ModelAttribute("employee") Employee employee) {
        ModelAndView view = new ModelAndView("employees/form");
        view.addObject("companies", companiesRepository.findAll(sortByAscName()));
        view.addObject("employee", employee);
        return view;
    }

    @RequestMapping(value = "/new", method = POST)
    public ModelAndView store(@ModelAttribute("employee") @Valid Employee employee,
                              BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            return form(employee);
        }
        else {
            employeesRepository.save(employee);
            return new ModelAndView("redirect:/employees");
        }
    }

    @RequestMapping(value = "/{id}/delete", method = GET)
    public ModelAndView delete(@PathVariable("id") long id) {
        if (!employeesRepository.exists(id)) {
            throw new ResourceNotFoundException("Employee not found");
        }
        employeesRepository.delete(id);
        return new ModelAndView("redirect:/employees");
    }

    @RequestMapping(value = "/{id}/nonEmployers", method = GET, produces = "application/json")
    @ResponseBody
    public Collection<Company> findNonEmployers(@PathVariable("id") long id) {
        // QUICKFIX because of this unresolved (to date) issue:
        // https://github.com/SpringSource/spring-data-rest/issues/19
        if (!employeesRepository.exists(id)) {
            return newArrayList();
        }
        return employeesRepository.findNonEmployersByEmployeeId(id);
    }

    @InitBinder("employee.employer")
    protected void customizeConversions(final WebDataBinder binder) {
        ((GenericConversionService) binder.getConversionService()).addConverter(entityConverter);
    }
}
