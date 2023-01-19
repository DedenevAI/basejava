package com.uraise.webapp.model;

import com.uraise.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

import static com.uraise.webapp.util.DataUtil.of;

@XmlAccessorType(XmlAccessType.FIELD)
public class Period implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String description;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate startDate;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate endDate;

    public Period() {
    }

    public Period(String title, String description, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.description = description == null ? "" : description;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public Period(String title, String description,int startYear, Month startMonth, int endYear, Month endMonth) {
        this(title, description, of(startYear, startMonth), of(endYear, endMonth));
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
        Period period = (Period) o;
        return Objects.equals(title, period.title) && Objects.equals(description, period.description) &&
                Objects.equals(startDate, period.startDate) && Objects.equals(endDate, period.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, startDate, endDate);
    }

    @Override
    public String toString() {
        return "Period{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
