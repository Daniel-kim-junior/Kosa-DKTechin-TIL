
---

### 프리미티브 타입과 레퍼런스 타입?

    primitive type

프리미티브란 원시적인 타입 즉 데이터 그 자체를 JVM의  Runtime Data Areas의 stack 영역에 그대로 저장하는 데이터 형식을 말합니다.

논리형(boolean) , 정수형(int, short, byte, long), 실수형(double, float), 문자형(char)

각각의 표현범위가 다름

    float은 4byte로 long의 8byte 보다 가용 용량은 적지만 더 많은 자료 범위를 표현할 수 있음 부동소수점 방식

-   메모리에 실제 값이 세팅된다.
-   JVM의 stack영역


    Reference type

JVM Heap 영역에 실제 값이 존재 Stack영역에 세팅된 heap영역의 주소값이 세팅

Heap 영역은 JVM의 Garbage Collector가 관리하는 영역


```
public void primitive_test() { 
    int i = 1; 
    double d 1.0; 
    primitiveType(i, d); 
    assertThat(i).isEqualTo(2); // false 
    assertThat(d).isEqualTo(2.0); // false 
}

public void primitiveType(int i, double d) { 
    i = 2; d = 2.0; 
}

```

**Java의 Call by Value로 인해 primitiveType(int i, double d)의
int i, double d 는 스택 영역(primitiveType)에 새로운 값이 복사되어 할당됨
기존 값과 별개이기 때문에 변경되지 않아 false로 판명

```
public void reference_test() {
    Animal animal = new Animal(); 
    animal.i = 1; 
    animal.d = 1.0; 
    referenceType(animal); 
    assertThat(animal.i).isEqualTo(2); // true       assertThat(animal.d).isEqualTo(2.0); // true 
    } 
    
    public void referenceType(Animal animal) { 
        animal.i = 2; animal.d = 2.0; 
    }
```


### 변수의 스코프와 라이프타임

일반 변수의 스코프는 중괄호 내에서만 영향을 미친다.
중괄호를 벗어난 즉 메소드나 클래스가 소멸 될 때 스코프를 벗어나게 되고 사라지게 된다.

```
public class JavaScope { 
    int i = 0; // instance varibles 
    public void scope() { // scope O } 
    
    public static void staticScope() { // scope X } }
```

static modifier는 클래스가 로드될때 메모리에 올라가기 때문에 객체가 생성되지 않아도 사용할 수 있다. 클래스가 힙 메모리에 인스턴스화 되는 순간 해당 필드가 생성되기 때문에 참조할 수 없다.

클래스 파일은 컴파일 시점 -> 클래스가 JVM에 로딩되는 시점
***(실행 시 클래스 메타 데이터와 함께 클래스 영역에 저장 Non - Heap 영역(MetaSpace))

Metaspace는 기존의 PermGen 영역보다 유연하게 메모리를 할당하여 클래스 메타데이터를 관리. 
Metaspace는 힙 영역 내부가 아니라, 네이티브 메모리 영역에서 클래스 메타데이터를 관리하며, 기존에는 고정 크기였던 PermGen 영역과 달리 필요한 만큼 메모리를 동적으로 할당

또한, Metaspace는 GC 작업도 더욱 효율적으로 처리할 수 있도록 개선 
예를 들어, PermGen 영역에서는 GC 작업을 위해 Full GC를 실행해야 했지만, Metaspace에서는 GC 작업을 위해 Young GC와 Old GC를 조합하여 더욱 효율적으로 메모리를 관리할 수 있게 되었습니다.


---

### 예외

- 자바를 개발하면서 접하게 되는 오류
1. 컴파일 오류 : 구문에 위배된 구현
2. 실행 오류:
	에러(Error) : 자바프로그램이 실행하는 동안 JVM 영역에서 발생되는 오류, 심각한 오류
	
	예외(Exception) : 자바프로그램이 실행하는 동안 자바프로그램 영역에서 발생하는 오류
	가벼운 오류 예외에 대한 대처코드를 구현하여 적용 가능 --> 예외 처리 구문

- 런타임 예외 : 발생원인이 프로그램 내부 --> 발생할 수도 있는 예외에 대한 처리코드 구현이 선택
- 일반 예외 : 발생원인이 프로그램 외부 --> 발생할 수도 있는 예외에 대한 처리코드 구현이 필수 
	ex) parseInt()

- 예외 처리 구문 관련 키워드
-- try, catch, finally,     throw, throws
	-----------------     ---------------------
	적극적 예외 처리    소극적인 예외 처리


---

### Abstract :
	추상 클래스와 메소드를 생성할 수 있는 Keyword
	추상 메소드가 있어도 되고 없어도 된다.
	생성자를 생성할 수 있는 것이 Interface와 가장 다른 점이다.
	public이 아닌 접근제어자도 사용 가능하다
	abstract 메소드들을 모두 구현해야한다.

## Interface :
	다중 상속을 가능하게 하는 장점을 가진 Interface
	Java8 이전에는 메소드를 구현할 수 없었지만 default 키워드로 생성 가능
	abstract 메소드들을 모두 구현해야한다.
	모든 구현은 public이 강제 된다.
	필드는 초기화 상수만 가능하고 초기화 까지 해야한다. public static final int FIRE = 0;
	public static final은 자동으로 설정되기 때문에 변경 불가이다. 
	int FIRE = 0 이라고 초기화 해주면 된다.
    
---

### 상속
   

- 원칙

1. 접근제어자(Modified)는 상속받은 Class보다 항상 범위가 넓거나 같아야 한다.
	ex) public Parent <= public Child (O)
	public Parent > protected Child(X)

2. 상속 받은 하위 클래스는 상위 클래스의 모든 필드와 메소드를 private과 상황상 다른 패키지의 default가 아닌 이상 상속 받는다
   
3. 상속 받은 상위 클래스의 메소드를 오버라이딩 해서 사용할 수 있다.
   
4. 상위 클래스의 생성자는 자동으로 상속되지만, 하위 클래스에서 사용하려면 명시적으로 생성자를 호출해야 한다. 
   
5. 다중 상속은 지원하지 않는다.


    - 상속 주의점!
        - 접근제어자를 조상보다 더 넓거나 같은 접근제어자만 사용 가능하다.
        - class 상속은 Override를 강제하지 않지만 interface는 Override를 강제한다.
        - 부모 변수에 담은 자식 인스턴스는 부모에 정의되어 있지 않은 메소드를 사용해야 할 때
            명시적 형변환이 필요하다
         
---
# Java의 접근 제한자


## Public

- 객체에 대한 모든 접근을 허용

## Protected

- 같은 패키지 내에 있는 객체, 상속관계에 있는 객체들만 허용

## Default

- 같은 패키지 내에 있는 객체들만 허용

## Private

- 현재 객체 내에서만 허용 (Class)


***주의점 : class의 접근 제한자는 public과 default만 허용한다

---

### 접근 제한자로 얻어지는 캡슐화(encapsulation)

- 데이터 보호의 장점 : 은닉화를 통해 데이터의 접근을 제어
- 유지 보수성의 장점 : 올바르게 설계된 객체 지향 코드는 각각의 모듈이 느슨하게 결합되어
	OCP가 잘지켜지므로 코드의 유지 보수성이 증가합니다.
- 사용자 편의성 : 잘 만들어진 캡슐은 올바르게 추상화되어 있기 때문에 사용법을 익히지 않고 프로그램을 설계할 수 있습니다.


---
# 클래스다이어그램 멤버들의 접근성 정의

**'+' : public

**'#' : protected

**생략 : default

**'-' : private

---

## Static

***Static 키워드는 메소드 혹은 필드를 클래스가 로딩 시 클래스 영역에 값(논리적 주소값 or Value)을 할당한다. 따라서 JVM이 Application Class loading을 시도할 때 메모리에 올라간다. 그래서 Class를 인스턴스화하지 않아도 사용이 가능하다.
Static 변수 혹은 메소드에 접근하기 위해서는 Class.__name__을 사용하여 Static 답게 접근하자


### 주의점

1.  논리 설계 : 객체의 구조를 설계할 때 재사용이 빈번한 필드나 메소드는 static으로 설계하자

2. 구조 설계 : static method를 설계할 때 static이 아닌 필드를 참조한다거나 해서 static을 쓰는 의미를 퇴색하지말자 다른 값이나 필드에 영향을 주지 않는 설계를 하자. 
   (스택 영역의 지역 변수를 쓰자)
   
   
---

### 스택 영역
- 메소드와 지역 변수, { } 영역 같은 아이들이 저장되는 곳
	자료구조 Stack처럼 FILO 구조를 가지고 있다. 무한 재귀에 빠지거나 한다면 StackOverflow 지옥이 기다리고 있다.


### 힙 영역
- 객체 new로 생성되는 아이들이 저장되는 곳
	GC의 주요 무대이며 힙 영역을 JVM만의 동작 방식으로 객체의 메모리를 자동으로 제거한다.
	객체의 재사용을 생활화해서 메모리 누수를 막고 GC의 동작을 최적화하는 코딩을 아로새기자


### 클래스 영역
- 클래스 로더를 통해 바이트코드로 생성된 class 파일을 JVM이 초기에 로딩하고 static 메소드와 브라켓을 올리는 영역 가장 먼저 메모리에 올라간다. 이 영역 또한 GC가 관리하긴 하지만 가장 마지막에 정리하는 영역이기도 하다 java8에서 부터 이 영역은 Native memory에 올라가게 되었으며 동적으로 확장된다고 한다.
