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
