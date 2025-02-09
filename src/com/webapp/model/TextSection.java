package com.webapp.model;

public class TextSection extends AbstractSection {
    private final String content;

    public TextSection(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return content;
    }
}
