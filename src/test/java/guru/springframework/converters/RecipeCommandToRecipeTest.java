package guru.springframework.converters;

import guru.springframework.commands.*;
import guru.springframework.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_ID);
        recipeCommand.setDescription(RECIPE_DESCRIPTION);
        recipeCommand.setPrepTime(RECIPE_PREPTIME);
        recipeCommand.setCookTime(RECIPE_COOKTIME);
        recipeCommand.setServings(RECIPE_SERVINGS);
        recipeCommand.setSource(RECIPE_SOURCE);
        recipeCommand.setUrl(RECIPE_URL);
        recipeCommand.setDirections(RECIPE_DIRECTIONS);
        recipeCommand.setDifficulty(RECIPE_DIFFICULTY);
        
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);
        notesCommand.setRecipeNotes(RECIPE_NOTES);
        recipeCommand.setNotes(notesCommand);

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(INGREDIENT_ID);
        ingredientCommand.setDescription(INGREDIENT_DESCRIPTION);
        ingredientCommand.setAmount(INGREDIENT_AMOUNT);
        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(UOM_ID);
        uomCommand.setDescription(UOM_DESCRIPTION);
        ingredientCommand.setUom(uomCommand);
        List<IngredientCommand> ingredientCommands = new ArrayList<>();
        ingredientCommands.add(ingredientCommand);
        recipeCommand.setIngredients(ingredientCommands);

        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(CATEGORY_ID);
        categoryCommand.setDescription(CATEGORY_DESCRIPTION);
        List<CategoryCommand> categories = new ArrayList<>();
        categories.add(categoryCommand);
        recipeCommand.setCategories(categories);

        // when
        Recipe recipe = recipeConverter.convert(recipeCommand);

        // then
        assertNotNull(recipe);
        assertNotNull(recipe.getNotes());
        assertNotNull(recipe.getIngredients());
        assertEquals(1, recipe.getIngredients().size());
        assertNotNull(recipe.getCategories());
        assertEquals(1, recipe.getCategories().size());
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(RECIPE_DESCRIPTION, recipe.getDescription());
        assertEquals(RECIPE_PREPTIME, recipe.getPrepTime());
        assertEquals(RECIPE_COOKTIME, recipe.getCookTime());
        assertEquals(RECIPE_SERVINGS, recipe.getServings());
        assertEquals(RECIPE_SOURCE, recipe.getSource());
        assertEquals(RECIPE_URL, recipe.getUrl());
        assertEquals(RECIPE_DIRECTIONS, recipe.getDirections());
        assertEquals(RECIPE_DIFFICULTY, recipe.getDifficulty());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(RECIPE_NOTES, recipe.getNotes().getRecipeNotes());
        assertEquals(INGREDIENT_ID, recipe.getIngredients().iterator().next().getId());
        assertEquals(INGREDIENT_DESCRIPTION, recipe.getIngredients().iterator().next().getDescription());
        assertEquals(INGREDIENT_AMOUNT, recipe.getIngredients().iterator().next().getAmount());
        assertEquals(UOM_ID, recipe.getIngredients().iterator().next().getUom().getId());
        assertEquals(UOM_DESCRIPTION, recipe.getIngredients().iterator().next().getUom().getDescription());
        assertEquals(CATEGORY_ID, recipe.getCategories().iterator().next().getId());
        assertEquals(CATEGORY_DESCRIPTION, recipe.getCategories().iterator().next().getDescription());
    }
}
