# JavaScript

중요 : JavaScript v5.0(격변), Ecma 2015 이후(모던 자바스크립트라고 대다수가 정의)

- var : 블록 스코프 지원 X, 재 할당 가능, 호이스팅 O,  
- let : 재 할당 가능, 블록 스코프 지원 O, 호이스팅 X, 다시 초기화는 불가

예전에는 브라우저가 유일한 웹앱을 구동시키는 런타임 환경이였지만
현재는 Node.js를 통해 다양한 Javascript 런타임 환경이 만들어짐


Babel : 트랜스 파일러 (브라우저가 지원하는 JavaScript 버전은 낮은 버전의 Javascript일 확률이 높다 -> JavaScript만이 브라우저에서 실행할 수 있는 코드이기 때문에 -> 내가 원하는 어떠한 버전의 Javascript 혹은 다른 언어로 클라이언트를 개발하고 그것을 트랜스파일링해서 안정적인 버전의 Javascript로 변환할 수 있는 도구)

Webpack, pacel.. : 압축, 난독화, 번들링 등 많은 기능을 가진 번들러들 
모듈들을 하나로 묶어 하나의 자바스크립트 파일로 많은 기능들을 우겨넣음 js 뿐만 아니라,
css, sass, img, svg 등 많은 파일들을 번들링함

```
const AJAX = new XMLHttpRequest();
const NEWS_URL = 'https://api.hnpwa.com/v0/news/1.json';
AJAX.open('GET', NEWS_URL , false);
AJAX.send();

const newsFeed = JSON.parse(AJAX.response);
console.log(newsFeed);

let dom = '<ul>'
newsFeed.every((v, i) => {
  if(i > 3) {
    dom += '</ul>';
    return false;
  }
  dom += `<li>${v.title}</li>`;
  return true;
})


document.getElementById("root").innerHTML = dom;

```

XHR == XMLHttpRequest == Ajax 통신을 가능하게 해주는 객체 (Asyncronous javacript and XML)
웹 페이지에서 동적으로 서버와 데이터를 교환할 수 있는 기술이다. 이를 통해 페이지 전체를 다시 로드하지 않고도 새로운 데이터를 가져와서 화면을 업데이트할 수 있다.

예를 들어, 사용자가 웹 페이지에서 어떤 동작을 수행했을 때, AJAX를 사용하여 서버에 요청을 보내고, 서버는 이에 대한 응답을 반환합니다. 이를 통해 사용자는 새로운 정보를 볼 수 있고, 페이지 전체를 다시 로드하지 않아도 된다.

AJAX는 XML 대신 JSON, HTML, 텍스트 등의 형식으로 데이터를 교환하는 경우도 많이 있다. 또한, AJAX를 사용하면 비동기적으로 데이터를 처리하기 때문에, 페이지의 성능과 응답성이 향상될 수 있다.

AJAX를 구현하기 위해서는 JavaScript를 사용하여 XMLHTTP 요청을 만들고, 서버와 통신하여 데이터를 처리하는 코드를 작성해야 한다. 이를 도와주는 여러 라이브러리와 프레임워크들이 있다. (Axios, JQuery, React, Vue 등...)


## Closure(클로저란?)

- 클로저는 함수와 그 함수가 선언됐을 때의 렉시컬 환경(Lexical environment)의 조합이다.

함수가 선언됐을 때의 렉시컬 환경이란?

```javascript
function outerFunc() {
  var x = 10;
  var innerFunc = function () { console.log(x); };
  innerFunc();
}

outerFunc(); // 10
```

outerFunc 내에서 innerFunc이 선언 되었고 호출되었다. 이때 내부함수 innerFunc는 자신을 포함하고 있는
외부함수 outerFunc의 변수 x에 접근 가능하다(당연히 innerFunc안에 '선언'되었기 때문)

```
스코프는 함수를 호출할 때가 아니라 함수를 어디에 선언하였는지에 따라 결정된다.
이를 렉시컬 스코핑(Lexical scoping)이라고 한다. 위 예제의 함수 innerFunc는 함수 outerFunc의 내부에서 선언되었기 때문에 함수 innerFunc의 상위 스코프는 함수 outerFunc이다. 함수 innerFunc가
전역에 선언되었다면 함수 innerFunc의 상위 스코프는 전역 스코프가 된다.
```

함수 innerFunc가 함수 outerFunc의 내부에 선언된 내부함수이므로 함수 innerFunc는 자신이 속한 렉시컬 스코프(전역, 함수 outerFunc, 자신의 스코프)를 참조할 수 있다. 이것을 실행 컨텍스트의 관점에서 설명해보자.

내부함수 innerFunc가 호출되면 자신의 실행 컨텍스트가 실행 컨텍스트 스택에 쌓이고 변수 객체(Variable Object)와 스코프 체인(Scope Chain) 그리고 this에 바인딩할 객체가 결정된다. 이때 스코프 체인은 전역 스코프를 가리키는 전역 객체와 함수 outerFunc의 스코프를 가리키는 함수 outerFunc의 활성 객체 (Activation object) 그리고 함수 자신의 스코프를 가리키는 활성 객체를 순차적으로 바인딩한다.

스코프 체인이 바인딩한 객체가 바로 렉시컬 스코프의 실체이다.
내부함수 innerFunc가 자신을 포함하고 있는 외부함수 outerFunc의 변수 x에 접근할 수 있는 것, 다시 말해
상위 스코프에 접근할 수 있는 것은 렉시컬 스코프의 레퍼런스를 차례대로 저장하고 있는 실행 컨텍스트의 스코프
체인을 자바스크립트 엔진이 검색하였기에 가능한 것이다.

1. innerFunc 함수 스코프(함수 자신의 스코프를 가리키는 활성 객체) 내에서 변수를 검색한다. 검색이 실패한다.
2. innerFunc 함수를 포함하는 외부 함수 outerFunc의 스코프(함수 outerFunc의 스코프를 가리키는 함수
outerFunc의 활성 객체)에서 변수 x를 검색 (검색 성공)

```javascript
function outerFunc() {
  var x = 10;
  var innerFunc = function () { console.log(x); };
  return innerFunc;
}

/**
 *  함수 outerFunc를 호출하면 내부 함수 innerFunc가 반환된다.
 *  그리고 함수 outerFunc의 실행 컨텍스트는 소멸한다.
 */
var inner = outerFunc();
inner(); // 10

```
함수 outerFunc는 내부함수 innerFunc를 반환하고 생을 마감했다. 즉, 함수 outerFunc는 실행된 이후 콜스택(실행 컨텍스트 스택)에서 제거되었으므로 함수 outerFunc의 변수 x 또한 더이상 유효하지 않게 되어 변수 x에 접근할 수 있는 방법은 달리 없어 보인다. 그러나 위 코드의 실행 결과는 변수 x의 값인 10이다. 이미 life-cycle이 종료되어 실행 컨텍스트 스택에서 제거된 함수 outerFunc의 지역변수 x가 다시 부활이라도 한 듯이 동작하고 있다. 뭔가 특별한 일이 일어나고 있는 것 같다.

이처럼 자신을 포함하고 있는 외부함수보다 내부함수가 더 오래 유지되는 경우, 외부 함수 밖에서 내부함수가 호출되더라도 외부함수의 지역 변수에 접근할 수 있는데 이러한 함수를 클로저(Closure)라고 부른다.

```
“A closure is the combination of a function and the lexical environment within which that function was declared.”
클로저는 함수와 그 함수가 선언됐을 때의 렉시컬 환경(Lexical environment)과의 조합이다.
```

클로저에 의해 참조되는 외부함수의 변수 즉 outerFunc 함수의 변수 x를 자유변수(Free variable)라고 부른다. 클로저라는 이름은 자유변수에 함수가 닫혀있다(closed)라는 의미로 의역하면 자유변수에 엮여있는 함수라는 뜻이다.

실행 컨텍스트의 관점에 설명하면, 내부함수가 유효한 상태에서 외부함수가 종료하여 외부함수의 실행 컨텍스트가 반환되어도, 외부함수 실행 컨텍스트 내의 활성 객체(Activation object)(변수, 함수 선언 등의 정보를 가지고 있다)는 내부함수에 의해 참조되는 한 유효하여 내부함수가 스코프 체인을 통해 참조할 수 있는 것을 의미한다.

즉 외부함수가 이미 반환되었어도 외부함수 내의 변수는 이를 필요로 하는 내부함수가 하나 이상 존재하는 경우 계속 유지된다. 이때 내부함수가 외부함수에 있는 변수의 복사본이 아니라 실제 변수에 접근한다는 것에 주의하여야 한다.

클로저는 자신이 생성될 때의 환경(Lexical environment)을 기억해야 하므로 메모리 차원에서 손해를 볼 수 있으니 남발하지는 말자




