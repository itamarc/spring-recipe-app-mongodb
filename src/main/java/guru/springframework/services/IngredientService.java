package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;

/*
 * Created by itamar at 2022-01-26
 */
public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand ingredient);

    RecipeCommand deleteByRecipeIdAndIngredientId(String recipeId, String ingredientId);
}
