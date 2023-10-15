from collections import deque

N, M = map(int, input().split())
result = [0, 0]

graph = []
for i in range(M):
    graph.append(list(map(str, input())))

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def bfs(x, y, color):
    queue = deque()
    queue.append((x, y))
    graph[x][y] = -1
    cnt = 1
    
    while queue:
        x, y = queue.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            
            if nx<0 or nx>=M or ny<0 or ny>=N:
                continue
            
            if graph[nx][ny] == -1:
                continue
            
            if graph[nx][ny] == color:
                graph[nx][ny] = -1
                queue.append((nx, ny))
                cnt += 1
    return cnt * cnt
    
for i in range(M):
    for j in range(N):
        if graph[i][j] == "W":
            result[0] += bfs(i, j, "W")
        if graph[i][j] == "B":
            result[1] += bfs(i, j, "B")

print(result[0], result[1])