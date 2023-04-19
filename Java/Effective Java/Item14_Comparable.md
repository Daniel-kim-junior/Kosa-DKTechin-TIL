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

- 이번 권고가 필수는 아니지만 꼭 지키는 것이 좋다. (x.compareTo(y)) == 0) == (x.equals(y)) 여야 한다.
Comparable을 구현하고 이 권고를 지키지 않는 모든 클래스는 그 사실을 명시해야 한다. 다음과 같이 명시하면 적당할 것이다.

"주의 : 이 클래스의 순서는 equals 메서드와 일관되지 않다." (equals를 Override 하지 않았다.)

수학적인 이야기라 해서 겁먹을 것 없다. equals 규약(아이템 10)이 그랬던 것처럼, 생각보다 복잡하지는 않다.

모든 객체에 대해 전역 동치관계를 부여하는 equals 메서드와 달리, compareTo는 타입이 다른 객체를 신경 쓰지 않아도 된다.

타입이 다른 객체가 주어지면 간단히 ClassCastException을 던져도 되며, 대부분 그렇게 한다.

물론, 이 규약에서는 다른 타입 사이의 비교도 허용하는데, 보통은 비교할 객체들이 구현한 공통 인터페이스를 매개로 이루어 진다.
(다형성)

hashCode 규약을 지키지 못하면 해시를 사용하는 클래스와 어울리지 못하듯, compareTo 규약을 지키지 못하면 비교를 활용하는 클래스와 어울리지 못한다.

비교를 활용하는 클래스의 예로는 정렬된 컬렉션인 TreeSet과 TreeMap, 검색과 정렬 알고리즘을 활용하는 유틸리티 클래스인 Collections와 Arrays가 있다.

이쯤에서 compareTo 규약을 살펴본다.

1. 두 객체 참조의 순서를 바꿔 비교해도 예상한 결과가 나와야 한다
  - 첫 번째 객체보다 두 번째 객체가 작으면, 두 번째보다 첫번째가 더 커야 한다.
  - 첫 번째가 두 번째와 크기가 같다면, 두 번째는 첫 번째와 같아야 한다.
  - 첫 번째가 두 번째보다 크면, 두 번째는 첫 번째보다 작아야 한다.

2. 두 번째 규약은 첫 번째가 두 번째보다 크고 두 번째가 세 번째보다 크면, 첫 번째는 세 번째보다 커야 한다.

3. 마지막 규약은 크기가 같은 객체들 끼리는 어떤 객체와 비교하더라도 항상 같아야 한다

이상의 세 규약은 compareTo 메서드로 수행하는 동치성 검사도 equals 규약과 똑같이 반사성, 대칭성, 추이성을 충족해야 한다.

그래서 주의사항도 똑같다. 기존 클래스를 확장한 구체 클래스에서 새로운 값 컴포넌트를 추가 했다면 compareTo 규약을 지킬 방법이 없다.

객체 지향적 추상화의 이점을 포기할 생각이 아니라면 말이다. 우회법도 같다. Comparable을 구현한 클래스를 확장해 값 컴포넌트를 추가하고 싶다면,

확장하는 대신에 독립된 클래스를 만들고, 이 클래스에 원래 클래스의 인스턴스를 가리키는 필드를 두자. 그런 다음 내부 인스턴스를 반환하는 '뷰' 메서드를 제공하면 된다.

이렇게 하면 바깥 클래스에 우리가 원하는 compareTo 메서드를 구현해 넣을 수 있다. 클라이언트는 필요에 따라 바깥 클래스의 인스턴스를 필드 안에 담긴 원래 클래스의 인스턴스로 다룰 수도 있다.

compareTo의 마지막 규약은 필수는 아니지만 꼭 지켜야 한다. 마지막 규약은 간단히 말하면 compareTo로 진행한 동치성 테스트의 결과가 equals와 같아야 한다는 것이다.

이를 지키면 compareTo로 줄지은 순서와 equals의 결과가 일관되게 된다. compareTo의 순서와 equals의 결과가 일관되지 않은 클래스도 여전히 동작은 한다. 단 이 클래스의 객체를 정렬된 컬렉션에 넣으면

해당 컬렉션이 구현한 인터페이스(Collection, Set, 혹은 Map)에 정의된 동작과 엇박자를 낼 것이다. 이 인터페이스들은 equals 메서드의 규약을 따른다고 되어 있지만, 놀랍게도 정렬된 컬렉션들은

동치성을 비교할 때 equals 대신 compareTo를 사용하기 때문이다. 아주 큰 문제는 아니지만 주의해야 한다.

compareTo와 equals가 동치되지 않는 BigDecimal 클래스를 예로 생각해보자. 빈 HashSet 인스턴스를 생성한 다음 new BigDecimal("1.0")과 new BigDecimal("1.00")을 차례로 추가한다.

이 두 BigDecimal은 equals 메소드로 비교하면 서로 다르기 때문에 HashSet은 원소를 두 개 가지게 된다. 하지만 HashSet 대신 TreeSet을 사용하게 되면 원소를 하나만 갖게 된다.

compareTo 메서드로 비교하면 두 BigDecimal 인스턴스가 똑같기 때문이다.

compareTo 메서드 작성 요령은 eqauls와 비슷하다. 몇 가지 차이점을 주의하자

Comparable은 타입을 인수로 받는 제네릭 인터페이스이므로 compareTo 메서드의 인수 타입은 컴파일 타입에 정해진다. 입력 인수 타입을 확인하거나 형 변환할 필요가 없다는 뜻이다.

인수의 타입이 잘못됐다면 컴파일 자체가 되지 않는다. 또한 null을 인수로 넣어 호출하면 NullPointerException을 던져야 한다. 물론 실제로도 인수(이 경우 null)의 멤버에 접근하려는 순간 이 예외가 던져질 것이다.

compareTo 메서드는 각 필드가 동치인지를 비교하는 게 아니라 그 순서를 비교한다. 객체 참조 필드를 비교하려면 compareTo 메서드를 재귀적으로 호출한다. Comparable을 구현하지 않은 필드나 표준이 아닌 순서로 비교해야 한다면 비교자(Comparator)를 대신 사용한다. 비교자는 직접 만들거나 자바가 제공하는 것 중에 골라 쓰면 된다.

```
객체 참조 필드가 하나뿐인 비교자
public final class CaseInsensitiveString implements Comparable<CaseInsensitiveString> {
  public int compareTo(CaseInsensitivieString cis) {
    return String.CASE_INSENSITIVE_ORDER.compare(s, cis.s);
  }
}
```

CaseInsensitiveString의 참조는 CaseInsensitiveString 참조와만 비교할 수 있다는 뜻으로, Comparable을 구현할 때 일반적으로 따르는 패턴이다.

관계 연산자와 < 와 >를 사용하는 이전 방식은 거추장스럽고 오류를 우발한다.
compare를 이용해서 값을 비교하자.

클래스에 핵심 필드가 여러 개라면 어느 것을 먼저 비교하느냐가 중요해진다. 가장 핵심적인 필드부터 비교해나가자. 비교 결과가 0이 아니라면, 순서가 결정 된다면 거기서 종료된다.

그 결과를 곧장 반환하고 가장 핵심이 되는 필드가 똑같다면, 똑같지 않은 필드를 찾을 때까지 그 다음으로 중요한 필드를 비교해나간다. 

```
public int compareTo(PhoneNumber pn) {
  int result = Short.compare(areaCode, pn.areaCode); // 가장 중요한 필드
  if(result == 0) {
    result = Short.compare(prefix, pn.prefix); // 두 번째로 중요한 필드
    if(result == 0) {
      result = Short.compare(lineNum, pn.lineNum); // 세 번째로 중요한 필드
    }
  } 
  return result;
}
```

자바 8에서 Comparator 인터페이스가 일련의 비교자 생성 메서드(comparator construction method)와 팀을 꾸려 메서드 연쇄 방식으로 비교자를 생성 할 수 있게 되었다.

그리고 이 비교자들은 Comparable 인터페이스가 원하는 compareTo 메서드를 구현하는데 멋지게 활용할 수 있다.

하지만 약간의 성능 저하가 뒤따른다.

```
private static final Comparator<PhoneNumber> COMPARATOR = 
          comparingInt((PhoneNumber pn) -> pn.areaCode)
          .thenComparingInt(pn -> pn.prefix)
          .thenComparingInt(pn -> pn.lineNum);

public int compareTo(PhoneNumber pn) {
  return COMPARATOR.compare(this, pn);
}
```

그 첫 번째인 comaringInt는 객체 참조를 int 타입 키에 매핑하는 키 추출 함수(key extractor function)를 인수로 받아, 그 키를 기준으로 순서를 정하는 비교자를 반환하는

정적 메서드다. 앞의 예에서 comparingInt는 람다(lambda)를 인수로 받으며, 이 람다는 PhoneNumber에서 추출한 지역 코드를 기준으로 전화번호의 순서를 정하는 Comparator<PhoneNumber>를 반환한다.

이 람다에서 입력 인수의 타입(PhoneNumber pn)을 명시한 점에 주목하자. 자바의 타입 추론 능력이 이 상황에서 타입을 알아낼 만큼 강력하지 않기 때문에 프로그램이 컴파일되도록 해준 것

두 전화번호의 지역 코드가 같을 수 있으니 비교 방식을 더 다듬어야 한다.

이 일은 두 번째 비교자 생성 메서드인 thenComparingInt가 수행한다. Comparator의 인스턴스 메서드로, int키 추출자 함수를 입력 받아 다시 비교자를 반환한다(이 비교자는 첫 번째 비교자를 적용한 다음 새로 추출한 키로 추가 비교를 수행한다) 원하는 만큼 연달아 호출할 수 있다.

두 번째 부터는 타입을 명시하지 않았는데 자바의 타입 추론 능력이 이 정도는 추론할 수 있기 때문

Comparator는 수많은 보조 생성 메서드들로 이루어져 있고, long과 double용으로는 comparingInt와 thenComparingInt의 변형 메서드를 준비했다. short처럼 더 작은 정수 타입에는

int용 버전을 사용하면 된다. 마찬가지로 float은 double용을 이용해 수행한다. 이런 식으로 자바의 숫자용 기본 타입을 모두 커버한다.

객체 참조용 비교자 생성 메서드도 준비되어 있다. 우선, comparing이라는 정적 메서드 2개가 다중 정의 되어 있다.

첫 번째는 키 추출자를 받아 그 키의 자연적 순서를 사용한다. 두 번째는 키 추출자 하나와 추출된 키를 비교할 비교자까지 총 2개의 인수를 받는다. 

또한 thenComparing이란 인스턴스 메서드가 3개 다중정의되어 있다. 
첫 번째는 비교자 하나만 인수로 받아 그 비교자로 부차 순서를 정한다. 두 번째는 키 추출자를 인수로 받아 그 키의 자연적 순서로 보조 순서를 정한다.
마지막 세 번째는 키 추출자 하나와 추출된 키를 비교할 비교자까지 총 2개의 인수를 받는다.

이따금 '값의 차'를 기준으로 첫 번째 값이 두 번째 값보다 작으면 음수를, 두 값이 같으면 0을, 첫 번째 값이 크면 양수를 반환하는 compareTo나 compare 메서드와 마주할 것이다.

```
static Comparator<Object> hashCodeOrder = new Comparator<>() {
  public int compare(Object o1, Object o2) {
    return o1.hashCode() - o2.hashCode();
  }
}

```

이 방식은 사용하면 안 된다. 이 방식은 정수 오버플로를 일으키거나 IEEE 754 부동소수점 계산 방식에 따른 오류를 낼 수 있다.

그렇다고 이번 아이템에서 설명한 방법대로 구현한 코드보다 월등히 빠르지도 않다.

## 정리하자면 다음 두 가지의 방식만 쓰자

```
static Comparator<Object> hashCodeOrder = new Comparator<>() {
  public int compare(Object o1, Object o2) {
    return Integer.compare(o1.hashCode(), o2.hashCode());
  }
}

static Comparator<Object> hashCodeOrder =
  Comparator.comparingInt(o -> o.hashCode());


순서를 고려해야 하는 값 클래스를 작성한다면 꼭 Comparable 인터페이스를 구현하여, 그 인스턴스들을 쉽게 정렬하고, 검색하고, 비교 기능을 제공하느 컬렉션과 어우러지도록 해야 한다.
compareTo 메서드에서 필드의 값을 비교할 때 < 와 > 연산자는 쓰지 말아야 한다. 그 대신 박싱된 기본 타입 클래스가 제공하는 정적 compare 메서드나 Comparator 인터페이스가 제공하는
비교자 생성 메서드를 사용하자.


```