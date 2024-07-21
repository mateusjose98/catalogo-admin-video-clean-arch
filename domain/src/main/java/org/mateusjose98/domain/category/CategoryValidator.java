package org.mateusjose98.domain.category;

import org.mateusjose98.domain.validation.Error;
import org.mateusjose98.domain.validation.ValidationHandler;
import org.mateusjose98.domain.validation.Validator;

public class CategoryValidator extends Validator {
    public static final int NAME_MIN_LENGTH = 3;
    public static final int NAME_MAX_LENGTH = 255;
    private final Category category;
    public CategoryValidator(final Category category, final ValidationHandler handler) {
        super(handler);
        this.category = category;
    }

    @Override
    public void validate() {
        checkNameConstraints();
    }

    private void checkNameConstraints() {
        final var name = category.getName();
        if(name == null) {
            this.validationHandler().append(new Error("Name is required"));
            return;
        }

        if(name.isBlank()) {
            this.validationHandler().append(new Error("Name cannot be empty"));
            return;
        }

        if(name.trim().length() < NAME_MIN_LENGTH || name.trim().length() > NAME_MAX_LENGTH){
            this.validationHandler().append(new Error("Name must be between 3 and 255"));
        }
    }
}
