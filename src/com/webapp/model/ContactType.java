package com.webapp.model;

public enum ContactType {
    PHONE("Телефон"),
    SKYPE("Skype"),
    EMAIL("Email"),
    LINKEDIN("LinkedIn"),
    GITHUB("GitHub"),
    STACKOVERFLOW("StackOverFlow");

    private String value;

    ContactType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
