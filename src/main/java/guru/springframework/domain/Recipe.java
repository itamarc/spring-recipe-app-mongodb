package guru.springframework.domain;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Recipe {

    private String id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Set<Ingredient> ingredients = new HashSet<>();
    private Byte[] image;
    private Difficulty difficulty;
    private Notes notes;
    private Set<Category> categories = new HashSet<>();

    public Ingredient addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        Set<Ingredient> ingredients = getIngredients();
        ingredients.add(ingredient);
        return ingredient;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }
}
