# Spring Data JPA

스프링 데이터 - 스프링에서 제공해주는 DB 접근을 위해 제공해주는 API

- RDBMS 뿐만 아니라 NoSQL(key-value, json 등..) 까지 커버링하는 다양한 서버 모듈 지원

### Spring Data JPA

DAO -> Repository로 이름 변화
DB CRUD 메서드 공통 인터페이스로 편의성 제공
JPA Repository를 상속받아서 Custom Repository를 구현해서 사용

Repository 개발 시 인터페이스만 작성해도 런타임 환경에서 Spring Data JPA가 구현 객체를 동적으로 생성해서 주입하므로 데이터 접근 계층을 개발할 때 구현 클래스 없이 인터페이스만 작성해도 된다.

**JpaRepository<T, ID> 인터페이스 상속 후 사용**

### 주요 메서드

| 메서드                   | 설명                                                                                      |
| ------------------------ | ----------------------------------------------------------------------------------------- |
| <S extends T> S save(S)  | 새로운 엔티티는 저장하고, 이미 있는 엔티티는 병합한다.                                    |
| delete(T)                | 엔티티 하나를 삭제한다.                                                                   |
| Optional<T> findById(ID) | ID로 엔티티 하나 조회                                                                     |
| List<T> findAll(...)     | 모든 엔티티를 조회한다. 정렬(sort)이나 페이징(Pageable) 조건을 파라미터로 제공할 수 있다. |
