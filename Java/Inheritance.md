
# 상속(extends)
   

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