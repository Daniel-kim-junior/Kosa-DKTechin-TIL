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

2. Version 2

- View를 분리하여 각각의 Controller가 각자의 View를 return 하게 함

- View는 ViewPath와 forward를 담당함

3. Version 3

- 중복 구현 제거 URL(.jsp, /WEB-INF 등...), 각 컨트롤러들의 http servlet 의존성 제거
Front Controller에게 ModelView 객체를 리턴하여
모든 로직이 frontController(viewResolver, ParameterMap 생성 등...)에게 집중

4. Version 4

- 모든 컨트롤러에게 model이라는 map을 뿌려주고 그 모델에 로직 데이터들을 다시 FrontController에게 전송
- 컨트롤러의 역할이 더 작아짐 return은 string으로만 반환하면 되는 쉬운 패턴 장착

5. Version 5(어댑터 패턴)

- 한 가지 인터페이스에만 의존했던 지난 아키텍처를 조금 더 유연하게 운용하기 위해 핸들러 어댑터를 사용해
다양한 방식의 컨트롤러(인터페이스)를 처리할 수 있도록 변경

ex) 110V와 220V가 호환 안되는 것을 중간에 어댑터를 둬서 처리하는 예시

핸들러 : 컨트롤러의 이름을 더 넓은 범위의 핸들러로 변경
어떤 것이든 해당하는 종류의 어댑터만 있으면 다 처리할 수 있음

핸들러 어댑터 : 중간에 어댑터 역할을 하는 객체 다양한 종류의 컨트롤러를 등록해놓고 쓸 수 잇음
 


### Spring MVC에서의 이름

- FrontController -> DispatcherServlet

- handlerMappingMap -> HandlerMapping

- MyHandlerAdapter -> HandlerAdapter

- ModelView -> ModelAndView

- viewResolver -> ViewResolver

- MyView -> View



*** Spring MVC는 HandlerMapping (RequestMapping 방식, beanNameUrl 방식 등으로 HandlerMapping List를 추가) 그것을 토대로 HandlerAdapter에서 legacy Controller를 implements 했거나 어노테이션으로 등록되어있는 등의 등록되어있는지 확인 후 handleRequest 등을 통해 실제 컨트롤러들에게 ModelAndView를 리턴받는다. 다형성으로 추상화 되어 있는 끝판왕 시스템이다.

*** viewResolver는 어떤 ViewTemplate도 지원가능하게 추상화 되어있고 그것을 토대로 JSP, Thymeleaf 등 다양한 템플릿들을 지원한다.

*** viewResolver는 springboot가 자동으로 suffix, prefix viewResolver(많은 뷰 리졸버 리스트 가 준비되어 있음)를 만들어 지원해준다.

*** ModelToRequest도 전부 추상화 되어있어서 Model에 넣기만해도 되는것이었다. 원래는 request에 넣어야하는데 ㅜㅜ

*** 다른 뷰는 실제 뷰를 렌더링하지만, JSP의 경우 forword()를 통해서 해당 JSP로 이동(실행)해야 렌더링 된다. (아마 성능적 불리가 여기서 나오는듯)


## Spring MVC 시작하기

- @RequestMapping + @Component == @Controller  (Class Level)
    HandlerMapping 대상이 된다.

- HandlerAdapter가 URL을 통해 해당 컨트롤러를 호출
