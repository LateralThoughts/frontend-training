package com.github.lateralthoughts.controllers;

import com.github.lateralthoughts.domain.Company;
import com.github.lateralthoughts.exceptions.ResourceNotFoundException;
import com.github.lateralthoughts.repositories.CompaniesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.validation.Valid;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/companies")
public class CompaniesController {

    private static final int PAGE_SIZE = 5;
    private final CompaniesRepository repository;

    @Inject
    public CompaniesController(CompaniesRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = GET)
    public ModelAndView all(@RequestParam(value = "page", defaultValue = "1") int page) {
        ModelAndView view = new ModelAndView("companies/index");
        Page<Company> pagedResult = repository.findAll(new PageRequest(page - 1, PAGE_SIZE, ASC, "name"));
        view.addObject("companies", pagedResult.getContent());
        view.addObject("lastPage", 1 + pagedResult.getTotalElements() / (1 + PAGE_SIZE));
        return view;
    }

    @RequestMapping(value = "/new", method = GET)
    public ModelAndView form(@ModelAttribute("company") Company company) {
        ModelAndView view = new ModelAndView("companies/form");
        view.addObject("company", company);
        return view;
    }

    @RequestMapping(value = "/new", method = POST)
    public ModelAndView store(@ModelAttribute("company") @Valid Company company,
                              BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            return form(company);
        }
        else {
            repository.save(company);
            return new ModelAndView("redirect:/companies");
        }
    }

    @RequestMapping(value = "/{id}/delete", method = GET)
    public ModelAndView delete(@PathVariable("id") long id) {
        if (!repository.exists(id)) {
            throw new ResourceNotFoundException("Company not found");
        }
        repository.delete(id);
        return new ModelAndView("redirect:/companies");
    }
}
