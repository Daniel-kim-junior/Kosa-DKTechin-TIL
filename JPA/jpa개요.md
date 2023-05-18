# JPA 개요

자바 Object와 Table을 서로 mapping 시켜 주는 기술

Java Persistence API

- 구현체
  - Hibernate
  - EclipseLink 등

장점

1. 생산성
2. 유지보수 - SQL을 다시 작성해야 하는 등 단순 반복 작업과 실수를 줄여준다
3. 패러다임의 불일치 해결 - 자바 객체와 SQL을 통합하는
4. 성능 - 애플리케이션과 데이터베이스 사이에서 성능 최적화 기회를 제공한다.
5. 데이터 접근 추상화와 벤더 독립성

- DB 종속성 해결

### EntityManager

- Entity를 관리하는 객체
- 데이터베이스에 대한 CRUD 작업은 모두 영속성 컨텍스트를 사용하는 EntityManager 객체를 통해 이루어진다.
- 동시성의 문제가 발생할 수 있으니 스레드 간에 공유하지 않는다.
- 모든 데이터 변경은 트랜잭션 안에서 이루어져야 한다.

### 엔티티(Entity)

JPA에서 **엔티티란 DB 테이블에 대응하는 하나의 객체**라고 생각할 수 있다.
@Entity 어노테이션이 붙은 클래스를 JPA에서는 엔티티라고 부르며, 이 엔티티는 영속성 컨텍스트에 담겨 EntityManager에 의해서 관리된다.

```
EntityManager em = factory.createEntityManager();
TypedQuery<Visitor> q = em.createQuery("select t from Visitor t", Visitor.class);
List<Visitor> list = q.getResultList();
em.close();
```

TypedQuery : 꺼내올때 작업을 전처리해주기 때문에 후처리가 쉽다
Query : 꺼내오고 후처리를 해줘야 할게 많다

### 표준 API(자바만의 API)

java. ---> 코어, 기본
javax. ---> 확장


### JPA 기본키 매핑 전략

IDENTITY : 기본키 생성을 DB에 위임
SEQUENCE : 기본키 생성 유일한 값을 순서대로 생성하는 특별한 데이터베이스 오브젝트이다.


