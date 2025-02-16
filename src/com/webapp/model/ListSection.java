package com.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private final List<String> items;

    public ListSection(List<String> items) {
        this.items = items;
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListSection that)) return false;
        return Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(items);
    }

    @Override
    public String toString() {
        return String.join("\n", items);
    }
}
