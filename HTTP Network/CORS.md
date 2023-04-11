# Cross Origin Resource Sharing

한마디로 브라우저의 리소스 관리 정의

한국어로 직역하면 교차 출처 리소스 공유이다.

여기서 교차출처는 "다른 출처"를 의미하는 것인데

여기서 말하는 출처(Origin)란 무엇일까?

### 출처(Origin)이란?

서버의 위치를 의미하는 https://google.com과 같은 URL들은 마치 하나의 문자열 같아 보여도,

사실은 여러개의 구성요소로 이루어져 있다.

protocol:// host / path ? Query String #Fragment

이때 출처는 Protocol과 Host, 그리고 포트 번호까지 모두 합친 것을 의미한다.

즉 서버의 위치를 찾아가기 위해 필요한 가장 기본적인 것들을 합쳐 놓은 것이다.

또한 출처 내의 포트 번호는 생략이 가능한데, 이는 각 웹에서 사용하는 HTTP, HTTPS 프로토콜의 기본 포트번호가 정해져 있기 때문이다.

**결국 출처는 프로토콜 + 호스트 + 포트번호인 것이다!!!

### SOP(Same-Origin-Policy)

웹 생태계에는 다른 출처로의 리소스를 제한하는 것과 관련된 두 가지 정책이 존재한다.

한 가지는 우리의 탐구영역인 CORS, 또 한가지는 SOP(Same-Origin Policy)이다.

SOP는 2011년 처음 등장한 보안 정책으로 말 그대로 "같은 출처에서만 리소스를 공유할 수 있다"
라는 규칙을 가진 정책이다.

그러나 웹이라는 오픈스페이스 환경에서 다른 출처에 있는 리소스를 가져와서 사용하는 일은 매우 흔한 일이라

무작정 막을 수도 없느 노릇이니 몇 가지 예외 조항을 두고 이 조항에 해당하는 리소스 요청은 출처가 다르더라도 허용하기로 했는데, 그 중 하나가 "CORS 정책을 지킨 리소스 요청" 이다.

우리가 다른 출처로 리소스를 요청한다면 SOP 정책을 위반한 것이 되고, 거기다가 SOP의 예외조항인 CORS 정책
까지 지키지 않는다면 아예 다른 출처의 리소스를 사용할 수 없게 되는 것이다.

즉 이렇게 다른 출처의 리소스를 사용하는 것을 제한하는 행위는 하나의 정책만으로 결정된 사항이 아니라는 의미가 되며, SOP에서 정의된 예외 조항과 CORS를 사용할 수 있는 케이스들이 맞물리지 않을 경우에는 아예 리소스 요청을 할 수 없는 케이스도 존재할 수 있다.

그렇다면 근본적인 의문으로 왜? 이런 정책을 설정한 것일까?

하지만 이렇게 출처가 다른 두 개의 어플리케이션이 마음대로 소통할 수 있는 환경은 꽤나 위험한 환경이다.

애초에 클라이언트 어플리케이션, 특히나 웹에서 돌아가는 클라이언트 어플리케이션은 사용자의 공격에 너무나도 취약한 아이라는 사실이다.

당장 브라우저 개발자 도구만 열어도 DOM이 어떻게 작성되었는지, 어떤 서버와 통신하는지, 리소스의 출처는 어디인지와 같은 정보들을 아무런 제재없이 열람할 수 있지 않나?

최근에는 자바스크립트 소스 코드를 난독화해서 읽기 어렵다고 하지만, 난독화는 어디까지나 난독화
암호화가 아니다.

그리고 아무리 난독화되어있다고 해도 사람이 바로 이해할 수 없는 정도도 아닌데다가, 소스 코드를 직접 볼 수 있다는 것 자체가 보안적으로 상당히 취약한 부분이다. 심지어 아직까지도 소스 코드의 난독화가 안되어 개발자 도구만 열면 <script> 태그 안에 날 것 그대로의 소스 코드가 떡하니 노출되어 있는 사이트도 많다.

이러한 상황 속에서 다른 출처의 어플리케이션이 서로 통신하는 것에 대해 아무런 제약도 존재하지 않는다면, 악의를 가진 사용자가 소스 코드를 구경한 후 CSRF(Cross-Site Request Forgery)나 XSS(Cross-Site Scripting)와 같은 방법을 사용하여 어플리케이션에서 코드가 실행된 것처럼 꾸며 사용자의 정보를 탈취하기가 너무 쉬워진다.

### 같은 출처와 다른 출처의 구분

출처가 같다고 판단하는 기준은 뭘까?

바로 Schema, Host, Port 이 3가지만 동일하면 된다.

https://naver.com:80라는 출처를 예로 들면 https:// 이라는 스키마에 naver.com 호스트를 가지고
:80번 포트를 사용하고 있다는 것이 같으면 같은 출처로 인정된다.

만약 https://naver.com:8080 같은 케이스는 만약 출처에 https://naver.com:80처럼 포트번호가 명시되어 있었다면 명백하게 다른 출처이지만 포트번호가 포함되지 않았다면
각 브라우저들의 독자적인 출처 비교 로직을 따라가게 된다.

여기서 중요한건 브라우저에 구현되어 있는 스펙이라는 것이다.
서버에 같은 출처의 요청만 받겠다는 스펙이 구현되어 있지 않으면
브라우저는 서버에게 정상적으로 요청을 보내고 정상적으로 서버는 응답한다.
후에 request 분석을 통해 Cors 정책을 위반했다면 그 응답을 버린다.

그렇기 때문에 브라우저를 통하지 않는 서버간 통신은 이 정책이 적용되지 않는다.
또 CORS 정책을 위반하는 리소스 요청 때문에 에러가 발생했다고 해도 서버 쪽 로그에는 정상적으로 응답했다는 로그만 남기 때문에, CORS가 돌아가는 방식을 정확히 모르면 에러 트레이싱에 난항을 겪을수도...?


그럼 본격적으로 어떤 방법을 통해 서로 다른 출처를 가진 리소스를 안전하게 사용할 수 있는지 알아보도록 하자.

기본적으로 웹 클라이언트 어플리케이션이 다른 출처의 리소스를 요청할 때는 HTTP 프로토콜을 사용하여 요청을 

보내게 되는데, 이 때 브라우저는 요청 헤더에 Origin이라는 필드에 요청을 보내는 출처를 함께 담아낸다.

```
Origin: https://github.com/Daniel-kim-junior
```

이후 서버가 이 요청에 대한 응답을 할 때 응답 헤더의 Access-Control-Allow-Origin이라는 값에 "이 리소

스를 접근하는 것이 허용된 출처"를 내려주고, 이후 응답을 받은 브라우저는 자신이 보냈던 요청의 Origin과 서

버가 보내준 응답의 Access-Control-Allow-Origin 을 비교해본 후 이 응답이 유효한 응답이 아닌지를 결정한다.


CORS가 동작하는 방식은 한 가지가 아니라 세 가지의 시나리오에 따라 변경된다.

---

### Preflight Request

프리플라이트(Preflight) 방식은 일반적으로 우리가 웹 어플리케이션을 개발할 때 가장 많이 마주치는 시나리오

브라우저는 요청을 한번에 보내지 않고 예비 요청과 본 요청으로 나누어 서버로 전송한다.

이때 브라우저가 본 요청을 보내기 전에 보내는 예비 요청을 Preflight라고 부르는 것,

이 예비 요청에는 HTTP 메소드 중 OPTIONS 메소드가 사용된다. 

예비 요청의 역할은 본 요청을 보내기 전에 브라우저 스스로 이 요청을 보내는 것이 안전한지 확인하는 과정이다.

---

### Preflight 플로우 차트

[!preflight](./img/cors-preflight.png)

요청의 유효성 검사

우리가 자바스크립트의 fetch API를 사용하여 브라우저에게 리소스를 받아오라는 명령을 내리면 브라우저는 

서버에게 예비 요청을 먼저 보내고, 서버는 이 예비 요청에 대한 응답으로 현재 자신이 어떤 것들을 허용하고, 

어떤 것들을 금지하고 있는지에 대한 정보를 응답 헤더에 담아서 브라우저에게 다시 보내주게 된다.

```js
const headers = new Headers({
  'Content-Type': 'text/xml',
});
fetch('https://github.com/Daniel-kim-junior', { headers });
```


```http
OPTIONS https://github.com/Daniel-kim-junior

Accept: */*
Accept-Encoding: gzip, deflate, br
Accept-Language: en-US,en;q=0.9,ko;q=0.8,ja;q=0.7,la;q=0.6
Access-Control-Request-Headers: content-type
Access-Control-Request-Method: GET
Connection: keep-alive
Host: evanmoon.tistory.com
Origin: https://github.com/Daniel-kim-junior
Referer: https://github.com/Daniel-kim-junior/2020/04/13/about
Sec-Fetch-Dest: empty
Sec-Fetch-Mode: cors
Sec-Fetch-Site: cross-site
```

실제로 OPTIONS 메소드로 브라우저가 보낸 요청을 보면, 단순히 Origin에 대한 정보 뿐만 아니라 자신이 예비 요청 이후 보낼 본 요청에 대한 다른 정보들도 함께 포함되어 있는 것을 볼 수 있다.

이 예비 요청에서 브라우저는 Access-Control-Request-Headers를 사용하여 자신이 본 요청에서 

Content-Type 헤더를 사용할 것을 알려주거나, Access-Control-Request-Method를 사용하여 이후 

GET 메소드를 사용할 것을 서버에게 미리 알려주고 있는 것이다.

이렇게 예비 요청을 보내면, 예비 요청에 대한 응답을 서버에서 해준다.

```http
OPTIONS https://github.com/Daniel-kim-junior 200 OK

Access-Control-Allow-Origin: https://github.com/Daniel-kim-junior
Content-Encoding: gzip
Content-Length: 699
Content-Type: text/xml; charset=utf-8
Date: Sun, 24 May 2020 11:52:33 GMT
P3P: CP='ALL DSP COR MON LAW OUR LEG DEL'
Server: Apache
Vary: Accept-Encoding
X-UA-Compatible: IE=Edge
```

여기서 눈여겨 볼것은 서버가 보내준 응답 헤더에 포함된 Access-Control-Allow-Origin : https://github.com/Daniel-kim-junior 라는 값이다.

깃허브 서버는 이 리소스에 접근이 가능한 출처는 오직 https://github.com/Daniel-kim-junior 뿐이라고 응답해 준 것이다.

나는 localhost:8080이므로 서버가 허용해준 출처와는 다른 출처이다.

결국 브라우저는 이 요청이 CORS 정책을 위반했다고 판단하고 에러를 뱉는다.


🚨 Access to fetch at ‘https://evanmoon.tistory.com/rss’ from origin ‘https://evan-moon.github.io’ has been blocked by CORS policy: Response to preflight request doesn’t pass access control check: The ‘Access-Control-Allow-Origin’ header has a value ‘http://evanmoon.tistory.com’ that is not equal to the supplied origin. Have the server send the header with a valid value, or, if an opaque response serves your needs, set the request’s mode to ‘no-cors’ to fetch the resource with CORS disabled.

이때 예비 요청에 대한 응답에서 에러가 발생하지 않고 정상적으로 `200`이 떨어졌는데, 콘솔 창에는 빨갛게 에러가 표시되기 때문에 많은 분들이 헷갈려하시는데, 

CORS 정책 위반으로 인한 에러는 예비 요청의 성공 여부와 별 상관이 없다. 브라우저가 CORS 정책 위반 여부를 판단하는 시점은 예비 요청에 대한 응답을 받은 이후이기 때문이다. 
(서버에서 Origin 값을 받아와야 교차 체크가 가능하기 때문)

예비 요청 자체가 실패해도 똑같이 CORS 정책 위반으로 처리될 수도 있지만, 중요한 것은 예비 요청의 성공/실패 여부가 아니라 

"응답 헤더에 유효한 Access-Control-Allow-Origin 값이 존재하는가"이다

만약 예비 요청이 실패해서 200이 아닌 상태코드가 내려오더라도 헤더에 저 값이 제대로 들어가있다면 적어도 CORS 정책 위반은 아니라는 의미이다.

대부분의 경우 이렇게 예비 요청, 본 요청을 나누어 보내는 프리플라이트 방식을 사용하기는 하지만, 모든 상황에서 이렇게 두 번씩 요청을 보내는 것은 아니다. 

조금 까다로운 조건이기는 하지만 어떤 경우에는 예비 요청없이 본 요청만으로 CORS 정책 위반 여부를 검사하기도 한다.

---

### Simple Request

이 시나리오에 대한 정식 명칭은 없지만 MDN의 CORS 문서에는 이 시나리오를 Simple Request라고 부르고 있다. 

단순 요청은 예비 요청을 보내지 않고 바로 서버에게 본 요청을 보낸 후, 서버가 이에 대한 응답으로 헤더에

Access-Control-Allow-Origin과 같은 값을 보내주면 그때 브라우저가 CORS 정책 위반 여부를 검사하는 

방식이다. 즉 프라이플라이트와 단순 요청의 시나리오는 전반적인 로직 자체는 같되, 예비 요청의 존재 여부만 다르다.

[!단순요청](./img/simple-request.png)


하지만 아무 때나 단순 요청을 사용할 수 있는 것은 아니고, 특정 조건을 만족하는 경우에만 예비 요청을 생략할 수 있다.

게다가 이 조건이 까다롭기 때문에 일반적인 방법으로 웹 어플리케이션 아키텍처를 설계하게 되면 거의 충족시키기 어려운 조건들이다.

```
1. 요청의 메소드는 GET, HEAD, POST 중 하나여야 한다.
2. Accept, Accept-Language, Content-Language, Content-Type, DPR, Downlink, Save-Data, Viewport-Width, Width를 제외한 헤더를 사용하면 안된다.
3. 만약 Content-Type을 사용하는 경우에는 application/x-www-form-urlencoded, multipart/form-data, text-plain만 허용된다.
```

사실 1번 조건의 경우에는 그냥 PUT이나 DELETE 같은 메소드를 사용하지 않으면 되는 것 뿐이다.

하지만 2번과 3번 조건 같은 경우는 조금 까다롭다.

애초에 저 조건에 명시된 헤더들은 진짜 기본적인 헤더들이기 때문에, 복잡한 상용 웹 어플리케이션에서 이 헤더들 외에 추가적인 헤더를 사용하지 않는 경우는 드물다.

당장 사용자 인증에 사용되는 Authorization 헤더 조차 저 조건에 포함되지 않는다.

게다가 대부분 HTTP API는 text/xml이나 application/json 컨텐츠 타입을 가지도록 설계되기 때문에 사실 상 이 조건들을 만족시키는 상황을 만들기는 그렇게 쉽지 않은 것이 현실

---

### Credentialed Request

3번째 시나리오는 인증된 요청을 사용하는 방법이다. 이 시나리오는 CORS의 기본적인 방식이라기 보다는 

다른 출처 간 통신에서 좀 더 보안을 강화하고 싶을 때 사용하는 방법이다.

기본적으로 브라우저가 제공하는 비동기 리소스 요청 API인 XMLHttpRequest 객체나 fetch API는 별도의 

옵션 없이 브라우저의 쿠키 정보나 인증과 관련된 헤더를 함부로 요청에 담지 않는다. 이때 요청에 인증과 관련

된 정보를 담을 수 있게 해주는 옵션이 바로 credentials 옵션이다.

이 옵션에는 총 3가지의 값을 사용할 수 있으며, 각 값들이 가지는 의미는 다음과 같다.

    same-origin : 같은 출처 간 요청에만 인증 정보를 담을 수 있다.
    include : 모든 요청에 인증 정보를 담을 수 있다.
    omit : 모든 요청에 인증 정보를 담지 않는다.

만약 여러분이 same-origin이나 Include와 같은 옵션을 사용하여 리소스 요청에 인증 정보가 포함된다면, 이제 브라우저는 다른 출처의 리소스를 요청할 때 단순히 Access-Control-Access-Origin만 확인하는 것이 아니라 좀 더 빡빡한 검사 조건을 추가하게 된다.

또 구글 크롬 브라우저의 credentials 기본 값은 같은 출처 내에서만 인증 정보를 사용하겠다는 same-origin

이기 때문에, 필자의 로컬 환경에서 https://github.com/Daniel-kim-junior 로 보내는 리소스 요청에는 당연

히 브라우저의 쿠키와 같은 인증 정보가 포함되어 있지 않다.

그렇기 때문에 브라우저는 단순히 Access-Control-Allow-Origin : * 이라는 값만 보고 "이 요청은 안전하

다" 라는 결론을 내린다. 그러나 credentials 옵션을 모든 요청에 인증 정보를 포함하겠다는 의미를 가진 

include로 변경하고 같은 요청을 보내면 상황이 달라진다

```js
fetch(' https://github.com/Daniel-kim-junior', {
  credentials: 'include', // Credentials 옵션 변경!
});
```

🚨 Access to fetch at ’[https://evan-moon.github.io/feed.xml](https://evan-moon.github.io/feed.xml)’ from origin ’[http://localhost:8000](http://localhost:8000/)’ has been blocked by CORS policy: The value of the ‘Access-Control-Allow-Origin’ header in the response must not be the wildcard ’*’ when the request’s credentials mode is ‘include’.

브라우저는 인증 모드가 include 일 경우, 모든 요청을 허용한다는 의미의 `*`를 Access-Control-Allow-Origin 헤더에 사용하면 안된다고 이야기 하고 있다.

이처럼 요청에 인증 정보가 담겨있는 상태에서 다른 출처의 리소스를 요청하게 되면 브라우저는 CORS 정책 위반 여부를 검사하는 룰에 다음 두 가지를 추가하게 된다.

1. Access-Control-Allow-Origin에는 `*`를 사용할 수 없으며, 명시적인 URL이어야 한다.
2. 응답 헤더에는 반드시 Access-Control-Allow-Credentials: true가 존재해야한다.

인증까지 얽혀있는 이 시나리오는 다른 시나리오에 비해 다소 복잡하다고 생각하지만 이렇게 CORS 정책에 대

한 다양한 시나리오를 알아두면 실제 상황에서 CORS 정책 위반으로 인한 문제가 발생했을 경우에 시간을 크게 

단축시킬 수 있어 숙지 해놓자.

---

### CORS를 해결할 수 있는 방법

지금까지 CORS가 무엇인지, 어떤 상황에서 CORS 정책이 적용되고 위반되는 것인지 알아봤다면 실질적으로 

CORS 정책 위반으로 인한 문제가 발생했을 경우에 해결할 수 있는 방법을 알아본다.

---

### Access-Control-Allow-Origin 세팅하기

CORS 정책 위반으로 인한 문제를 해결하는 가장 대표적인 방법은, 그냥 정석대로 서버에서 `Access-Control-Allow-Origin` 헤더에 알맞은 값을 세팅해주는 것이다

이때 와일드카드인 `*`을 사용하여 이 헤더를 세팅하게 되면 모든 출처에서 오는 요청을 받아먹겠다는 의미이므

로 당장은 편할 수 있겠지만, 바꿔서 생각하면 정체도 모르는 이상한 출처에서 오는 요청까지 모두 받아먹겠다

는 오픈 마인드와 다를 것 없으므로 보안적으로 심각한 이슈가 발생할 수도 있다.

그러니 가급적이면 귀찮더라도 Access-Control-Allow-Origin: 특정 url 과 같이 출처를 명시하도록 하자.

이 헤더는 Nginx나 Apache와 같은 서버 엔진의 설정에서 추가할 수도 있지만, 아무래도 복잡한 세팅을 하기는 불편하기 때문에 소스 코드 내에서 응답 미들웨어 등을 사용하여 세팅하는 것을 추천한다.

Spring, Express, Django와 같은 프레임워크들은 모두 CORS 관련 설정을 위한 세팅이나 미들웨어 라이브

러리를 제공하고 있으니 세팅 자체가 어렵진 않다.

---

### Webpack Dev Server로 리버스 프록싱 하기


사실 CORS를 가장 많이 마주치는 환경은 바로 로컬에서 프론트엔드 어플리케이션을 개발하는 경우라고 해도 

과언이 아니다. 백엔드에는 이미 Access-Control-Allow-Origin 헤더가 세팅되어있겠지만, 이 중요한 헤더

에다 http://localhost:3000 같은 범용적인 출처를 넣어주는 경우는 드물기 때문이다.

프론트엔드 개발자는 대부분 웹팩과 `webpack-dev-server`를 사용하여 자신의 머신에 개발 환경을 구축하게 

되는데, 이 라이브러리가 제공하는 프록시 기능을 사용하면 아주 편하게 CORS 정책을 우회할 수 있다.

```js
module.exports = {
  devServer: {
    proxy: {
      '/api': {
        target: 'https://api.evan.com',
        changeOrigin: true,
        pathRewrite: { '^/api': '' },
      },
    }
  }
}
```
이렇게 설정을 해놓으면 로컬 환경에서 `/api`로 시작하는 URL로 보내는 요청에 대해 브라우저는

`localhost:8000/api`로 요청을 보낸 것으로 알고 있지만, 사실 뒤에서 웹팩이 `https://api.evan.com`

으로 요청을 프록싱해주기 때문에 마치 CORS 정책을 지킨 것처럼 브라우저를 속이면서도 우리는 원하는 서버

와 자유롭게 통신을 할 수 있다. 즉, 프록싱을 통해 CORS 정책을 우회할 수 있는 것이다.

혹시 [webpack-dev-middleware](https://github.com/webpack/webpack-dev-middleware)와 Node 서버의 조합으로 개발 환경을 직접 구축했더라도 [http-proxy-middleware](https://github.com/chimurai/http-proxy-middleware) 라이브러리를 사용하면 손쉽게 프록시 설정을 할 수 있으니 걱정하지말자. 
(`webpack-dev-server`도 내부적으로는 어차피 `http-proxy-middleware`를 사용한다)

다만 이 방법은 실제 프로덕션 환경에서도 클라이언트 어플리케이션의 소스를 서빙하는 출처와 API 서버의 출처가 같은 경우에 사용하는 것이 좋다. 

물론 로컬 개발 환경에서야 웹팩이 요청을 프록싱해주니 아무 이상이 없겠지만, 어플리케이션을 빌드하고 서버에 올리고 나면 더 이상 `webpack-dev-server`가 구동하는 환경이 아니기 때문에 프록싱이고 나발이고 이상한 곳으로 API 요청을 보내기 때문이다.

예를 들어 API 서버의 출처는 `https://api.evan.com`이고 클라이언트 어플리케이션을 서빙하는 서버의 출처는 `https://www.evan.com`이라면, 다음과 같은 상황이 발생한다는 것이다.

```js
fetch('/api/me');
```

```http
로컬환경에서는...
GET https://api.evan.com/me 200 OK

실제 서버에는 프록싱 로직이 없음...
GET https://www.evan.com/api/me 404 Not Found
```

물론 비즈니스 로직 내에서 `process.env.NODE_ENV`와 같은 빌드 환경 변수를 사용하여 분기 로직을 작성하는 방법도 있지만, 개인적으로 비즈니스 로직에 이런 개발 환경 전용 소스가 포함되는 것은 별로 좋지 않다고 생각해서 피하는 편이다.

 `webpack-dev-server`의 프록싱 옵션을 사용하여 자체적으로 해결하는 방법도 있지만, 이 방법은 로컬 개발 환경에서만 통하는 방법인데다가, 근본적인 문제 해결 방법이 아니기 때문에 결국 운영 환경에서 CORS 정책 위반 문제를 해결하기 위해서는 백엔드 개발자의 도움이 필요할 수 밖에 없다.


결론 : 서버 개발자가 다 하자!!



