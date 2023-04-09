# Comparable(compareTo)

- compareTo는 Object 메소드가 아니다

하지만 Object의 equals 메소드와 닮았다 (두 가지 성격만 빼면 거의 같다)

1. 단순 동치성 비교가 아닌 순서 비교 가능

2. 제네릭하다. (equals는 제네릭 지원 x)

Comparable을 구현했다는 것은 그 클래스의 인스턴스들에게 자연적인 순서(natural Order)가 있다는 것을 의미한다.

그래서 Comparable을 구현한 객체들의 배열은 다음처럼 손쉽게 정렬할 수 있다.

```
Arrays.sort(a);
```

검색, 극단값 계산, 자동 정렬되는 컬렉션 관리도 역시 쉽게 할 수 있다. 예컨데 다음 프로그램은 명령줄 인수들을 (중복 제거하고) 알파벳 순으로 출력한다.
String이 comparable을 구현한 덕분이다.

```
public class WordList {
  public static void main(String args[]) {
    Set<String> s = new TreeSet<>();
    Collections.addAll(s, args);
    System.out.println(s);
  }
}
```

Comparable을 구현하여 이 인터페이스를 활용하는 수 많은 제네릭 알고리즘과 컬렉션의 힘을 빌리자
자바 플랫폼 라이브러리의 모든 값 클래스와 열거 타입이 Comparable을 구현했다.

알파벳, 숫자, 연대 같이 순서가 명확한 값 클래스를 작성한다면 반드시 Comparable 인터페이스를 구현하자

public interface Comparable<T> {
int compareTo(T t);
}

### compareTo 메서드의 일반 규약

1. 이 객체와 주어진 객체의 순서를 비교한다. 이 객체가 주어진 객체보다 작으면 음의 정수를, 같으면 0을
   크면 양의 정수를 반환한다. 이 객체와 비교할 수 없는 타입의 객체가 주어지면 ClassCastException을 던진다.

다음 설명에서 sgn(표현식) 표기는 수학에서 말하는 부호 함수(signum function)을 말한다.
표현식의 값이 음수, 0, 양수일때 -1, 0, 1을 반환하도록 정의했다.

- Comparable을 구현한 클래스는 모든 x, y에 대해 sgn(x.compareTo(y)) == -sgn(y.compareTo(x)) 여야 한다.
  (따라서 x.compareTo(y)는 y.compareTo(x)가 예외를 던질 때에 한해 예외를 던져야 한다.)

- Comparable을 구현한 클래스는 추이성을 보장해야 한다. 즉, (x.compareTo(y) > 0 && y.compareTo(z) > 0 이면 x.compareTo(z) > 0 이다

- Comparable을 구현한 클래스 모든 z에 대해 x.compareTo(y) == 0 이면
  sgn(x.compareTo(z)) == sgn(y.compareTo(z)) 다
