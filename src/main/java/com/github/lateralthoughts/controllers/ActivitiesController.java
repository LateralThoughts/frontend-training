package com.github.lateralthoughts.controllers;

import com.github.lateralthoughts.domain.Activity;
import com.github.lateralthoughts.exceptions.ResourceNotFoundException;
import com.github.lateralthoughts.repositories.ActivitiesRepository;
import com.github.lateralthoughts.repositories.CompaniesRepository;
import com.github.lateralthoughts.repositories.EmployeesRepository;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import static com.github.lateralthoughts.collections.sort.CompanySort.sortByAscName;
import static com.github.lateralthoughts.collections.sort.EmployeeSort.sortByAscLastName;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("activities")
public class ActivitiesController {

    private final ActivitiesRepository activitiesRepository;
    private final EmployeesRepository employeesRepository;
    private final CompaniesRepository companiesRepository;
    private final DomainClassConverter<?> entityConverter;

    @Inject
    public ActivitiesController(ActivitiesRepository activitiesRepository,
                                EmployeesRepository employeesRepository,
                                CompaniesRepository companiesRepository,
                                DomainClassConverter<?> entityConverter) {

        this.activitiesRepository = activitiesRepository;
        this.employeesRepository = employeesRepository;
        this.companiesRepository = companiesRepository;
        this.entityConverter = entityConverter;
    }

    @RequestMapping(value = "/new", method = GET)
    public ModelAndView form(@ModelAttribute("activity") Activity activity) {
        ModelAndView view = new ModelAndView("activities/form");
        view.addObject("companies", companiesRepository.findAll(sortByAscName()));
        view.addObject("employees", employeesRepository.findAll(sortByAscLastName()));
        view.addObject("activity", activity);
        return view;
    }

    @RequestMapping(value = "/new", method = POST)
    public ModelAndView store(@ModelAttribute("activity") @Valid Activity activity,
                              BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            return form(activity);
        }
        else {
            activitiesRepository.save(activity);
            return new ModelAndView("redirect:/");
        }
    }

    @RequestMapping(value = "/{id}/delete", method = GET)
    public ModelAndView delete(@PathVariable("id") long id) {
        if (!activitiesRepository.exists(id)) {
            throw new ResourceNotFoundException("Activity not found");
        }
        activitiesRepository.delete(id);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = {"/", ""}, method = GET, headers = {"Accept=application/json"})
    @ResponseBody
    public Iterable<Activity> all() {
        return activitiesRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = GET, headers = {"Accept=application/json"})
    @ResponseBody
    public Activity one(@PathVariable("id") long id) {
        if (!activitiesRepository.exists(id)) {
            throw new ResourceNotFoundException("Activity not found");
        }
        return activitiesRepository.findOne(id);
    }

    @InitBinder
    protected void customizeConversions(final WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(getDefaultDateFormat(), true));
        ((GenericConversionService) binder.getConversionService()).addConverter(entityConverter);
    }

    private DateFormat getDefaultDateFormat() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        return format;
    }
}
