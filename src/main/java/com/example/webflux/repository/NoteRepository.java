package com.example.webflux.repository;

import com.example.webflux.model.Note;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class NoteRepository {

    private final Map<String, Note> data = new ConcurrentHashMap<>();

    public NoteRepository() {
        save(new Note("Welcome", "This is your first reactive note."));
    }

    public Flux<Note> findAll() {
        Collection<Note> notes = data.values();
        return Flux.fromIterable(notes);
    }

    public Mono<Note> findById(String id) {
        return Mono.justOrEmpty(Optional.ofNullable(data.get(id)));
    }

    public Mono<Note> save(Note note) {
        data.put(note.getId(), note);
        return Mono.just(note);
    }

    public Mono<Void> deleteById(String id) {
        data.remove(id);
        return Mono.empty();
    }
}
