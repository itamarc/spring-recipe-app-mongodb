package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class CategoryCommandToCategoryTest extends GenericConverterTest {
    CategoryCommandToCategory categoryConverter;

    @BeforeEach
    void setUp() {
        categoryConverter = new CategoryCommandToCategory();
    }

    @Test
    void nullObject() {
        assertNull(categoryConverter.convert(null));
    }

    @Test
    void emptyObject() {
        assertNotNull(categoryConverter.convert(new CategoryCommand()));
    }

    @Test
    void convert() {
        // given
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(CATEGORY_ID);
        categoryCommand.setDescription(CATEGORY_DESCRIPTION);

        // when
        Category category = categoryConverter.convert(categoryCommand);

        // then
        assertNotNull(category);
        assertNotNull(category.getRecipes());
        assertEquals(CATEGORY_ID, category.getId());
        assertEquals(CATEGORY_DESCRIPTION, category.getDescription());
    }
}