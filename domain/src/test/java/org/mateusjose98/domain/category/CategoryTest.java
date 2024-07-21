package org.mateusjose98.domain.category;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mateusjose98.domain.exceptions.DomainException;
import org.mateusjose98.domain.validation.handler.ThrowsValidationHandler;

public class CategoryTest {

    @Test
    public void givenAValidParams_WhenCallNewCategory_thenInstantiateACategory() {
        final var expectedName = "Filmes";
        final var expectedDescription = "Categoria de filmes";
        final var expectedIsActive = true;
        final var  actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAnInvalidNullName_WhenCallNewCategory_thenShouldReceiveError() {
        final var expectedDescription = "Categoria de filmes";
        final var expectedIsActive = true;

        final var category = Category.newCategory(null, expectedDescription, expectedIsActive);

        final var exception = Assertions
                .assertThrows(DomainException.class,
                        () -> category.validate(new ThrowsValidationHandler())
                );
        Assertions.assertEquals("Name is required", exception.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidEmptyName_WhenCallNewCategory_thenShouldReceiveError() {
        final String blankName = "";
        final var expectedDescription = "Categoria de filmes";
        final var expectedIsActive = true;

        final var category = Category.newCategory(blankName, expectedDescription, expectedIsActive);

        final var exception = Assertions
                .assertThrows(DomainException.class,
                        () -> category.validate(new ThrowsValidationHandler())
                );
        Assertions.assertEquals("Name cannot be empty", exception.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNameLengthLessThan3_WhenCallNewCategory_thenShouldReceiveError() {
        final String blankName = "A   ";
        final var expectedDescription = "Categoria de filmes";
        final var expectedIsActive = true;

        final var category = Category.newCategory(blankName, expectedDescription, expectedIsActive);

        final var exception = Assertions
                .assertThrows(DomainException.class,
                        () -> category.validate(new ThrowsValidationHandler())
                );
        Assertions.assertEquals("Name must be between 3 and 255", exception.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNameLengthMoreThan255_WhenCallNewCategory_thenShouldReceiveError() {
        final String blankName = "O Fabuloso Gerador de Lero-lero v2.0 é capaz de gerar qualquer quantidade de texto vazio e prolixo, ideal para engrossar uma tese de mestrado, impressionar seu chefe ou preparar discursos capazes de curar a insônia da platéia. Basta informar um título pomposo qualquer (nos moldes do que está sugerido aí embaixo) e a quantidade de frases desejada. Voilá! Em dois nano-segundos você terá um texto - ou mesmo um livro inteiro - pronto para impressão. Ou, se preferir, faça copy/paste para um editor de texto para formatá-lo mais sofisticadamente. Lembre-se: aparência é tudo, conteúdo é nada.";
        final var expectedDescription = "Categoria de filmes";
        final var expectedIsActive = true;

        final var category = Category.newCategory(blankName, expectedDescription, expectedIsActive);

        final var exception = Assertions
                .assertThrows(DomainException.class,
                        () -> category.validate(new ThrowsValidationHandler())
                );
        Assertions.assertEquals("Name must be between 3 and 255", exception.getErrors().get(0).message());
    }

    @Test
    public void givenAvalidActiveCategory_whenCallDeactivate_thenShouldDeactivateCategory() throws InterruptedException {
        final var category = Category.newCategory("Filmes", "Categoria de filmes", true);
        final var updatedAt = category.getUpdatedAt();

        Assertions.assertTrue(category.isActive());
        Thread.sleep(1);
        final var actualCategory = category.deactivate();

        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(category.getName(), actualCategory.getName());
        Assertions.assertEquals(category.getDescription(), actualCategory.getDescription());
        Assertions.assertFalse(actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(actualCategory.getDeletedAt());


    }

    @Test
    public void givenAvalidInactiveCategory_whenCallActivate_thenShouldActiveCategory() throws InterruptedException {
        final var category = Category.newCategory("Filmes", "Categoria de filmes",
                false);
        final var updatedAt = category.getUpdatedAt();

        Assertions.assertFalse(category.isActive());
        Thread.sleep(1);
        final var actualCategory = category.activate();

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertEquals(category.getName(), actualCategory.getName());
        Assertions.assertEquals(category.getDescription(), actualCategory.getDescription());
        Assertions.assertTrue(actualCategory.isActive());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualCategory.getDeletedAt());


    }

    @Test
    public void givenAValidCategory_whenCallUpdate_thenShouldUpdateCategory() throws InterruptedException {
        final var category = Category.newCategory("Filmes", "Categoria de filmes", true);
        final var updatedAt = category.getUpdatedAt();
        final var expectedName = "Séries";
        final var expectedDescription = "Categoria de séries";
        Thread.sleep(1);
        final var actualCategory = category.update(expectedName, expectedDescription, true);

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertTrue(actualCategory.isActive());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualCategory.getDeletedAt());
    }


    


}
