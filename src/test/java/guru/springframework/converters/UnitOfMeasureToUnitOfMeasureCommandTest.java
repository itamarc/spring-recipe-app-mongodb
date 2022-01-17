package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureToUnitOfMeasureCommandTest extends GenericConverterTest {
    UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    @BeforeEach
    void setUp() {
        uomConverter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    void nullObject() {
        assertNull(uomConverter.convert(null));
    }

    @Test
    void emptyObject() {
        assertNotNull(uomConverter.convert(new UnitOfMeasure()));
    }

    @Test
    void convert() {
        // given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);
        uom.setDescription(UOM_DESCRIPTION);

        // when
        UnitOfMeasureCommand uomCommand = uomConverter.convert(uom);

        // then
        assertNotNull(uomCommand);
        assertEquals(UOM_ID, uomCommand.getId());
        assertEquals(UOM_DESCRIPTION, uomCommand.getDescription());
    }
}