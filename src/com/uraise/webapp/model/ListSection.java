package com.uraise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
@XmlAccessorType(XmlAccessType.FIELD)
public class ListSection extends AbstractSection {
    private static final long serialVersionUID = 1L;
    public static final ListSection EMPTY = new ListSection("");
    private List<String> content;

    public ListSection() {
    }

    public ListSection(List<String> contenList) {
        this.content = contenList;
    }

    public ListSection(String... items) {
        this(Arrays.asList(items));
    }

    public List<String> getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public String toString() {
        return "ListAbstaractSection{" +
                "content=" + content +
                '}';
    }
}
