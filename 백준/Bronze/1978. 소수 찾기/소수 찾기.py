T = int(input())
num = list(map(int, input().split()))
cnt = 0
for j in range(len(num)):
    if num[j] == 1: continue
    for k in range(2, num[j]+1):
        if num[j]%k==0: break
    if k==num[j]:   cnt += 1
print(cnt)