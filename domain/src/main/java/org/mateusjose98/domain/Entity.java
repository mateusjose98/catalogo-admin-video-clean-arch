package org.mateusjose98.domain;

import org.mateusjose98.domain.validation.ValidationHandler;

import java.util.Objects;

public abstract class Entity<ID extends Identifier> {
    protected final ID id;

    public abstract void validate(ValidationHandler validationHandler);
    public Entity(final ID id) {
        this.id = Objects.requireNonNull(id, "ID cannot be null");;
    }
    public ID getId() {
        return id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Entity<?> entity = (Entity<?>) object;
        return Objects.equals(getId(), entity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
