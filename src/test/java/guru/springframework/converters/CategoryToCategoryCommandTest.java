package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryToCategoryCommandTest extends GenericConverterTest {
    CategoryToCategoryCommand categoryConverter;

    @BeforeEach
    void setUp() {
        categoryConverter = new CategoryToCategoryCommand();
    }

    @Test
    void nullObject() {
        assertNull(categoryConverter.convert(null));
    }

    @Test
    void emptyObject() {
        assertNotNull(categoryConverter.convert(new Category()));
    }

    @Test
    void convert() {
        // given
        Category category = new Category();
        category.setId(CATEGORY_ID);
        category.setDescription(CATEGORY_DESCRIPTION);

        // when
        CategoryCommand categoryCommand = categoryConverter.convert(category);

        // then
        assertNotNull(categoryCommand);
        assertEquals(CATEGORY_ID, categoryCommand.getId());
        assertEquals(CATEGORY_DESCRIPTION, categoryCommand.getDescription());
    }
}
