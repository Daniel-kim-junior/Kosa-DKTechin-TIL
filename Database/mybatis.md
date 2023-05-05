# Mybatis

- JDBC connection, 예외처리 등을 추상화 해놓아서 편하다.

Annotation, Xml 등으로 쿼리를 관리할 수 있다.

자바 코드와 완전하게 결합되지 않은 점이 단점이지만 SQL native query로 복잡한 쿼리를 쓸 수 있어서

JPA같은 ORM보다 복잡도는 덜하다.

```
@Mapper로 interface 등록 후 

해당 추상 메서드를 Mapper.xml과 id와 메서드 이름을 동일하게 명시해서 사용하면 된다.

@MapperScan을 main class에 명시해서 mapper를 스캔해야 한다.


```
