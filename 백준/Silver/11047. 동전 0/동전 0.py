N, K = list(map(int, input().split()))

coin = []
for n in range(N):
    coin.append(int(input()))

answer = 0
while K!=0:
    for i in range(N-1, -1, -1):
        if not coin[i] > K:
            answer += int(K/coin[i])
            K %= coin[i]

print(answer)