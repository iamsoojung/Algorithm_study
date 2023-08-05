import sys
M = int(sys.stdin.readline())
nSum, nXor = 0,0

for _ in range(M):
    n = list(map(int, sys.stdin.readline().split()))
    if n[0] == 1:
        nSum += n[1]
        nXor ^= n[1]
    elif n[0] == 2:
        nSum -= n[1]
        nXor ^= n[1]
    elif n[0] == 3:
        print(nSum)
    elif n[0] == 4:
        print(nXor)
