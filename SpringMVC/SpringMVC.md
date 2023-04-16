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


  

