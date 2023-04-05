# 상속관계 매핑

- 관계형 데이터베이스는 상속 관계 X
- 슈퍼타입 서브타입 관계라는 모델링 기법(RDB)이 객체 상속과 유사
- 상속관계 매핑 : 객체의 상속과 구조와 - DB의 슈퍼타입 서브타입 관계를 매핑


### 조인 전략
  객체의 상속 관계 (abstract or another)를 조인으로 pk와 type column등으로 조인해서 데이터를 가져오는 전략
  다중 테이블 전략이다. (공통 분모는 부모 테이블에)

### 단일 테이블 전략
  하나의 테이블에 type column으로 객체들을 구분하는 전략

### 구현 클래스마다 테이블 전략
  서로 다른 테이블에 구현 (모든 필드를 따로)



***JPA default 전략은 단일 테이블 전략


```

@Inheritance(strategy = InheritanceType.XXX)

  - Joined : 조인 전략 테이블 생성 (비지니스 적으로 중요하고 복잡하면)
    - 장점 : 테이블 정규화
            외래 키 참조 무결성 제약조건 활용 가능
            저장공간 효율화
    - 단점 : 조회 시 조인을 많이 사용, 성능 저하 (But Query를 잘 짜면...)
            조회 쿼리가 복잡함 (복잡성 up)
            데이터 저장시 Insert SQL 2번 호출
            그래도 정석 (정규화, 설계의 깔끔함, 객체와 어울리는 등)


  - SINGLE_TABLE : 단일 테이블 전략 (단순할 때, 확장 가능성 없을때)
    - 장점 : 단일 테이블이기 때문에 성능 우수(조인 X)
    - 단점 : 자식 엔티티가 매핑한 컬럼은 모두 null 허용 (공통 컬럼 빼고 전부 Null 허용해야함 무결성 down)
            단일 테이블에 모든 것을 저장하므로 테이블이 커질 수 있다. 상황에 따라 조회 성능이 더 느려질 수 있음


  - TABLE_PER_CLASS : 구현 클래스마다 테이블 전략 (abstract Class를 상속받아 구현함 부모 타입의 테이블은 존재하지 않게 된다)
    - 장점 : not null 사용 가능... 서브타입을 명확하게 구분해 처리할 때 효과적...

    - 단점 : 각각의 테이블이 구현되어 있기 때문에  부모 타입으로 추상화(item_id) 되어 있는 객체 지향 쿼리를 JPA로 날리게 된다면
            Full outer join으로 관련 테이블들(Album, Book, Car)을 전부 뒤져서 가져오게 된다. (Select에서 치명적 단점 쓰면 안됨!!!!!)
            통합 쿼리가 매우 어려움, 변경에 닫혀있어서...


@DiscriminatorColumn : type Column을 자동 생성
(생성해주는 것이 좋음 DB에서 작업할 때 튜플을 명확하게 확인 가능)

@DiscriminatorValue : 자식 Entity에 Column Value 변경 가능

```
