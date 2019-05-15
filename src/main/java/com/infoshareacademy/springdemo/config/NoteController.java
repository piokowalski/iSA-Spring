package com.infoshareacademy.springdemo.config;

import com.infoshareacademy.springdemo.model.Note;
import com.infoshareacademy.springdemo.repository.NoteRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NoteController {

    private static final Logger LOG = LoggerFactory.getLogger(NoteController.class);

    private NoteRepository noteRepository;

    @Autowired
    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        List<Note> result = noteRepository.findAllByOrderByDateDesc();
        LOG.info("Found {} notes", result.size());

        return result;
    }

    @GetMapping("/notes/{id}")
    public Note getNote(@PathVariable("id") Long id) {
        Optional<Note> result = noteRepository.findById(id);

        if (result.isPresent()) {
            LOG.info("Note with id {} found!", id);
        } else {
            LOG.warn("Note with id {} not found :-C", id);
        }

        return result.orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping("/notes")
    public Note addNote(@RequestBody Note note) {
        LOG.info("Adding a new note: {}", note);
        noteRepository.save(note);
        LOG.info("Saved with id {}", note);
        return note;
    }
    @DeleteMapping("/notes/{id}")
    public void deleteNote(@PathVariable("id") Long id) {
        LOG.info("Deleting note with id {}", id);

        Optional<Note> note = noteRepository.findById(id);

        if (note.isPresent()) {
            LOG.info("Found a note with id {}", id);
            noteRepository.delete(note.get());
        } else {
            LOG.warn("Note with id {} note found ;(", id);
            throw new ResourceNotFoundException("No notes found with id " + id);
        }
    }
}