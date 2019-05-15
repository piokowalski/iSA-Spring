package com.infoshareacademy.springdemo.config;

import com.infoshareacademy.springdemo.model.Note;
import com.infoshareacademy.springdemo.repository.NoteRepository;
import java.time.LocalDateTime;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitConfiguration {

    @Autowired
    private NoteRepository noteRepository;

    @PostConstruct
    public void init() {
        noteRepository.save(
                new Note("Kup pomidory", LocalDateTime.now()));

        noteRepository.save(
                new Note("Przygotuj się na rozmowę", LocalDateTime.now())
        );
    }
}