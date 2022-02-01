package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;

/*
 * Created by itamar at 2022-01-26
 */
public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand ingredient);

    RecipeCommand deleteByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
