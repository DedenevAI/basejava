package com.uraise.webapp.model;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = createResume("uuid1", "Ivan Ivanov");
        System.out.println(resume);
        System.out.println(resume.getContacMap());
        System.out.println(resume.getSectionMap());
    }

    public static Resume createResume(String uuid, String fullName) {
        final String dummy = "dummy";
        Resume resume = new Resume(uuid, fullName);

        Map<ContactType, String> contacMap = resume.getContacMap();
        contacMap.put(ContactType.TELEPHONE_NUMBER, dummy);
        contacMap.put(ContactType.SKYPE, dummy);

        Map<SectionType, AbstractSection> sectionMap = resume.getSectionMap();
        sectionMap.put(SectionType.OBJECTIVE, new TextSection(dummy));
        sectionMap.put(SectionType.PERSONAL, new TextSection(dummy));

        List<String> achievemntContentList = new ArrayList<>();
        achievemntContentList.add(dummy);
        achievemntContentList.add(dummy);
        sectionMap.put(SectionType.ACHIEVEMENT, new ListSection(achievemntContentList));

        List<String> qualificationsContentList = new ArrayList<>();
        qualificationsContentList.add(dummy);
        qualificationsContentList.add(dummy);
        sectionMap.put(SectionType.QUALIFICATIONS, new ListSection(qualificationsContentList));

        List<Company> expCompanyList = new ArrayList<>();
        expCompanyList.add(new Company(new Link(dummy, dummy), new Period(dummy, dummy, 2000, Month.JANUARY,
                2001, Month.JANUARY)));
        expCompanyList.add(new Company(new Link(dummy, dummy), new Period(dummy, dummy, 2000, Month.JANUARY,
                2001, Month.JANUARY)));
        sectionMap.put(SectionType.EXPERIENCE, new CompanySection(expCompanyList));

        List<Company> eduCompanyList = new ArrayList<>();
        expCompanyList.add(new Company(new Link(dummy, dummy), new Period(dummy, dummy, 2001, Month.JANUARY,
                2002, Month.JANUARY)));
        expCompanyList.add(new Company(new Link(dummy, dummy), new Period(dummy, dummy, 2001, Month.JANUARY,
                2002, Month.JANUARY)));
        sectionMap.put(SectionType.EDUCATION, new CompanySection(eduCompanyList));
        return resume;
    }
}
