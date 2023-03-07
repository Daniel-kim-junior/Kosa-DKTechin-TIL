
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

