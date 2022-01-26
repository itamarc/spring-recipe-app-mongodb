package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;

/*
 * Created by itamar at 2022-01-26
 */
public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
