# Spring MVC

### @RestController vs @Controller

- @Controller는 반환 값이 'String'이면 뷰 이름으로 인식 된다. 그래서 '뷰를 찾고 뷰가 렌더링' 된다.
- @RestController는 반환 값으로 뷰를 찾는 것이 아니라, 'HTTP 메시지 바디에 바로 입력'한다. 따라서 실행 결과로
  ok 메시지를 받을 수 있다. @ResponseBody 와 관련이 있다.
  
  
@RequestMapping("/hello-basic")
- 대부분의 속성을 배열[]로 제공하므로 다중 설정 가능 ex) {"/hello-basic", "/hello-go"}

URL 요청: '/hello-basic', /hello-basic/'을 스프링은 같은 요청으로 받아들여 준다.

@RequestMapping은 method attribute를 지정해주지 않으면 모든 요청을 다 허용한다.

그것을 축약해서 @GetMapping @PostMapping @PutMapping @DeleteMapping @PatchMapping으로 요청에 제약을 걸어주는 것이 좋다.

@PathVariable
- URL에서 직접 값을 추출해서 사용 가능함
localhost:8080/mapping/{userId} -> @PathVariable("userId") String data

또 파라미터, 헤더, Request Content Type(consumes), Request Accept Type(produces)
기반으로 요청을 받을 수도 또는 거부할 수도 있다.


- Controller 메소드에서는 여러가지 값을 받을 수 있다.(매개 변수로..Flexible Signiture)
    - HttpServletRequest
    - HttpServletResponse
    - HttpMethod : method 조회
    - Locale : Locale 정보 조회
    - @RequestHeader MultiValueMap<String, String> headerMap : 모든 HTTP헤더를 MutliValueMap 형식으로 조회한다.
    - @RequestHeader("host") String host
        - 특정 HTTP 헤더 조회
        - 속성
            - 필수 값 여부 : required
            - 기본 값 속성 : 'defaultValue'
    - @CookieValue(value = "myCookie", required = false) String cookie
        - 특정 쿠키를 조회한다.
        - 속성 :
            - 필수 값 여부 : 'required'
            - 기본 값 속성 : 'defaultValue'

- MultiValueMap
    - Map과 유사한데, 하나의 키에 여러 값을 받을 수 있다.
    - HTTP header, HTTP 쿼리 파라미터와 같이 하나의 키에 여러 값을 받을 때 사용한다.
        - 'keyA=value1 & keyA=value2

```java
MultiValueMap<String, String> map = new LinkedMultiValueMap();
map.add("keyA", "valueA");
map.add("keyA", "valueB");

// [valueA, valueB]
List<String> values = map.get("keyA");

```


### Filter

- Abstract Method 없는 Abstract Class 절대 구현해야할 로직은 없지만 상속 받아서 쓴다

- Filter란 웹 클라이언트에서 요청한 웹 자원들(Servlet 또는 JSP, 등 정적 자원들)이 수행되기 전 또는 후에 수행되는 객체로서 request 또는 response에 영향을 주거나 또는 특정 처리를 할 수 있다.
    Filter의 응용 예로 인증, 로깅, 이미지 변환, 데이터 압축, 암호화, 스트림 토큰화, XML 변환 등이 있다.


사용법
```
ex) DispatcherType.Forward

    @WebFilter(dispatcherTypes = {DispatcherType.REQUEST}, urlPatterns("/*"))
    public class HangulFilter implements Filter {
        public void doFilter(ServletRequest request, ServletHttpResponse res, FilterChain chain) {
            HttpServletRequest req = (HttpServletRequest) request;
            if(req.getMethod().equals("POST")) {
                req.setCharacterEncoding("utf-8");
            }
            chain.doFilter(req, res);
        }
    }
}
```
FilterChain 객체를 통해 다음에 수행되어야할 객체에 대한 정보를 넘길 수 있다.
