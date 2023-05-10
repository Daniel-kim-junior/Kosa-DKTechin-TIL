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
1.

