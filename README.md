# Spring Boot WebFlux Demo

간단한 노트 API를 제공하는 Spring Boot WebFlux 예제입니다. WebFlux의 논블로킹 API와 `WebTestClient`를 활용한 테스트를 포함합니다.

## 빌드 및 실행

```bash
mvn spring-boot:run
```

애플리케이션이 실행되면 `http://localhost:8080/notes` 엔드포인트가 열립니다.

## 주요 엔드포인트

- `GET /notes` : 저장된 모든 노트 조회 (처음 실행 시 기본 노트 1개 포함)
- `GET /notes/{id}` : ID로 특정 노트 조회
- `POST /notes` : 새 노트 생성 (예: `{ "title": "Hello", "content": "Reactive" }`)

## 테스트 실행

```bash
mvn test
```
