package org.mateusjose98.domain.validation;

import java.util.List;

public interface ValidationHandler {

    ValidationHandler append(Error anError);
    ValidationHandler append(ValidationHandler otherHandler);
    ValidationHandler validate(Validation validation);
    List<Error> getErrors();
    default boolean hasErrors() {
        return getErrors() != null && !getErrors().isEmpty();
    }
    interface Validation {
        void validate();
    }
}
