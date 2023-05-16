# 5월_16일

DAO --- interface

    VisitorJdbcDAO
    VisitorMybatisDAO
    VisitorJpaDAO
    
**다형성을 이용해 구현체를 나누어 확장성을 도모하자**

- 교수님은 VisitorController와 VisitorApp VisitorDAO를 나누어서 구성하였고 Controller에서 DAO에 접근하는 식으로 구성했다.

EntityManagerFactory는 싱글톤 객체 -> 단 한번만 만들자

em.find() -> pk로 찾을 수 있는 유용한 메서드

> 영속성 컨텍스트에 영속 상태로 등록되어 있는 객체는 Setter로 바꿔준다면 JPA가 변경 사항을
더티 체킹해서 DB에 변경사항을 commit 한다

``` Update, Insert, delete 
EntityManager.getTransaction().begin();
EntityManager.remove(student); // DB에 적용해야 하는 로직
EntityManager.getTransaction().commit();
```

EntityManager는 스레드 Safe하게 설계되어있기 때문에 스레드 간 공유하지 않는것이 중요하다.(트랜젝션)

@CreationTimestamp - 자동으로 날짜 정보를 생성해주는 어노테이션

---

### JPA 연관관계 매핑 기초

엔티티는 다른 엔티티와 연관관계가 대부분 존재한다. 
(테이블의 연관관계와는 다르다)

연관관계는 크게 단방향과 양방향으로 구분함
자바 코드에서 객체 참조관계를 뜻한다.

**참조하고 있는 객체의 Type으로 정의해야 한다.**

오라클은 camelCase를 snake_case로 바꿈 
따라서 JPA를 사용할 땐 필드 이름을 명확하게 지정하는것이 좋다 (커스텀 하게)

Forien key는 참조하고 있는 Many의 위치에 있는 member에 위치하게 된다.

member --> team과의 관계
단방향 참조 관계(member에 등록되어 있는 Team을 통해 객체를 탐색한다.)
(Team으로 member를 탐색하지 않는 아키텍처)
참조 되기만 하는 객체는 처리해 줄 로직 X

참조 하는 필드는
@ManyToOne(다대일의 DB 참조 관계 정의 어노테이션, 객체 참조관계와는 다름)
@JoinColumn(name = "TEAM_ID")
private Team team;
이런식으로 어노테이션과 옵션 등을 설정 해 줘야한다.


@ManyToOne(optional = false 연관 엔티티가 항상 있어야함 - meeting이 없다면 댓글이 존재할 수 없기 때문에 필수 요소로 지정 옵션)
@JoinColumn - 필수 x, 지정하지 않으면 create 할 때 Meeting이 들고 있는 Primarykey에 자동으로 지정 (ex.. 컬럼명 : refid_meeting_id)
private Meeting refid;

---

### jpql에서 join

"select m from Member m join m.team t where t.teamName = :tn"
과 같이 객체 참조 연관관계를 이용해 join 하면 된다.
참조하고있는 객체를 먼저 쿼리의 테이블로 설정한다.

- null을 줄 수도 있음
"select m from Member m where m.team = null"


---
### Spring Data JPA

