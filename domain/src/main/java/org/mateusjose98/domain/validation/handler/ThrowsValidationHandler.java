package org.mateusjose98.domain.validation.handler;

import org.mateusjose98.domain.exceptions.DomainException;
import org.mateusjose98.domain.validation.Error;
import org.mateusjose98.domain.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {
    @Override
    public ValidationHandler append(Error anError) {
        throw DomainException.with(anError);
    }

    @Override
    public ValidationHandler append(ValidationHandler otherHandler) {
        throw DomainException.with(otherHandler.getErrors());
    }

    @Override
    public ValidationHandler validate(Validation validation) {
        try {
            validation.validate();
            return this;
        } catch (Exception e) {
            throw DomainException.with(new Error(e.getMessage()));
        }
    }

    @Override
    public List<Error> getErrors() {
        return List.of();
    }
}
