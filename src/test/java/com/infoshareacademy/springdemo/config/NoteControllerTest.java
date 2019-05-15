package com.infoshareacademy.springdemo.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import com.infoshareacademy.springdemo.SpringDemoApplication;
import com.infoshareacademy.springdemo.model.Note;
import com.infoshareacademy.springdemo.repository.NoteRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringDemoApplication.class)
public class NoteControllerTest {

    @Autowired
    private NoteController noteController;

    @Autowired
    private NoteRepository noteRepository;

    private Long lastInsertedId;

    @Before
    public void beforeEach() {
        noteRepository.deleteAll();

        noteController.addNote(new Note("lalala", LocalDateTime.now()));

        Note note = noteController.addNote(new Note("popopopo", LocalDateTime.now()));
        lastInsertedId = note.getId();
    }

    @Test
    public void shouldGetAllNotes() {
        List<Note> notes = noteController.getAllNotes();
        assertThat(notes).hasSize(2);
    }

    @Test
    public void shouldGetSingleNote() {
        Note note = noteController.getNote(lastInsertedId);
        assertNotNull(note);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void shouldThrowExceptionWhenNoteNotFound() {
        noteController.getNote(200l);
    }

    @Test
    public void shouldAddNewNote() {
        Note note = new Note("super notka", LocalDateTime.now().minusDays(21));

        Note savedNote = noteController.addNote(note);
        assertNotNull(savedNote.getId());
    }

    @Test
    public void shouldDeleteExistingNote() {
        noteController.deleteNote(lastInsertedId);

        Optional<Note> note = noteRepository.findById(lastInsertedId);
        assertThat(note.isPresent()).isFalse();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void shouldThrowExceptionWhenDeletingNotExistingNote() {
        noteController.deleteNote(2131321l);
    }
}