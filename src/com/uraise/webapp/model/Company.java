package com.uraise.webapp.model;

import java.io.Serializable;
import java.util.Objects;

public class Company implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Link homePage;
    private final Period period;

    public Company(Link homePage, Period period) {
        this.homePage = homePage;
        this.period = period;
    }

    public Link getHomePage() {
        return homePage;
    }

    public Period getPeriod() {
        return period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(homePage, company.homePage) && Objects.equals(period, company.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, period);
    }

    @Override
    public String toString() {
        return "Company{" +
                "homePage=" + homePage +
                ", period=" + period +
                '}';
    }
}
