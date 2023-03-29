# 변수 관리


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