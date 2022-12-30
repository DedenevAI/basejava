package com.uraise.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Company {
    private final Link homePage;
    private final String title;
    private final String description;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Company(String name, String url, String title, String description, LocalDate startDate, LocalDate endDate) {
        this.homePage = new Link(name, url);
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Link getHomePage() {
        return homePage;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(homePage, company.homePage) && Objects.equals(title, company.title) &&
                Objects.equals(description, company.description) && Objects.equals(startDate, company.startDate) &&
                Objects.equals(endDate, company.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, title, description, startDate, endDate);
    }

    @Override
    public String toString() {
        return "Company{" +
                "homePage=" + homePage +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
