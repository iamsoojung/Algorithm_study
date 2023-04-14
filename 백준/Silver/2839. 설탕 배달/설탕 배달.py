N = int(input())
result = 0

while N>0:
    if N%5==0:
        N = int(N/5)
        result += N
        print(result)
        break
    N -= 3
    result += 1
    if N==0:
        print(result)
    elif N<0:
        print(-1)