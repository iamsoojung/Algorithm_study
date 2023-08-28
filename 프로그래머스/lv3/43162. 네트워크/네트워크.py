def solution(n, computers):
    count = 0
    visited = [False] * n # [False, False, False]
    # visited = [False for _ in range(0, n)]
    
    def dfs(vertex):
        # 더 이상 도달할 곳이 없는 경우 -> 단순히 반복이 끝난 경우
        visited[vertex] = True
        
        for idx in range(n):
            if computers[vertex][idx] == 1 and visited[idx] == False:
                dfs(idx)
        
    for i in range(n):  # 0부터 n-1까지
        if visited[i] == False:
            dfs(i)
            count += 1
    
    return count