package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureCommandToUnitOfMeasureTest extends GenericConverterTest {
    UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    @BeforeEach
    void setUp() {
        uomConverter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    void nullObject() {
        assertNull(uomConverter.convert(null));
    }

    @Test
    void emptyObject() {
        assertNotNull(uomConverter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    void convert() {
        // given
        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(UOM_ID);
        uomCommand.setDescription(UOM_DESCRIPTION);

        // when
        UnitOfMeasure uom = uomConverter.convert(uomCommand);

        // then
        assertNotNull(uom);
        assertEquals(UOM_ID, uom.getId());
        assertEquals(UOM_DESCRIPTION, uom.getDescription());
    }
}