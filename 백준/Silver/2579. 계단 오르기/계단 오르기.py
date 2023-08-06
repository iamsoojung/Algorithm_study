import sys

N = int(sys.stdin.readline())
arr = [int(input()) for _ in range(N)]
dp = [0] * N

if len(arr) <= 2:
    print(sum(arr))
else:   # 계단이 3개 이상
    dp[0] = arr[0]
    dp[1] = arr[0] + arr[1]
    for i in range(2, N):
        dp[i] = max(dp[i-2] + arr[i], dp[i-3] + arr[i-1] + arr[i])
    print(dp[-1])