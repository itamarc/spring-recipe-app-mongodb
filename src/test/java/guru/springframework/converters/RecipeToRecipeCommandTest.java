package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Category;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

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
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setDescription(RECIPE_DESCRIPTION);
        recipe.setPrepTime(RECIPE_PREPTIME);
        recipe.setCookTime(RECIPE_COOKTIME);
        recipe.setServings(RECIPE_SERVINGS);
        recipe.setSource(RECIPE_SOURCE);
        recipe.setUrl(RECIPE_URL);
        recipe.setDirections(RECIPE_DIRECTIONS);
        recipe.setDifficulty(RECIPE_DIFFICULTY);
        
        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        notes.setRecipeNotes(RECIPE_NOTES);
        recipe.setNotes(notes);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGREDIENT_ID);
        ingredient.setDescription(INGREDIENT_DESCRIPTION);
        ingredient.setAmount(INGREDIENT_AMOUNT);
        UnitOfMeasure uomCommand = new UnitOfMeasure();
        uomCommand.setId(UOM_ID);
        uomCommand.setDescription(UOM_DESCRIPTION);
        ingredient.setUom(uomCommand);
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(ingredient);
        recipe.setIngredients(ingredients);

        Category category = new Category();
        category.setId(CATEGORY_ID);
        category.setDescription(CATEGORY_DESCRIPTION);
        Set<Category> categories = new HashSet<>();
        categories.add(category);
        recipe.setCategories(categories);
        // when
        RecipeCommand recipeCommand = recipeConverter.convert(recipe);

        // then
        assertNotNull(recipeCommand);
        assertNotNull(recipeCommand.getNotes());
        assertNotNull(recipeCommand.getIngredients());
        assertEquals(1, recipeCommand.getIngredients().size());
        assertNotNull(recipeCommand.getCategories());
        assertEquals(1, recipeCommand.getCategories().size());
        assertEquals(RECIPE_ID, recipeCommand.getId());
        assertEquals(RECIPE_DESCRIPTION, recipeCommand.getDescription());
        assertEquals(RECIPE_PREPTIME, recipeCommand.getPrepTime());
        assertEquals(RECIPE_COOKTIME, recipeCommand.getCookTime());
        assertEquals(RECIPE_SERVINGS, recipeCommand.getServings());
        assertEquals(RECIPE_SOURCE, recipeCommand.getSource());
        assertEquals(RECIPE_URL, recipeCommand.getUrl());
        assertEquals(RECIPE_DIRECTIONS, recipeCommand.getDirections());
        assertEquals(RECIPE_DIFFICULTY, recipeCommand.getDifficulty());
        assertEquals(NOTES_ID, recipeCommand.getNotes().getId());
        assertEquals(RECIPE_NOTES, recipeCommand.getNotes().getRecipeNotes());
        assertEquals(INGREDIENT_ID, recipeCommand.getIngredients().iterator().next().getId());
        assertEquals(INGREDIENT_DESCRIPTION, recipeCommand.getIngredients().iterator().next().getDescription());
        assertEquals(INGREDIENT_AMOUNT, recipeCommand.getIngredients().iterator().next().getAmount());
        assertEquals(UOM_ID, recipeCommand.getIngredients().iterator().next().getUom().getId());
        assertEquals(UOM_DESCRIPTION, recipeCommand.getIngredients().iterator().next().getUom().getDescription());
        assertEquals(CATEGORY_ID, recipeCommand.getCategories().iterator().next().getId());
        assertEquals(CATEGORY_DESCRIPTION, recipeCommand.getCategories().iterator().next().getDescription());
    }
}