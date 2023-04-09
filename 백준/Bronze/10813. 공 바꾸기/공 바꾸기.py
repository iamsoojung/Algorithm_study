N, M = map(int, input().split())
num = [i for i in range(1, N+1)]

for k in range(M):
    i, j = map(int, input().split())
    num[i-1], num[j-1] = num[j-1], num[i-1]

for l in range(N):
    print(num[l], end=" ")