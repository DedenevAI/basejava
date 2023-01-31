package com.uraise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanySection extends AbstractSection {
    private static final long serialVersionUID = 1L;
    private List<Company> content;

    public CompanySection() {
    }
    public CompanySection(Company... company) {
        this(Arrays.asList(company));
    }

    public CompanySection(List<Company> content) {
        Objects.requireNonNull(content, "content must not be null");
        this.content = content;
    }

    public List<Company> getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanySection that = (CompanySection) o;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public String toString() {
        return "CompanyAbstaractSection{" +
                "content=" + content +
                '}';
    }
}
