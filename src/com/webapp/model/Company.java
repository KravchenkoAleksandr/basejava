package com.webapp.model;

import java.time.LocalDate;
import java.util.List;

public class Company {
    private final String name;
    private final List<Period> periods;

    public Company(String name, List<Period> periods) {
        this.name = name;
        this.periods = periods;
    }

    public static class Period {
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String position;
        private final String description;

        public Period(LocalDate startDate, LocalDate endDate, String position, String description) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.position = position;
            this.description = description;
        }
    }
}
