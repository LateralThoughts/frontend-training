package com.github.lateralthoughts.domain;

import com.google.common.base.Objects;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

import static com.google.common.base.Objects.equal;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "activities", uniqueConstraints = {
    @UniqueConstraint(name = "onePerDay", columnNames = {"employee_id","day"})
})
public class Activity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @ManyToOne
    @NotNull
    private Employee employee;
    @ManyToOne
    @NotNull
    private Company client;
    @NotNull
    @Temporal(value = TIMESTAMP)
    private Date day;
    @NotNull
    @Min(0)
    private BigDecimal rate;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Company getClient() {
        return client;
    }

    public void setClient(Company client) {
        this.client = client;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Activity activity = (Activity) o;
        return equal(employee, activity.employee)
            && equal(client, activity.client)
            && equal(day, activity.day)
            && equal(rate, activity.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(employee, client, day, rate);
    }
}
