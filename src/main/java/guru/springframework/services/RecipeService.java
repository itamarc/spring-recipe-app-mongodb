package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    public Set<Recipe> getRecipes();

    public Recipe findById(String id);

    public RecipeCommand saveRecipeCommand(RecipeCommand command);

    public RecipeCommand findCommandById(String id);

    public void deleteById(String id);
}
