# FrontController

- Spring MVC의 궁극적인 핵심

  요청이 올때 마다 각각의 URL로 매핑해준 과거의 서블릿의 중복 요소들을 집합시켜
  하나의 서블릿이 입구에서 요청에 맞는 컨트롤러를 찾아 호출하는 패턴
  공통 처리 가능
  프론트 컨트롤러를 제외한 나머지 컨트롤러는 서블릿을 사용하지 않아도 된다.

  Spring Web MVC의 Dispatcher Servlet이 FrontController 패턴으로 구현되어 있음

1. Version 1

- Controller의 공통적인 부분 즉 HttpServlet을 상속 받는 부분을 interface로 느슨하게 결합한 후
  하나의 서블릿 프론트 컨트롤러에서 Map으로 각 서블릿들을 관리 /edu/index/\* 같이 모든 url들에 대한 요청을 받는다
  map에 각 서블릿의 구체 url에 대한 정보를 담아두고 <URL, Interface> 형태로 저장
  각 서블릿에 대한 요청이 오면 Interface의 구현체들을 인스턴스화 해서 각 컨트롤러를 실행 시킨다.

  참고) 개발할 때는 왠만하면 상대경로
