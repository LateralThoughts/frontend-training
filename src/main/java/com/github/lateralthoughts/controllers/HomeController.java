package com.github.lateralthoughts.controllers;

import com.github.lateralthoughts.collections.comparators.ActivityDayComparator;
import com.github.lateralthoughts.collections.functions.ActivityToClient;
import com.github.lateralthoughts.domain.Activity;
import com.github.lateralthoughts.domain.Company;
import com.github.lateralthoughts.domain.Employee;
import com.github.lateralthoughts.repositories.ActivitiesRepository;
import com.google.common.base.Functions;
import com.google.common.collect.FluentIterable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import static com.github.lateralthoughts.collections.sort.ActivitySort.sortByAscClientAndEmployee;
import static com.google.common.base.Objects.firstNonNull;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Multimaps.index;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class HomeController {

    private final ActivitiesRepository activitiesRepository;

    @Inject
    public HomeController(ActivitiesRepository activitiesRepository) {
        this.activitiesRepository = activitiesRepository;
    }

    @RequestMapping(value = "/", method = GET)
    public ModelAndView showReports(@RequestParam(value = "framework", defaultValue = "require") String framework) {
        if (firstNonNull(framework, "").equalsIgnoreCase("angular")) {
            return new ModelAndView("home/angular");
        }
        ModelAndView view = new ModelAndView("home/index");
        Iterable<Activity> activities = activitiesRepository.findAll(sortByAscClientAndEmployee());
        view.addObject("activitiesByClientAndEmployee", activities_by_Employer_Employee_Client(activities));
        return view;
    }

    private Map<Company, Map<Employee, Map<Company, Collection<Activity>>>> activities_by_Employer_Employee_Client(Iterable<Activity> activities) {
        Map<Company, Map<Employee, Map<Company, Collection<Activity>>>> result = new HashMap<Company, Map<Employee, Map<Company, Collection<Activity>>>>();
        for (Activity activity : activities) {
            updateActivityRecords(result, activity);
        }
        return result;
    }

    private void updateActivityRecords(Map<Company, Map<Employee, Map<Company, Collection<Activity>>>> previousResult, Activity currentActivity) {
        Company employer = currentActivity.getEmployee().getEmployer();
        Map<Employee, Map<Company, Collection<Activity>>> employeesActivities = firstNonNull(
            previousResult.get(employer),
            new HashMap<Employee, Map<Company, Collection<Activity>>>()
        );

        Employee employee = currentActivity.getEmployee();
        Map<Company, Collection<Activity>> previousRecords = firstNonNull(
            employeesActivities.get(employee),
            new HashMap<Company, Collection<Activity>>()
        );

        Collection<Activity> employeeActivities = firstNonNull(
            newArrayList(FluentIterable.from(previousRecords.values()).transformAndConcat(Functions.<Collection<Activity>>identity()).toList()),
            new TreeSet<Activity>(new ActivityDayComparator())
        );

        employeeActivities.add(currentActivity);
        employeesActivities.put(employee, index(employeeActivities, new ActivityToClient()).asMap());
        previousResult.put(employer, employeesActivities);
    }
}
