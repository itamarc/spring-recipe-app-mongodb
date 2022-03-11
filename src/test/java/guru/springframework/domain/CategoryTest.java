package guru.springframework.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    Category category;

    @BeforeEach
    public void setUp() {
        category = new Category();
    }

    @Test
    void getId() {
        String idValue = "4";
        category.setId(idValue);
        assertEquals(idValue, category.getId());
    }

    @Test
    void getDescription() {
        String description = "Some recipe description";
        category.setDescription(description);
        assertEquals(description, category.getDescription());
    }

    @Test
    void getRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        Recipe recipe1 = new Recipe();
        recipe1.setId("5");
        recipes.add(recipe1);
        Recipe recipe2 = new Recipe();
        recipe2.setId("6");
        recipes.add(recipe2);
        category.setRecipes(recipes);
        assertArrayEquals(recipes.toArray(), category.getRecipes().toArray());
    }
}
