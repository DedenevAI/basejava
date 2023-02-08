package com.uraise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {
    private static final long serialVersionUID = 1L;
    public static final Resume EMPTY = new Resume();
    static {
        EMPTY.setSection(SectionType.OBJECTIVE, TextSection.EMPTY);
        EMPTY.setSection(SectionType.PERSONAL, TextSection.EMPTY);
        EMPTY.setSection(SectionType.ACHIEVEMENT, ListSection.EMPTY);
        EMPTY.setSection(SectionType.QUALIFICATIONS, ListSection.EMPTY);
        EMPTY.setSection(SectionType.EXPERIENCE, new OrganizationSection(Organization.EMPTY));
        EMPTY.setSection(SectionType.EDUCATION, new OrganizationSection(Organization.EMPTY));
    }
    // Unique identifier
    private String uuid;
    private String fullName;
    private final Map<SectionType, AbstractSection> sectionMap = new EnumMap<>(SectionType.class);
    private final Map<ContactType, String> contacMap = new EnumMap<>(ContactType.class);

    public Resume() {
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public Map<SectionType, AbstractSection> getSectionMap() {
        return sectionMap;
    }

    public AbstractSection getSection(SectionType type) {
        return sectionMap.get(type);
    }

    public void setSection(SectionType type, AbstractSection section) {
        sectionMap.put(type, section);
    }

    public Map<ContactType, String> getContacMap() {
        return contacMap;
    }

    public String getContact(ContactType type) {
        return contacMap.get(type);
    }

    public void setContact(ContactType type, String value) {
        contacMap.put(type, value);
    }

    public void addContact(ContactType type, String value) {
        contacMap.put(type, value);
    }

    public void addSection(SectionType type, AbstractSection section) {
        sectionMap.put(type, section);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid) && fullName.equals(resume.fullName) && sectionMap.equals(resume.sectionMap) && contacMap.equals(resume.contacMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, sectionMap, contacMap);
    }

    @Override
    public String toString() {
        return uuid + '(' + fullName + ')';
    }

    @Override
    public int compareTo(Resume o) {
        int cmp = fullName.compareTo(o.fullName);
        return cmp != 0 ? cmp : uuid.compareTo(o.uuid);
    }
}
