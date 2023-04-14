while(1):
    n = int(input())
    if n==-1:   break
    num = []
    for i in range(1, n):
        if n%i==0:
            num.append(i)
    if sum(num)==n:
        print(n, "=", num[0], end=" ")
        for j in range(1, len(num)):
            print("+", num[j], end=" ")
        print()
    else:
        print(n, "is NOT perfect.")