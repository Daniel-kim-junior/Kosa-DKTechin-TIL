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
