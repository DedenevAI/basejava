package com.uraise.webapp.model;

public enum ContactType {
    TELEPHONE_NUMBER("Тел.: "),
    SKYPE("Skype: "),
    EMAIL("Email: "),
    LINKEDIN("LinkedIn: "),
    GITHUB("GitHub: "),
    STACKOVERFLOW("Stackoverflow: "),
    HOMEPAGE("Домашняя страница");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
