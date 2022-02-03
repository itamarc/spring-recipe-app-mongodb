package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
    private final NotesToNotesCommand notesConverter;
    private final CategoryToCategoryCommand categoryConverter;
    private final IngredientToIngredientCommand ingredientConverter;

    public RecipeToRecipeCommand(NotesToNotesCommand notesConverter,
                                 CategoryToCategoryCommand categoryConverter,
                                 IngredientToIngredientCommand ingredientConverter) {
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null) {
            return null;
        }
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(source.getId());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setImage(source.getImage());
        if (source.getNotes() != null) {
            recipeCommand.setNotes(notesConverter.convert(source.getNotes()));
        }
        // categories
        if (source.getCategories() != null && !source.getCategories().isEmpty()) {
            source.getCategories().forEach(
                    category -> recipeCommand.getCategories().add(categoryConverter.convert(category)));
        }
        // ingredients
        if (source.getIngredients() != null && !source.getIngredients().isEmpty()) {
            source.getIngredients().forEach(
                    ingredient -> recipeCommand.getIngredients().add(ingredientConverter.convert(ingredient)));
        }
        return recipeCommand;
    }
}
