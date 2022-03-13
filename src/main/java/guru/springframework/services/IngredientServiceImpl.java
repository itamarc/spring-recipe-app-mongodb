package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * Created by itamar at 2022-01-26
 */
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeToRecipeCommand recipeToRecipeCommand;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
                                 IngredientCommandToIngredient ingredientCommandToIngredient,
                                 RecipeToRecipeCommand recipeToRecipeCommand,
                                 RecipeRepository recipeRepository,
                                 UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(String recipeId, String ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (!recipeOptional.isPresent()) {
            // TODO impl error handling
            log.error("Recipe ID not found: " + recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if (!ingredientCommandOptional.isPresent()) {
            // TODO impl error handling
            log.error("Ingredient ID not found: " + ingredientId);
        } else {
            ingredientCommandOptional.get().setRecipeId(recipeId);
        }

        return ingredientCommandOptional.orElse(null);
    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if (!recipeOptional.isPresent()) {
            // TODO Toss error if not found!
            log.error("Recipe not found for ID: " + command.getRecipeId());
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if (ingredientOptional.isPresent()) {
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                ingredientFound.setUom(unitOfMeasureRepository.findById(command.getUom().getId())
                        .orElseThrow(() -> new RuntimeException("Unit of Measure not found!"))); // TODO address this
            } else {
                Ingredient ingredient = ingredientCommandToIngredient.convert(command);
                recipe.addIngredient(ingredient);
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                    .findFirst();

            if (!savedIngredientOptional.isPresent()) {
                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
                        .filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
                        .filter(recipeIngredients -> recipeIngredients.getUom().getId().equals(command.getUom().getId()))
                        .findFirst();
            }

            // to check for fail
            IngredientCommand savedIngredient = ingredientToIngredientCommand.convert(savedIngredientOptional.get());
            savedIngredient.setRecipeId(recipe.getId());
            return savedIngredient;
        }
    }

    @Override
    public RecipeCommand deleteByRecipeIdAndIngredientId(String recipeId, String ingredientId) {
        // find recipe
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()) {
            // TODO Toss error if not found!
            log.error("Recipe not found for ID: " + recipeId);
            return new RecipeCommand();
        } else {
            Recipe recipe = recipeOptional.get();

            // remove ingredient
            Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientId))
                    .findFirst();
            if (ingredientOptional.isPresent()) {
                Ingredient ingredientToDelete = ingredientOptional.get();
                recipe.getIngredients().remove(ingredientToDelete);

                // save recipe
                Recipe savedRecipe = recipeRepository.save(recipe);
                log.debug("Removed ingredient with ID: " + ingredientId);

                return recipeToRecipeCommand.convert(savedRecipe);
            } else {
                log.error("Ingredient not found with ID: " + ingredientId);
                return recipeToRecipeCommand.convert(recipe);
            }
        }
    }
}
