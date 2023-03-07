
## 1. Hard Reference

	GC에 일반적으로 Collecting 되지 않는 strongly reachable한 객체
	우리가 주로 사용하는 StringBuffer sb = new StringBuffer(); 같은 선언으로 생성되는 객체를 지칭한다.

이러한 Hard Reference의 단점 중 하나는 예를들어 Map의 Key를 객체로 설정했을 때 Hard Reference를 갖는데 더 이상 객체를 사용하지 않게 되면 해당 Entry를 삭제하면 되나, 그렇지 않으면 Memory leak이 발생한다.




