package com.github.lateralthoughts.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

import static com.google.common.base.Objects.equal;
import static com.google.common.base.Objects.toStringHelper;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Length(min = 3)
    private String name;
    @NotEmpty
    private String address;
    @JsonIgnore
    @OneToMany(cascade = REMOVE, mappedBy = "employer")
    private List<Employee> employees;
    @JsonIgnore
    @OneToMany(cascade = REMOVE, mappedBy = "client")
    private List<Activity> clientActivities;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Activity> getClientActivities() {
        return clientActivities;
    }

    public void setClientActivities(List<Activity> clientActivities) {
        this.clientActivities = clientActivities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;
        return equal(name, company.name)
            && equal(address, company.address);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, address);
    }

    @Override
    public String toString() {
        return toStringHelper(this)
            .add("name", name)
            .add("address", address)
        .toString();
    }
}
