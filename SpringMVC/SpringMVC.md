# Spring MVC

### @RestController vs @Controller

- @Controller는 반환 값이 'String'이면 뷰 이름으로 인식 된다. 그래서 '뷰를 찾고 뷰가 렌더링' 된다.
- @RestController는 반환 값으로 뷰를 찾는 것이 아니라, 'HTTP 메시지 바디에 바로 입력'한다. 따라서 실행 결과로
  ok 메시지를 받을 수 있다. @ResponseBody 와 관련이 있다.
