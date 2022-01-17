package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientCommandToIngredientTest extends GenericConverterTest {
    IngredientCommandToIngredient ingredientConverter;

    @BeforeEach
    void setUp() {
        ingredientConverter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    void nullObject() {
        assertNull(ingredientConverter.convert(null));
    }

    @Test
    void emptyObject() {
        assertNotNull(ingredientConverter.convert(new IngredientCommand()));
    }

    @Test
    void convert() {
        // given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(INGREDIENT_ID);
        ingredientCommand.setDescription(INGREDIENT_DESCRIPTION);
        ingredientCommand.setAmount(INGREDIENT_AMOUNT);
        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(UOM_ID);
        uomCommand.setDescription(UOM_DESCRIPTION);
        ingredientCommand.setUom(uomCommand);

        // when
        Ingredient ingredient = ingredientConverter.convert(ingredientCommand);

        // then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUom());
        assertEquals(INGREDIENT_ID, ingredient.getId());
        assertEquals(INGREDIENT_DESCRIPTION, ingredient.getDescription());
        assertEquals(INGREDIENT_AMOUNT, ingredient.getAmount());
        assertEquals(UOM_ID, ingredient.getUom().getId());
    }
}