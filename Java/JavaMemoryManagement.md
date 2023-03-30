### 스택 영역
- 메소드와 지역 변수, { } 영역 같은 아이들이 저장되는 곳
	자료구조 Stack처럼 FILO 구조를 가지고 있다. 무한 재귀에 빠지거나 한다면 StackOverflow 지옥이 기다리고 있다.


### 힙 영역
- 객체 new로 생성되는 아이들이 저장되는 곳
	GC의 주요 무대이며 힙 영역을 JVM만의 동작 방식으로 객체의 메모리를 자동으로 제거한다.
	객체의 재사용을 생활화해서 메모리 누수를 막고 GC의 동작을 최적화하는 코딩을 아로새기자


### 클래스 영역
- 클래스 로더를 통해 바이트코드로 생성된 class 파일을 JVM이 초기에 로딩하고 static 메소드와 브라켓을 올리는 영역 가장 먼저 메모리에 올라간다. 이 영역 또한 GC가 관리하긴 하지만 가장 마지막에 정리하는 영역이기도 하다 java8에서 부터 이 영역은 Native memory에 올라가게 되었으며 동적으로 확장된다고 한다.


### 주요 메모리 참조 방식 3가지

1. ***강한 참조 (Strong Reference) - 자바의 기본 객체 메모리 참조 구조
```
MyClass obj = new MyClass();
```
*obj 변수가 참조를 가지고 있는 한, MyClass 객체는 GC 대상이 되지 않습니다.*

![image](https://user-images.githubusercontent.com/67178562/226343496-46f1887a-fd3b-4092-b7ed-6d3b3820539d.png)

2. **부드러운 참조 (Soft Reference) - 대상 객체를 참조하는 경우가 SoftReference 객체만 존재하는 경우 GC의 대상이 됩니다.
```
MyClass ref = new MyClass(); 
SoftReference<MyClass> softRef = new SoftReference<MyClass>(ref); 

// 이 시점에 GC의 실행 대상이 가능 
ref = null; 

// JVM의 메모리가 부족하지 않아 GC 실행 대상이 되지 않은 경우 
// null이 반환되지 않고 기존 객체가 반환됨 ref = softRef.get();
```
![image](https://user-images.githubusercontent.com/67178562/226343550-6ff45ee7-9f94-4a9a-8498-ffd5d1430257.png)


***대상 객체를 참조하는 경우가 SoftReference 객체만 존재하는 경우 GC의 대상이 됩니다.  
단, JVM의 메모리가 부족한 경우에만 힙영역에서 제거되고 메모리가 부족하지 않다면 굳이 제거하지 않습니다.



4. ***약한 참조(Weak Reference) 
```
MyClass ref = new MyClass(); 
WeakReference<MyClass> weakRef = new WeakReference<MyClass>(ref); 

// 이 시점에 GC의 실행 대상이 가능 
ref = null; 

// 다음 GC 실행시 무조건 힙 메모리에서 제거 
// 제거된 경우 null 반환 
ref = softRef.get();
```
![image](https://user-images.githubusercontent.com/67178562/226343586-23ae22c7-b883-4e45-a7a5-6543cbca88a2.png)


**대상 객체를 참조하는 경우가 WeakReferences 객체만 존재하는 경우 GC의 대상이 됩니다.  
다음 GC 실행시 무조건 힙 메모리에서 제거됩니다.  

(톰캣 컨테이너의 ConcurrentCache class에서 WeakHashMap을 사용 중)



---