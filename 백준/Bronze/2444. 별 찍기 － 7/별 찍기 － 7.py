N = int(input())

for i in range(N):
    for j in range(N-i-1):
        print(" ", end="")
    
    for k in range((i+1)*2-1):
        print("*", end="")
    print()

for i in range(1, N):
    for j in range(i):
        print(" ", end="")
    for k in range((N-i)*2-1):
        print("*", end="")
    print()
