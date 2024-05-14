N = 1
cnt = 0

while N != 0 :
    cnt += 1
    N = int(input())

    name = [input() for _ in range(N)]
    stuList = [input() for _ in range(2*N - 1)]
    num = [i.split()[0] for i in stuList]
    for i in num:
        if num.count(i) % 2 == 1 :
            print(cnt, name[int(i)-1])