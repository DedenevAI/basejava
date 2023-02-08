package com.uraise.webapp.model;

import java.time.Month;
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
        List<String> achievemntContentList = new ArrayList<>();
        achievemntContentList.add(dummy);


        resume.addContact(ContactType.TELEPHONE_NUMBER, dummy);
        resume.addContact(ContactType.EMAIL, dummy);

        resume.addSection(SectionType.OBJECTIVE, new TextSection(dummy));
        resume.addSection(SectionType.PERSONAL, new TextSection(dummy));

        resume.addSection(SectionType.ACHIEVEMENT, new ListSection(achievemntContentList));
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection(achievemntContentList));

        resume.addSection(SectionType.EXPERIENCE, new ListSection(achievemntContentList));

        resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(
                new Organization("Institute", null,
                        new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "student", dummy),
                        new Organization.Position(2001, Month.MARCH, 2000, Month.JANUARY, "student", dummy))));
        resume.addSection(SectionType.EDUCATION, new OrganizationSection(
                new Organization("Institute", null,
                        new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "student", dummy),
                        new Organization.Position(2001, Month.MARCH, 2000, Month.JANUARY, "student", dummy))));

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
//
        return resume;
    }
}
