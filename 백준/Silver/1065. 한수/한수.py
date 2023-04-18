N = int(input())
cnt = 0
if N<100:
    cnt = N
else:
    cnt = 99
    for i in range(100, N+1):
        a = i//100
        b = (i%100)//10
        c = (i%100)%10
        if a-b == b-c:
            cnt += 1

print(cnt)