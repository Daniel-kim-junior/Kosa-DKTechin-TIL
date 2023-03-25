### 주요 유형 참조 방식 3가지

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

### 제네릭 메소드(Generic Method)


**제네릭 메소드**는 메소드의 선언 부에 적은 제네릭으로 리턴 타입, 파라미터의 타입이 정해지는 메소드이다.

![image](https://user-images.githubusercontent.com/67178562/226343790-c9b4e6c0-bc1f-432c-afa9-1e04d337e34c.png)



제네릭 예시

```
public class Student<T> { static T name; } static keyword는 제네릭 변수 선언 불가
```

생각하면 당연한 것이다. 제네릭은 인스턴스화 될 때 타입이 정해지는데 static 키워드는 Class가 로드 될 때 메모리에 적재되기 때문에 사용이 불가하다.


```
public class Student<T> { static T getName(T name) { return name; } } 역시 사용 불가
```


이것을 해결하기 나온 것이 *제네릭 메소드
호출 시에 타입이 정해지기 때문에 선언이 가능하다.


```
public class Student<T> { 
	static <T> T getOneStudent(T id) { 
		return id; 
	} 
}
```

**여기서 주의해야 할 점은 Student<T>의 T와 제네릭 메소드의 T는 완전히 별개라는 것이다.

class의 T는 인스턴스 타입이라고 생각하고 제네릭 메소드의 T는 지역변수라고 생각해야한다.



### 제네릭 메소드를 사용하므로써 얻을 수 있는 이점

```
public static void printAll(ArrayList<? extends Test> list1, ArrayList<? extends Test> list2) { // 로직 }

public static <T extends Test> void printAll(ArrayList<T> list1, ArrayList<T> list2) { // 로직 }

```

두 로직의 차이는 뭘까?

- 첫 번째 메소드는 static으로 선언하기 위해 매개변수에 일일이 타입제한을 걸어주어야 했다.

- 두 번째 메소드는 제네릭 메소드로 선언함으로서 보다 깔끔한 코드를 작성할 수 있게 했다.

### 제너릭 클래스와 독립적

다시 정리하자면 형식과 사용이 제너릭 클래스와 똑같지만, 클래스의 <T>와 제너릭 메소드의 <T>는 다르다 또 제네릭 클래스인지 아닌지는 제네릭 메소드와는 아무 상관없다.

```
class Student<T>{

    public T getOneStudent(T id){ return id; }  // 1
    
    public <T> T getId(T id){return id;} // 2 제네릭 클래스의 T와 다름  
    
    public <S> T toT1(S id){return id; }  // 3
    
    public static <S> T toT2(S id){return id;}  // 4 에러 
}
```

-   1번의 경우 클래스의 제너릭 타입 T를그대로 사용하는 경우다.
-   2번의 경우 클래스의 제너릭 타입 T와 제너릭 메소드 타입 T는 다르다.
-   3번의 경우 static 메소드가 아닌 일반메소드기 때문에 클래스의 타입과 제너릭 메소드의 타입을 같이 사용가능하다.
-   4번의 경우 static 메소드기 때문에 클래스의 제너릭 타입 T를 사용하기 때문에 에러가 발생한다.



---


### LinkedList의 동작 방식

- Double Linked List

---
### Collections methods

---
### Iterator, forEach, stream

---
### File, URL, 표준 입출력, byteStream -> String

---
### JSON vs xml

---
### Inner Class

---
### JDBC (Java Database Connectivity)

