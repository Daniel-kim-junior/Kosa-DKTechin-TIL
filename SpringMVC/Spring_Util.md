### 스프링 스케쥴러

Cron 표현식을 통해 특정 Date를 반영하여 메소드를 실행 할 수 있는 스프링의 기능

### Filter vs interceptor

Filter Chain을 통해 클라이언트의 요청을 Dispatcher Servlet에 도착하기 전에 처리할 수 있는 기능
리소스가 수행되기 전, 수행되고 난 후 로직을 구현할 수 있다.(AOP 대체 불가)

interceptor : spring 제공 기술 spring과 관련있는 서비스에 대한 부분(Dispatcher Servlet 이후)을 구현하면 좋고
Filter : spring과 관련없는 Dispatcher Servlet이 실행되기 전에 구현해야 하는 web 로직에 대한 부분

- interceptor (AOP로 대체 가능) - response, request 객체 조작 불가
  - preHandle : handler mapping 전에 수행(return boolean이 특징 false 일때 다음 로직 수행 x)
  - postHandle : handler mapping 후에 수행 (에러 발생 시 수행 x)
  - afterCompletion : 완료 후 (에러 발생 시에도 수행)

interceptor는 Filter와 다르게 Configuration 설정이 필요하다

### 오류 처리

@ExceptionHandler와 @ControllerAdvice

@ExceptionHandler : 스프링 MVC에서는 에러나 예외를 처리하기 위한 특별한 방법을 제공하는데 @ExcptionHandler 어노테이션을 이용하면 된다. 스프링 컨트롤러에서 정의한 메서드(@RequestMapping)에서 기술한 예외가 발생되면 자동으로 받아낼 수 있다.
이를 이용하여 컨트롤러에서 발생하는 예외를 View단인 JSP등으로 보내서 처리할 수 있다.

### WebSocket 라이프 사이클

HTTP/1.1에서 등장한 코드 101번을 통해 프로토콜 체인지 Event 발생
브라우저 상에서 서버와 서버끼리 통신 가능함

옛날에는 Ajax Polling을 사용하여 주기적으로 서버에 요청하여 브라우저가 실시간으로 응답하는 것처럼 보이게 했음(서비스 사용자가 많을 때)
-> Long polling(서비스 사용자가 적을 때) 을 이용하여 개선 (서버에 응답 객체가 존재하지 않는다면 기다리는 식으로 알고리즘 개선)

-> WebSocket을 사용하여 상태 유지

WebSocket event: onopen, onerror, onclose, onmessage
