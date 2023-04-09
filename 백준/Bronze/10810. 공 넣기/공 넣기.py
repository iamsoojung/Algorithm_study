N, M = map(int, input().split())
bag = [0 for i in range(N)]

for i in range(M):
    i, j, k = map(int, input().split())
    for j in range(i-1, j):
        bag[j] = k

for i in range(N):
    print(bag[i], end=" ")