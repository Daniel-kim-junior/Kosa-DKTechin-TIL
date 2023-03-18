
# Java Virtual Machine (JVM)

	자바 프로그램(프로세스)을 실행 할 수 있는 가상 머신
	바이트코드로 컴파일 할 수 있는 다른 프로그래밍 언어(Kotlin, Groovy)도 실행 할 수 있다.


# .java 컴파일

	.java는 Java의 가장 작은 파일 단위 Class를 뜻한다.
	이 클래스 파일을 바이트코드로 JVM은 컴파일이 가능하다.

***명령어 : javac <옵션> <소스파일>
옵션들은 javac --help로 확인할 수 있다

# Java 실행하기
	메인 클래스로 실행할 수 있다
	jar파일을 실행할 수 있다
	필요한 모듈을 지정할 수 있다.
	단일 파일 소스로 직접 실행할 수 있다.
	꿀팁! 단일 파일로 실행할 경우 소스로부터 실행할 수 있다.

```null
Usage: java [options] <mainclass> [args...]
           (to execute a class)
   or  java [options] -jar <jarfile> [args...]
           (to execute a jar file)
   or  java [options] -m <module>[/<mainclass>] [args...]
       java [options] --module <module>[/<mainclass>] [args...]
           (to execute the main class in a module)
   or  java [options] <sourcefile> [args]
           (to execute a single source-file program)
```

### 다른 유용한 명령어
	javap : 디스어셈블러
	jdb : 디버거

---

# 바이트코드

	바이트코드는 가상머신이 실행 할 수 있는 2진 코드이다.
[오라클 문서 링크](https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html){:target="_blank"}를 보면 JVM의 인스트럭션 셋을 직접 볼 수 있는데 일반적인 기계어보다 약간 추상화되어 있어서 명령이 더 직관적이고 이해하기 쉽다.
Hello.java

```null
cat Hello.java
public class Hello {
	public static void main(String[] args) {
		System.out.println("Hello, CodeSquad!\n");
	}
}
```

16진수 덤프

```null
xxd Hello.class
00000000: cafe babe 0000 003a 001d 0a00 0200 0307  .......:........
00000010: 0004 0c00 0500 0601 0010 6a61 7661 2f6c  ..........java/l
00000020: 616e 672f 4f62 6a65 6374 0100 063c 696e  ang/Object...<in
00000030: 6974 3e01 0003 2829 5609 0008 0009 0700  it>...()V.......
00000040: 0a0c 000b 000c 0100 106a 6176 612f 6c61  .........java/la
00000050: 6e67 2f53 7973 7465 6d01 0003 6f75 7401  ng/System...out.
00000060: 0015 4c6a 6176 612f 696f 2f50 7269 6e74  ..Ljava/io/Print
00000070: 5374 7265 616d 3b08 000e 0100 1248 656c  Stream;......Hel
00000080: 6c6f 2c20 436f 6465 5371 7561 6421 0a0a  lo, CodeSquad!..
00000090: 0010 0011 0700 120c 0013 0014 0100 136a  ...............j
000000a0: 6176 612f 696f 2f50 7269 6e74 5374 7265  ava/io/PrintStre
000000b0: 616d 0100 0770 7269 6e74 6c6e 0100 1528  am...println...(
000000c0: 4c6a 6176 612f 6c61 6e67 2f53 7472 696e  Ljava/lang/Strin
000000d0: 673b 2956 0700 1601 0005 4865 6c6c 6f01  g;)V......Hello.
000000e0: 0004 436f 6465 0100 0f4c 696e 654e 756d  ..Code...LineNum
000000f0: 6265 7254 6162 6c65 0100 046d 6169 6e01  berTable...main.
00000100: 0016 285b 4c6a 6176 612f 6c61 6e67 2f53  ..([Ljava/lang/S
00000110: 7472 696e 673b 2956 0100 0a53 6f75 7263  tring;)V...Sourc
00000120: 6546 696c 6501 000a 4865 6c6c 6f2e 6a61  eFile...Hello.ja
00000130: 7661 0021 0015 0002 0000 0000 0002 0001  va.!............
00000140: 0005 0006 0001 0017 0000 001d 0001 0001  ................
00000150: 0000 0005 2ab7 0001 b100 0000 0100 1800  ....*...........
00000160: 0000 0600 0100 0000 0100 0900 1900 1a00  ................
00000170: 0100 1700 0000 2500 0200 0100 0000 09b2  ......%.........
00000180: 0007 120d b600 0fb1 0000 0001 0018 0000  ................
00000190: 000a 0002 0000 0003 0008 0004 0001 001b  ................
000001a0: 0000 0002 001c                           ......
```

javap로 디스어셈블

```null
javap -c Hello
Compiled from "Hello.java"
public class Hello {
  public Hello();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #13                 // String Hello, CodeSquad!\n
       5: invokevirtual #15                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       8: return
}
```

## JIT 컴파일러

-   [참고문서](https://docs.oracle.com/en/java/javase/14/vm/java-hotspot-virtual-machine-performance-enhancements.html#GUID-1D9B26AD-8E0A-4771-90DA-A81A2C1F5B55)
-   Just In Time Compiler: 실행시간에 바이트코드를 네이티브 코드로 변환해서 성능을 높여준다. 대신 시작시간이 조금 늦어질 수도 있다.
-  JIT 컴파일 방식이 도입되어 사용자가 처음 실행할 때 바이트코드를 컴파일해서 [기계어](https://www.newiki.net/w/index.php?title=%EA%B8%B0%EA%B3%84%EC%96%B4&action=edit&redlink=1 "기계어 (없는 문서)") 실행파일로 변환해 놓고 이걸 대신 실행하는 방식으로 바꾸면서 속도를 대폭 향상시키는 효과를 얻었다. 보통 JIT 컴파일은 최초 실행할 때 한번 [컴파일](https://www.newiki.net/wiki/%EC%BB%B4%ED%8C%8C%EC%9D%BC "컴파일")해서 [기계어](https://www.newiki.net/w/index.php?title=%EA%B8%B0%EA%B3%84%EC%96%B4&action=edit&redlink=1 "기계어 (없는 문서)") 파일을 만들어 놓고 그 이후에는 원본의 내용이 바뀌지 않았다면 이전에 [컴파일](https://www.newiki.net/wiki/%EC%BB%B4%ED%8C%8C%EC%9D%BC "컴파일")해 두었던 [기계어](https://www.newiki.net/w/index.php?title=%EA%B8%B0%EA%B3%84%EC%96%B4&action=edit&redlink=1 "기계어 (없는 문서)") 파일을 실행시키는 방식으로 사용된다.

장점이라면 역시 속도. 그때문에 JIT 컴파일이 생겨난 것이기도 하다. 실행할 때마다 기계어 변환 과정이 필요한 인터프리트 방식, 혹은 중간코드 방식에 비해서 속도가 빠를 수밖에 없다. 단점은 프로그램을 처음으로 실행할 때에는 [컴파일](https://www.newiki.net/wiki/%EC%BB%B4%ED%8C%8C%EC%9D%BC "컴파일") 과정 때문에 초기에 실행이 시작되는 속도가 많이 느리며, 소스 코드 혹은 바이트코드 파일과 실행파일 코드가 같이 있기 때문에 용량을 많이 잡아먹게 된다. 소스 코드나 바이트코드가 바뀌었을 경우에 컴파일을 다시 해야 하므로 그 변화 여부를 체크하고 있어야 한다.

### 주의점
- 메모리 사용량 - 특히 중복되는 코드가 많을때
- 코드 예측이 어려움 - 컴파일 시기에 알 수 없는 동적인 요소들에 대한 고려가 필요
- 보안 이슈 - JIT 컴파일러는 프로그램 실행 중에 코드를 컴파일하므로 발생할 수 있는 보안 취약점에 대한 위험성




## JVM의 구성 요소
![[Pasted image 20230307204518.png]]
-   클래스로더: Loading, Linking, 초기화 담당

### JVM 메모리 구조

-   메소드 영역: 모든 클래스 정보와 static 변수가 저장됨
-   힙영역: 객체가 저장됨. gc 대상
-   스택 영역: per thread 자료구조. 메소드의 스택으로 사용됨
-   PC 레지스터스: per thread 자료구조. 각 스레드의 PC를 저장함
-   네이티브 메소드 스택: per thread 자료구조. 네이티브 메소드의 스택으로 사용된다고 한다.

*Native Method란? : C, C++, 어셈블리 등의 네이티브 언어로 작성된 코드를 호출하는 메커니즘
네이티브 메소드는 Java 언어로 작성된 코드와 네이티브 코드 간의 인터페이스를 제공하며, 네이티브 코드는 Java 가상 머신에서 직접 실행된다.

네이티브 메소드는 성능이 중요한 작업이나 하드웨어 접근이 필요한 작업에 사용된다. 예를 들어, 그래픽 라이브러리나 데이터베이스 드라이버와 같은 라이브러리는 네이티브 메소드를 사용하여 Java 프로그램에서 호출할 수 있다.

Java에서 네이티브 메소드를 호출하려면 해당 메소드가 선언된 클래스에서 `native` 키워드를 사용하여 메소드를 선언해야한다. 또한 해당 클래스는 `JNI(Java Native Interface)`를 사용하여 네이티브 메소드와 상호 작용하게된다. 
JNI는 Java 언어와 C/C++ 언어 간의 상호 운용성을 제공하는 프로그래밍 인터페이스이다.




## JDK와 JRE의 차이

-   [링크](https://stackoverflow.com/questions/1906445/what-is-the-difference-between-jdk-and-jre)
-   JRE: 라이브러리, JVM, 애플릿을 돌리기 위해 필요한 컴포넌트 등으로 구성됨
-   JDK: JRE + 컴파일러, 디버거 등 개발에 필요한 도구가 추가됨



### Annotation Processor란?

자바 컴파일러가 컴파일 과정에서 사용하는 기능
어노테이션 프로세서를 사용하여 소스 코드에서 선언된 어노테이션에 대한 정보를 추출하여
해당 정보를 바탕으로 코드를 생성하거나 분석할 수 있다.

```
어노테이션 프로세서는 자바 5부터 지원되는 기능으로, 소스 코드의 메타데이터 정보를 활용
하여 컴파일러나 런타임 환경에서 필요한 정보를 제공하는 역할

일반적으로는 컴파일 타임에 실행된다
```

*어노테이션 프로세서를 사용하여 할 수 있는 일

1. 코드 생성 - 어노테이션 프로세서를 사용하여 소소코드에 선언된 어노테이션을 정보를 바탕으로 코드를 생성할 수 있다. 예를 들어 JPA(@Entity) 어노테이션을 이용하여 Entity class를 생성하거나, Lombok(@Getter, @Setter) 어노테이션을 이용하여 Getter/Setter 메소드를 자동으로 생성하는 등의 작업이 가능함
2.  코드 검증 - 어노테이션 프로세서를 사용하여 코드에서 선언된 어노테이션 정보를 검증할 수 있다. 예를 들어 @NonNull 어노테이션을 사용하여 null 값을 허용하지 않는 변수에 null 값을 할당하면 컴파일 타임에 오류를 발생시킬 수 있다.
3. 리소스 생성 - 어노테이션 프로세서를 사용하여 리소스 파일을 생성할 수 있다. 예를 들어, 소스 코드에서 선언된 어노테이션을 사용하여 XML파일이나 프로퍼티 파일을 생성할 수 있다.

어노테이션 프로세서를 작성하기 위해서는 javax.annotation.processing 패키지의 AnnotationProcessor 인터페이스를 구현하고, META-INF/services/javax.annotation.processing.Processor 파일을 생성하여 구현한 AnnotationProcessor 클래스를 등록해주어야 한다.

어노테이션 프로세서는 자바의 메타프로그래밍 기술 중 하나이며, 코드를 생성하거나 분석하는 데 매우 유용한 기능이다.
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


### Garbage Collection(GC)

JVM에 위치해 있는 GC는 자동으로 메모리를 관리해주는 기능이다.
GC는 사용되지 않는 객체를 자동으로 인식하여, 해당 객체가 사용하는 메모리를 해제.

개발자가 메모리에 대해 신경을 덜 쓸 수 있게 도와준다.

GC는 일반적으로 Heap영역에서 동작하지만 클래스 영역에서 클래스를 로드하거나 언로드 할 때도
Young GC와 Old GC로 나누어 효율적으로 메모리를 관리하는데
그 과정은 다음과 같다.
Young GC와 Old GC는 JVM에서 수행하는 Garbage Collection(GC) 작업 중에서 메모리 영역별로 구분하여 실행하는 방식

Young GC는 Eden 영역과 Survivor 영역1, 2에서 실행되는 GC 작업을 의미. 새로 생성된 객체는 Eden 영역에 할당되며, 

### Eden 영역이 가득 차면

객체 중에서 살아남은 객체는 Survivor 영역1로 이동됩니다. 그리고 다음번 Young GC가 실행될 때, Survivor 영역1에 남아있는 객체들 중에서 살아남은 객체는 Survivor 영역2로 이동된다. 
이후, Young GC가 다시 실행될 때는 Survivor 영역1에 남아있는 객체들과 Survivor 영역2에 남아있는 객체들 중에서 살아남은 객체들을 모두 Old 영역으로 이동시킨다.

Old GC는 Old 영역에서 실행되는 GC 작업을 의미하고 Old 영역은 Young GC가 여러번 수행되어도 살아남은 객체들이 이동하게 되는 메모리 영역이기 때문에 

Young GC보다는 큰 객체들이 저장되는 공간이다.

따라서 Old GC는 Young GC보다 더 드문 간격으로 실행되며, Old 영역에서 메모리 부족이 발생하거나, Full GC를 실행해야 할 때 실행된다.

Young GC와 Old GC는 메모리 영역별로 구분하여 실행함으로써, 메모리 부족을 더욱 효율적으로 처리할 수 있으며, GC 작업의 성능을 향상시킬 수 있다. (Divide)


GC의 과정은 두가지로 나눌 수 있는데
첫번째 단계는 더 이상 사용하지 않는 객체를 식별하는 것
두번째 단계는 해당 객체가 사용하고 있던 메모리를 해제하는 것

1.  Mark and Sweep 알고리즘

Java에서 가장 기본적으로 사용되는 GC 알고리즘은 Mark and Sweep 알고리즘입니다. 이 알고리즘은 다음과 같은 과정을 거칩니다.

-   먼저, 모든 객체를 순회하면서 사용되는 객체를 표시합니다. 이 과정을 "Mark" 단계라고 합니다.
-   Mark 단계가 끝나면, 표시되지 않은 객체(사용되지 않는 객체)를 Heap에서 제거합니다. 이 과정을 "Sweep" 단계라고 합니다.

이 과정에서 GC는 메모리에서 불필요한 객체를 제거하고, 메모리를 효율적으로 관리하여 애플리케이션의 성능을 최적화합니다.

2.  압축(Compaction) 알고리즘

Mark and Sweep 알고리즘 외에도, Java에서는 압축(Compaction) 알고리즘도 사용됩니다. 이 알고리즘은 Heap에서 사용되지 않는 객체를 제거하는 대신, 사용되는 객체를 한 곳으로 모아서 메모리 공간을 확보하는 방법입니다. 이렇게 하면 GC가 빈번히 발생할 때마다 Heap 내부에 메모리 조각이 생기는 것을 방지할 수 있습니다.

압축 알고리즘은 Mark and Sweep 알고리즘에 비해 더 복잡하고 성능이 좋지 않은 경우가 있지만, Heap 내부에서 메모리 조각이 발생하는 것을 방지하므로 애플리케이션의 성능을 개선하는 데 도움을 줄 수 있습니다.


---

### 에러 처리

    try catch finally - 적극적 예외 처리
    runTime Error를 의도적인 프로그램의 한 부분으로 바꿀 수 있다.
    
    throw throws - 에러를 상위 스택으로 책임을 위임하는 소극적 예외 처리 마지막까지 던지게 된다면 JVM이 처리하게 된다
    
    
    Exception을 조상으로 가지고 있는 메소드들은 반드시 예외를 처리해 주어야 한다.
    RunTimeException을 조상으로 가지고 있는 메소드들은 예외를 선택적으로 처리할 수 있다.


---

### interface vs Abstract class
    - 둘다 추상화(다형성)를 위한 도구
    - Java8에서 부터 Interface는 default method와 static Method를 가지게 되었다.(구현 가능)
    - interface는 상수만 사용할 수 있다 (public static final - 자동으로 붙여준다.)
    - interface는 모든 추상 메서드를 public으로 강제한다.
    - 반면에 Abstract는 다양한 접근제어자를 사용 가능하다.
    - Abstract는 생성자를 가질 수 있다.
    - interface는 다중 상속이 가능하다 상속 받을 때는 extends 키워드를 사용한다
    
---

### 상속
    - 상속 주의점!
        - 접근제어자를 조상보다 더 넓거나 같은 접근제어자만 사용 가능하다.
        - class 상속은 Override를 강제하지 않지만 interface는 Override를 강제한다.
        - 부모 변수에 담은 자식 인스턴스는 부모에 정의되어 있지 않은 메소드를 사용해야 할 때
            명시적 형변환이 필요하다
        


### 주요 유형 참조 방식 3가지

1. 강한 참조 (Strong Reference) - 
2. 부드러운 참조 (Soft Reference) -
3. 약한 참조(Weak Reference) -

---

### 제네릭
