package org.mateusjose98.application.category.create;

import org.mateusjose98.domain.category.Category;
import org.mateusjose98.domain.category.CategoryGateway;
import org.mateusjose98.domain.validation.handler.ThrowsValidationHandler;

import java.util.Objects;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public CreateCategoryOutput execute(final CreateCategoryCommand input) {

        final var aCategory = Category.newCategory(input.name(), input.description(), input.isActive());
        aCategory.validate(new ThrowsValidationHandler());

        Category category = categoryGateway.create(aCategory);

        return CreateCategoryOutput.from(category);
    }
}
