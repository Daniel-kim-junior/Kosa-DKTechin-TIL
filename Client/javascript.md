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




# 이벤트 전파(Event Propagation)

- 자바스크립트 대부분의 이벤트는 캡쳐링 단계와 버블링 단계를 가진다.
- 아래와 같은 HTML에서 LInk를 클릭하면 DOM은 click event를 발생시키는데,
- 이벤트는 정확히 target element에서 발생하지 않는다.
- 세가지의 단계를 거치는데

```html
<body>
   <section>
     <p>paragraph<a>Link</a></p>
   </section>
</body>
```

### 1. 캡쳐링 단계

Link를 클릭할때, DOM 트리의 꼭대기인 Document root에서 이벤트가 발생한다. 

Document root 부터 캡쳐링이 발생하면서 target element 까지 내려온다.

그 때 이벤트는 target  element의 모든 부모 요소를 지나간다.

### 2. Target 단계

이벤트가 target에 도착하면, 이벤트가 바로 처리되는  target 단계가 시작된다. 

이벤트 리스너는 이벤트 발생을 기다린다. 그리고 이벤트가 발생하자마자 콜백함수를 실행한다.

```jsx
document.querySelector("a").addEventListener("click", () => {
  alert("Link 클릭!");
});
```

### 3. 버블링 단계

그리고 다시 이벤트가 target부터 document root로 올라가며 캡쳐링 단계처럼 모든 부모 요소를 지나간다.

## 이벤트 버블링

이벤트 버블링이란 버블링단계에서 상위  부모요소들을 거쳐가며 이벤트가 전파되는 현상을 말한다.

다음과 nav 메뉴가 있다. .nav__menu에 랜덤한 배경색을 넣는 이벤트를 등록한다.

```html
<nav>
  <ul class="nav__menus">
    <li>
      <a class="nav__menu" href="#">menu1</a>
    </li>
    <li>
      <a class="nav__menu" href="#">menu2</a>
    </li>
    <li>
      <a class="nav__menu" href="#">menu3</a>
    </li>
    <li>
      <a>SignUp</a>
    </li>
  </ul>
</nav>
```

```jsx
const getRandom = (min, max) => {
  return Math.floor(Math.random() * (max - min + 1) + min);
};

const randomColor = () => {
  return `rgb(${getRandom(0, 255)},${getRandom(0, 255)},${getRandom(0, 255)})`;
};
```

그리고 첫번째 링크(menu1)인 ***.nav__menu*** 에 이벤트를 등록한다.

```jsx
document.querySelector(".nav__menu").addEventListener("click", function (e) {
  this.style.backgroundColor = randomColor();
});
```

이벤트핸들러 안에서 `this` 는 이벤트 핸들러가 연결된 요소를 가리킨다. 여기서는 ***document.querySelector(“.nav__menu”)*** 이다.

다음에 상위 태그인 nav_menus에 이벤트 핸들러를 등록하면

```jsx
document.querySelector(".nav__menus").addEventListener("click", function (e) {
        this.style.backgroundColor = randomColor();
 });
```

![Animation.gif](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0731bc0f-8e82-49fc-b699-d00da65d90cf/Animation.gif)

이런식으로 이벤트 전파가 된다.

이러한 버블링 현상을 이용해 상위 태그에 이벤트 등록을 한번만 해놓으면

그 하위 태그에서 그에 해당하는 이벤트가 발생했을 때 상위 태그까지 전파가 되므로 

똑같은 이벤트들을 발생시킬 수 있다.

## target, currentTarget

event.target

```jsx
document.querySelector(".nav__menu").addEventListener("click", function (e) {
  this.style.backgroundColor = randomColor();
  console.log(e.target);
});
```

위의 예시에서 각 이벤트리스너에 ***e.target*** 을 콘솔로 찍어보면 클릭한 DOM의 정보가 나온다. `target`은 이벤트가 처음 발생한 곳. 즉 클릭이 발생한 곳이기 때문이다.

event.currentTarget

```jsx
document.querySelector(".nav__menu").addEventListener("click", function (e) {
  this.style.backgroundColor = randomColor();
  console.log(e.currentTarget);
});

이벤트핸들러에서 `currentTarget` 은 `this` 와 같아서 현재 실행중인 handler가 할당된 요소이다.
```

### **event.stopPropagation()**

---

`event.stopPropagation()` 으로 이벤트 전파를 막을수있다.

```jsx
document.querySelector(".nav__menu").addEventListener("click", function (e) {
  this.style.backgroundColor = randomColor();
  e.stopPropagation();
});
```


## 이벤트 캡쳐링

캡쳐링 단계에서 이벤트를 캐치하려면 ***addEventListener*** 의 세번째 파라미터를 정의할 수 있다. 

기본적으로 ***default***는 ***false*** 인데, 이때 핸들러는 버블링 단계로 설정된다. 

이벤트 캡쳐링은 흔히 사용 되지는 않지만 옵션을 `true` 로 설정하면 이벤트 버블링과 반대 방향으로 탐색한다.

```jsx
document.querySelector(".nav__menu").addEventListener(
  "click",
  function (e) {
    this.style.backgroundColor = randomColor();
    console.log(e.currentTarget.className);
  },
  true
);

document.querySelector(".nav__menus").addEventListener(
  "click",
  function (e) {
    this.style.backgroundColor = randomColor();
    console.log(e.currentTarget.className);
  },
  true
);

document.querySelector(".nav").addEventListener(
  "click",
  function (e) {
    this.style.backgroundColor = randomColor();
    console.log(e.currentTarget.className);
  },
  true
);
```

콘솔로 찍으면 nav → .nav__menus → .nav__menu 순으로 콘솔이 찍힌다.

### **이벤트 위임(Event Delegation)**

---

이벤트 위임이란 하위 요소마다 이벤트를 붙이지 않고 상위 요소에서 하위 요소의 이벤트들을 제어하는 방식을 말한다.

예를들어 위의 *nav* 예시에서, 메뉴를 눌렀을때 메뉴에 맞는 섹션으로 이동하는 스크롤 기능을 추가한다고 하자.

```html
<nav class="nav">
  <ul class="nav__menus">
    <li class="nav__item">
      <a class="nav__menu" href="#menu1">menu1</a>
    </li>
    <li class="nav__item">
      <a class="nav__menu" href="#menu2">menu2</a>
    </li>
    <li class="nav__item">
      <a class="nav__menu" href="#menu3">menu3</a>
    </li>
    <li class="nav__item">
      <a>SignUp</a>
    </li>
  </ul>
</nav>

<section id="menu1">...</section>
<section id="menu2">...</section>
<section id="menu3">...</section>
```

```jsx
document.querySelectorAll(".nav__menu").forEach(function (el) {
  el.addEventListener("click", function (e) {
    e.preventDefault();
    const id = this.getAttribute("href");
    document.querySelector(id).scrollIntoView({
      behavior: "smooth",
    });
  });
});
```

이런식으로 forEach를 써서 처리해야하는데 

문제는 element마다 이벤트 핸들러를 추가하므로 여기서는 element가 세개지만 만약 리스트 아이템이 많아진다면 비효율적이다. 이 번거로운 작업을 해결할 수 있는 방법이 바로 이벤트 위임이다.

먼저 **공통의 부모요소에 이벤트리스너를 추가**
하고 이벤트리스너 안에서 어떤 요소가 이벤트를 발생시켰는지 확인한다.

```
document.querySelector(".nav__menus").addEventListener("click", function (e) {
  e.preventDefault();
  console.log(e.target);
  const id = e.target.getAttribute("href");
  document.querySelector(id).scrollIntoView({
    behavior: "smooth",
  });
});
```

# 실행 컨텍스트 (execution context)

소스코드(ECMAScript code)를 4가지 타입으로 구분한다.

4가지 타입의 소스코드는 실행 컨텍스트를 생성한다.

1. 전역 코드
2. 함수 코드
3. eval 코드
4. 모듈 코드

전역 코드

전역에 존재하는 소스코드를 말한다. 전역에 정의된 함수, 클래스 등의 내부 코드는 포함되지 않는다.

전역 코드는 전역 변수를 관리하기 위해 최상위 스코프인 전역 스코프를 생성해야 한다.

var 키워드로 선언된 전역 변수와 함수 선언문으로 정의된 전역 함수를

 전역 객체의 프로퍼티와 메서드로 바인딩하고 참조하기 위해 전역 객체와 연결되어야 한다.

브라우저에서는(window) node(global)

이를 위해 전역 코드가 평가되면 

전역 실행 컨텍스트가 생성된다.

**함수 코드**

함수 내부에 존재하는 소스코드를 말한다. 

함수 내부에 중첩된 함수, 클래스 등의 내부 코드는 포함되지 않는다.

**Eval 코드**

빌트인 전역 함수인 eval 함수에 인수로 전달되어 실행되는 소스코드를 말한다. 

strict mode에서 자신만의 독자적인 스코프를 생성하며

이를 위해 eval 코드가 평가되면 

eval 실행 컨텍스트가 생성된다.

**모듈 코드**

모듈 내부에 존재하는 소스코드를 말한다. 모듈 내부의 함수, 클래스 등의 내부 코드는 포함되지 않는다.

모듈 코드는 독자적 스코프를 생성

**소스코드의 평가와 실행**

모든 소스코드는 실행에 앞서 평가 과정을 거치며 코드를 실행하기 위한 준비를 한다.

평가 과정에서 실행 컨텍스트를 생성

변수, 함수 등의 선언문만 먼저 실행하여 실행된 변수나 함수 식별자를 키로 

실행 컨텍스트가 관리하는 스코프(렉시컬 환경의 환경 레코드)에 등록

소스코드 평가 과정이 끝나면 선언문을 제외한 소스코드가 순차적으로 실행되기 시작한다.

런타임이 시작되면 변수나 함수의 참조를 실행하고 실행 컨텍스트가 관리하는 스코프에서 검색해서 취득한다.

변수 값의 변경 등 소스코드의 실행 결과는 다시 실행 컨텍스트가 관리하는 스코프에 등록한다.

```jsx
var x;
x = 1;
```

다음 코드는 2가지 과정으로 나누어 처리

1. 소스코드 평가 과정에서 변수 선언문 var  x 를 먼저 실행

  2. 실행 컨텍스트가 관리하는 스코프에 등록되고     undefined로 초기화

소스코드 실행 과정 돌입

변수 선언문 var x;는 소스 평가 과정에서 이미 실행이 완료

소스코드 실행 과정에서는 변수 할당문 x = 1; 만 실행

이때 x 변수에 값을 할당하려면 먼저 x 변수가 선언된 변수인지 확인해야 한다.

이를 위해 실행 컨텍스트가 관리하는 스코프에 x 변수가 등록되어 있는 지 확인한다.

x 변수가 선언된 변수인지 확인한다. 만약 x변수가 실행 컨텍스트가 관리하는 스코프에 등록되어 있다면 x 변수는 선언된 변수, 즉 소스코드 평가 과정에서 선언문이 실행되어 등록된 변수다.

x 변수는 선언된 변수라면 값을 할당하고 할당 결과를 실행 컨텍스트에 등록하여 관리한다.

```jsx
// 전역 변수 선언
const x = 1;
const y = 2;

// 함수 정의
function foo(a) {
// 지역 변수 선언
  

// 130 // 103
  console.log(x + y + a);
}

// 함수 호출
foo(100);

// 메서드 호출

console.log(x + y); // 3

```

**전역 코드 평가** - 실행하기에 앞서 전역 코드 평가 과정을 거친다 소스 평가 과정에서는 선언문만 먼저 실행 한다. 결과적으로 전역변수와 전역 함수가 실행 컨텍스트가 관리하는 전역 스코프에 등록 된다. 
이 때 var 키워드로 선언된 전역 변수와 함수 선언문으로 정의된 전역 함수는 전역 객체의 프로퍼티와 메서드가 된다.

**전역 코드 실행** - 전역 코드 평가 과정이 끝나면 런타임이 시작되어 전역 코드가 순차적으로 실행 이 때 전역 변수에 값이 할당되고 함수가 호출된다. 함수가 호출되면 순차적으로 실행되던 전역 코드의 실행을 일시 중단하고 코드 실행 순서를 변경하여 함수 내부로 진입한다.

**함수 코드 평가** - 함수 호출에 의해 코드 실행 순서가 변경되어 함수 내부로 진입하면 함수 내부의 문들을 실행하기에 앞서 함수 코드 평가 과정을 거치며 함수 코드를 실행하기 위한 준비를 한다. 이 때 매개변수와 지역 변수 선언문이 먼저 실행 되고, 그 결과 생성된 매개변수와 지역 변수가 실행 컨텍스트가 관리하는 지역 스코프에 등록된다. 또한 함수 내부에서 지역 변수처럼 사용할 수 있는 arguments 객체가 생성되어 지역 스코프에 등록되고 this 바인딩도 결정된다.

## 함수 코드 실행

1. console.log 메서드를 호출 하기 위해 먼저 식별자인 console을 스코프 체인을 통해 검색한다.
2. 이를 위해 함수 코드의 지역 스코프는 상위 스코프인 전역 스코프와 연결 되어야 한다.
3. console 식별자는 스코프 체인에 등록되어 있지 않고 전역 객체에 프로퍼티로 존재한다.
4. 전역 객체의 프로퍼티가 전역 변수처럼 전역 스코프를 통해 검색 가능해야 한다.
5. log 프로퍼티를 console 객체의 프로토타입 체인을 통해 검색한다.
6. console.log 메서드에 인수 표현식 a + x + y 가 평가 된다.
7. a, x , y 식별자는 스코프 체인을 통해 검색한다.
8. console.log 메서드의 실행이 종료되면 함수 코드 실행 과정이 종료된다.
9. 전역 코드 실행을 계속 한다.

이처럼 코드가 실행되려면 스코프를 구분하여 식별자와 바인딩 된 값이 관리되어야 한다.

그리고 중첩 관계에 의해 스코프 체인을 형성하여 식별자를 검색할 수 있어야 하고,

전역 객체의 프로퍼티도 전역 변수처럼 검색 할 수 있어야 하는데

이를 관리하는 것이 실행 컨텍스트이다.

식별자와 스코프는 실행 컨텍스트의 렉시컬 환경으로 관리하고 

코드 실행 순서는 실행 컨텍스트 스택으로 관리한다.

## 실행 컨텍스트 스택(콜 스택)

```jsx
const x = 1;

function foo() {
    const y = 2;
  function bar () {
        const z = 3;
    console.log(x + y + z);
  }
  bar();
}

foo();

```

### 코드의 제어권 이관

전역 실행 컨텍스트 → foo 함수 실행 컨텍스트 → bar함수 실행 컨텍스트

← bar 함수 실행 컨텍스트 ← foo 함수 실행 컨텍스트 ← 전역 실행 컨텍스트 

실행 컨텍스트 스택 → 실행 순서

렉시컬 환경 → 스코프와 식별자를 관리

```jsx
const x = 1;

function foo () {
    const x = 2;
  const y = 4;
    function bar() {
    const y = 2;

      return x + y;
    }
}

bar();

정적 스코프 (렉시컬환경)
console.log(foo());

Global Lexical Environment
x         1
foo      <function object>

         | 스코프 체인
foo Lexical Environment
x         4
y         2
```

렉시컬 환경은 키와 값을 갖는 객체 형태의 스코프(전역, 함수, 블록 스코프)를 생성하여

식별자를 키로 등록하고 식별자에 바인딩된 값을 관리한다.

즉 렉시컬 환경은 스코프를 구분하여 식별자를 등록하고 관리하는 저장소 역할을 한다.

실행 컨텍스트에는

LexicalEnvironment와  VariableEnvironment 가 공존하는데

생성 초기에는 하나의 동일한 렉시컬 환경을 참조한다.

이후 몇가지 상황을 만나게 되면 VariableEnvironment가 새로운 렉시컬 환경을 생성하고,

두개의 참조값이 달라지는 경우도 있다.

(Strict mode, eval 코드, try/Catch)

렉시컬 환경은 두개의 컴포넌트로 구성된다.

1. 환경 레코드

스코프에 포함된 식별자를 등록하고 등록된 식별자에 바인딩 된 값을 관리하는 저장소

  2. 외부 렉시컬 환경에 대한 참조

상위 스코프로 연결된 단방향 링크드 리스트 스코프 체인

# 화살표 함수와 일반함수의 가장 큰 차이

자바스크립트의 경우 함수 호출 방식에 의해 [this](https://poiemaweb.com/js-this)에 바인딩할 어떤 객체가 동적으로 결정된다. 

다시 말해, 함수를 선언할 때 this에 바인딩할 객체가 정적으로 결정되는 것이 아니고, 

**함수를 호출할 때 함수가 어떻게 호출되었는지에 따라** this에 바인딩할 객체가 동적으로 결정된다.

콜백 함수 내부의 this는 전역 객체 window를 가리킨다.

```jsx
var a = 10;

function Prefixer(prefix) {
    var a = 20;
  this.prefix = prefix;
    
}

Prefixer.prototype.prefixArray = function (arr) {
  // (A)
  return arr.map(function (x) {
    return this.prefix + ' ' + x; // (B)
  });
};

let pre = new Prefixer('Hi');
console.log(pre.prefixArray(['Lee', 'Kim']));
```

(A) 지점에서의 this는 생성자 함수 Prefixer가 생성한 객체, 즉 생성자 함수의 인스턴스(위 예제의 경우 pre)이다.

(B) 지점에서 사용한 this는 아마도 생성자 함수 Prefixer가 생성한 객체(위 예제의 경우 pre)일 것으로 기대하였겠지만, 이곳에서 this는 전역 객체 window를 가리킨다. 이는 생성자 함수와 객체의 메소드를 제외한 모든 함수(내부 함수, 콜백 함수 포함) 내부의 this는 전역 객체를 가리키기 때문이다.

콜백 함수 내부의 this가 메소드를 호출한 객체(생성자 함수의 인스턴스)를 가리키게 하려면 아래의 3가지 방법이 있다.

```jsx
// Solution 1: that = this
function Prefixer(prefix) {
  this.prefix = prefix;
}

Prefixer.prototype.prefixArray = function (arr) {
  let that = this;  // this: Prefixer 생성자 함수의 인스턴스
  return arr.map(function (x) {
    return that.prefix + ' ' + x;
  });
};

let pre = new Prefixer('Hi');
console.log(pre.prefixArray(['Lee', 'Kim']));
```

```jsx
// Solution 2: map(func, this)
function Prefixer(prefix) {
  this.prefix = prefix;
}

Prefixer.prototype.prefixArray = function (arr) {
  return arr.map(function (x) {
    return this.prefix + ' ' + x;
  }, this); // this: Prefixer 생성자 함수의 인스턴스
};

var pre = new Prefixer('Hi');
console.log(pre.prefixArray(['Lee', 'Kim']));
```

ES5에 추가된 [Function.prototype.bind()](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Function/bind)로 this를 바인딩

```jsx
// Solution 3: bind(this)
function Prefixer(prefix) {
  this.prefix = prefix;
}

Prefixer.prototype.prefixArray = function (arr) {
  return arr.map(function (x) {
    return this.prefix + ' ' + x;
  }.bind(this)); // this: Prefixer 생성자 함수의 인스턴스
};

var pre = new Prefixer('Hi');
console.log(pre.prefixArray(['Lee', 'Kim']));
```

## 화살표 함수의 this

일반 함수는 함수를 선언할 때 this에 바인딩할 객체가 정적으로 결정되는 것이 아니고, 함수를 호출할 때 함수가 어떻게 호출되었는지에 따라 this에 바인딩할 객체가 동적으로 결정된다고 하였다.

화살표 함수는 함수를 선언할 때 this에 바인딩할 객체가 정적으로 결정된다. 동적으로 결정되는 일반 함수와는 달리 **화살표 함수의 this 언제나 상위 스코프의 this를 가리킨다.** 이를 **Lexical this**라 한다. 

화살표 함수의 this 바인딩 객체 결정 방식은 함수의 상위 스코프를 결정하는 방식인 [렉시컬 스코프](https://poiemaweb.com/js-scope#7-%EB%A0%89%EC%8B%9C%EC%BB%AC-%EC%8A%A4%EC%BD%94%ED%94%84)
와 유사하다.(선언적으로 결정)

```jsx
function Prefixer(prefix) {
  t*his.prefix* = prefix;
}

Prefixer.prototype.prefixArray = function (arr) {
  // this는 상위 스코프인 prefixArray 메소드 내의 this를 가리킨다.
  return arr.map(x => `${this.prefix}  ${x}`);
};

const pre = new Prefixer('Hi');
console.log(pre.prefixArray(['Lee', 'Kim']));
```

화살표 함수는 call, apply, bind 메소드를 사용하여 this를 변경할 수 없다.

```
window.x = 1;
const normal = function () { return this.x; };
const arrow = () => this.x;

console.log(normal.call({ x: 10 })); // 10
console.log(arrow.call({ x: 10 }));  // 1
```

## 화살표 함수를 사용해서는 안되는 경우

## **메소드**

화살표 함수로 메소드를 정의하는 것은 피해야 한다. 화살표 함수로 메소드를 정의하여 보자.

```
// Bad
const person = {
  name: 'Lee',
  sayHi: () => console.log(`Hi ${this.name}`)
};

person.sayHi(); // Hi undefined
```

위 예제의 경우, 메소드로 정의한 화살표 함수 내부의 this는 메소드를 소유한 객체, 즉 메소드를 호출한 객체를 가리키지 않고 상위 컨텍스트인 전역 객체 window를 가리킨다. 따라서 화살표 함수로 메소드를 정의하는 것은 바람직하지 않다.

```jsx
/ Good
const person = {
  name: 'Lee',
  sayHi() { // === sayHi: function() {
    console.log(`Hi ${this.name}`);
  }
};

person.sayHi(); // Hi Lee
```

메소드 축약 표현을 사용하도록 하자

## ****prototype****

화살표 함수로 정의된 메소드를 prototype에 할당하는 경우

```
// Bad
const person = {
  name: 'Lee',
};

Object.prototype.sayHi = () => console.log(`Hi ${this.name}`);

person.sayHi(); // Hi undefined

```

화살표 함수로 객체의 메소드를 정의하였을 때와 같은 문제가 발생한다. 따라서 prototype에 메소드를 할당하는 경우, 일반 함수를 할당한다.

```jsx
// Good
const person = {
name: 'Lee',
};

Object.prototype.sayHi = function() {
console.log(`Hi ${this.name}`);
};

person.sayHi(); // Hi Lee
```

## **생성자 함수**

화살표 함수는 생성자 함수로 사용할 수 없다. 생성자 함수는 prototype 프로퍼티를 가지며 prototype 프로퍼티가 가리키는 프로토타입 객체의 constructor를 사용한다. 하지만 화살표 함수는 prototype 프로퍼티를 가지고 있지 않다.

```
const Foo = () => {};

// 화살표 함수는 prototype 프로퍼티가 없다
console.log(Foo.hasOwnProperty('prototype')); // false

const foo = new Foo(); // TypeError: Foo is not a constructor
```

## **4.4 addEventListener 함수의 콜백 함수**

addEventListener 함수의 콜백 함수를 화살표 함수로 정의하면 this가 상위 컨택스트인 전역 객체 window를 가리킨다.

```
// Bad
var button = document.getElementById('myButton');

button.addEventListener('click', () => {
  console.log(this === window); // => true
  this.innerHTML = 'Clicked button';
});

```

따라서 addEventListener 함수의 콜백 함수 내에서 this를 사용하는 경우, function 키워드로 정의한 일반 함수를 사용하여야 한다. 일반 함수로 정의된 addEventListener 함수의 콜백 함수 내부의 [this](https://poiemaweb.com/js-event#43-dom-level-2-event-listener)는 이벤트 리스너에 바인딩된 요소(currentTarget)를 가리킨다.

```
// Good
var button = document.getElementById('myButton');

button.addEventListener('click', function() {
  console.log(this === button); // => true
  this.innerHTML = 'Clicked button';
});
```
