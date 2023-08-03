# 전형적인 그래프 문제
# DFS(깊이 우선 탐색)로 풀이한 경우 (타인 풀이 참고)

def dfs(start):
    visited[start] = 1
    for i in graph[start]:
        if visited[i] == 0:
            dfs(i)

N = int(input())
M = int(input())

graph = [[] for i in range(N+1)]    # 그래프 (0(x), 1~N)
visited = [0] * (N+1)   # 방문했는지 (0(x), 1~N)

for m in range(M):
    a, b = map(int, input().split())
    graph[b] += [a]
    graph[a] += [b] # 양방향 연결

dfs(1)
print(sum(visited)-1)   # XX와 연결된 컴퓨터 개수 출력 (XX 제외)