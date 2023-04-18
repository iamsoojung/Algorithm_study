import sys
N = int(sys.stdin.readline())
co = []
for i in range(N):
    x, y = map(int, sys.stdin.readline().split())
    co.append([x,y])
co.sort()

for j in range(N):
    print(co[j][0], co[j][1])