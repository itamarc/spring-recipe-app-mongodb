package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {
    @Override
    public Category convert(CategoryCommand source) {
        if (source == null) {
            return null;
        }
        Category category = new Category();
        category.setId(source.getId());
        category.setDescription(source.getDescription());
        // TODO set Recipe
        return category;
    }
}
