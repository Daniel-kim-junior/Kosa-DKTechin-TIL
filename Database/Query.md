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
