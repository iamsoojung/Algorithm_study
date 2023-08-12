import sys
from collections import deque

# 깊이 우선 탐색 (재귀로 구현)
def DFS(start):
    visited[start] = True
    print(start, end=" ")
    
    for i in graph[start]:
        if not visited[i]:
            DFS(i)

# 넓이 우선 탐색 (큐로 구현)
def BFS(start):
    queue = deque([start])
    visited[start] = True
    
    while queue:
        v = queue.popleft()
        print(v, end=" ")
        for i in graph[v]:
            if not visited[i]:
                visited[i] = True
                queue.append(i)

# 정점, 간선, 탐색을 시작할 정점 번호
N, M, V = map(int, sys.stdin.readline().split())
graph = [[] for _ in range(N + 1)]

for _ in range(M):
    a, b = map(int, sys.stdin.readline().split())
    graph[a].append(b)
    graph[b].append(a)

# 정렬
for i in graph:
    i.sort()

# DFS
visited = [False] * (N+1)
DFS(V)

print()

# BFS
visited = [False] * (N+1)
BFS(V)