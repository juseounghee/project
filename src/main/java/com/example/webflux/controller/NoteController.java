package com.example.webflux.controller;

import com.example.webflux.model.Note;
import com.example.webflux.model.NoteRequest;
import com.example.webflux.repository.NoteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/notes", produces = MediaType.APPLICATION_JSON_VALUE)
public class NoteController {

    private final NoteRepository repository;

    public NoteController(NoteRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Flux<Note> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Note> findById(@PathVariable String id) {
        return repository.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Note> create(@RequestBody NoteRequest request) {
        Note note = new Note(request.title(), request.content());
        return repository.save(note);
    }
}
