package guru.springframework.converters;

import java.math.BigDecimal;

import guru.springframework.domain.Difficulty;

public class GenericConverterTest {
    // category
    protected static final String CATEGORY_ID = "10";
    protected static final String CATEGORY_DESCRIPTION = "Sandwich";
    // notes
    protected static final String NOTES_ID = "11";
    protected static final String RECIPE_NOTES = "Some random fake recipe notes.";
    // uom
    protected static final String UOM_ID = "12";
    protected static final String UOM_DESCRIPTION = "Teaspoon";
    // ingredient
    protected static final String INGREDIENT_ID = "13";
    protected static final String INGREDIENT_DESCRIPTION = "Pepper";
    protected static final BigDecimal INGREDIENT_AMOUNT = BigDecimal.valueOf(1.23);
    // recipe
    protected static final String RECIPE_ID = "14";
    protected static final String RECIPE_DESCRIPTION = "Some random fake recipe";
    protected static final Integer RECIPE_PREPTIME = 30;
    protected static final Integer RECIPE_COOKTIME = 60;
    protected static final Integer RECIPE_SERVINGS = 12;
    protected static final String RECIPE_SOURCE = "Some random fake recipe source";
    protected static final String RECIPE_URL = "http://www.fakerecipe.com";
    protected static final String RECIPE_DIRECTIONS = "Some random fake recipe directions";
    protected static final Difficulty RECIPE_DIFFICULTY = Difficulty.EASY;
}
