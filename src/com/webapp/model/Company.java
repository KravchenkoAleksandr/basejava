package com.webapp.model;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Company {
    private final String name;
    private final String link;
    private final List<Period> periods;

    public Company(String name, String link, List<Period> periods) {
        this.name = name;
        this.link = link;
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

        @Override
        public String toString() {
            String endTime = endDate != null ? endDate.toString() : "по настоящее время";
            return "Period{" +
                    "startDate=" + startDate +
                    ", endDate=" + endTime +
                    ", position='" + position + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return name + " (" + link + ")\n" +
                periods.stream()
                        .map(Period::toString)
                        .collect(Collectors.joining("\n"));
    }
}
