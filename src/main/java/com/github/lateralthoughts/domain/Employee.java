package com.github.lateralthoughts.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import org.hibernate.annotations.Fetch;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.google.common.base.Objects.equal;
import static java.util.Locale.ENGLISH;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.FetchMode.JOIN;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @ManyToOne
    @NotNull
    @Fetch(value = JOIN)
    private Company employer;
    @JsonIgnore
    @OneToMany(cascade = REMOVE, mappedBy = "employee")
    private List<Activity> activities;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id =  id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Company getEmployer() {
        return employer;
    }

    public void setEmployer(Company employer) {
        this.employer = employer;
    }

    public String getCompleteName() {
        return firstName + " " + lastName.toUpperCase(ENGLISH);
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;
        return equal(firstName, employee.firstName)
            && equal(lastName, employee.lastName)
            && equal(employer, employee.employer);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(firstName, lastName, employer);
    }

}
