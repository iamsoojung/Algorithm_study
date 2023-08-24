'''
가중치, 무방향, 양방향 그래프

N개의 마을 => N개의 정점 (1~N)
K 시간 이하로만 배달이 가능한 마을만 주문 받을 것

3시간 이하로 배달할 수 있는 마을? 4개

마을의 개수 N
각 마을을 연결하는 도로의 정보 road[0] = [1,2,1]
음식 배달이 가능한 시간 K

빠른 도로만 이용할 겁니다! 최단 경로

# 인접 행렬 => 가중치
# 1 대신 가중치(최소값) => 가중치를 담은 인접 행렬 먼저 만듦

최소 배달 시간을 담을 배열 하나 더 만들면 좋겠다!
1 번째 마을의 배달 시간은 항상 0으로 고정하면 좋겠다.

배열 하나를 더 만들건데 1번째 마을을 넣을 거에요

while 배열:
    그 배열 안에 든 걸 꺼내요 = 1번째 마을
    
    마을 개수만큼 순회 => 이동 시간 계산하려고!
        이동 시간이 그 최소 배달 시간에 담긴 배열 내의 시간보다 작다면?
            최소 배달 시간 내의 시간을 이동시간으로 갱신시켜주자
            다음 마을을 1번째 마을을 담았던 배열에 넣어준다
            
최소 배달 시간이 담긴 배열을 가지고 K 이하인 것만 골라서 카운트

'''

def solution(N, road, K):
    answer = 0
    graph = [[] * (N+1)]

    for i in range(N+1):
        # float('inf') : 아주 큰 값으로 초기화하려고
        graph.append([float('inf')] * (N+1))    # 0번 버텍스 버리고, 1번부터 시작하려고
        
    # 인접 행렬에 가중치를 넣어줄 것
    # min()
    for start, next, time in road:
        graph[start][next] = min(graph[start][next], time)
        graph[next][start] = min(graph[next][start], time)
    
    delivery_time = [float('inf')] * (N+1)
    delivery_time[1] = 0
    
    # 첫번째 마을에서부터 시작한다고 했기 때문에 1을 담은 거에요
    arr = [1]
    
    while arr:
        current_town = arr.pop(0)
        
        for delivery_town in range(1, N+1):
            # 이동한 시간 + 이동할 시간
            spent_time = delivery_time[current_town] + graph[current_town][delivery_town]
            # 이동 시간이 담겨있는 배달 시간보다 spent_time이 작다면?
            if spent_time < delivery_time[delivery_town]:
                delivery_time[delivery_town] = spent_time
                arr.append(delivery_town)
        
    for time in delivery_time:
        if time <= K:
            answer += 1
    
    return answer