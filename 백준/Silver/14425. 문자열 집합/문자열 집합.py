import sys
N, M = map(int, sys.stdin.readline().split())

S = set([sys.stdin.readline() for _ in range(N)])
cnt = 0

for _ in range(M):
    check = sys.stdin.readline()
    if check in S:
        cnt += 1
        
print(cnt)
