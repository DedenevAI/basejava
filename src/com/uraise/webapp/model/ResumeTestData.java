package com.uraise.webapp.model;

import java.util.ArrayList;
import java.util.List;

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


        resume.addContact(ContactType.TELEPHONE_NUMBER, dummy);
        resume.addContact(ContactType.EMAIL, dummy);

        resume.addSection(SectionType.OBJECTIVE, new TextSection(dummy));
        List<String> achievemntContentList = new ArrayList<>();
        achievemntContentList.add(dummy);
        resume.addSection(SectionType.EXPERIENCE, new ListSection(achievemntContentList));

//        Map<SectionType, AbstractSection> sectionMap = resume.getSectionMap();
//        sectionMap.put(SectionType.OBJECTIVE, new TextSection(dummy));
//        sectionMap.put(SectionType.PERSONAL, new TextSection(dummy));
//
//        List<String> achievemntContentList = new ArrayList<>();
//        achievemntContentList.add(dummy);
//        achievemntContentList.add(dummy);
//        sectionMap.put(SectionType.ACHIEVEMENT, new ListSection(achievemntContentList));
//
//        List<String> qualificationsContentList = new ArrayList<>();
//        qualificationsContentList.add(dummy);
//        qualificationsContentList.add(dummy);
//        sectionMap.put(SectionType.QUALIFICATIONS, new ListSection(qualificationsContentList));
//
//        resume.addSection(SectionType.EXPERIENCE, new CompanySection(
//                new Company(new Link(dummy, dummy), new Period(dummy, dummy, 2000, Month.JANUARY, 2001, Month.JANUARY)),
//                new Company(new Link(dummy, dummy), new Period(dummy, dummy, 2000, Month.JANUARY, 2001, Month.JANUARY))));
//        resume.addSection(SectionType.EDUCATION, new CompanySection(
//                new Company(new Link(dummy, dummy), new Period(dummy, dummy, 2002, Month.JANUARY, 2003, Month.JANUARY)),
//                new Company(new Link(dummy, dummy), new Period(dummy, dummy, 2002, Month.JANUARY, 2003, Month.JANUARY))));
//
        return resume;
    }
}
