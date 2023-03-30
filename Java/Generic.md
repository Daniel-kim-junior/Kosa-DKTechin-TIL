# 제네릭


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





