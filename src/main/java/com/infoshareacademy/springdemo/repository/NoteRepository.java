package com.infoshareacademy.springdemo.repository;

import com.infoshareacademy.springdemo.model.Note;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<Note, Long> {

    List<Note> findAllByOrderByDateAsc();

    List<Note> findAllByOrderByDateDesc();
}