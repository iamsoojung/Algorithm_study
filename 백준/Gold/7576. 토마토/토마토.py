from collections import deque
M , N = map(int, input().split())

graph = []
for i in range(N):
    graph.append(list(map(int, input().split())))

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

queue = deque()
for i in range(N):
    for j in range(M):
        if graph[i][j] == 1:
            queue.append([i, j])

def bfs():
    while queue:
        x, y = queue.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            
            if (0 <= nx < N) and (0 <= ny < M) and graph[nx][ny] == 0:
                graph[nx][ny] = graph[x][y] + 1
                queue.append((nx, ny))
bfs()

day = 0
for row in graph:
    for col in row:
        if col == 0:    # 익지 않았다면 주변도 안익음
            print(-1)
            exit()
    else:
        day = max(day, max(row))

print(day-1)    # 며칠이 지났는지
            