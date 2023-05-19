# [Rest 란?]

Rest는 "REpresentational State Transfer"의 약어로, 하나의 URI는 하나의 고유한 리소스(Resource)를 대표하도록 설계된다는 개념이다.
즉, 자원을 이름으로 구분하여 해당 자원의 상태(정보)를 주고받는 모든 것을 의미한다.

표준 통신 방식이 없던 과거에는 분산 객체 통신, 프로시저 콜 등으로 난립하던 시절에 기준을 잡은 http 통신 아키텍처

ex) 만약에 웹 페이지의 상품이 등록되어있을때 html에 링크를 거는 식으로 만들 수 있을 것이다.
하지만 브라우저에서만 국한된다는 점, 상품의 링크가 변경되었을때 전부 변경해야 해야 하는 단점이 드러난다.

따라서 진정한 Rest API 서비스라면 상품 이미지 뿐만 아니라 주어진 내용과 관련된 좀 더 자세한 정보들을 동시에 보내주어야 한다.(이미지 url 등...)
그것이 application/hal+json이다.

[REST가 필요한 이유]

- 어플리케이션 분리 및 통합
- Web을 기반으로 하는 C&S 환경의 다양한 프로그램 개발
- 다양한 클라이언트의 등장


DTO같은 객체를 Return 하지 않는 이상 text/plain


Spring Rest, HateOAS


RestController

- SpringBoot 에서는 별도의 xml 파일 설정없이 어노테이션으로 등록
- RequestBody (Post)

@ResponseBody와 ResponseEntity

return ResponseEntity.ok().headers()... builder 패턴으로 처리가능

HAL(Hypertext Application Language)은 JSON 또는 XML 코드 내의 외부 리소스에 대한 링크와 같은
하이퍼미디어를 정의하기 위한 Internet Draft ("진행 중인 작업") 표준 규칙 이다.







