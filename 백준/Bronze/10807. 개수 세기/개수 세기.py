N = int(input())
num = list(map(int, input().split()))
v = int(input())
cnt = 0

for i in range(N):
    if v == num[i]:
        cnt += 1

print(cnt)