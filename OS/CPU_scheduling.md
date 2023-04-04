# CPU Scheduling

### CPU burst
  - running : load store || add store || read from file || store increment index write to file
  CPU bound - cpu burst > I/O burst

### I/O burst
  - waiting -> ready : wait for I/O
  I/O bound - I/O burst > cpu burst

I/O bound가 CPU bound 보다 빈번함

### CPU scheduler를 구현하여 I/O bound를 줄여보자

  - ready 상태에 있는 Process중 CPU allocates를 위한 Process선택 알고리즘

  - 어떻게 다음 프로세스를 선택할 수 있을까?

  - LinkedList or Binary Tree?
  - FIFO Queue: First-In-First-Out (Round- robin)
  - Priority Queue: 프로세스 우선순위를 어떻게 고려할 것인가?
  - 선택하는 친구

### Preemptive(선점형) vs Non-preemptive (비-선점형):
  - Non-preemptive : 프로세스가 CPU를 선점하면 Terminate 불가 (자발적으로 나오기 전까지)
  - Preemptive : 스케줄러가 프로세스를 쫓아낼 수 있다.

  
### Decision Making
  1. running to waiting state
  2. running to ready state
  3. waiting to ready state
  4. process terminates

  1, 4일 때 : non-preemptive 자발적 종료
  2, 3일 때 : preemptive vs non-preemptive

### dispatcher
  - CPU core의 컨트롤을 다른 프로세스에게 넘기는 Context Switch를 하게 해주는 모듈
  - Context를 다른 Context로
  - Switching to user mode
  - jumping to the proper location to resume the user program(적절한 위치로 유저 프로그램을 Resume 시키는 작업)
  - switching 해주는 친구
  - dispatcher should be as fast as possible
  - dispatcher latency 발생 (save state time, restore state time) - 가급적 짧아야 한다.

  - Linux (vmstat 1초 3개) - 컨텍스트 스위칭 확인
    cat /proc/1/status - nonvoluntary - 선점형, voluntary - 비-선점형

### Scheduling Criteria(목적)
  - CPU utilization : CPU as busy as possible
  - Throughput : MAX the number of process completed per time unit
  - Turnaround time : 실행에서 종료까지 시간 최소화
    - How long does it take to execute a process?
    - from the time of submission to the time of completion
  ***중요 Waiting time :
    - the amount of time that a process spends waiting in the ready queue.
  
  - Response Time:
    - the time it takes to start responding


***어떤 프로세스에게 CPU를 할당할까?

  - FCFS : First Come First Served
    - The simplelest CPU-scheduling algorithm
    - The process that requests the CPU first
    - is allocated the CPU first
    - can be easily implemented with a FIFO queue.
    ex ) p0 - burstTime : 24s, waitingTime : 0s
         p1 - burstTIme : 3s, waitingTime : 27s
         p2 - burstTime : 3s, waitingTime : 30s
        total waitingTime - 57s -> avg 19s
        total turnAroundTime - 24s + 27s + 30s -> 81s -> avg 27s
        CPU burst Time에 따라 천차만별 (낮지 않다)
        Non-preemptive Scheduling : Process의 수가 낮을 수록 유리하다 (현대 컴퓨팅에 어울리지 않음)
        Convoy Effect : CPU Burst가 높은 하나의 프로세스가 CPU를 지나치게 점유

  - SJF : Shortest Job First(SRTF: Shortest Remaining Time First)
    - shortest-next-CPU-burst-first : CPU burst가 낮은 것 부터 처리
    - 같이 도착했다면 FCFS를 채택
    

  - RR: Round-Robin : Time Sharing
  
  - Priorty-based : priority RR

  - MLQ : Multi-Level Queue - priority Multi RR

  - MLFQ : Multi-Level FeedBack Queue - priority Multi FeedBack RR

