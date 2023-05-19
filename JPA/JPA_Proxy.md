# JPA proxy

- 프록시의 특징

프록시 객체는
entityManager.getReference(Member.class, member.getId());
이와 같이 클래스와, id를 통해 만들 수 있다.

프록시 객체는 처음 사용할 때 한 번만 초기화
프록시 객체를 초기화 할 때, 프록시 객체가 실제 엔티티로 바뀌는 것은 아니다
초기화 되면 프로시 객체를 통해서 실제 엔티티에 접근 가능한 말 그대로 프록시

프록시 객체는 원본 엔티티를 상속받는데, 따라서 타입 체크시 주의해야 한다.
(== 비교 실패, 대신 instance Of 사용)
이러한 문제가 있기 때문에 java에서 타입 비교는 ==으로 비교하기 보다는 instance Of를 사용하자

영속성 컨텍스트에 찾는 엔티티가 이미 있으면 em.getReference()를 호출해도 실제 엔티티 반환

이유 :

- 이미 영속성 컨텍스트에 객체가 등록되어 있기 때문에 프록시를 사용한다고 해서 성능 이점을 누릴 수 없기 때문
- JPA에서는 같은 것을 참조하는 객체들은 항상 == 비교시 true를 반환하게 설계되어있음(트랜잭션의 무결성 보장)

영속성 컨텍스트의 도움을 받을 수 없는 준영속 상태일 때, 프록시를 초기화하면 문제 발생
(하이버네이트는 org.hibernate.LazyInitializationException 예외 발생시킴)

### 프록시 확인

- 프록시 인스턴스의 초기화 여부 확인
  PersistenceUnitUtil.isLoaded(Object entity)

- 프록시 클래스 확인 방법
  entity.getClass().getName() 출력

- 프록시 강제 초기화
  org.hibernate.Hibernate.initialize(entity);

- 참고: JPA 표준은 강제 초기화 없음
  강제 호출: member.getName();

### 프록시의 장점

fetch = FetchType.LAZY로 연관관계 객체를 Lazy load 할 수 있는데
가령 member 와 Team의 관계일 때 member를 조회하면 member의 정보만 get해오고 team은 프록시 객체로 설정된다.

향후 team에 대한 정보를 가져올 때 Team에 대한 쿼리를 프록시 객체를 통해 Load한다. (실제 사용 시점에 프록시 초기화)

하지만 Member와 Team을 함께 사용하는 로직이 많다면 즉시 로딩을 하는 편이 좋다.(FetchType.Eager) 이론적으로는...

**실제로는 무조건 Lazy**

### 프록시와 즉시 로딩 주의

- 가급적 지연 로딩만 사용(특히 실무에서)

- 즉시 로딩을 적용하면 예상하지 못한 SQL이 발생

- 즉시 로딩은 JPQL에서 N+1 문제를 일으킨다.

- @ManyToOne, @OneToOne은 기본이 즉시 로딩 -> LAZY로 설정

- @OneToMany, @ManyToMany는 기본이 지연 로딩

### 완벽하게 해결 x 루프 쿼리를 해결하는 방법들

- fetch join으로 한방 쿼리를 만든다
- Entity Graph
- Batch Size

