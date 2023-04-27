## 서버에서 처리해야 하는 업무

### WAS를 직접 구현한다고 해보자

    1. 서버 TCP/IP 연결 대기, 소켓 연결
    2. HTTP 요청 메시지를 파싱해서 읽기
    3. HTTP Method를 분석
    4. Content-Type 확인
    5. POST라면 HTTP Body 내용 파싱
    6. 저장 프로세스 실행
    7. 비지니스 로직 실행
       - 데이터베이스에 저장 요청
    8. HTTP 응답 메시지 생성 시작
       - HTTP 시작 라인 생성
       - Header 생성
       - 메시지 바디에 HTML 생성에서 입력
    9. TCP/IP에 응답 전달, 소켓 종료

---

## 의미있는 작업은 7번 밖에 없다

    - 서블릿이 7번 이외의 모든 작업을 지원해준다.

---

## HTTP 요청, 응답 흐름

- HTTP 요청시
  - WAS는 Request, Response 객체를 새로 만들어서 서블릿 객체 호출
  - 개발자는 Request 객체에서 HTTP 요청 정보를 편리하게 꺼내서 사용
  - 개발자는 Response 객체에 HTTP 응답 정보를 편리하게 입력하게 입력
  - WAS는 Response 객체에 담겨있는 내용으로 HTTP 응답 정보를 생성

WAS안에는 서블릿 컨테이너가 존재
서블릿 컨테이너는 서블릿 객체들의 생명주기를 관리
서블릿 객체는 싱글톤으로 관리

Request, Response는 클라이언트마다 다르니 요청마다 생성

---

## 서블릿 객체를 호출하는 쓰레드

다중 요청을 위해 멀티 쓰레드 지원하는 WAS

*장점 :
동시 요청 처리 가능
리소스(CPU, 메모리) 허용까지 가능
하나의 쓰레드가 지연 되어도, 나머지 쓰레드는 정상 동작

*단점:
쓰레드 생성 비용이 비싸다
고객의 요청이 올 때마다 쓰레드를 생성하면, latency 저하
쓰레드는 컨텍스트 스위칭 비용이 발생
쓰레드 생성에 제한이 없다
고객 요청이 너무 많이 오면, CPU, 메모리 임계점을 넘어버려 서버가 DIE

## 해결책 : 쓰레드 풀

- 미리 쓰레드를 만들어놓고 요청 종료시에 쓰레드 종료가 아닌 대기 상태로 재활용
  1.  생성 비용 감소
  2.  쓰레드 풀의 크기로 트래픽 크기의 한계를 설정할 수 있음 (대기열)

- 쓰레드 풀이 너무 작으면 - 레이턴시 증가 -쓰레드 풀이 너무 크면 - 트래픽의 제한이 높기 때문에 서버 리소스가 커버 되지 않으면 서버가 죽어버린다.

WAS가 멀티쓰레드를 처리해줘서 고맙다...

쓰레드 풀의 적정 숫자를 성능 테스트를 통해
적절한 스레드 풀 숫자를 위해 테스트 해야한다.
nGrinder 등...

멀티 쓰레드 환경이므로 싱글톤 객체(서블릿, 스프링 빈)는 주의해서 사용해야한다.
지역변수 이외에는 모두 Thread Safe 하지않다.

HttpServletRequest 객체의 역할
서블릿은 개발자가 HTTP 요청 메시지를 편리하게 사용할 수 있도록 개발자 대신에 파싱한다. 그 결과를 HttpServletResponse 객체에 담아서 제공한다.

HttpServletRequest를 사용하면 다음과 같은 HTTP 요청 메시지를 편리하게 조회 할 수 있다.


### Web Server(Application Server) - 기존의 아키텍처 (boot 이전)
> Web Container > 여러 개의 Web Application




**HTTP 요청 메시지

```
POST /save HTTP/1.1
Host: localhost:8080
Content-Type: application/x-www-form-urlencoded

username=kim&age=20
```

- START LINE
  - HTTP 메소드
  - URL
  - 쿼리 스트링
  - 스키마, 프로토콜
- HEADER
  - 헤더 조회
- BODY
  - form 파라메터 형식 조회
  - message body 데이터 직접 조회 (json)

HttpServletRequest 객체는 추가적으로 여러가지 부가기능도 함께 제공한다.

**임시 저장소 기능

- 해당 HTTP 요청이 시작부터 끝날 때까지 유지되는 임시 저장소 기능
  - 저장 : `request.setAttribute(name, value)`
  - 조회 : `request.getAttribute(name)`

**세션 관리기능

- request.getSession(create: true)

*** 중요
HttpServletRequest, HttpServletResponse를 사용할때 가장 중요한 점은 이 객체들이 HTTP요청 메시지, HTTP응답 메시지를 편리하게 사용하도록 도와주는 객체라는 점이다. 따라서 이 기능에 대해서 깊이있는 이해를 하려면 HTTP 스펙이 제공하는 요청, 응답 메시지 자체를 이해 해야한다.


### get 복수 파라메터 처리

```
복수 파라메터 (ex) username=kim, username=han) 등의 중복으로 key를 보내올때
reques.getParameterValues()로 값을 받아야 데이터 손실이 없다.
중복일때 getParameter를 사용하면 첫 번째 값을 반환한다.
```

### FORM Data 처리

content-type : application/x-www-form-urlencode
message body : username=hello&age=20

Get에서 살펴본 쿼리 파라미터 형식과 같다. 따라서 쿼리 파라미터 조회 메서드를 그대로 사용하면 된다.

서버 입장에서는 둘의 형식이 동일하기 때문에 같은 메서드로 request를 조회하면된다


### 정리
content-type은 HTTP 메시지 바디의 데이터 형식을 지정한다.
GET URL 쿼리 파라미터 형식으로 클라이언트에서 서버로 데이터를 전달할 때는 HTTP 메시지 바디를 사용하지 않기
때문에 content-type이 없다.
POST HTML Form 형식으로 데이터를 전달하면 HTTP 메시지 바디에 해당 데이터를 포함해서 보내기 때문에 바디에
포함된 데이터가 어떤 형식인지 content-type을 꼭 지정해야 한다.

---

### Body에 text로 데이터 전송

- request.getInputStream()으로 바이트스트림으로 값이 들어오게 되는데 이것을
String으로 변환하는 작업을 해주어야 함 StreamUtils 같은 스프링에서 제공해주는 것을 썼다

ServletInputStream inputStream = request.getInputStream();

String messageBody = StreamUtils.copyString(inputStream, StandardCharsets.UTF_8);
이런식으로 문자를 -> byte, byte -> 문자로 변환해줄때는 encoding 형식을 꼭 명시해야한다.

---

### Body에 JSON으로 데이터 전송

- 요새 표준으로 채택된 JSON...

- content-type: "application/json"
- message body : {"username": "hello", "age" : 20}
- ObjectMapper로 만들어 놓은 VO로 JSON to Object 변환(Jackson 라이브러리)

```
application/json 은 스펙상 utf-8 형식을 사용하도록 정의되어 있다.
그래서 스펙에서 charset=utf8과 같은 추가 파라미터를 지원하지 않는다.
따라서 application/json이라고만 사용해야한다.
response.getWriter()를 사용하면 추가 파라미터를 자동으로 추가해버린다.
따라서 response.getOutputStream()으로 전송하자
```

```
form으로 전송하는것은 POST가 표준 
Spring은 POST가 아닌 put이나 PATCH를 Post로 변환해서 데이터를 받는 것일 뿐(히든필드)
```

## MVC 패턴

Model-View-Controller-Service 로 패턴을 나눈다.

기존의 웹 기술은
Servlet + html -> Servlet + JSP로 이어졌는데

JSP가 너무 많은 로직을 담당하다보니 유지보수가 지옥이였다.

따라서 JSP는 View만 담당하고 Servlet은 Controller를 Service에서 비지니스 로직을 담당하는 식으로
구조를 바꾸었다

Model은 View와 Controller가 통신하는 일종의 DTO다 request 객체안에는 저장소가 있는데

Service에서 온 데이터를
setAttribute()로 값을 저장하고 getAttribute()로 View(JSP)에서 데이터를 렌더링한다.

/WEB-INF : 이 경로 안에 JSP가 있다면 외부에서 직접 JSP를 호출할 수 없다. 우리가 기대하는 것은 항상 컨트롤러를 통해서 JSP를 호출하는 것이다.(폴더 구조를 통한 URL 작성 접근 불가)


### redirect vs forward

    리다이렉트는 실제 클라이언트(웹 브라우저)에 응답이 나갔다가, 클라이언트가 redirect 경로로 다시 요청한다
    따라서 클라이언트가 인지할 수 있고, URL 경로도 실제로 변경된다.(화면 깜빡임)
    반면에 forward는 서버 내부에서 일어나는 호출이기 때문에 클라이언트가 전혀 인지하지 못한다.
    


### 한계

굉장히 많은 중복(viewPath, forward)

데드 코드(Request or Response)

공통처리가 어려움 - 메서드 extract 해서 호출하는 것 또한 중복

코드 변경시 전체 코드를 뜯어 고쳐야하는 문제점
prefix : /WEB-INF/views/
suffix : .jsp


- 공통처리가 어려운 점(해결책)
    Front Controller의 패턴의 등장 (수문장 역할 입구를 하나로)

### 세션 유지 시간 (브라우저 가동 시간)

- Servlet 객체는 싱글톤 (최초 요청시 생성) Tomcat은 다중 request를 멀티 스레드로 동기적으로 동작함
init()이 요청 처음에 일어날 action을 구현
service() Service의 request와 response를 담당
destroy() - 1. 서버가 죽을 때
          - 2. 자동 reload 될 때
          
ServletContext : 서버가 종료될 때까지 유지, 모든 클라이언트가 공유 ---- application scope
HttpSession : 브라우저가 기동되어 있는 유지, 클라이언트 별로 ---- session scope
HttpServletRequest : 요청 동안 유지, 클라이언트 별로 ---- request scope

param - http parameter
PageConetxt ------- page scope
