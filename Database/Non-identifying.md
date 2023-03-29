# 식별과 비식별

- 데이터베이스 테이블 사이 관계는외래 키가 기본 키에 포함되는지 여부에 따라
식별 관계와 비식별 관계로 구분한다.

## 식별 관계(identifying Relationship)
- 부모 테이블의 기본 키를 내려받아서 자식 테이블의 기본 키 + 외래 키로 사용하는 관계

## 비식별 관계(Non-Identifying Relationship)
- 부모 테이블의 기본 키를 받아서 자식 테이블의 외래 키로만 사용하는 관계
그리고 외래 키에 NULL을 허용하는지에 따라 필수적 비식별 관계와 선택적 비식별 관계로 나눈다.

- 필수적 비식별 관계(Mandatory)
  외래 키에 NULL을 허용하지 않는다. 연관관계를 필수적으로 맺어야 한다.

- 선택적 비식별 관계(Optional)
  외래 키에 NULL을 허용한다. 연관관계를 맺을지 말지 선택할 수 있다.

데이터베이스 테이블 설계 시 식별 관계나 비식별 관계 중 하나를 선택해야 한다.
최근에는 비식별 관계를 주로 사용하고, 꼭 필요한 곳에만 식별 관계를 사용하는 추세다.

JPA는 두 관계를 모두 지원한다.

## 복합 키: 비식별 관계 매핑

```
@Entity
public class Hello {
	@Id
	private String id1;

	@Id
	private String id2; // 실행 시점에 매핑 예외 발생!
}
```

JPA는 영속성 컨텍스트에 엔티티를 보관할 때 엔티티의 식별자를 키로 사용한다.
그리고 식별자 구분을 위해 equals와 HashCode를 사용해서 동등성 비교를 한다.

그런데 식별자 필드가 하나일 때는 보통 자바의 기본 타입을 사용해서 문제가 없지만,
식별자 필드가 2개 이상이면 별도의 식별자 클래스를 만들고 그곳에 equals와hashCode를 구현해야 한다.

JPA는 복합 키를 지원하기 위해 @IdClass(RDB에 가까운 방법)와 @EmbeddedId(객체지향에 더 가까운 방법)를 제공한다.

@IdClass
*** 기본 키를 복합 키로 구성하려면 복합 키를 매핑하기 위한 식별자 클래스를 별도로 만들어야 한다.

- 여기서 부모, 자식 은 객체의 상속과는 무관하며, 단지 테이블의 키를 내려받은 것을 강조하기 위한 것

```
// 부모
@Entity
@IdClass(ParentId.class)
public class Parent {
	
	@Id
	@Column(name = "PARENT_ID1")
	private String id1; // ParentId.id1과 연결
	
	@Id
	@Column(name = "PARENT_ID2")
	private String id2; // ParentId.id2와 연결

	private String name;

}

```


import java.io.Serializable;

// 식별자 클래스
public class ParentId implements Serializable {
	private String id1; // Parent.id1 매핑
	private String id2; // Parent.id2 매핑

	public ParentId(){}

	public ParentId(String id1, String id2) {
		this.id1 = id1;
		this.id2 = id2;
	}

	@Override
	public boolean equals(Object o) {...}

	@Override
	public int hashCode() {...}
}

// 자식 클래스
@Entity
public class Child {
	@Id
	private String id;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "PARENT_ID1",
        	referencedColumnName = "PARENT_ID1"),
      	@JoinColumn(name = "PARENT_ID2",
        	referencedColumnName = "PARENT_ID2") // name 과 referencedColumnName 이 같으면 referencedColumnName 은 생략 가능하다.
	})


}

@IDClass를 사용할 때 식별자 클래스는 다음 조건을 만족해야 한다.
1. 식별자 클래스의 속성명과 엔티티에서 사용하는 식별자의 속성명이 같아야 한다.

Parent.id1 과 ParentId.id1, Parent.id2와 ParentId.id2 가 같다.
2. Serializable 인터페이스를 구현해야 한다.

3. equals, hashCode를 구현해야 한다.

4. 기본 생성자가 있어야 한다.

5. 식별자 클래스는 public이어야 한다.

- 이 방법은 SQL 친화적인 방법이다.


### @EmbeddedId 방법

객체 지향적인 방법이다.

```
@Entity
public class Parent {
    @EmbeddedId
    private ParentId id;
    private String name;
    ...
}
import java.io.Serializable;
@Embeddable
public class ParentId implements Serializable {
    @Column(name = "PARENT_ID1")
    private String id1;
    @Column(name = "PARENT_ID2")
    private String id2;
    // equals and hashCode 구현
    ...
}
```




