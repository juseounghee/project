package com.example.webflux;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import com.example.webflux.model.Note;
import com.example.webflux.model.NoteRequest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NoteControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void shouldListSeedNote() {
        List<Note> notes = webTestClient
                .get()
                .uri("http://localhost:" + port + "/notes")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(APPLICATION_JSON)
                .expectBodyList(Note.class)
                .returnResult()
                .getResponseBody();

        assertThat(notes).isNotEmpty();
    }

    @Test
    void shouldCreateAndFetchNote() {
        NoteRequest request = new NoteRequest("Test", "Created through test");

        Note created = webTestClient
                .post()
                .uri("http://localhost:" + port + "/notes")
                .contentType(APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Note.class)
                .returnResult()
                .getResponseBody();

        assertThat(created).isNotNull();
        assertThat(created.getId()).isNotBlank();

        Note fetched = webTestClient
                .get()
                .uri("http://localhost:" + port + "/notes/" + created.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Note.class)
                .returnResult()
                .getResponseBody();

        assertThat(fetched).isNotNull();
        assertThat(fetched.getTitle()).isEqualTo("Test");
        assertThat(fetched.getContent()).isEqualTo("Created through test");
    }
}
