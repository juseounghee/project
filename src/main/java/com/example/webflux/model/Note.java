package com.example.webflux.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Note {

    private final String id;
    private final String title;
    private final String content;
    private final Instant createdAt;

    public Note(String title, String content) {
        this(UUID.randomUUID().toString(), title, content, Instant.now());
    }

    public Note(String id, String title, String content, Instant createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Note withUpdatedContent(String updatedContent) {
        return new Note(this.id, this.title, updatedContent, this.createdAt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(id, note.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
