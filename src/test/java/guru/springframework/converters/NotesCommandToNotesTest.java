package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesCommandToNotesTest extends GenericConverterTest {
    NotesCommandToNotes notesConverter;

    @BeforeEach
    void setUp() {
        notesConverter = new NotesCommandToNotes();
    }

    @Test
    void nullObject() {
        assertNull(notesConverter.convert(null));
    }

    @Test
    void emptyObject() {
        assertNotNull(notesConverter.convert(new NotesCommand()));
    }

    @Test
    void convert() {
        // given
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);
        notesCommand.setRecipeNotes(RECIPE_NOTES);

        // when
        Notes notes = notesConverter.convert(notesCommand);

        // then
        assertNotNull(notes);
        assertEquals(NOTES_ID, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }
}