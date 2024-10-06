package org.mateusjose98.application.category.create;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mateusjose98.domain.category.CategoryGateway;
import org.mateusjose98.domain.exceptions.DomainException;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateCategoryUseCaseTest {

    @InjectMocks
    private DefaultCreateCategoryUseCase useCase;

    @Mock
    private CategoryGateway gateway;

    @Test
    public void givenAValidCommandWithInactiveCategory_whenCallsCreateCategoryUseCase_shouldReturnInactiveCategoryId() {
        final var expectedDescription = "Filme description";
        final var expectedName = "Filme name";
        final var expectedIsActive = false;

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        Mockito.when(gateway.create(Mockito.any()))
                .thenAnswer(AdditionalAnswers.returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand);

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(gateway, Mockito.times(1))
                .create(
                        Mockito.argThat(
                                aCategory -> {
                                    return aCategory.getName().equals(expectedName)
                                            && aCategory.getDescription().equals(expectedDescription)
                                            && aCategory.isActive() == expectedIsActive
                                            && aCategory.getCreatedAt() != null
                                            && aCategory.getUpdatedAt() != null
                                            && aCategory.getDeletedAt() != null;
                                }
                        ));

    }

    @Test
    public void givenAInValidName_whenCallsCreateCategoryUseCase_shouldReturnDomainException() {
        final var expectedDescription = "Filme description";
        final var expectedIsActive = true;
        final var expectedErrorMessage = "Name is required";
        final var expectedErrorCount = 1;


        final var aCommand = CreateCategoryCommand.with(null, expectedDescription, expectedIsActive);

        final var domainException = Assertions.assertThrows(DomainException.class, () -> {
            final var actualOutput = useCase.execute(aCommand);
        });

        Assertions.assertEquals(expectedErrorMessage, domainException.getMessage());
        Mockito.verify(gateway, Mockito.times(0)).create(Mockito.any());

    }

    @Test
    public void givenAValidCommand_whenGatewayThrowsRandomException_shouldReturnException() {
        final var expectedDescription = "Filme description";
        final var expectedName = "Filmes";
        final var expectedIsActive = true;
        final var expectedErrorMessage = "Error";


        Mockito.when(gateway.create(Mockito.any()))
                .thenThrow(new IllegalStateException("Error"));

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        final var exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            final var actualOutput = useCase.execute(aCommand);
        });

        Assertions.assertEquals(expectedErrorMessage, exception.getMessage());
        Mockito.verify(gateway, Mockito.times(1))
                .create(
                        Mockito.argThat(
                                aCategory -> {
                                    return aCategory.getName().equals(expectedName)
                                            && aCategory.getDescription().equals(expectedDescription)
                                            && aCategory.isActive() == expectedIsActive
                                            && aCategory.getCreatedAt() != null
                                            && aCategory.getUpdatedAt() != null
                                            && aCategory.getDeletedAt() == null;
                                }
                        ));

    }


    @Test
    public void givenAValidCommand_whenCallsCreateCategoryUseCase_shouldReturnCategoryId() {
        final var expectedName = "Filmes";
        final var expectedDescription = "Filme description";
        final var expectedIsActive = true;


        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        Mockito.when(gateway.create(Mockito.any()))
                .thenAnswer(AdditionalAnswers.returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand);

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(gateway, Mockito.times(1))
                .create(
                        Mockito.argThat(
                                aCategory -> {
                                    return aCategory.getName().equals(expectedName)
                                            && aCategory.getDescription().equals(expectedDescription)
                                            && aCategory.isActive() == expectedIsActive
                                            && aCategory.getCreatedAt() != null
                                            && aCategory.getUpdatedAt() != null
                                            && aCategory.getDeletedAt() == null;
                                }
                        ));
    }


}
