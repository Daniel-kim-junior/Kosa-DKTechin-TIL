# HTTP 요청 데이터 - 개요

## HTTP란?

- 80번 포트를 사용하는 응용 계층 프로토콜
  인터넷에서 데이터를 주고 받을 수 있는 프로토콜
  HTTP/1.1, HTTp/2.0 등이 있다.

  구성요소 (Request)

  - 요청 라인 : Method 방식, 요청 URL
  - 요청 헤더 : 인코딩 방식
  - 요청 본체 : Request Body

  구성요소(Response)

  - 상태 라인 : 상태 코드와 프로토콜 버전
  - 헤더 : 인코딩 방식, 서버 정보
  - Response Body

```
상태 코드
2xx : Response.OK
3xx : 리퀘스트 완료를 위해 추가 동작이 필요함.
4xx : Client Error
5xx : Server Error
```

---

### Http Method

    1. Get : 단순히 데이터를 요청하는 Method (멱등성을 보장하고 안전한 메소드)
    2. Post : 데이터를 등록하는 Method(멱등성을 보장하지 않고 안전하지 않은 메소드)
    3. Put : 데이터를 수정하는 Method(멱등성을 보장하지만 안전하지 않은 메소드)
    4. PATCH : 데이터를 부분적으로 수정할 수 있는 Method(멱등성이 항상 보장됨을 장담할 수 없음, 안전하지 않은 메소드)
    5. Delete : 데이터를 삭제하는 Method(멱등성을 보장하지만 안전하지 않은 메소드)

### 멱등성이란?

- 서버에 동시에 요청하는 경우 서버 상태가 동일하여 항상 같은 값을 Response하는 것을 보장하는 성질
- ex) 같은 Request put을 여러번 호출해도 항상 동일한 값이 Response됨을 보장하기 때문에 멱등함
- PATCH는 부분 수정이 가능하기 때문에 멱등성이 보장되지 않는다.

### 안전함이란?

- 서버의 상태를 변화시키지 않는 Method, #Get #Head 등...

---

### 캐시 가능성

- 브라우저 또한 소프트웨어이고 캐싱이 가능하기 때문에 Response를 캐싱할 수 있다.
- 값을 변화시키지 않는 Get이나 Head 정도만 캐시 가능성이 보장 된다.

GET - 쿼리 파라미터

- /url?username=hello&age=20
- 메시지 바디없이, URL의 쿼리 파라미터에 데이터를 포함하여 전달
- 예) 검색, 필터, 페이징 등에서 많이 사용하는 방식

POST - HTML Form

- content-type : application/x-www-form-urlencoded
- 메시지 바디에 쿼리 파라미터 형식으로 전달 username=hello&age=20
- 예) 회원 가입, 상품 주문, HTML Form 사용

HTTP message body에 데이터를 직접 담아서 사용

- HTTP API에서 주로 사용, JSON, XML, TEXT
- 데이터 형식은 주로 json 사용
- POST, PUT, PATCH
