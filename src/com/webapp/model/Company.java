package com.webapp.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Company {
    private final String name;
    private final String website;
    private final List<Period> periods;

    public Company(String name, String website, List<Period> periods) {
        this.name = name;
        this.website = website;
        this.periods = periods;
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }

    public List<Period> getPeriods() {
        return periods;
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

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getPosition() {
            return position;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Period period = (Period) o;
            return Objects.equals(startDate, period.startDate) && Objects.equals(endDate, period.endDate) && Objects.equals(position, period.position) && Objects.equals(description, period.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, position, description);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(name, company.name) && Objects.equals(website, company.website) && Objects.equals(periods, company.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, website, periods);
    }

    @Override
    public String toString() {
        return name + " (" + website + ")\n" +
                periods.stream()
                        .map(Period::toString)
                        .collect(Collectors.joining("\n"));
    }
}
