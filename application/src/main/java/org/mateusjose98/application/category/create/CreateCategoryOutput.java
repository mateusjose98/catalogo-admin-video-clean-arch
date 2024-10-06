package org.mateusjose98.application.category.create;

import org.mateusjose98.domain.category.Category;
import org.mateusjose98.domain.category.CategoryID;

public record CreateCategoryOutput(
        CategoryID id
) {
    public static CreateCategoryOutput from(Category category) {
        return new CreateCategoryOutput(category.getId());
    }
}
