# Spring Boot WebFlux Demo

간단한 노트 API를 제공하는 Spring Boot WebFlux 예제입니다. WebFlux의 논블로킹 API와 `WebTestClient`를 활용한 테스트를 포함합니다. Java 25와 Spring Boot 4.0.0 정식 버전을 기준으로 최신 설정을 사용합니다.

> 참고: 모든 노트 데이터는 애플리케이션 메모리에 있는 `ConcurrentHashMap`에 저장되므로 실행 중에만 유지됩니다. DB 설정 없이 빠르게 WebFlux 패턴을 확인할 때 활용할 수 있습니다.

## 빌드 및 실행

필수 요구 사항: JDK 25+, Maven 3.9+

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

## Maven Central 403 오류 원인

일부 제한된 네트워크/샌드박스 환경에서는 Maven Central(`https://repo1.maven.org/maven2`) 접속이 차단되어 부모 POM 다운로드가 403(Forbidden)으로 실패할 수 있습니다. 로컬 개발 PC에서는 일반적으로 발생하지 않지만, 다음과 같은 방법으로 우회할 수 있습니다.

- 사내/제3의 프록시 또는 미러 저장소를 `~/.m2/settings.xml`에 설정하여 Maven Central 대신 접근 가능한 저장소를 사용합니다.
- 이미 다운로드해 둔 로컬 캐시(`~/.m2/repository`)를 복사해 넣어 네트워크 의존성을 줄입니다.
- 네트워크 정책이 없는 환경에서 빌드·테스트를 실행합니다.
