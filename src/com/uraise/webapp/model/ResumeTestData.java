package com.uraise.webapp.model;

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
        sectionMap.put(SectionType.OBJECTIVE,new TextSection("Ведущий стажировок и корпоративного обучения " +
                "по Java Web и Enterprise технологиям"));
        sectionMap.put(SectionType.PERSONAL,new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. " +
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
        sectionMap.put(SectionType.ACHIEVEMENT,new ListSection(achievemntContentList));

        List<String> qualificationsContentList = new ArrayList<>();
        qualificationsContentList.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualificationsContentList.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        sectionMap.put(SectionType.QUALIFICATIONS,new ListSection(qualificationsContentList));

        List<Company> expCompanyList = new ArrayList<>();
        expCompanyList.add(new Company("Java Online Projects", "Автор проекта.\n" +
                "Создание, организация и проведение Java онлайн проектов и стажировок.", "10/2013 - Сейчас"));
        expCompanyList.add(new Company("Wrike",
                "Старший разработчик (backend)\n" +
                "Проектирование и разработка онлайн платформы управления проектами Wrike " +
                "(Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
                        "Двухфакторная аутентификация, " + "авторизация по OAuth1, OAuth2, JWT SSO.","10/2014 - 01/2016"));
        sectionMap.put(SectionType.EXPERIENCE, new CompanySection(expCompanyList));

        List<Company> eduCompanyList = new ArrayList<>();
        expCompanyList.add(new Company("Coursera",
                "'Functional Programming Principles in Scala' by Martin Odersky","03/2013 - 05/2013"));
        expCompanyList.add(new Company("Luxoft",
                        "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'",
                "03/2011 - 04/2011"));
        sectionMap.put(SectionType.EDUCATION, new CompanySection(eduCompanyList));

        System.out.println(resume);
        System.out.println(resume.getContacMap());
        System.out.println(resume.getSectionMap());

    }
}
