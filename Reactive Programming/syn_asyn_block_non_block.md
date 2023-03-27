동기와 비동기는 프로세스 수행 순서 보장에 대한 메커니즘
블록과 논블록킹은 프로세스의 유휴 상태에 대한 개념

## 블록킹(Blocking)과 논블로킹(Non-Blocking)

- 프로세스의 제어권을 어떻게 처리하느냐에 따라 달라진다.
제어권이란 자신(함수)의 코드를 실행할 권리같은 것
제어권을 가진 함수는 자신의 코드를 끝까지 실행한 후, 자신을 호출한
함수에게 돌려준다.

### Blocking

- 블로킹은 A함수가 B함수를 호출하면, 제어권을 가진 A가 호출한 B 함수에게 넘겨주는 것이다.
A는 B에게 제어권을 넘겨줬기 때문에 B함수의 실행이 끝날 때까지 실행을 잠시 멈춘다.
B함수의 실행이 끝나면 자신을 호출한 A에게 제어권을 돌려준다.

### Non-Blocking

- 논블로킹은 A함수가 B함수를 호출해도 제어권을 그대로 자신이 가지고 있는 것이다.
이 경우네는 B함수가 실행되지만, 제어권을 A함수가 계속 가지고 있기 때문에 계속해서 자신의 코드를 실행한다.

## 동기(Synchronous)와 비동기(Asynchronous)

- 동기와 비동기의 차이는 호출되는 함수의 작업 완료 여부를 신경쓰는지의 차이다.

### Synchronous

- 동기는 A함수가 B함수를 호출한 뒤, B함수의 리턴값을 계속 확인하면서 관심을 갖는 것이다.

### Asynchronous

- 비동기는 A함수가 B함수를 호출한 뒤, B함수의 작업 완료 여부를 신경쓰지 않는 것이다. A함수가 B함수를 호출할 때
콜백 함수를 함께 전달해서, 함수 B의 작업이 완료되면 함께 보낸 콜백 함수를 실행한다.


이 개념들을 조합해 네가지 경우를 만들어 낼 수 있다!!!

## Sync-Blocking
    - A함수는 B함수의 리턴값에 관심을 갖는다.(동기)
    - A함수는 B함수를 호출하고 제어권을 넘겨준 다음, B함수의 실행을 완료할 때까지 기다린다.(블로킹)
    - ex) C나 JAVA의 코드 실행후 커맨드에서 입력을 기다리는 프로세스 (프로세스의 제어권을 넘기고 wait - 입력값에 따라 프로그램의 수행이 결정)

## Sync-NonBlocking
    - A함수가 B를 실행했지만 제어권을 넘겨주지 않음
    - 그러나 B의 결과값에 따라 A에게 영향을 주기때문에 관심을 가진다.
    - ex) Game 로딩창에서 로드되는 데이터 로드율.(데이터를 계속 물어봄. 제어권은 여전히 나에게 있어 화면에 로드율이 변화함)
    - 로드 완료 후 다음 코드가 실행

## Async-Nonblocking
    - A함수는 B함수의 리턴값을 신경쓰지 않고, 콜백함수를 보냄. (비동기)
    - 자신의 코드를 계속 실행(논블로킹)
    - B함수는 자신의 작업이 끝나면 A함수가 준 콜백 함수를 실행.
    - ex) Ajax요청, js 비동기 콜백

## Async-blocking
    - A함수는 B함수의 리턴값에 신경쓰지 않고, 콜백함수를 보낸다.(비동기)
    - 그런데 B 함수의 작업에 관심이 없음에도 불구하고, A함수는 B함수에게 제어권을 넘기기 때문에,
    - A함수는 자신과 관련이 없는 B함수의 작업이 끝날 때까지 기다려야한다. (블로킹)
    - 이 조합은 사용 X
