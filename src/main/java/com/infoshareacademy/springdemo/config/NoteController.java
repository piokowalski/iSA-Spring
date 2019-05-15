package com.infoshareacademy.springdemo.config;


import com.infoshareacademy.springdemo.model.Note;
import com.infoshareacademy.springdemo.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;
    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        return noteRepository.findAllByOrderByDateDesc();
    }

    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable(value = "id") Long noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException());
    }

    @PostMapping("/notes")
    public Note createNote(@RequestBody Note note) {
        return noteRepository.save(note);
    }

}
