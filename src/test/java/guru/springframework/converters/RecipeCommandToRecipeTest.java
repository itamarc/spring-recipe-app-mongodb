package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Category;
import guru.springframework.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandToRecipeTest extends GenericConverterTest {
    RecipeCommandToRecipe recipeConverter;

    @BeforeEach
    void setUp() {
        recipeConverter = new RecipeCommandToRecipe(
                                new NotesCommandToNotes(),
                                new CategoryCommandToCategory(),
                                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()));
    }

    @Test
    void nullObject() {
        assertNull(recipeConverter.convert(null));
    }

    @Test
    void emptyObject() {
        assertNotNull(recipeConverter.convert(new RecipeCommand()));
    }

    @Test
    void convert() {
        // given
        RecipeCommand recipeCommand = null; // TODO Create object

        // when
        Recipe recipe = recipeConverter.convert(recipeCommand);

        // then
        assertNotNull(recipe);
        // TODO ASSERT EQUALS for each attribute
    }
}