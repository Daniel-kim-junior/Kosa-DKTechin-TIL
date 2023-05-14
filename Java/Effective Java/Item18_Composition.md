# 상속보다는 컴포지션을 사용하라

상속은 코드를 재사용하는 강력한 수단, but 항상 최선은 아니다.
잘못 사용하면 오류를 내기 쉬움

상위 클래스와 하위 클래스를 모두 같은 프로그래머가 같은 패키지 안에서 통제한다면 상속도 안전한 방법이다.
확장할 목적으로 설계되었고 문서화도 잘 된 클래스도 마찬가지로 안전.

하지만 일반적인 구체 클래스를 패키지 경계를 넘어, 즉 다른 패키지의 구체 클래스를 상속하는 일은 위험
상기하자면, 이 책에서 '상속'은 클래스가 클래스를 상속하는 상속을 뜻함

메서드 호출과 달리 상속은 캡슐화를 해친다.

왜냐하면 상위 클래스가 변화한다면 하위 클래스가 그 메소드를 온전하게 쓸 수 있기 때문에 의도치 않은 버그를 일으킬 수 있기 때문이다.

상위 클래스는 릴리스마다 내부 구현이 달라질 수 있으며, 그 여파로 코드 한 줄 건드리지 않은 하위 클래스가 오동작 가능성 up

구체적인 예
HashSet을 사용하는 프로그램이 있다고 가정하자
성능을 높이려면 이 커스텀 HashSet은 처음 생성된 이후 원소가 몇 개 더해졌는지 알 수 있어야 한다.

변형된 HashSet을 만들어 추가된 원소의 수를 저장하는 변수와 접근자 메서드를 추가한 코드
```
public class InstrumentedHashSet<E> extends HashSet<E> {
    // 추가된 원소의 수
    private int addCount = 0;
    public InstrumentedHashSet(){}
    
    public InstrumentedHashSet(int initCap, float loadFactor) {
        super(initCap, loadFactor);
    }
    
    @Override public boolean add(E e) {
       addCount++;
       return super.add(e);
    }
    
    @Override public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }
    
    public int getAddCount() {
        return addCount;
    }

}

```

이 클래스는 잘 구현된 것처럼 보이지만 제대로 작동하지 않는다. 이 클래스의 인스턴스에 addAll 메서드로 원소 3개를 더했다고 해보자.

InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
s.addAll(List.of("틱", "탁탁", "펑"));
이 메소드를 호출하면 3을 반환하리라 기대하지만 실제로는 6 반환

원인은 HashSet addAll 메서드가 add 메서드를 사용해 구현된 데 있다. 이런 내부 구현 방식은 HashSet 문서에는 당연히 정의되어 있지 않다.
InstrumentedHashSet의 addAll은 addCount에 3을 더한 후 HashSet의 addAll 구현을 호출했다.
HashSet의 addAll은 각 원소를 add 메서드를 호출해 추가하는데, 이때 불리는 add는 InstrumentedHashSet에서 재정의한 메서드다. 따라서 addCount에 값이 중복해서 더해지기 때문에
6이 된다.

### 문제의 해결법
1. addAll 메서드를 재정의를 하지 않는다.
    - 당장은 제대로 동작할지 모르나, HashSet의 addAll이 add 메서드를 이용해 구현했음을 가정한 해법
    하지만 상위 클래스가 어떻게 구현되어있는지 또 상위 클래스의 변경에 가혹하다. 이처럼 자신의 다른 부분을
    사용하는 '자기사용 (self-use)' 여부는 해당 클래스의 내부 구현 방식에 해당하며, 자바 플랫폼 전반적인
    정책인지, 그래서 다음 릴리스에서도 유지될지는 알 수 없다. 이런 가정에 기댄 InstrumentedHashSet도
    깨지기 쉽다.
2. addAll 메서드를 다른 식으로 재정의한다.
 - 주어진 컬렉션을 순회하며 원소 하나당 add 메서드를 한 번만 호출하는 것이다. 이 방식은 HashSet의 addAll을 더 이상 호출하지 않으니 addAll이 add를 사용하는지와 상관없이 결과가 옳다는 점이 있다.
 - 하지만 이러한 방식은 구현 방식도 복잡하고, 시간도 더 들고, 오류나 성능을 저하 시킬 수 있는 우려를 발생 시킨다.
 - 하위 클래스에서는 접근할 수 없는 private 필드를 써야 하는 상황이라면 이 방식으로는 구현자체가 불가능하다

3. 다음 릴리스에서 상위 클래스에 새로운 메소드가 추가될 때
ex) 보안 때문에 컬렉션에 추가된 모든 원소가 특정 조건을 만족해야만 하는 프로그램을 생각해보자.
그 컬렉션을 상속하여 원소를 추가하는 모든 메서드를 재정의해 필요한 조건을 먼저 검사하게끔 하면 될 것
하지만 이 방식이 통하는 것은 상위 클래스에 또 다른 원소 추가 메서드가 만들어지기 전까지다.
다음 릴리스에서 우려한 일이 생기면, 하위 클래스에서 재정의하지 못한 그 새로운 메서드를 사용해 '허용되지 않은'
원소를 추가할 수 있게 된다.
실제로도 컬렉션 프레임워크 이전부터 존재하던 HashTable과 Vector를 컬렉션 프레임워크에 포함시키자 이와
관련된 보안 구멍들을 수정해야 하는 사태가 벌어졌다.
이상의 두 문제 모두 메서드 재정의가 원인이었다. 따라서 클래스를 확장하더라도 메서드를 재정의하는 대신 새로운
메서드를 추가하면 괜찮으리라 생각할 수도 있다. 이 방식이 훨씬 안전한 것은 맞지만, 위험이 전혀 없는 것은
아니다. 다음 릴리스에서 상위 클래스에 새 메서드가 추가됐는데, 운 없게도 하필 여러분이 하위 클래스에 추가한
메서드와 시그니처가 같고 반환 타입은 다르다면 클래스는 컴파일조차 되지 않는다.
만약 반환 타입 자체가 같다면 Override한 꼴이니 앞서의 문제와 똑같은 상황에 부닥친다.
또 이 메서드를 작성할 때는 상위 클래스의 메서드는 존재하지도 않았으니, 만든 메서드는 상위 클래스의 메서드가
요구하는 규약을 만족하지 못할 가능성이 크다.

4. 해결책

기존 클래스를 확장하는 대신, 새로운 클래스를 만들고 private 필드로 기존 클래스의 인스턴스를 참조하게 하자.
기존 클래스가 새로운 클래스의 구성요소로 쓰인다는 뜻에서 이러한 설계를 컴포지션(composition; 구성)이라 한다.
새 클래스의 인스턴스 메서드들은 (private 필드로 참조하는) 기존 클래스의 대응하는 메서드를 호출해 그 결과를 반환한다.
이 방식을 전달(Forwarding)이라 하며, 새 클래스의 메서드들을 전달 메서드(forwarding method)라 부른다.
그 결과 새로운 클래스는 기존 클래스의 내부 구현 방식의 영향에서 벗어나며, 심지어 기존 클래스에 새로운 메서드가 추가되더라도 전혀
영향을 받지 않는다.

구체적인 예시
(집합 클래스)
```
public class IntrumentedSet<E> extends ForwardingSet<E> {
    private int addCount = 0;
    
    public InstrumentedSet(Set<E> s) {
        super(s);
    }
    
    @Override public boolean add(E e) {
        addCount++;
        return super.add(e);
    }
    
    @Override public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }
    
    public int getAddCount() {
        return addCount;
    }
}
```

(전달 클래스)
```
public class ForwardingSet<E> implements Set<E> {
    private final Set<E> s;
    public ForwardingSet(Set<E> s) { this.s = s; }
    public void clear() { s.clear(); }

    public boolean contains(Object o) {return s.contains(o);}
    public boolean isEmpty() {return s.isEmpty();}
    public int size() {return s.size();}
    public Iterator<E> iterator() {return s.iterator();}
    public boolean add(E e) {return s.add(e);}
    public boolean remove(Object o) {return s.remove(o);}
    public boolean containsAll(Collection<?> c) {return s.containsAll(c);}
    public boolean addAll(Collection<? extends E> c) {return s.addAll(c);}
    public boolean removeAll(Collection<?> c) {return s.removeAll(c);}
    public boolean retainAll(Collection<?> c) {return s.retainAll(c);}
    public Object[] toArray() {return s.toArray();}
    public <T> T[] toArray(T[] a) {return s.toArray(a);}
    @Override public boolean equals(Object o) {return s.equals(o);}
    @Override public int hashCode() {return s.hashCode();}
    @Override public String toString() {return s.toString();}
}
```

InstrumentedSet은 HashSet의 모든 기능을 정의한 Set 인터페이스를 활용해 설계되어 견고하고 유연하다.
구체적으로는 Set 인터페이스를 구현했고, Set의 인스턴스를 인수로 받는 생성자를 하나 제공한다.
임의의 Set의 계층 기능을 덧씌워 새로운 Set으로 만드는 것이 핵심이다.

상속 방식은 구체 클래스 각각을 따로 확장해야 하며, 지원하고 싶은 상위 클래스의 생성자 각각에 대응하는 생성자를 별도로
정의 해주어야 한다.

컴포지션 방식은 한 번만 구현해두면 어떠한 Set 구현체라도 계측할 수 있으며, 기존 생성자들과도 함께 사용할 수 있다.

```
Set<Instant> times = new InstrumentedSet<>(new TreeSet<>(cmp));
Set<E> s = new InstrumentedSet<>(new HashSet<>(INIT_CAPACITY));
```

InstrumentedSet을 이용하면 대상 Set 인스턴스를 특정 조건하에서만 임시로 계측할 수 있다.
```
static void walk(Set<Dog> dogs) {
    InstrumentedSet<Dog> iDogs = new InstrumentedSet<>(dogs);
}
```

다른 Set 인스턴스를 감싸고(wrap) 있다는 뜻에서 InstrumentedSet 같은 클래스를 래퍼 클래스라 하며, 다른 Set에
계측 기능을 덧씌운다는 뜻에서 데코레이터 패턴이라고 한다.

컴포지션과 전달의 조합은 넓은 의미로 위임(delegation)이라고 부른다. 단, 엄밀히 따지면 래퍼 객체가 내부 객체에 자기
자신의 참조를 넘기는 경우만 위임에 해당한다.

래퍼 클래스는 단점이 거의 없지만, 래퍼 클래스가 콜백(callback) 프레임워크와는 어울리지 않는다는 점만 주의하자
콜백 프레임워크에서는 자기 자신의 참조를 다른 객체에 넘겨서 다음 호출(콜백) 때 사용하도록 한다. 내부 객체는
자신을 감싸고 있는 래퍼의 존재를 모르니 대신 자신(this)의 참조를 넘기고, 콜백 때는 래퍼가 아닌 내부 객체를 호출하게 된다
이를 SELF문제라고 한다. 전달 메서드가 성능에 주는 영향이나 래퍼 객체가 메모리 사용량에 주는 영향은 거의 없다.
따라서 재사용할 수 있는 전달 클래스를 인터페이스당 하나씩만 만들어두면 원하는 기능을 덧씌우는 전달 클래스들을
아주 손쉽게 구현할 수 있다.

상속은 반드시 하위 클래스가 상위 클래스의 '진짜' 하위 타입인 상황에서만 쓰여야 한다. 다르게 말하면, 클래스 B가
클래스 A와 is-a 관계일 때만 클래스 A를 상속해야 한다. 클래스 A를 상속하는 클래스 B를 작성하려 한다면
"B가 정말 A인가?" 라고 자문하자. "그렇다"고 확신할 수 없다면 B는 A를 상속해서는 안 된다.
대답이 "아니다" 라면 A를 private 인스턴스로 두고, A와는 다른 API를 제공해야 하는 상황이 대다수다.
즉 A는 B의 필수 구성요소가 아니라 구현하는 방법 중 하나일 뿐이다.

자바 플랫폼 라이브러리에서도 이 원칙을 명백히 위반한 클래스들을 찾아 볼 수 있다. 예를 들어, 스택은 벡터가 아니므로
Stack은 Vector를 확장해서는 안 됐다. 마찬가지로, 속성 목록도 해시테이블이 아니므로 Properties도 HashTable을
확장해서는 안 됐다. 두 사례 모두 컴포지션을 사용했다면 더 좋았을 것이다.

컴포지션을 써야 할 상황에서 상속을 사용하는 것은 내부 구현을 불필요하게 노출하는 꼴.

그 결과 API가 내부 구현에 묶이고 그 클래스의 성능도 영원히 제한된다. 더 심각한 문제는 클라이언트가 노출된 내부에 직접
접근할 수 있다는 점이다.
사용자를 혼란스럽게 할 수 있는데 예컨데 Properties의 인스턴스인 p가 있을 때, p.getProperty(key)와 p.get(key)는
결과가 다를 수 있다. 전자가 Properties의 기본 동작인 데 반해, 후자는 Properties의 상위 클래스인 HashTable로 부터 물려받은 메서드이
기 때문이다. 가장 심각한 문제는 클라이언트에서 상위 클래스를 직접 수정하여 하위 클래스의 불변식을 해칠 수 있다는 사실이다.
예컨데 Properties는 키와 값으로 문자열만 허용하도록 설계하려 했으나, 상위 클래스인 HashTable의 메서드를 직접 호출하면 이 불변식을
깨버릴 수 있다. 불변식이 한번 깨지면 load와 store같은 다른 Properties API는 더 이상 사용할 수 없다. 이 문제가 밝혀졌을 때는
이미 수많은 사용자가 문자열 이외의 타입을 Properties의 키나 값으로 사용하고 있었다.

컴포지션 대신 상속을 사용하기로 결정하기 전에 마지막으로 자문해야할 질문
1. 확장하려는 클래스의 API에 결함이 없는가?
2. 결함이 있다면, 우리의 클래스 API까지 전파돼도 괜찮은가?
컴포지션으로는 이런 결함을 숨기는 새로운 API를 설계할 수 있지만, 상속은 상위 클래스의 API를 결함까지 그대로 승계한다.
