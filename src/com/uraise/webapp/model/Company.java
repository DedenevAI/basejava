package com.uraise.webapp.model;

import java.util.Objects;

public class Company {
    private final String title;
    private final String description;
    private final String date;

    public Company(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(title, company.title) && Objects.equals(description, company.description) && Objects.equals(date, company.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, date);
    }

    @Override
    public String toString() {
        return "Company{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }
}
