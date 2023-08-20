# 1+2+3+4+....n+필요한 수

S = int(input())

total = 0
cnt = 0

for i in range(1, S+1):
    total += i
    if (total > S):
        break
    cnt += 1
    
print(cnt)