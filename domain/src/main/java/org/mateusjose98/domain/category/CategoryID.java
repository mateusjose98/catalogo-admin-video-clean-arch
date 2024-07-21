package org.mateusjose98.domain.category;

import org.mateusjose98.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class CategoryID extends Identifier {

    private final String value;

    private CategoryID(final String value) {
        Objects.requireNonNull(value, "Category ID is required");
        this.value = value;
    }

    public static CategoryID unique() {
        return CategoryID.from(java.util.UUID.randomUUID().toString().toLowerCase());
    }

    public static CategoryID from(String anId) {
        return new CategoryID(anId);
    }

    public static CategoryID from(final UUID anId) {
        return new CategoryID(anId.toString().toLowerCase());
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CategoryID that = (CategoryID) object;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
