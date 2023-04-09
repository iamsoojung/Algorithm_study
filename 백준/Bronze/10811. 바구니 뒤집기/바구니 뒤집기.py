N, M = map(int, input().split())
num = [i for i in range(1,N+1)]

for k in range(M):
    i, j = map(int, input().split())
    num[i-1:j] = reversed(num[i-1:j])

for i in range(N):
    print(num[i], end=" ")