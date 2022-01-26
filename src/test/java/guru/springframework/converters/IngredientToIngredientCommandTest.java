package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientToIngredientCommandTest extends GenericConverterTest {
    IngredientToIngredientCommand ingredientConverter;

    @BeforeEach
    void setUp() {
        ingredientConverter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    void nullObject() {
        assertNull(ingredientConverter.convert(null));
    }

    @Test
    void emptyObject() {
        assertNotNull(ingredientConverter.convert(new Ingredient()));
    }

    @Test
    void convert() {
        // given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGREDIENT_ID);
        ingredient.setDescription(INGREDIENT_DESCRIPTION);
        ingredient.setAmount(INGREDIENT_AMOUNT);
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);
        uom.setDescription(UOM_DESCRIPTION);
        ingredient.setUom(uom);
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        ingredient.setRecipe(recipe);

        // when
        IngredientCommand ingredientCommand = ingredientConverter.convert(ingredient);

        // then
        assertNotNull(ingredientCommand);
        assertNotNull(ingredientCommand.getUom());
        assertEquals(INGREDIENT_ID, ingredientCommand.getId());
        assertEquals(INGREDIENT_DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(INGREDIENT_AMOUNT, ingredientCommand.getAmount());
        assertEquals(UOM_ID, ingredientCommand.getUom().getId());
        assertEquals(RECIPE_ID, ingredientCommand.getRecipeId());
    }
}