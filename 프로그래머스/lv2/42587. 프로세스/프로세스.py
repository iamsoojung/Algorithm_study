'''
우선순위 큐를 사용해야 하는 문제다!
    왜죠? 큐(Queue), 우선순위

priorities 큐로 쓴다고 가정해보자
큐에서 하나씩 꺼내요
    큐의 안에 최댓값을 찾아서 꺼낸 값이랑 비교
    작아? 다시 큐에 넣어
    커? 꺼내 -> 어디 배열에 차곡차곡 담을까?
=> 뭘 덜 생각해버렸다... location

같이 생각해야 하는 것 중에 location도 생각해야 함
    => 원래 location에 위치했던 요소가 배열에 차곡차곡 담겼을 때
        => 몇 번째 인덱스에 있는냐가 주요 골자
    => 그럼 원 배열에 담겨 있던 요소의 index도 기억해줄 필요가 있다.
    => 어떻게 기억해? 튜플을 쓰는 것
    => 기존의 priorities = [2, 1, 3, 2]
    => (우선순위, 기존 인덱스)가 담긴 큐로 변환 = [(2,0), (1,1), (3,2), (2,3)]
    
    (큐 내부가 동작하는 부분)
    => 1번 [(2,0), (1,1), (3,2), (2,3)]
    => 2번 [(1,1), (3,2), (2,3), (2,0)]
    => 3번 [(3,2), (2,3), (2,0), (1,1)]
    => 4번 [(2,3), (2,0), (1,1)]
    
    (최후에 나오는 결과 배열, location = 2)
    => output = [(3,2), (2,3), (2,0), (1,1)]
    
    return idx(인덱스) + 1
'''

from collections import deque

def solution(priorities, location):
    answer = 0
    result = []
    
    queue = deque()
    
    '''
    # (우선순위, 기존 인덱스)가 담긴 큐로 변환
    for idx in range(len(priorities)):
        queue.append((priorities[idx], idx))
    '''
    
    # enumerate 사용 : idx랑 요소를 동시에 접근하면서 for문 둘 수 있는 친구 <- 파이썬스러운
    for idx, priority in enumerate(priorities):
        queue.append((priority, idx))
    
    while queue:
        current = queue.popleft()
        
        # 큐 내에서 더 큰 우선순위가 있는지 확인
        # any() 라는 함수를 쓸 것임 -> list의 원소를 필터링하거나 조건을 검사할 때 사용하는 함수
        # (2,0)
        # queue 안을 순회하면서 순회 중인 튜플의 우선순위가
        # 현재 프로세스에서 pop한 튜플의 우선순위보다 큰지 확인
        if any(current[0] < idx[0] for idx in queue):
            queue.append(current)
            
        # queue 안에 들어있는 priority랑 비교했을 때 우선순위가 크거나 같다면
        else:
            result.append(current)
    
    for idx, priority in enumerate(result):
        if priority[1] == location:
            return idx + 1
            
    return answer
    
    