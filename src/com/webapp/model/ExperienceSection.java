package com.webapp.model;

import java.util.List;

public class ExperienceSection extends AbstractSection {
    private final List<Company> companies;

    public ExperienceSection(List<Company> companies) {
        this.companies = companies;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    @Override
    public String toString() {
        return "ExperienceSection{" +
                "companies=" + companies +
                '}';
    }
}
