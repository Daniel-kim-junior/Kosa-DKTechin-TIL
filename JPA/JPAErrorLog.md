1. SQL Table은 허용하지 않는 Identify가 있다.
    - order, user, etc... sql에 관련된 식별자는 사용해서는 안된다.
2. Entity 어노테이션을 붙인 친구들도 JPA의 관리대상이기 때문에
    영속성 컨텍스트에 의해 관리 된다. 즉 자동으로 생성되는 GenerateValue 등을 직접 조작해서는
    안된다!
3. SQL 지연 쿼리를 쓰기 때문에 transation이 종료되는 시점에 SQL쿼리가 나가게 된다
    다만 예외적으로 JPQL로 직접 쿼리를 날리는 경우나 Create 할때 ID 식별자를 SQL에게
    위임하는 경우에는 Flush 시점에 쿼리가 바로 날라가게 된다.
    

    
