package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesToNotesCommandTest extends GenericConverterTest {
    NotesToNotesCommand notesConverter;

    @BeforeEach
    void setUp() {
        notesConverter = new NotesToNotesCommand();
    }

    @Test
    void nullObject() {
        assertNull(notesConverter.convert(null));
    }

    @Test
    void emptyObject() {
        assertNotNull(notesConverter.convert(new Notes()));
    }

    @Test
    void convert() {
        // given
        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        notes.setRecipeNotes(RECIPE_NOTES);

        // when
        NotesCommand notesCommand = notesConverter.convert(notes);

        // then
        assertNotNull(notesCommand);
        assertEquals(NOTES_ID, notesCommand.getId());
        assertEquals(RECIPE_NOTES, notesCommand.getRecipeNotes());
    }
}