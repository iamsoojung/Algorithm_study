n = int(input())
for i in range(n):
    ox = list(input())
    cnt = 1
    result = 0
    
    for j in ox:
        if j=='O':
            result += cnt
            cnt += 1
        else:
            cnt = 1
    print(result)