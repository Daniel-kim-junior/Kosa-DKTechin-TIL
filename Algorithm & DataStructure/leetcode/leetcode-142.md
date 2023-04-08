```
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode heir = head;
        ListNode tor = head;
        boolean capture = false;
        while(heir != null && tor != null) {
            if(heir.next == null || heir.next.next == null)
                return null;
            if(capture) {
                heir = heir.next;
                tor = tor.next;
                if(heir.equals(tor)) {
                    return heir;
                }
            } else {
                heir = heir.next.next;
                tor = tor.next;
                if(heir.equals(tor)) {
                    if(heir.equals(head) && tor.equals(head)) {
                        return head;
                    }
                    capture = true;
                    heir = head;
                }

            }


        }
        return null;
    }
}
```

토끼와 거북이 알고리즘 예시 문제이다.
토끼와 거북이 알고리즘은 그래프의 Cycle이 있는지 확인하는 알고리즘인데

1. head Node에 토끼와 거북이 노드를 위치시킨다.
2. 토끼 Node는 두칸씩 이동하고 거북이 Node는 한 칸씩 이동한다.
3. 만약 싸이클이 있다면 토끼 노드가 거북이 Node를 잡게 된다.

이 문제는 싸이클의 시작점(Tail Pointer가 가르키는)이 어디인지 확인하는 문제이다.

토끼와 거북이 알고리즘의 응용 문제인데

거북이가 토끼에게 시작점에서 잡히게 된다면 싸이클의 시작점은(Tail 포인터가 가르키는) 시작점이 되게 된다.

그 이외의 경우에는 거북이는 절대 한바퀴의 싸이클을 돌 수 없기때문에(토끼에게 잡히기 전까지)

\*\*\*(싸이클의 시작점까지 거북이가 이동하는 거리(싸이클 한바퀴) + 시작점에서 싸이클의 시작점까지의 거리) 전체 노드의 길이가 된다.
