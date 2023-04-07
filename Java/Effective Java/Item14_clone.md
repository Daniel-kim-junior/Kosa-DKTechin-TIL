# clone 재정의는 주의해서 진행하라

Cloneable은 복제해도 되는 클래스임을 명시하는 용도의 믹스인 인터페이스 (mixin interface)지만
의도대로 사용되지 않는다.

가장 큰 문제는 clone 메서드가 선언된 곳이 Cloneable이 아닌 Object이고, 그마저도 protected라는데 있다.

그래서 Cloneable을 구현하는 것만으로는 외부 객체에서 clone 메서드를 호출할 수 없다.

리플렉션을 사용하면 가능하지만, 100% 성공하는 것이 아니다.

해당 객체가 접근이 허용된 clone 메서드를 제공한다는 보장이 없기 때문이다.

Cloneable 인터페이스는 메서드가 없는 마크 인터페이스이다.

그렇다면 Cloanble 인터페이스가 하는일이 뭘까?

이 인터페이스는 Object의 protected 메서드인 clone의 동작 방식을 결정 한다.

Cloneable을 구현한 클래스의 인스턴스에서 clone을 호출하면 그 객체의 필드들을 하나하나

복사한 객체를 반환하며, 그렇지 않은 클래스의 인스턴스에서 호출하면 ClassNotSupportedException을 던진다.

\*\*\*이것은 인터페이스를 상당히 이례적으로 사용한 예 -> 사용 X

### 인터페이스를 구현한다는 것은 일반적으로 해당 클래스가 그 인터페이스에서 정의한 기능을 제공한다고 선언하는 행위이다.!!!

그런데 Cloneable의 경우에는 상위 클래스에 정의된 protected 메서드의 동작 방식을 변경한 것이다.

실무에서는 Cloneable을 구현한 클래스는 clone 메서드를 public으로 제공하며, 사용자는 당연히 복제가 제대로 이뤄지리라 기대한다.

이 기대를 만족시키려면 그 클래스와 모든 상위 클래스는 복잡하고, 강제할 수 없고, 허술하게 기술된 프로토콜을 지켜야만 한다.

그 결과로 무결성, 위험, 모순적 메커니즘이 발생한다.

생성자를 호출하지 않고도 객체를 생성할 수 있게 되는 것이다.(접근 제어자를 public으로 바꿔야만 하기 때문에)

### clone 메서드의 일반 규약

```
이 객체의 복사본을 생성해 반환한다. '복사'의 정확한 뜻은 그 객체를 구현한 클래스에 따라 다를 수 있다.
일반적인 의도는 다음과 같다. 어떤 객체 x에 대해 다음 식은 참이다.

x.clone() != x; (무결성)

또한 다음 식도 참이다.

x.clone().getClass() == x.getClass();

하지만 이상의 요구를 반드시 만족해야 하는 것은 아니다.
한편 다음 식도 일반적으로 참이지만, 역시 필수는 아니다.

x.clone().equals(x) == true

관례상, 이 메서드가 반환하는 객체는 super.clone()을 호출해 얻어야 한다.

이 클래스와 (Object를 제외한) 모든 상위 클래스가 이 관례를 따른다면 다음 식은 참이다.

x.clone().getClass() == x.getClass();

관례상 반환된 객체와 원본 객체는 독립적이어야 한다. 이를 만족하려면 super.clone()으로 얻은
객체의 필드 중 하나 이상을 반환 전에 수정해야 할 수도 있다.

```

강제성이 없다는 점만 빼면 생성자 연쇄(constructor chaining)와 살짝 비슷한 메커니즘이다.

즉, clone 메서드가 super.clone이 아닌, 생성자를 호출해 얻은 인스턴스를 반환해도 컴파일러는 불평하지 않을 것..

하지만 이 클래스의 하위 클래스에서 super.clone을 호출한다면 잘못된 클래스의 객체가 만들어져,

결국 하위 클래스의 clone 메서드가 제대로 동작하지 않게 된다.(생성자 호출 문제점...)

클래스가 final이라면 걱정해야 할 하위 클래스가 없으니 이 관례를 무시해도 안전하다.

하지만 final 클래스의 clone 메서드가 super.clone을 호출하지 않는다면 Cloneable을 구현할 이유도 없다.

Object의 clone 구현 동작 방식에 기댈 필요가 없기 때문이다.

제대로 동작하는 clone 메서드를 가진 상위 클래스를 상속해 Cloneable을 구현하고 싶다고 해보자

먼저 super.clone을 호출한다. 그렇게 얻은 객체는 원본의 완벽한 복제본일 것이다.

클래스에 정의된 모든 필드는 원본 필드와 똑같은 값을 가진다. 모든 필드가 기본 타입이거나 불변 객체를 참조한다면

이 객체는 완벽히 우리가 원하는 상태라 더 손볼 것이 없다.

쓸데없는 복사를 지양한다는 관점에서 보면 불변 클래스는 굳이 clone 메서드를 제공하지 않는 게 좋다.

이 점을 고려해 PhoneNumber의 clone을 구현해본다.

```
implements Cloneable

@Override public PhoneNumber clone() {
  try {
    return (PhoneNumber) super.clone();
  } catch(CloneNotSupportedException e) {
    throw new AssertionError();
  }
}
```

이 메서드가 동작하게 하려면 PhoneNumber의 클래스 선언에 Cloneable을 구현한다고 추가해야 한다.

Object의 clone 메서드는 Object를 반환하지만 PhoneNumber의 clone 메서드는 PhoneNumber를 반환하게 했다.

자바의 covariant return typing을 이용하는 것이 권장 방식이다.

재정의한 메서드의 반환 타입은 상위 클래스의 메서드가 반환하는 타입의 하위 타입일 수 있다.

이 방식으로 클라이언트가 형변환하지 않아도 되도록 하자 (단일 책임 원칙)

super.clone 호출을 try-catch 블록으로 감싼 이유는 Object의 clone 메서드가 검사 예외(checked exception)인

ClassNotSupportedException을 던지도록 선언되었기 때문이다.

PhoneNumber가 Cloneable을 구현하니, 우리는 super.clone()이 성공할 것임을 안다.

CloneNotSupported Exception이 사실은 비검사 예외(unchecked exception)였어야 했다는 신호다.

앞서 구현이 클래스가 가변 객체를 참조하는 순간 재앙이 된다.

### 가변 객체 Clone

```
public class Stack {
  private Object[] elements;
  private int size = 0;
  private static final int DEFAULT_INITIAL_CAPACITY = 16;

  public Stack() {
    this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
  }

  public void push(Object e) {
    ensureCapacity();
    elements[size++] = e;
  }

  public Object pop() {
    if(size == 0)
      throw new EmptyStackException();
    Object result = elements[--size];
    elements[size] = null; // 다 쓴 참조 해제
    return result;
  }

  // 원소를 위한 공간을 적어도 하나 이상 확보한다.
   private void ensureCapacity() {
    if(elements.length == size)
      elements = Arrays.copyOf(elements, 2 * size + 1);
   }
}
```

이 클래스를 복제할 수 있도록 만들어보자. clone 메서드가 단순히 super.clone의 결과를 반환한다면 어떻게 될까?

반환된 Stack 인스턴스의 size 필드는 올바른 값을 갖겠지만, elements 필드는 원본 Stack 인스턴스와 똑같은 배열을 참조할 것이다. (얕은 복사)

원본이나 복제본 중 하나를 수정하면 다른 하나도 수정되어 불변식을 해친다는 이야기다. 따라서 프로그램이 이상하게 동작하거나

NullPointerException을 던질 것이다.

Stack 클래스의 하나뿐인 생성자를 호출한다면 이러한 상황은 절대 일어나지 않는다. clone 메서드는 사실상 생성자와 같은 효과를 낸다.

즉 clone은 원본 객체에 아무런 해를 끼치지 않는 동시에 복제된 객체의 불변식을 보장해야 한다. 그래서 Stack의 clone 메서드는 제대로 동작하려면

스택 내부 정보를 복사해야 하는데, 가장 쉬운 방법은 elements 배열의 clone을 재귀적으로 호출해야 해 주는 것이다.

```
@Override public Stack clone() {
  try {
    Stack result = (Stack) super.clone();
    result.elements = elements.clone();
    return result;
  } catch (CloneNotSupportedException e) {
    throw new AssertionError();
  }
}
```

elements.clone의 결과를 Object[]로 형변환 할 필요는 없다. 배열의 clone은 런타임 타입과 컴파일타임 타입 모두가 원본 배열과 똑같은 배열을 반환한다.

배열은 clone의 기능을 제대로 사용하는 유일한 예라고 할 수 있다.

한편, elements 필드가 final이였다면 앞서의 방식은 작동하지 않는다. final 필드에는 새로운 값을 할당할 수 없기 때문이다.

이것은 근본적인 문제로, 직렬화와 마찬가지로 Cloneable 아키텍처는 '가변 객체를 참조하는 필드는 final로 선언하라'는 일반 용법과 충돌한다

(단 원본과 복제된 객체가 그 가변 객체를 공유해도 안전하다면 괜찮다 - 당연)

그래서 복제할 수 있는 클래스를 만들기 위해 일부 필드에서 final 한정자를 제거해야 할 수도 있다.

clone을 재귀적으로 호출하는 것만으로는 충분하지 않을 때도 있다.

해쉬테이블용 clone 메서드를 생각해보자. 해시테이블 내부는 버킷들의 배열이고, 각 버킷은 키-값 쌍을 담는 연결 리스트의 첫번째 엔트리를 참조한다.

```
public class HashTable implements Cloneable {
  private Entry[] buckets = ...;

  private static class Entry {
    final Object key;
    Object value;
    Entry next;
    Entry(Object key, Object value, Entry next) {
      this.key = key;
      this.value = value;
      this.next = next;
    }
  }

  @Override public HashTable clone() {
    try {
      HashTable result = (HashTable) super.clone();
      result.buckets = buckets.clone();
      return result;
    } catch(CloneNotSupportedException e) {

    }
  }
}
```

복제본은 자신만의 버킷 배열을 갖지만, 이 배열은 원본과 같은 연결리스트를 참조하여 원본과 복제본 모두 예기치 않게 동작할 가능성이 생긴다.

이 배열은 원본과 같은 연결 리스트를 참조하여 원본과 참조하여 원본과 복제본 모두 예기치 않게 동작할 가능성이 생긴다.

이를 해결하려면 각 버킷을 구성하는 연결 리스트를 복사해야 한다.

```
public class HashTable implements Cloneable {
  private Entry[] buckets = ...;

  private static class Entry {
    final Object key;
    Object value;
    Entry next;

    Entry(Object key, Object value, Entry next) {
      this.key = key;
      this.value = value;
      this.next = next;
    }

    Entry deepCopy() {
      return new Entry(key, value,
        next == null ? null : next.deepCopy());
    }

  }
  @Override public HashTable clone() {
    try {
      HashTable result = (HashTable) super.clone();
      result.buckets = new Entry[buckets.length];
      for(int i = 0; i < buckets.length; i++) {
        if(buckets[i] != null)
          result.buckets[i] = buckets[i].deepCopy();
      }
      return result;
    } catch(ClassNotSupportedException e) {
      throw new AssertionError();
    }
  }

}

```

private 클래스인 HashTable.Entry는 깊은 복사(deep copy)를 지원하도록 보강되었다. HashTable의 clone 메서드는 먼저

적절한 크기의 새로운 버킷 배열을 할당한 다음 원래 버킷의 배열을 순회하며 비지 않은 각 버킷에 대해 깊은복사를 수행한다

이때 Entry의 deepCopy 메서드는 자신이 가리키는 연결 리스트 전체를 복사하기 위해 자신을 재귀적으로 호출한다.

하지만 이 방법은 연결리스트의 길이가 길어지면 리스트의 원소 수만큼 스택 프레임을 소비한다. StackOverflow 위험 O

이 문제를 피하려면 deepCopy를 재귀 호출 대신 반복자를 써서 순회하는 방법으로 수정해야 한다.

```
Entry deepCopy() {
  Entry result = new Entry(key, value, next);
  for(Entry p = result; p.next != null; p = p.next) {
    p.next = new Entry(p.next.key, p.next.value, p.next.next);
  }
  return result;
}
```

### 복잡한 가변 객체를 복제하는 마지막 방법

1. super.clone()을 호출하여 얻은 객체의 모든 필드를 초기 상태로 설정한 다음, 원본 객체의 상태를 다시 생성하는 고수준 메서드들을
   호출한다.

- 방금의 예시에서 bucket 필드를 새로운 버킷 배열로 초기화 한 다음 원본 테이블에 담긴 모든 키-값 쌍 각각에 대해
  복제본 테이블의 put(key, value) 메서드들을 호출해 둘의 내용이 같게 해주면 된다.

고수준 API를 이용하면 보다 graceful 하지만 아무래도 저수준에서 바로 처리할 때보다는 느리다...
또한 Cloneable 아키텍처의 기초가 되는 필드 단위 객체 복사를 우회하기 때문에 전체 Cloneable 아키텍처와는 어울리지 않는 방식이다.

생성자에서는 재정의 될 수 있는 메서드를 호출하지 않아야 하는데 의도치 않은 clone 메서드도 마찬가지다.
만약 clone이 하위클래스에서 재정의한 메서드를 호출하게 된다면, 하위클래스는 복제 과정에서 자신의 상태를 고칠 기회를 잃게 되어
원본과 복제본의 상태가 달라질 가능성이 있다.

따라서 앞 문단에서 얘기한 put(key, value) 메서드는 final이거나 private이여야 한다.(private 이라면 final이 아닌 public 메서드가 사용하는 도우미 메서드일 것이다.)

Object의 clone 메서드는 ClassNotSupportedException을 던진다고 선언했지만 재정의한 메서드는 그렇지 않다.

\*\*\*public인 clone 메서드에서는 throws 절을 없애야한다. 검사 예외를 던지지 않아야 그 메서드를 사용하기 편리하기 때문
(비검사 예외이기 때문)

상속해서 쓰기 위한 설계 방식 두 가지 중 어느 쪽에서든, 상속용 클래스는 Cloneable을 구현해서는 안 된다.
Object의 방식을 모방할 수도 있다. 제대로 동작하는 clone 메서드를 구현해 protected로 두고 ClassNotSupportedException을 던질 수 있게 선언하는 것

또 다른 방법으로는 clone을 동작하지 않게 구현해놓고 하위 클래스에서 재정의를 막는 것

```
하위 클래스의 복제본을 막아버리는 클론 (상속 관계에서 clone은 예기치 못한 장애를 일으킬 수 있다)
@Override protected final Object clone() throws ClassNotSupportedException {
  throw new CloneNotSupportedException();
}
```

2. Cloneable을 구현한 스레드 안전 클래스를 작성할 때는 clone 메서드도 동기화 해주어야 한다.
   Object.clone()는 동기화를 신경쓰지 않았다
   그러니 super.clone() 호출 이외에 다른 할 일이 없다해도 clone을 재정의해주고 동기화 해주어야 한다.

요약하자면 Cloneable을 구현하는 클래스는 clone 메서드를 재정의해야한다.
이 때 접근제어자는 public으로, 반환 타입은 자기 자신 공변 타이핑으로 한다.
가장 먼저 super.clone메서드를 호출한 후 필요한 필드를 전부 적절히 수정한다.
객체의 모든 깊은 내부 구조에 있어서도(가변 객체들) deepCopy를 수행한다.
주로 재귀적인 방법이지만 객체의 깊이가 깊다면 Iterate하게 구현한다.
기본 타입 필드와 불변 객체 참조만 갖는 클래스라면 아무 필드도 수정할 필요 없다.
단 일련번호나 고유 ID는 수정해주어야한다.

결론은 Cloneable을 구현한 클래스를 확장한다면 어쩔 수 없이 clone을 잘 작동하도록 구현한다.
또 Arrays.clone() 메서드는 적극적으로 사용한다.
그렇지 않다면 최선은 복사 생성자와 복사 팩터리 메서드이다

```
public Yum(Yum yum) {...};

public static Yum newInstance(Yum yum) {...}
```

이 방식은 생성자를 쓰는 방식을 사용해 보다 안전하며 엉성하게 문서화된 규약에 기대지 않고, 정상적인 final 필드 용법과도 충돌하지 않으며, 불필요한 검사 예외를 던지지 않고, 형변환도 필요하지 않다.

또 해당 클래스가 구현한 인터페이스 타입을 인자로 받을 수 있다. 관례상 모든 범용 컬렉션 구현체는 Collection이나 Map 타입을
받는 생성자를 제공한다.

ex) HashSet s -> new TreeSet<>(s)로 처리할 수 있다. 이것도 큰 장점이다.
