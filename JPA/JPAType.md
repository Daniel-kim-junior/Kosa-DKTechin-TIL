# JPA의 타입

### 값 타입

- String, int, Integer 등의 순수 primitive type 및 Wrapper 클래스
- Wrapper 클래스는 값 그 자체가 아닌 주소 값을 공유하기 때문에 문제가 될 수 있으나
  자바의 Wrapper클래스는 변경에 닫혀있기 때문에 안전하게 설계가능하다.

### 임베디드 타입

Period(startDate, endDate), Address(city, street, zipcode) 등등 연관 값타입을
모아서 하나의 클래스로 만들어 사용
JPA에서 사용하기 위해 설정이 필요함

- @Embeddable: 값 타입을 정의하는 곳에 표시
- @Embedded: 값 타입을 사용하는 곳에 표시
- 기본 생성자 필수

### 임베디드 타입의 장점

- 재사용
- 높은 응집도
- Period isWork()처럼 해당 값 타입만 사용하는 의미 있는 메소드를 만들 수 있음
- 임베디드 타입을 포함한 모든 값 타입은, 값 타입을 소유한 엔티티에 생명주기를 의존함

값 타입을 묶어서 클래스를 정의한다면 행위까지 정의할 수 있어서 가질 수 있는 이득이 크다
