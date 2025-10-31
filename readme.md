## 1. 2주차: 개발 환경 설정 및 GitHub 연동


### 1.1. 개발 환경 구축

본 프로젝트의 개발 생산성과 일관성을 위해 통합 개발 환경으로 Visual Studio Code를, 프로그래밍 언어로는 Java를 사용합니다.

*   **도구 선택**: 업계의 최신 동향을 반영하여, 전통적인 무거운 IDE 대신 가볍고 확장성이 뛰어난 VS Code를 표준 IDE로 채택합니다. Java는 안정성과 장기 지원을 고려하여 JDK 17 또는 21 버전을 필수로 요구합니다.
*   **VS Code 확장 프로그램 설치**: 순수한 텍스트 편집기인 VS Code를 강력한 Java 및 Spring 개발 환경으로 변모시키기 위해 다음 두 가지 핵심 확장 프로그램 팩을 설치해야 합니다.
    *   `Extension Pack for Java`: Java 코드 자동 완성, 디버깅, Maven 프로젝트 관리 등 필수적인 Java 개발 기능을 제공합니다.
    *   `Spring Boot Extension Pack`: Spring Initializr 연동, Spring Boot 애플리케이션 대시보드 등 Spring Boot 개발 생산성을 극대화하는 도구를 포함합니다.
*   **JDK 환경 설정 (`settings.json`)**: JDK 설치 후, VS Code가 Java 컴파일러와 런타임을 인식할 수 있도록 `settings.json` 파일에 JDK의 설치 경로를 명시적으로 설정해야 합니다. 이 과정은 Java 언어 서버를 활성화하는 데 필수적입니다.
    *   **코드 예시 (`settings.json`):**
        ```json
        "spring-boot.ls.java.home": "C:\\Program Files\\Java\\jdk-21",
        "java.jdt.ls.java.home": "C:\\Program Files\\Java\\jdk-21"
        ```
    *   **설명**: 위 설정은 VS Code의 Java 관련 확장 기능들이 사용할 JDK의 홈 디렉터리를 지정합니다. JSON 형식에서 경로 구분자인 `\`는 이스케이프 처리해야 하므로 `\\`로 표기합니다.

### 1.2. 첫 Spring Boot 프로젝트 생성

VS Code에 통합된 Spring Initializr 도구를 사용하여 프로젝트의 기본 구조와 의존성을 신속하게 설정합니다. 이는 Spring Boot가 지향하는 '빠른 애플리케이션 개발' 철학을 잘 보여주는 기능입니다.

*   **프로젝트 생성 절차**: VS Code의 명령어 팔레트(`Ctrl + Shift + P`)를 열고 `Spring Initializr: Create a Maven Project...`를 선택하여 프로젝트 생성을 시작합니다.
*   **프로젝트 메타데이터 설정**: 프로젝트의 기본 정보를 다음과 같이 설정합니다.
    *   **Spring Boot Version**: 3.2.x 이상
    *   **Project Language**: Java
    *   **Group ID**: `com.example` 
    *   **Packaging type**: `Jar` 
    *   **Java Version**: 17
*   **의존성 선택**: 프로젝트의 전체 기능 구현에 필요한 초기 기술 스택을 구성하는 7개의 의존성을 선택합니다. 이 의존성들은 웹, 데이터베이스 연동, 템플릿 엔진, 개발 편의 기능 등을 포괄합니다.

| 의존성  | 분류  | 목적 및 역할 |
| :--- | :--- | :--- |
| Spring Boot DevTools | 개발자 도구 | 애플리케이션 코드 변경 시 빠른 재시작(LiveReload)을 지원하여 개발 속도를 향상시킵니다. |
| Lombok | 개발자 도구 | `@Getter`, `@Setter`, `@Builder` 등의 어노테이션을 통해 반복적인 자바 코드를 자동으로 생성하여 코드 가독성을 높입니다. |
| Spring Web | 웹 | 내장된 Apache Tomcat 서버를 포함하며, RESTful API를 비롯한 웹 애플리케이션을 구축하는 데 필요한 핵심 기능을 제공합니다. |
| Spring Web Services | 웹 | SOAP 기반의 웹 서비스 개발을 용이하게 합니다 . |
| Thymeleaf | 템플릿 엔진 | 서버 사이드에서 동적인 HTML 뷰를 생성하기 위한 최신 자바 템플릿 엔진입니다. |
| Spring Data JPA | SQL | JJPA를 사용하여 SQL 데이터베이스에 데이터를 쉽게 영속화하고 관리할 수 있는 기능을 제공합니다. |
| MySQL Driver | SQL | Spring Boot 애플리케이션이 MySQL 데이터베이스와 통신하기 위해 필요한 JDBC 드라이버입니다. |

### 1.3. 기본 페이지 실행 및 테스트


*   **뷰(View) 생성**: Spring Boot는 Thymeleaf와 같은 템플릿 엔진 사용 시 `src/main/resources/templates` 디렉터리에서 뷰 파일을 자동으로 찾습니다. 이 경로에 `index.html` 파일을 생성합니다.
    *   **코드 예시 (`index.html`):**
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>index 메인페이지</title>
        </head>
        <body>
            <h1>안녕하세요!</h1>
        </body>
        </html>
        ```
*   **애플리케이션 실행**: 프로젝트의 시작점인 `DemoApplication.java` 파일의 `main` 메서드를 실행하거나, 단축키 `Ctrl + F5`를 사용하여 애플리케이션을 시작합니다. `@SpringBootApplication` 어노테이션은 자동 설정, 컴포넌트 스캔 등 Spring Boot의 모든 부트스트래핑 과정을 담당합니다. 콘솔 로그를 통해 내장 톰캣 서버가 8080 포트에서 성공적으로 시작되었는지 확인합니다.
*   **초기 문제 해결**: 이 단계에서 애플리케이션을 실행하면 데이터베이스 연결 정보가 없어 오류가 발생합니다. 이는 의도된 상황으로, 아직 데이터베이스를 설정하지 않았기 때문입니다. `pom.xml` 파일에서 `spring-boot-starter-data-jpa`와 `mysql-connector-j` 의존성을 임시로 주석 처리하여 문제를 해결합니다.
    *   이 과정은 단순한 오류 해결을 넘어, 의존성 관리의 중요성을 실체적으로 학습하는 기회를 제공합니다. 먼저 전체 기술 스택을 정의한 후, 아직 구성되지 않은 부분을 비활성화하여 웹 계층만 독립적으로 테스트하는 방식입니다. 이는 '데이터베이스 설정이 필요하다'는 명확한 과제를 제시하고, 4주차에 이 문제를 해결하는 자연스러운 학습 흐름을 만듭니다.

### 1.4. GitHub 연동 및 소스 코드 관리

프로젝트의 변경 이력을 체계적으로 관리하고 협업의 기반을 마련하기 위해 버전 관리 시스템인 Git을 도입하고 원격 저장소인 GitHub와 연동합니다.

*   **Git 설치 및 설정**: 먼저 로컬 컴퓨터에 Git for Windows를 설치합니다. 설치가 완료되면 VS Code의 '소스 제어' 탭이 Git과 자동으로 통합되어 GUI 환경에서 Git 명령을 사용할 수 있게 됩니다.
*   **저장소 초기화 및 첫 커밋**: VS Code 내에서 '리포지토리 초기화' 버튼을 클릭하여 현재 프로젝트 폴더를 Git 저장소로 만듭니다. 변경된 파일들을 스테이징하고, "2주차 개발환경 설정"과 같은 의미 있는 커밋 메시지를 작성한 후 첫 커밋을 완료합니다.
*   **Git 사용자 정보 설정**: Git을 처음 사용하는 경우, 커밋 작성자를 식별하기 위해 사용자 이름과 이메일을 전역 설정으로 등록해야 합니다. 이 설정은 향후 모든 커밋에 자동으로 기록됩니다.
    *   **코드 예시:**
        ```bash
        git config --global user.name "Your Name"
        git config --global user.email "your.email@example.com"
        ```
    *   **설명**: 이 명령어들은 최초 한 번만 실행하면 되며, 커밋 권한 오류를 방지하는 필수적인 초기 설정입니다.
*   **원격 저장소 연동 및 푸시**: '브랜치 게시' 또는 'Publish to GitHub' 기능을 사용하여 로컬 저장소를 GitHub에 생성된 원격 저장소와 연결하고, 첫 커밋 내역을 서버로 푸시합니다.

### 1.5. URL 맵핑과 컨트롤러 기초

정적 HTML 파일을 직접 링크하는 방식이 아닌, Spring MVC의 요청 처리 흐름을 이해하고 컨트롤러를 통해 동적 콘텐츠를 제공하는 방법을 학습합니다.

*   **문제 상황**: `index.html`에서 `<a href="/hello.html">`과 같이 다른 HTML 파일로 직접 링크를 걸면 `404 Not Found` 오류가 발생합니다.
    *   이는 Spring MVC 아키텍처의 핵심인 `DispatcherServlet` 때문입니다. 모든 웹 요청은 `DispatcherServlet`에 의해 가로채지며, 서블릿은 파일 시스템에서 직접 파일을 찾는 대신, 해당 URL 요청을 처리하도록 맵핑된 컨트롤러 메서드를 찾습니다. 이 오류는 Spring의 프론트 컨트롤러 패턴이 동작하고 있음을 보여주는 실질적인 증거입니다.
*   **해결 방안 (컨트롤러와 뷰)**:
    *   **컨트롤러 생성 (`DemoController.java`):** `@Controller` 어노테이션으로 클래스를 웹 컨트롤러로 지정합니다.
    *   **요청 맵핑 (`@GetMapping`):** `@GetMapping("/hello")` 어노테이션을 사용하여 `/hello` 경로의 HTTP GET 요청을 `hello()` 메서드에 연결합니다.
    *   **데이터 전달 (`Model`):** `Model` 객체는 컨트롤러에서 뷰로 데이터를 전달하는 매개체입니다. `model.addAttribute("data", "방갑습니다.");` 코드는 "data"라는 이름으로 문자열 값을 뷰에 전달합니다.
    *   **뷰 이름 반환:** `return "hello";`는 `hello`라는 논리적 뷰 이름을 반환하며, Spring Boot는 이를 `templates/hello.html` 파일로 해석하여 렌더링합니다.
    *   **뷰 렌더링 (`hello.html`):** `hello.html` 파일에서는 Thymeleaf의 `th:text` 속성을 사용하여 컨트롤러에서 전달받은 데이터를 출력합니다: `<p th:text="${data}"></p>`. 이로써 '요청 → DispatcherServlet → 컨트롤러 → 모델 → 뷰 → 응답'으로 이어지는 기본적인 MVC 처리 흐름이 완성됩니다.

---

## 2. 3주차: 포트폴리오 작성하기


### 2.1. 정적 리소스(Static Resources) 관리 및 템플릿 적용

다운로드한 Bootstrap 템플릿의 에셋 파일들을 Spring Boot 프로젝트의 올바른 위치에 배치하고, 애플리케이션이 이 리소스들을 정상적으로 제공할 수 있도록 설정합니다.

*   **템플릿 준비**: 무료로 제공되는 Bootstrap 5 기반의 "ProMan" 포트폴리오 템플릿을 다운로드하여 압축을 해제합니다.
*   **파일 구조화**: Spring Boot가 정적 콘텐츠를 올바르게 인식하고 제공하기 위한 파일 배치는 매우 중요합니다.
    *   템플릿의 메인 HTML 파일(`index.html`)은 동적 뷰로 처리되므로 `src/main/resources/templates` 폴더로 이동합니다.
    *   CSS, JS, 이미지, 라이브러리(`lib`) 등 모든 정적 에셋 폴더들은 `src/main/resources/static` 폴더로 이동합니다.
*   **`application.properties` 설정**: Spring Boot는 기본적으로 `classpath:/static/` 경로를 정적 리소스 위치로 사용하지만, 이를 `application.properties` 파일에 명시적으로 설정하여 구성의 일관성과 명확성을 확보합니다.
    *   **코드 예시 (`application.properties`):**
        ```properties
        spring.web.resources.static-locations=classpath:/static/
        ```
    *   **설명**: `classpath:` 접두사는 해당 경로가 컴파일된 프로젝트 리소스의 루트를 기준으로 함을 의미합니다. 이 설정을 통해 애플리케이션은 `/css/style.css`와 같은 요청이 들어왔을 때 `src/main/resources/static/css/style.css` 파일을 찾아 제공하게 됩니다.

### 2.2. 포트폴리오 템플릿 커스터마이징

템플릿의 HTML 코드를 직접 수정하여 자신만의 포트폴리오 콘텐츠로 채워 넣습니다. 이름, 전문 분야, 프로필 이미지 등을 개인화합니다.

*   **네비게이션 바 수정**: 웹사이트의 주 메뉴를 사용자가 이해하기 쉽도록 한글로 변경하고, 향후 구현할 블로그 게시판으로 이동하는 '게시판' 메뉴를 추가합니다.
*   **헤더 콘텐츠 개인화**: 웹사이트의 첫인상을 결정하는 메인 헤더 영역을 수정합니다.
    *   이름을 자신의 이름으로 변경합니다: `<h1>홍길동</h1>`.
    *   타이핑 애니메이션 효과가 적용되는 텍스트를 자신의 전문 분야나 목표로 수정합니다: `<div class="typed-text d-none">풀스택 웹 개발자, AI 개발/응용, 서버 운영/관리</div>`.
*   **프로필 이미지 변경**: 기본 제공되는 프로필 이미지를 `static/img` 폴더에 위치한 자신의 이미지 파일(`profile.png`)로 교체합니다.
*   **'소개' 섹션 업데이트**: 자신의 강점, 기술, 목표 등을 설명하는 'About Me' 섹션의 텍스트를 개인의 이력에 맞게 수정합니다.

### 2.3. 상세 페이지 추가 및 라우팅

메인 페이지에 요약된 정보 외에 더 상세한 내용을 보여주기 위한 별도의 페이지를 만들고, 메인 페이지에서 이 페이지로 이동할 수 있도록 링크와 라우팅을 구현합니다.

*   **상세 페이지 링크 생성**: `index.html`의 'Read More' 버튼이 `/about_detailed` URL로 요청을 보내고, 새 탭에서 열리도록 `<a>` 태그를 수정합니다.
    *   **코드 예시 (`index.html` 링크):**
        ```html
        <a class="btn btn-primary py-3 px-5 mt-3" href="/about_detailed" target="_blank">상세 소개</a>
        ```
*   **상세 페이지 뷰 생성**: `templates` 폴더에 `about_detailed.html`이라는 새 파일을 생성합니다. 작업의 효율성을 위해 기존 `index.html`의 전체 코드를 복사한 후, 상세 소개에 맞는 내용으로 수정하여 페이지를 구성합니다.
*   **컨트롤러 맵핑 추가**: `/about_detailed` URL 요청을 처리하고 `about_detailed.html` 뷰를 반환하는 핸들러 메서드를 `DemoController.java`에 추가합니다.
    *   **코드 예시 (`DemoController.java`):**
        ```java
        @GetMapping("/about_detailed")
        public String about() {
            return "about_detailed";
        }
        ```
    *   **설명**: 이 메서드는 별도의 데이터를 `Model`에 담아 전달할 필요 없이, 단순히 논리적 뷰 이름만 반환하여 해당 URL과 뷰 파일을 연결하는 Spring MVC의 간단하고 강력한 라우팅 기능을 보여줍니다.

---

## 3. 4주차: MySQL 데이터베이스 연동 및 MVC 구조화

### 3.1. 데이터베이스 연동 준비 및 설정

MySQL 서버를 설치하고 데이터베이스를 생성한 후, Spring Boot 애플리케이션이 이 데이터베이스에 연결할 수 있도록 관련 의존성을 활성화하고 연결 정보를 설정합니다.

*   **MySQL 준비**: 로컬 환경에 MySQL Server 8.x 버전을 설치합니다. 설치 후, SQL 명령(`create database spring;`)을 실행하여 프로젝트에서 사용할 `spring`이라는 이름의 새 데이터베이스를 생성합니다.
*   **의존성 활성화**: 2주차에 임시로 주석 처리했던 `pom.xml`의 `spring-boot-starter-data-jpa`와 `mysql-connector-j` 의존성의 주석을 해제하여 데이터베이스 관련 기능을 다시 활성화합니다.
*   **`application.properties` 설정**: 데이터베이스 연결에 필요한 모든 정보를 `application.properties` 파일에 추가합니다.
    *   **코드 예시 (`application.properties`):**
        ```properties
        spring.datasource.url=jdbc:mysql://localhost:3306/spring?serverTimezone=Asia/Seoul
        spring.datasource.username=root
        spring.datasource.password=your_password
        spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
        spring.jpa.hibernate.ddl-auto=update
        spring.jpa.show-sql=true
        ```
    *   **설명**: 이 설정 블록은 데이터베이스 연동의 핵심입니다.
        *   `spring.datasource.*`: 데이터베이스 서버의 주소, 사용자 계정, 비밀번호 등 접속 정보를 정의합니다.
        *   `spring.jpa.hibernate.ddl-auto=update`: JPA 구현체인 Hibernate가 애플리케이션 실행 시 엔티티 클래스의 정의와 데이터베이스 스키마를 비교하여 자동으로 변경(예: 테이블 생성, 컬럼 추가)하도록 설정합니다. 이는 개발 단계에서 매우 유용한 기능입니다.
        *   `spring.jpa.show-sql=true`: Hibernate가 실행하는 모든 SQL 쿼리를 콘솔에 출력하여, 데이터베이스 작업에 대한 디버깅을 용이하게 합니다.

### 3.2. 프로젝트 구조 리팩토링

데이터베이스 연동으로 인해 애플리케이션의 복잡도가 증가함에 따라, 코드의 각 기능(표현, 비즈니스 로직, 데이터 접근)을 명확히 분리하기 위해 프로젝트의 패키지 구조를 재구성합니다.

*   **리팩토링의 필요성**: 단일 패키지 구조는 소규모 프로젝트에서는 문제가 없지만, 데이터 영속성 로직이 추가되면서 각기 다른 역할을 하는 클래스들이 혼재하게 됩니다. 계층형 아키텍처를 도입하면 관심사의 분리 원칙을 준수하여 코드의 가독성, 재사용성, 테스트 용이성을 크게 향상시킬 수 있습니다. 이는 소프트웨어 공학의 핵심 원리 중 하나로, 요구사항이 복잡해짐에 따라 아키텍처도 함께 진화해야 함을 보여줍니다. 이러한 선제적인 리팩토링은 기술 부채를 방지하는 좋은 습관입니다.
*   **새로운 패키지 구조**: 기존의 `com.example.demo` 패키지 하위에 다음과 같은 서브 패키지를 생성하여 클래스들을 역할에 따라 재배치합니다.
    *   `controller`: HTTP 요청을 받아 처리하고 응답을 반환하는 웹 계층.
    *   `service`: 비즈니스 로직을 처리하는 서비스 계층. 컨트롤러와 리포지토리 사이의 중재자 역할을 합니다.
    *   `repository`: 데이터베이스에 직접 접근하여 CRUD 작업을 수행하는 데이터 접근 계층.
    *   `domain` (또는 `model`): 데이터베이스 테이블과 매핑되는 JPA 엔티티 클래스가 위치하는 도메인 계층.

### 3.3. 영속성 계층(Persistence Layer) 구현

데이터베이스의 테이블 구조를 자바 객체로 표현하는 엔티티 클래스와, 해당 엔티티에 대한 데이터베이스 작업을 추상화하는 리포지토리 인터페이스를 정의합니다.

*   **엔티티 생성 (`TestDB.java`):** `domain` 패키지 내에 데이터 모델을 정의하는 클래스를 생성합니다.
    *   `@Entity`: 이 클래스가 JPA에 의해 관리되는 엔티티임을 선언합니다.
    *   `@Table(name = "testdb")`: 엔티티가 `testdb`라는 이름의 데이터베이스 테이블과 매핑됨을 명시합니다.
    *   `@Id` 및 `@GeneratedValue`: 필드가 테이블의 기본 키이며, 값이 자동으로 생성(auto-increment)됨을 나타냅니다.
    *   `@Data` (Lombok): `getters`, `setters`, `toString` 등 필수 메서드를 컴파일 시점에 자동으로 생성하여 코드를 간결하게 유지합니다.
*   **리포지토리 생성 (`TestRepository.java`):** `repository` 패키지 내에 데이터 접근을 위한 인터페이스를 생성합니다.
    *   `public interface TestRepository extends JpaRepository<TestDB, Long>`: Spring Data JPA의 `JpaRepository`를 상속받는 것만으로, `save()`, `findById()`, `findAll()`, `deleteById()` 등 기본적인 CRUD 메서드를 별도의 구현 없이 즉시 사용할 수 있게 됩니다. 이는 Spring Data JPA가 제공하는 가장 강력한 기능 중 하나입니다.

### 3.4. 서비스 및 컨트롤러 계층 연동

영속성 계층 위에 비즈니스 로직을 담당하는 서비스 계층을 구축하고, 컨트롤러가 이 서비스를 통해 데이터를 조회하여 뷰에 전달하는 전체 흐름을 완성합니다.

*   **서비스 생성 (`TestService.java`):** `service` 패키지 내에 비즈니스 로직을 포함할 클래스를 생성합니다.
    *   `@Service`: 이 클래스가 스프링 컨테이너에 의해 관리되는 서비스 빈임을 나타냅니다.
    *   `@Autowired private TestRepository testRepository;`: 의존성 주입을 통해 스프링 컨테이너가 `TestRepository`의 구현체 인스턴스를 자동으로 주입해 줍니다. 이를 통해 서비스 클래스는 리포지토리의 구체적인 구현 방식에 의존하지 않고, 인터페이스에만 의존하게 되어 결합도가 낮아집니다.
*   **컨트롤러 수정 (`DemoController.java`):** 컨트롤러가 리포지토리에 직접 접근하는 대신, 서비스 계층을 통해 데이터를 요청하도록 수정합니다.
    *   `@Autowired TestService testService;`: 컨트롤러는 이제 `TestService`에 의존합니다.
    *   `@GetMapping("/testdb")`: `/testdb` 요청을 처리하는 핸들러 메서드는 `testService.findByName(...)`을 호출하여 데이터를 가져온 후, `Model`에 담아 `testdb` 뷰로 전달합니다.
*   **통합 테스트**: 애플리케이션을 실행하면 Hibernate가 `TestDB` 엔티티를 기반으로 데이터베이스에 `testdb` 테이블을 자동으로 생성합니다. 데이터베이스 관리 도구를 사용하여 이 테이블에 데이터를 수동으로 삽입한 후, 브라우저에서 `/testdb` 페이지에 접속하면 삽입된 데이터가 화면에 표시됩니다. 이로써 컨트롤러-서비스-리포지토리-데이터베이스로 이어지는 전체 계층이 성공적으로 연동되었음을 검증할 수 있습니다.

---

## 4. 5주차: 블로그 게시판 구현 (1)


### 4.1. 게시글 목록 조회 기능 구현

데이터베이스에 저장된 모든 게시글을 가져와 웹 페이지의 테이블 형태로 사용자에게 보여주는 기능을 구현합니다.

*   **엔티티 정의 (`Article.java`):** 블로그 게시글을 나타내는 `Article` 엔티티를 `domain` 패키지에 생성합니다. 이 엔티티는 `id`, `title`, `content` 필드를 포함하며, `@Builder` 어노테이션을 사용하여 객체 생성의 안정성과 가독성을 높입니다.
*   **리포지토리 정의 (`BlogRepository.java`):** `Article` 엔티티에 대한 데이터베이스 작업을 처리하기 위해 `JpaRepository<Article, Long>`를 상속받는 간단한 인터페이스를 `repository` 패키지에 생성합니다.
*   **서비스 로직 구현 (`BlogService.java`):** 모든 게시글을 조회하는 비즈니스 로직을 `BlogService`에 추가합니다. `findAll()` 메서드는 내부적으로 `blogRepository.findAll()`을 호출하여 모든 `Article` 엔티티 리스트를 반환합니다.
*   **컨트롤러 구현 (`BlogController.java`):** 블로그 관련 웹 요청을 전담할 `BlogController`를 생성합니다.
    *   **코드 예시 (`BlogController.java` - 조회):**
        ```java
        @GetMapping("/article_list")
        public String article_list(Model model) {
            List<Article> list = blogService.findAll(); // 서비스에서 모든 게시글 조회
            model.addAttribute("articles", list); // 조회된 리스트를 모델에 추가
            return "article_list"; // 뷰 이름 반환
        }
        ```
*   **뷰 구현 (`article_list.html`):** 컨트롤러로부터 전달받은 게시글 목록을 화면에 출력합니다.
    *   **Thymeleaf 반복문**: Thymeleaf의 `th:each` 속성을 사용하여 `articles` 리스트를 순회하며 각 게시글에 대한 테이블 행(`<tr>`)을 동적으로 생성합니다.
    *   **코드 예시 (`article_list.html` - tbody):**
        ```html
        <tbody>
            <tr th:each="article: ${articles}">
                <td th:text="${article.id}"></td>
                <td th:text="${article.title}"></td>
                <td th:text="${article.content}"></td>
            </tr>
        </tbody>
        ```

### 4.2. 게시글 생성 기능 구현

사용자가 웹 페이지의 폼을 통해 제목과 내용을 입력하여 새 게시글을 데이터베이스에 저장하는 기능을 구현합니다.

*   **입력 폼 추가 (`article_list.html`):** 게시글 목록 페이지에 제목과 내용을 입력받는 HTML `<form>`을 추가합니다. 이 폼은 `method="post"` 속성을 가지며, `/api/articles` 엔드포인트로 데이터를 전송합니다.
*   **DTO 도입 (`AddArticleRequest.java`):** 클라이언트의 요청 데이터를 서버에서 받기 위한 DTO 클래스를 `service` 패키지에 생성합니다.
    *   DTO를 사용하는 것은 중요한 아키텍처 결정입니다. 엔티티 클래스를 요청/응답에 직접 사용하지 않고 DTO를 사용함으로써, API의 명세와 데이터베이스 스키마를 분리할 수 있습니다. 이는 불필요한 내부 필드(예: `id`, 생성일)의 노출을 막아 보안을 강화하고, 각 계층이 독립적으로 변경될 수 있는 유연성을 제공합니다. DTO 내 `toEntity()` 메서드는 수신한 데이터를 영속성 객체인 엔티티로 변환하는 역할을 명확히 합니다.
*   **REST 컨트롤러 구현 (`BlogRestController.java`):** API 요청을 처리하기 위해 `@RestController` 어노테이션이 붙은 컨트롤러를 별도로 생성합니다. `@RestController`는 `@Controller`와 `@ResponseBody`를 합친 것으로, 메서드의 반환 값을 뷰 이름이 아닌 데이터 자체로 응답하게 합니다.
    *   **코드 예시 (`BlogRestController.java` - 생성):**
        ```java
        @PostMapping("/api/articles")
        public ResponseEntity<Article> addArticle(@ModelAttribute AddArticleRequest request) {
            Article savedArticle = blogService.save(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                               .body(savedArticle);
        }
        ```
*   **서비스 로직 수정 (`BlogService.java`):** 게시글을 저장하는 `save` 메서드를 `BlogService`에 추가합니다. 이 메서드는 DTO를 인자로 받아, `request.toEntity()`를 통해 `Article` 엔티티로 변환한 후 리포지토리를 사용하여 데이터베이스에 저장합니다.
*   **전통적 웹 방식 (리다이렉트)**: RESTful API 방식 대신, 전통적인 웹 애플리케이션처럼 폼 제출 후 게시글 목록 페이지로 다시 돌아가게 할 수도 있습니다. 이 경우, `@RestController`가 아닌 일반 `@Controller`를 사용하고, 메서드의 반환 값을 `return "redirect:/article_list";`로 지정하여 브라우저가 해당 URL로 재요청하도록 지시합니다. 이는 API 응답과 웹 페이지 전환의 차이를 보여주는 좋은 비교 사례입니다.
    *   **코드 예시 (리다이렉트를 사용하는 컨트롤러):**
        ```java
        // BlogController.java 내부에 작성
        @PostMapping("/api/articles")
        public String addArticle(@ModelAttribute AddArticleRequest request) {
            blogService.save(request);
            return "redirect:/article_list"; // 게시글 목록 페이지로 리다이렉트
        }
        ```

---


## 5-- 6주차: 블로그 게시판 구현 (2)


### 5.1. 게시글 수정 기능 구현

기존 게시글의 내용을 변경할 수 있는 기능을 구현합니다. 이 기능은 (1) 수정할 데이터를 담은 폼을 보여주는 단계와 (2) 폼 제출 시 데이터베이스를 업데이트하는 두 단계로 구성됩니다.

*   **1단계: 수정 폼 보여주기**
    *   **동적 링크 생성 (`article_list.html`):** 게시글 목록의 각 행에 '수정' 버튼을 추가하고, Thymeleaf를 사용하여 `/article_edit/{id}` 형태의 동적 URL(예: `/article_edit/5`)을 생성합니다.
    *   **조회 컨트롤러 (`@GetMapping`):** `BlogController.java`에 위 URL 요청을 처리할 메서드를 추가합니다. `@PathVariable` 어노테이션으로 URL 경로의 `id` 값을 추출하여 해당 게시글을 서비스로부터 조회한 후, `Model`에 담아 `article_edit.html` 뷰로 전달합니다.
*   **2단계: 수정 내용 처리**
    *   **수정 폼 (`article_edit.html`):** `article_edit.html` 뷰에는 게시글 생성 폼과 유사한 형태의 폼을 만듭니다. HTML `<form>` 태그는 기본적으로 GET과 POST 메서드만 지원하므로, 데이터 수정을 의미하는 PUT 요청을 보내기 위해 Spring의 `HiddenHttpMethodFilter`를 활용해야 합니다.
        *   **코드 예시 (Hidden Input):**
            ```html
            <form th:action="@{/api/article_edit/{id}(id=${article.id})}" method="post">
                <input type="hidden" name="_method" value="put">
                <button type="submit">수정</button>
            </form>
            ```
    *   **필터 활성화 (`application.properties`):** `HiddenHttpMethodFilter`가 동작하도록 `application.properties` 파일에 다음 설정을 추가해야 합니다.
        *   **코드 예시 (`application.properties`):**
            ```properties
            spring.mvc.hiddenmethod.filter.enabled=true
            ```
        *   **설명**: 이 설정은 Spring Boot에게 `_method`라는 이름의 숨겨진 필드가 포함된 POST 요청을 가로채, 해당 필드의 값(예: `put`, `delete`)에 해당하는 HTTP 메서드로 변환하여 처리하도록 지시합니다. 이를 통해 HTML 폼의 제약 속에서도 RESTful한 URL 설계를 유지할 수 있습니다.
    *   **업데이트 컨트롤러 (`@PutMapping`):** `BlogController`에 `@PutMapping` 어노테이션을 사용하여 실제 업데이트 요청을 처리하는 메서드를 추가합니다. 이 메서드는 서비스의 `update` 메서드를 호출한 후, 게시글 목록 페이지로 리다이렉트합니다.
    *   **서비스 및 엔티티 로직 (`update` 메서드):** 서비스 계층의 `update` 메서드는 ID로 기존 게시글을 찾고, 엔티티 자체에 정의된 `update` 메서드를 호출하여 객체의 상태를 변경한 후, 변경된 엔티티를 저장합니다. 엔티티 내부에 업데이트 로직을 캡슐화하는 것은 도메인 주도 설계의 좋은 실천 사례입니다.

### 5.2. 게시글 삭제 기능 구현

게시글 목록 페이지에서 사용자가 특정 게시글을 영구적으로 삭제할 수 있는 기능을 구현합니다.

*   **삭제 폼 (`article_list.html`):** '삭제' 버튼을 클릭하면 DELETE 요청을 보낼 수 있도록, 수정 기능과 마찬가지로 `HiddenHttpMethodFilter`를 사용하는 작은 폼을 각 게시글 행에 추가합니다.
    *   **코드 예시 (삭제 폼):**
        ```html
        <form th:action="@{/api/article_delete/{id}(id=${article.id})}" method="post">
            <input type="hidden" name="_method" value="delete">
            <button type="submit" class="btn btn-danger">삭제</button>
        </form>
        ```
*   **삭제 컨트롤러 (`@DeleteMapping`):** `BlogController`에 `@DeleteMapping` 어노테이션이 붙은 메서드를 추가합니다. 이 메서드는 `@PathVariable`로 전달받은 ID를 사용하여 서비스의 `delete` 메서드를 호출하고, 작업 완료 후 목록 페이지로 리다이렉트합니다.
*   **삭제 서비스 로직 (`delete` 메서드):** `BlogService`에 `delete(Long id)` 메서드를 추가하고, 내부적으로 `blogRepository.deleteById(id)`를 호출하여 데이터베이스에서 해당 레코드를 삭제합니다.

### 5.3. 기본 예외 처리 구현

사용자가 URL을 직접 조작하여 존재하지 않는 게시글 ID로 접근하는 등 예외적인 상황에 대응하여, 사용자에게 친절한 오류 페이지를 보여주는 기능을 구현합니다.

*   **컨트롤러 예외 처리 로직**: 게시글 수정 페이지를 보여주는 `@GetMapping("/article_edit/{id}")` 메서드에서, 리포지토리로부터 반환된 `Optional<Article>` 객체의 `isPresent()` 메서드를 사용하여 게시글의 존재 여부를 확인합니다.
*   **오류 페이지 반환**: 만약 게시글이 존재하지 않는 경우(`false`), 기존의 뷰 이름 대신 `/error_page/article_error`와 같은 커스텀 오류 페이지의 뷰 이름을 반환하도록 로직을 수정합니다.
*   **오류 페이지 뷰 (`article_error.html`):** `templates/error_page` 폴더에 사용자 친화적인 메시지를 담은 `article_error.html` 파일을 생성합니다. 이 페이지는 Spring Boot의 기본 "Whitelabel Error Page"보다 훨씬 나은 사용자 경험을 제공합니다.
*   **향후 개선 방향**: 강의 자료에서는 더 정교하고 전역적인 예외 처리를 위해 `@ControllerAdvice`를 사용하는 방안을 암시하며, 이는 애플리케이션의 안정성을 한 단계 더 높일 수 있는 다음 학습 목표가 될 수 있음을 시사합니다.

---
