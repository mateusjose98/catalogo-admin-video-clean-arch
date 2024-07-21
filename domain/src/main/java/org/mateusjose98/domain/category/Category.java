package org.mateusjose98.domain.category;

import org.mateusjose98.domain.AggregateRoot;
import org.mateusjose98.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.UUID;

public class Category extends AggregateRoot<CategoryID> {
    private String name;
    private String description;
    private boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Category(final CategoryID id, final String name, final String description, final boolean isActive, final Instant createdAt, final Instant updatedAt, final Instant deletedAt) {
        super(id);
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static Category newCategory(String aName, String aDescription, boolean isActive) {
        final var now = Instant.now();
        final var deletedAt = isActive ? null : now;
        return new Category(CategoryID.unique(), aName, aDescription, isActive, now, now, deletedAt);
    }

    @Override
    public void validate(ValidationHandler validationHandler) {
        new CategoryValidator(this, validationHandler).validate();
    }

    public Category deactivate() {
        if(getDeletedAt() == null) {
            this.deletedAt = Instant.now();
        }
        this.isActive = false;
        this.updatedAt = Instant.now();
        return this;
    }

    public Category activate() {
        this.deletedAt = null;
        this.isActive = true;
        this.updatedAt = Instant.now();
        return this;
    }


    public Category update(String name, String description, boolean isActive) {
        this.name = name;
        this.description = description;
        if(isActive) {
            activate();
        } else {
            deactivate();
        }
        this.updatedAt = Instant.now();
        return this;
    }

    @Override
    public CategoryID getId() {
        return super.getId();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return isActive;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }


}