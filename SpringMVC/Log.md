# Log에 대해

운영 시스템에서는 'System.out.println()' 같은 시스템 콘솔을 사용해서 필요한 정보를 출력하지 않고, 별도의 로깅 라이브러리를 사용해서 로그를 출력한다.

### 로깅 라이브러리

스프링 부트 라이브러리를 사용하면 스프링 부트 로깅 라이브러리('spring-boot-starter-logging')가 함께 포함된다. 스프링 부트 로깅 라이브러리는 기본으로 다음 로깅 라이브러리를 사용한다.

- SLF4J
- Logback

로그 라이브러리는 Logback, Log4J, Log4J2 등등 수많은 라이브러리가 있따. 그것을 통합해서 인터페이스로 제공하는 것이 바로 SLF4J 라이브러리이다.

SLF4J는 인터페이스이고, 구현체로 Logback 같은 로그 라이브러리를 선택하면 된다.
실무에서는 스프링 부트가 기본으로 제공하는 Logback을 대부분 사용한다.

Logger 인터페이스를 구현한
LoggerFactory (Factory Method Pattern)

```
private Logger log = LoggerFactory.getLogger(getClass());
private static final Logger log = LoggerFactory.getLogger(xxx.class);
@Slf4j : 롬복 사용 가능
```

### log Level

1. trace level이 모든 레벨의 로그를 다 보는 레벨

2. debug level은 trace를 제외한 모든 레벨

(1, 2번은 개발 서버에서만... 로그가 너무 많아짐)

3. info level (warn, error)

TRACE > DEBUG > INFO > WARN > ERROR

개발 서버는 Debug 출력
운영 서버는 INFO 출력

전체 로그 레벨 설정도 가능하다

- logging.level.root=debug
  이렇게 root에 레벨을 주게 되면 기본 라이브러리부터 전부 로그가 찍혀나온다.

!!!주의할 점
log.trace("trace my log=" + name);
이런 식으로 작성해도 문제는 없다 하지만 만약 trace level이 아니라고 가정해보자
log.trace ...는 로그에 찍히지 않는다.
하지만 자바는 내부적으로 trace my log name이라는 문자열을 연산해서 저장해놓는다.
실제로 로그가 찍히지 않아도 말이다!!

### 로그를 사용해서 좋은점

- 쓰레드 정보, 클래스 이름 같은 부가적인 정보를 함께 볼 수 있고, 출력 모양을 조정할 수 있다.
- 로그 레벨에 따라 개발 서버에서는 모든 로그를 출력하고, 운영서버에서는 출력하지 않는 등의 상황설정이 가능하다.
- 콘솔 출력 뿐만 아니라, 파일이나 네트워크 등, 로그를 별도의 위치에 남길 수 있다. 특히 파일로 남길 때는 일별, 특정 용량에 따라
  로그를 분할하는 것도 가능하다.
- 성능도 일반 System.out보다 좋다(내부 버퍼링, 멀티 쓰레드 등등) 실무에서는 그래서 로그를 꼭 사용해야 한다.

### SLF4J 로그 인터페이스 구현체들

- Logback, Log4J2

Log4J의 보안 취약점으로 인해 현재 보완된 Log4J2 혹은 Logback을 주로 사용


### 패키지 별로 로깅 레벨 지정

- 다음 방법으로 상위 패키지의 디폴트 레벨을 설정하고, 하위 패키지들에 대한 각각의 로깅 레벨을 별도로 설정할 수 있다.
logging.level.org.springframework.web=info
logging.level.com.example.springedu=debug
