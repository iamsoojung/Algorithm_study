M = int(input())
N = int(input())

num = []
i,j=0,0
for i in range(M, N+1):
    for j in range(2, i+1):
        if i%j==0: break
    if j==i:
        num.append(i)

if len(num)==0:
    print(-1)
else:
    print(sum(num))
    print(min(num))