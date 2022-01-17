package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeToRecipeCommandTest extends GenericConverterTest {
    RecipeToRecipeCommand recipeConverter;

    @BeforeEach
    void setUp() {
        recipeConverter = new RecipeToRecipeCommand(
                                new NotesToNotesCommand(),
                                new CategoryToCategoryCommand(),
                                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()));
    }

    @Test
    void nullObject() {
        assertNull(recipeConverter.convert(null));
    }

    @Test
    void emptyObject() {
        assertNotNull(recipeConverter.convert(new Recipe()));
    }

    @Test
    void convert() {
        // given
        Recipe recipe = null; // TODO Create object

        // when
        RecipeCommand recipeCommand = recipeConverter.convert(recipe);

        // then
        assertNotNull(recipeCommand);
        // TODO ASSERT EQUALS for each attribute
    }
}