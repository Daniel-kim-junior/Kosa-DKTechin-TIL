# HTML

- 웹에서 의사소통할 때 문서의 골격을 제공하고 문서의 의미를 제공하고 문서를 어떻게 읽어야하는지
제공하는 문서

### HTML5
whatwg.org/   html5 표준 명세
caniuse.com   브라우저 사실적 표준확인 사이트

- Block Container -> Flow Content(Body에 포함될 수 있는 거의 모든 요소)
- Inline Container -> Phrasing Content(단락을 형성하는 컨텐츠)
- Metadata Content(콘텐츠와 문서를 구조 하는 요소를 의미 display: none)
- Heading Content(h1, h2, h3 ...)
- Sectioning Content(nav, article, aside, section, display: block)
- Embedded Content(멀티미디어 컨텐츠 inline, ex) img, iframe...)
- Interactive Content(사용자와 상호작용 하는 컨텐츠 inline ex) a, button, label...)
- Transparent content models(투명한 요소를 제거해도 부모와 자식 관계가 문법적으로 유효 해야함, ex) a, video, noscript ...)

ex) a태그는 Transparent 요소고 Transparent 요소 밑에는 어떤 요소가 와도 상관없다.
```
<p>
    <a>
        <div></div>
    </a>
</p>

```  
하지만 Transparent는 부모 요소의 콘텐츠 모델을 따라가고 p의 콘텐츠 모델은 phrasing이기 때문에
div - Flow Contents, palpable 요소를 담을 수 없다. 유효하지 않은 구조이다.


