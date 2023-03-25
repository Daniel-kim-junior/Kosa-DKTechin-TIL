
## DataBase

- ***Database란?
	한 조직의 여러 응용 시스템들이 공용(Shared)하기 위해
	통합, 저장, 운영데이터 집합
	컴퓨터 시스템과 무관하게 데이터의 구조적 집합을 의미한다(종이, 장부 등도 가능)
	논리적, 물리적 데이터의 집합이라고 생각한다.
----

### DBMS(Database Management System)

- DB 관리를 위한 컴퓨터 시스템
- 전사적 정보 관리
- 관련된 데이터의 Set
- 데이터에 접근하는 프로그램 Set
- 효율적이고 편한 사용을 위한 환경


**무수히 많은 Transaction의 향연

---

### Transaction이란??

	트랜잭션이란 - 데이터베이스의 상태를 변화시키기 위해 수행하는 작업 단위를 뜻한다.
	데이터 베이스의 상태를 변화시키는 것은 무엇을 의미하는가?
	바로 질의어 
		SELECT, Update, Insert, Delete 등을 통해 데이터베이스에 접근하는 것이다.

**착각하기 쉬운점은 작업의 단위는 하나의 질의 문장이 아니라는 점!!!

```
작업단위는 많은 질의어 명령문들을 개발자가 정하는 기준에 따라 정하는 것을 의미한다!

예를들어 게시판에서
사용자가 게시글을 작성하고, 작성하기 버튼을 상호작용하고 업데이트 된 게시판을 보게 되었을때

데이터베이스에서 일어나는 일은
Post Request가 가고 서버 DB에 게시글이 저장되고 다시 클라이언트로 Response되어 Redirection(Select)이 일어나서 Update가 된다.

여기서 일어나는 작업은 Insert와 Select가 함께 일어난다

이런 일련의 과정 설계를 Transaction이라고 한다.

트랜잭션 설계를 처음부터 잘해야 효율성 있는 시스템을 개발할 수 있다.

```


---

## Transaction의 4가지 특징

*ACID를 잘 지키자*

*Atomicity(원자성)* - **원자성은 Transaction이 DB에 전부 반영되거나 전부 반영되지 않아야 한다는 특성이다. 트랜잭션은 사람이 설계한 논리적인 작업 단위기 때문에 일처리가 작업단위 별로 일어나야한다.

*Consistency(일관성) - **작업처리 결과가 항상 일관성이 있어야 한다는 것 트랜잭션이 일어날 때 다른 트랜잭션으로 인해 DB가 변경되더라도 트랜잭션이 일어났을 당시에 DB를 참조하여 진행해야 한다. 이렇게 함으로써 사용자가 일관된 데이터를 볼 수 있게 만들어야 한다.

*Isolation(독립성) - **둘 이상의 트랜잭션이 일어나고 있을 때 서로 간섭하거나 침범해서는 안되는 성질이다. 트랜잭션이 끝날때까지 다른 트랜잭션이 참조할 수 없다

*Durability(지속성) - **성공적인 트랜잭션이 일어나면 트랜잭션의 결과가 영구적으로 시스템에 반영되어야 하는 성질이다.

---
## RollBack과 Commit

***Commit - 트랜잭션이 성공적으로 끝났을 때, 데이터베이스가 일관성있는 상태에 있을 때 성공적인 트랜잭션 종료 연산이다.

***RollBack - 하나의 트랜잭션의 원자성이 깨지거나 트랜잭션 처리가 비정상적으로 종료 되었을때
이전 Commit 이후의 Transaction 처리 단위대로 RollBack을 진행 할 수 있다.

#Commit 이전의 결과로는 되돌아갈 수 없는 것을 주의하자

---
## RDBMS(MySQL)

	MySQL의 장점 중 하나는 여러개의 스토리지 엔진을 지원한다는 것이다.

	스토리지 엔진이란?
	DB에서 데이터를 어떤 방식으로 저장하고 접근할 것인지에 대한 기능을 제공하는 소프트웨어
	각 스토리지 엔진의 특성에 따라 속도와 안정성 그리고 기능 제공의 차이점이 발생한다.

**1.MyISAM

**MySQL 기본 스토리지 엔진 - 데이터 저장에 제한이 없고 매우 효율적 방식
    Full-Text 인덱스를 지원하며 특정 인덱스에 대한 메모리 캐쉬 지원
    Transaction을 미지원
    그러나 Table Lock을 지원하여 잦은 변경 및 삭제에 대해서는 좋은 성능이 나오지 못하나
    데드락을 예방할 수 있음
    테이블 작업시 특정 행을 수정하려고 하면 테이블 전체에 락이 걸려 다른 스레드가 작업하지 못한다. (Table Level Lock)
    Transaction을 미지원하기 때문에 작업 도중 문제가 생겨도 DB안에 데이터가 들어가기 때문에 주의해야함
    Select가 많거나 중요하지 않는 데이터를 다루는 Logging 작업 저장에 어울릴듯 하다.


**2. InnoDB
	ACID Transaction을 지원한다. MyISAM보다 데이터 저장비율이 낮고, 데이터 로드 속도가 느리다.
	(아무래도 Transaction을 지원하다 보니 데이터 검증을 위한 Cost가 증가하는 듯)
	특정 데이터와 인덱스에 대해 메모리 캐쉬 지원함
	데이터 압축은 불가능하며 자동 에러 복구 기능을 제공
	Table Lock이 아닌 ROW 레벨의 Lock을 지원
	테이블 작업시 테이블 전체가 아닌 해당 행만 잠기게 되며 나머지 부분은 수정 가능하여
	Insert, update, delete에 대한 속도가 빠르다.
	주로 데이터 입력 및 수정이 빈번한 높은 퍼포먼스를 요구하는 시스템에 적합하다.

**3. Cluster
	Transaction을 지원하고 모든 데이터와 인덱스가 메모리에 존재
	그러므로 속도가 매우 빠르다. PK 사용 시 최상의 속도를 나타냄

**4. Archive
	MySQL 5.0이후의 엔진 자동적으로 데이터 압축 지원(InnoDB와 다른 점) 다른 엔진에 비해 80% 저장 공간 절약 효과를 가지고 있다고 한다. 빠른 데이터 로드 속도를 자랑하지만
	Select와 Insert만 가능하다.

**5. Federated
	MySQL 5.0부터 새롭게 도입된 엔진으로 물리적 데이터베이스에 대한 논리적 데이터베이스를 생성하여 원격 데이터를 컨트롤 할 수 있다. 실행속도는 네트워크 요소에 따라 좌우되면 테이블 정의를 통한 SSL 보안 처리를 한다. 분산 데이터베이스 환경에 사용한다

---
## DB 용어 (카디널리티, 스키마, 릴레이션, 어트리뷰트, 튜플, 도메인, 차수)

*1. 릴레이션(엔티티, 테이블)
```
릴레이션이란 관계형 데이터베이스에서 정보를 구분하여 저장하는 기본 단위이다.
ERD(Entity Relationship Diagram)의 가장 큰 구성요소 중 하나다.

특징:
	- 한 릴레이션에는 똑같은 튜플(Tuple)이 포함될 수 없다. (Row의 중복 불가)
	- 한 릴레이션에 포함된 튜플 사이에는 순서가 없다.
	- 튜플들의 삭제, 삽입 등의 작업으로 릴레이션은 시간에 따라 변한다.(Index 또한 변함)
	- 릴레이션 스키마를 구성하는 속성들간의 순서는 중요하지 않다.
	- 속성의 유일한 식별을 위해 속성의 명칭은 유일해야한다. 속성을 구성하는 값은 동일한 값이 있을 수 있다.
	- 릴레이션을 구성하는 튜플을 유일하게 식별하기 위해 속성들의 부분집합을 키(Key)로 설정한다.
	- 속성의 값은 논리적으로 더 이상 쪼갤 수 없는 원자 값만을 저장한다.

```

*2.속성(attribute)
```
하나의 릴레이션은 현실세계의 어떤 개체(entity)를 표현하고 저장한다.

엔티티는 논리적 혹은 물리적 개념 둘다 가능하다.

속성은 내가 정의내리고 싶은 Entity의 특성 항목이다.
```

*3.차수(degree)*
```
한 릴레이션 안에 있는 속성 수를 차수 라고 한다.

엔티티의 최소 차수는 1이다.

그렇기 때문에 모든 엔티티(릴레이션)은 적어도 하나 이상의 속성을 가지고 있어야 한다.
```

*4.튜플(Tuple)
```
테이블의 각 행을 레코드라고 한다.
레코드를 튜플이라고 부른다.
```

*5.카디날리티(Cardinality)*
```
카디날리티는 릴레이션 튜플의 개수이다.

그래서 아직 삽입되지 않은
릴레이션(엔티티, 테이블)
은 카디날리티가 0일 수 있다.

카디날리티는 변동성을 가진다.
```

*6. 도메인(Domain)*
```
도메인은 릴레이션에 포함된 속성들이 각각 가질 수 있는 값들의 집합이다.

예를들어 집이라는 릴레이션에
거래종류라는 속성이 있따고 하자
거래종류에는 (전세, 월세, 매매)가 있다. 이 집합을 X라고 이름 붙이자

X라는 도메인만 거래종류 속성이 가질 수 있다.

하나의 도메인을 여러 속성에서 공유할 수는 있고 속성의 이름과 도메인의 이름은 서로 동일하지 않아도 된다.

```

*7.스키마*
```
스키마는 데이터베이스의 구조와 제약조건에 관한 전반적인 명세를 기술한 메타데이터의 집합이다.

엔티티와 속성 관계 및 데이터 조작 시 데이터 값들이 갖는 제약 조건 전반에 대한 정의다.

스키마는 세개로 나누어진다.

1. 외부 스키마 : 외부에서 현실세계에 가까운 엔티티들의 나열이다.
2. 개념 스키마 : 개발자가 정의 내리는 속성에 대한 정의가 있다.
3. 내부 스키마 : 속성들의 더 세부적인(DB 친화적인 index 등...) 정보들을 담고 있는 스키마이다. 

```


***논리 모델과 물리모델
Entity  ------ Table
속성 -------- 컬럼
  관계, 릴레이션
키 그룹 ----- Index


## Primary key

```
릴레이션에서 튜플을 구분하기 위하여 사용하는 기본 키

하나의 속성, 여러개의 속성을 묶은 복합키가 가능하다.
관리자에 의해 릴레이션 생성시 정의되며, 자동으로 Index가 생성된다.

동일한 PK는 존재할 수 없다.

Candidate Key(후보 키) - 튜플을 식별 할 수 있는 최소한의 속성 집합
PK가 될 수 있는 후보들
유일성과 최소성을 충족시킨다면

Alternative Key(대체 키) - 후보 키 중 기본 키가 아닌 속성
```

### Foreign Key
```
다른 릴레이션의 기본 키를 참조하는 키

다른 릴레이션의 튜플 저장 관리

릴레이션간의 관계 정립

Null이 가능 참조 하지 않을 수 있다.
```

---

### SQL 실행 순서
FROM(Relation Select) -> Where(Relation Read and where check) -> select(Projection) -> order by

우선순위는 헷갈리기 때문에 괄호로 감싸는게 알맞다

---

### 집계 함수

***여러 행으로 부터 하나의 결과 값을 반환

```
AVG
Count
MAX
MIN
SUM
STDDEV
VARIANCE
등 ...
``` 

***집계 함수 주의할 점 
1. Group by에 참여한 Column이나 Aggregate 함수만 사용 될 수 있다
2. Where 절은 Aggregation 이전 단계에 적용된다.
3. Null인 데이터는 집합 함수에 참여하지 않는다. 

Count( * )은 해당 Row의 총 개수를 Null 포함

***또 Having 절은 Aggreation 이후 filtering기능으로 적용된다.


## CASE

- CASE와 중첩 CASE문으로 조건문을 처리가능
- 주의점 : Group by나 order by를 할 때 CASE를 같이 써줘야 한다는 것


## Index

	Index에 대한 고찰...
	Index란?
	- Index는 TABLE 에 저장되어 있는 데이터를 쉽게 찾기 위한 색인(목차)
	- 별도의 메모리를 할당하여(특정 컬럼 대상) 튜플의 값과 Pointer를 저장하여
	- 전체 Table을 검색하지 않아도 해당 튜플을 바로 찾아갈 수 있는 구조

**별도의 메모리로 운영하기 때문에, Insert / Update / Delete와 같은 데이터 변경시에
Index 공간에도 더 많은 변경이 있어서 성능이 조금 느려짐, Select의 성능은 훨씬 늘어난다.
- 데이터 변경에 대한 행위만 느려진다!!
- 검색은 향상된다


***B+TREE DataStructure

	Index 기반으로 하는 B-Tree 자료구조는 크게 3가지 레벨로 구성되어 있다.
	ROOT -> BRANCH(Internal) -> Leaf
	상위 레벨에서는 하위 레벨에 대한 key 값의 Range 정보를 가지고 있다는 것


**Range Scan?

	특정 Key 값의 범위에 대해서만 검색을 할 수 있는 것


**Leaf Node에 들어있는 데이터는 실질적인 데이터가 담겨있는 Row를 가리키는 주소값
- 이 주소값을 빠르게 찾기 위한 query를 작성해야 한다!!!



## Index 탐색 과정

![[Pasted image 20230303175832.png]]

- **수직적 탐색 :     인덱스 스캔 시작지점을 찾는 과정
- **수평적 탐색 :     데이터를 찾는 과정

#수직적탐색 은 Root -> Branch -> Leaf로 탐색하여 검색조건을 만족하는 첫 번째 레코드를 찾는 것을 목표.(이진 탐색)

#수평적탐색 은 수직적 탐색에서 찾은 첫 번째 레코드를 바탕으로, 찾고자 하는 데이터가 더 안나올 때 까지 Leaf Block을 수평적으로 스캔.
여기서 획득한 Pointer를 기반으로 실제 테이블에 엑세스.

```
그렇다면 어떻게 설계하는 것이 올바르게 인덱스를 설계하는 것일까?

1. 수직적 탐색에서 최대한 빠르게 첫 번째 레코드 시작점을 찾는것
2. 수평적 탐색에서 해당하는 범위 레코드 수를 줄이는 것
```


### 수평적 탐색에서 해당하는 범위 레코드 수를 줄이는 법

	범위 레코드의 수를 줄이는 방법은 바로 카디널리티가 높아야 한다는 것.
	즉 중복이 최대한 없는 컬럼이 유리하다는 것
	(중복 value가 많다는 것은 그만큼 많은 범위의 레코드를 검색해야 한다.)


### 결합 Index로 해결 해보자

	- 아래와 같은 Table 을 만들고, 데이터는 대략적으로 100만건
```
import org.springframework.data.annotation.CreatedDate 
import java.math.BigDecimal 
import java.time.ZonedDateTime 
import javax.persistence.* 
@Entity @Table(name = "order_table") 
class OrderTable( 
@Id @GeneratedValue 
val id: Long? = null, 
@Column(name = "purchaser_id", nullable = false) 
val purchaser: Long, 
@Column(name = "seller_id", nullable = false) 
val seller: Long, 
@Column(name = "gift", nullable = false) 
val isGift: Boolean, 
@Column(name = "service_location", length = 20) 
val serviceLocation: String?, 
@Column(name = "pay_amount", scale = 2) 
val payAmount: BigDecimal, ) 
{ 

@CreatedDate 
@Column(name = "created_at", nullable = false) 
var createdAt: ZonedDateTime = ZonedDateTime.now() 

override fun equals(other: Any?): Boolean { 
if (this === other) 
return true 
if (other !is OrderTable) return false 
if (id != other.id) return false 

	return true 
} 
override fun hashCode(): Int { return id.hashCode() } }
```


***DDL
```
-- auto-generated definition 
create table order_table ( 
id bigint not null primary key, 
created_at datetime not null, 
gift bit not null, 
pay_amount decimal(19, 2) null, 
purchaser_id bigint not null, 
seller_id bigint not null, 
service_location varchar(20) null )
```


***카디널리티 크기 순서
```
select count(distinct id) 'id', 
count(distinct purchaser_id) 'purchaser', 
count(distinct seller_id) 'seller', 
count(distinct gift) 'gift', 
count(distinct pay_amount) 'pay_amount', 
count(distinct service_location) 'service_location' 
from order_table ;
```
![[Pasted image 20230303184214.png]]


### 카디널리티에 따른 성능 차이

	주문 데이터를 찾아보고 싶은데, 요구사항은 구매유형(buy/gift), 샐러(seller_id), 결제금액(pay_amount) 기준으로 집계

	2가지 결합 Index를 만들어보자
	첫번째는 카디널리티가 낮은 순서에서 높은 순서대로 진행
	두번째는 카디널리티가 높은 순서에서 낮은 순서대로 진행


```
create index order_table__gift_index on order_table (gift, seller_id, pay_amount); create index order_table__pay_amount_index on order_table (pay_amount, seller_id, gift);
```

1. 첫번째 결과 (***카디널리티가 낮은 순서에서 높은 순서의 결합 Index)
```
test_db> select * from order_table use index(order_table__gift_index) where pay_amount between 200000 and 300000 and seller_id = 5000 and gift is true [2022-06-26 18:58:06] 9 rows retrieved starting from 1 in 1 s 882 ms (execution: 1 s 863 ms, fetching: 19 ms)

1초 882 ms 소요
```

2. 두번째 결과 (***카디널리티가 높은 순서에서 낮은 순서의 결합 Index)
```
test_db> select * from order_table use index(order_table__pay_amount_index) where pay_amount between 200000 and 300000 and seller_id = 5000 and gift is true [2022-06-26 18:58:32] 9 rows retrieved starting from 1 in 76 ms (execution: 55 ms, fetching: 21 ms)

76 ms 소요
```

***카디널리티에 따라 쿼리 성능이 확연히 차이나는것을 알 수 있는 부분
결과적으로 비교 연산에 대한 횟수를 줄이기 위해서라도 카디널리티는 Index 설계에 아주 중요한 요소를 하게 된다.


### 복합 Index에서 조건절에 누락이 발생하게 된다면?

- 복합 Index 에서 첫번째 column 은 항상 조건절에 있어야 된다.


### Index가 항상 모든 검색에서 효율적인가?

	![[Pasted image 20230303185308.png]]
	Index를 통해 검색하면 항상 속도가 빠르기 때문에 좋아보이지만
	Index를 통해 검색한다는 것은 하나의 튜플(Block)을 I/O로 실어나르는 Process가 일어난다.
	그렇기 때문에 소량의 데이터를 빠르게 읽을 때 굉장히 효과적이다.

	반대로 Table Full Scan 한다는 것은 Multi Block I/O 기반으로 읽게된다.
	Mutli Block I/O는 여러개의 Block을 한번에 I/O로 실어 나른다.
	그렇기 때문에 대량의 데이터에 I/O가 일어나면 효과적이다


***그래서 Table Full Scan이 항상 나쁘다고 볼 수 없다
대량의 데이터를 추출하는 Process에는 Table Full Scan이 효과적!!


#TableFull scan

Multi Block I/O 이기 때문에 집계성 SQL / 배치 프로그램에 유용 (수백만 ~ 수천만)

#IndexRange scan

Single Block I/O 라서 소량의 데이터를 추출하는 비즈니스 쿼리에 유용


## Join

![[Pasted image 20230303190624.png]]

### CROSS JOIN(상호 조인 - 카테시안 곱)
![[Pasted image 20230303190754.png]]
- 모든 행을 조인시키는 기능 (두 테이블의 각 행의 개수를 곱한 수 만큼 출력)


### SELF JOIN(자체 조인)


## 서브 쿼리란?

하나의 SQL문안에 포함되어 있는 또 다른 SQL 문을 말합니다.

---
#주의사항
1. 서브 쿼리를 괄호로 감싸서 사용한다.
2. 서브 쿼리는 단일 행 또는 복수 행 비교 연산자와 함께 사용 가능하다.
3. 서브 쿼리에서는 ORDER BY 를 사용하지 못한다.
---

***서브쿼리가 사용 가능한 곳

1. SELECT 절

2. FROM 절

3. WHERE 절

4. HAVING 절

5. ORDER BY 절

6. INSERT 문의 VALUES 절

7. UPDATE 문의 SET 절
---

## WHERE 절

***단일 행 서브쿼리

	서브 쿼리가 단일 행 비교 연산자(=, <, >, <=, >=, <>)와 함께할 때는 서브쿼리 결과 건수가 반드시 1개 이하여야 합니다.
	만약 결과가 2건 이상일 경우 오류가 발생합니다.

```
SELECT C1, C2, C3
FROM T1
WHERE C1 = (SELECT C1
            FROM T2
            WHERE C2 = '3')
ORDER BY C1, C2, C3;

잘못 설계한 SQL문일 가능성이 높다
```

```
그룹 함수를 사용한 쿼리

SELECT C1, C2, C3
FROM T1
WHERE C1 <= (SELECT AVG(C1)
            FROM T2
            WHERE C2 = '3')
ORDER BY C1, C2, C3;

단일 행 서브쿼리로 적합하다.
```
---

***다중 행 서브쿼리**
- 서브 쿼리의 결과가 2건 이상 반환될 수 있으면 반드시 다중 행 비교 연산자(IN, ALL, ANY, SOME)와 함께 사용해야 한다.

|다중 행 연산자|설명|
|------|---|
|IN|서브쿼리의 결과에 존재하는 임의의 값과 동일한 조건|
|ALL|서브쿼리의 결과에 존재하는 모든 값을 만족하는 조건(AND)|
|ANY|서브쿼리의 결과에 존재하는 어떤 하나의 값이라도 만족하는 조건 (OR)|
|EXISTS|서브쿼리의 결과를 만족하는 값이 존재하는지 여부를 확인하는 조건|

---

***다중 컬럼 서브쿼리
	- 서브 쿼리의 결과값이 여러개의 컬럼이 반환되어 메인 쿼리의 조건과 동시에 비교하는 것을 의미한다.
	
```
SELECT C1, C2, C3
FROM T1
WHERE (C1, C2) IN (SELECT C1, C2
                   FROM T2
                   WHERE C2 = '3')
ORDER BY C1, C2, C3;
```

---
***연관 서브쿼리
	-서브 쿼리 내에 메인쿼리 컬럼이 사용된 서브쿼리이다.
```
SELECT T1.C1, T1.C2, T1.C3 FROM T1 T1 WHERE (T1.C1, T1.C2) IN (SELECT T2.C1, T2.C2 FROM T2 T2 WHERE T2.C2 = T1.C2) -- 메인 쿼리의 컬럼을 서브쿼리에 사용 
ORDER BY T1.C1, T1.C2, T1.C3;
```

---
## Select절(스칼라 서브쿼리)

	- 스칼라 서브쿼리는 한 행, 한 컬럼만을 반환하는 서브쿼리

```
SELECT T1.C1, (SELECT AVG(T2.C1) FROM T2 T2)
FROM T1 T1;
```
---
## FROM절(인라인 뷰)

	- 인라인 뷰는 SQL 문이 실행될 때만 임시적으로 생성되는 동적인 뷰이기 때문에 데이터베이스에 해당 정보가 저장되지 않는다.
	- 인라인 뷰는 동적으로 가상의 테이블을 만들어 조인 방식을 사용하는 것과 같다

```
SELECT T1.C1, T2.C1, T2.C2
FROM T1 T1,
     (SELECT C1, C2 FROM T2) T2
WHERE T1.C1 = T2.C1;
```

---
## HAVING절

	- 그룹함수와 함께 사용될 때 그룹핑된 결과에 대해 부가적인 조건을 주기 위해 사용합니다.

```
SELECT T1.C1, T2.C1, T2.C2 FROM T1 T1, T2 T2 WHERE T1.C1 = T2.C1 GROUP BY T1.C1, T2.C1, T2.C2 HAVING AVG(T1.C1) < (SELECT AVG(C1) FROM T2 );
```

---

## Update문

```
UPDATE T1 T1
   SET T1.C1 = (SELECT T2.C3 FROM T2 T2 WHERE T2.C1 = T1.C1);
```

---

## Insert문

```
INSERT INTO T1 (C1, C2, C3) SELECT C1, C2, C3 FROM T2;

INSERT INTO T1 (C1, C2, C3) VALUES ((SELECT C1 FROM T2), (SELECT C2 FROM T2), (SELECT C3 FROM T2));
```





