package com.webapp.model;

import java.util.List;

public class EducationSection extends AbstractSection {
    private final List<Company> companies;

    public EducationSection(List<Company> companies) {
        this.companies = companies;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    @Override
    public String toString() {
        return "EducationSection{" +
                "companies=" + companies +
                '}';
    }
}
