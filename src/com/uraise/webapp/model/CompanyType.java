package com.uraise.webapp.model;

import java.util.Objects;

public class CompanyType {
    private final String title;
    private final String description;
    private final double date;

    public CompanyType(String title, String description, double date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyType that = (CompanyType) o;
        return Double.compare(that.date, date) == 0 && Objects.equals(title, that.title) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, date);
    }

    @Override
    public String toString() {
        return "CompanyType{" +
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

    public double getDate() {
        return date;
    }
}
