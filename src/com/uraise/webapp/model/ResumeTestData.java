package com.uraise.webapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");

        Map<ContactType, String> contacMap = resume.getContacMap();
        contacMap.put(ContactType.TELEPHONE_NUMBER, "+7(921) 855-0482");
        contacMap.put(ContactType.SKYPE, "skype:grigory.kislin");

        Map<SectionType, AbstractSection> sectionMap = resume.getSectionMap();
        sectionMap.put(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения " +
                "по Java Web и Enterprise технологиям"));
        sectionMap.put(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. " +
                "Пурист кода и архитектуры."));

        List<String> achievemntContentList = new ArrayList<>();
        achievemntContentList.add("Организация команды и успешная реализация Java проектов для сторонних заказчиков: " +
                "приложения автопарк на стеке Spring Cloud/микросервисы, " +
                "система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, " +
                "многомодульный Spring Boot + Vaadin проект для комплексных DIY смет");
        achievemntContentList.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", " +
                "\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). " +
                "Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и " +
                "ведение проектов. Более 3500 выпускников.");
        sectionMap.put(SectionType.ACHIEVEMENT, new ListSection(achievemntContentList));

        List<String> qualificationsContentList = new ArrayList<>();
        qualificationsContentList.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualificationsContentList.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        sectionMap.put(SectionType.QUALIFICATIONS, new ListSection(qualificationsContentList));

        List<Company> expCompanyList = new ArrayList<>();
        expCompanyList.add(new Company(new Link("JavaOPS", "https://javaops.ru/"), new Period("Автор проекта.",
                "Создание, организация и проведение Java онлайн проектов и стажировок.",
                LocalDate.of(2013, 10, 1), null)));
        expCompanyList.add(new Company(new Link("Wrike", "https://www.wrike.com/"), new Period("Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, " +
                        "Guava, Vaadin, PostgreSQL, Redis). " +
                        "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.",
                LocalDate.of(2014, 10, 1), LocalDate.of(2016, 1, 1))));
        sectionMap.put(SectionType.EXPERIENCE, new CompanySection(expCompanyList));

        List<Company> eduCompanyList = new ArrayList<>();
        expCompanyList.add(new Company(new Link("Coursera", "https://www.coursera.org/learn/scala-functional-programming"),
                new Period("'Functional Programming Principles in Scala' by Martin Odersky",null,
                        LocalDate.of(2013, 3, 1), LocalDate.of(2013, 5, 1))));
        expCompanyList.add(new Company(new Link("Luxoft", "https://ibs-training.ru/kurs/obektno-orientirovannyy_analiz_i_proektirovanie_na_uml.html"),
                new Period("Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'",null,
                        LocalDate.of(2011, 3, 1), LocalDate.of(2011, 4, 1))));
        sectionMap.put(SectionType.EDUCATION, new CompanySection(eduCompanyList));

        System.out.println(resume);
        System.out.println(resume.getContacMap());
        System.out.println(" ");
        System.out.println(resume.getSectionMap());

    }
}
