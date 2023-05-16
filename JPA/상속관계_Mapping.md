# 상속관계 매핑

- Item
  - Album
  - Movie
  - Book

### 조인 전략

@Inheritance(strategy=InheritanceType.XXX)

- 각각 테이블로 변환해서 조인해서 값을 조회
- JOINED

### 단일 테이블 전략

- 논리 모델을 한 테이블에 다 넣는 전략
- SINGLE_TABLE
- null 허용 (데이터 무결성 흠집)

### 구현 클래스마다 테이블 전략

- 공통 분모 없이 각각 테이블에 넣는 전략
- TABLE_PER_CLASS
- 모든 상속 테이블을 union해서 가져오는 문제점!! 쓰지말기를


@MappedSuperClass : 공통 분모를 묶어서 속성만 상속받아 쓸 수 있는 클래스 (상속 관계 매핑 x, 엔티티X, 테이블과 매핑X)
부모 클래스를 상속 받는 자식 클래스에 매핑 정보만 제공
조회, 검색 불가(em.find(BaseEntity) 불가)
직접 생성해서 사용할 일이 없으므로 추상 클래스 권장
