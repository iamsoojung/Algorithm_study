import sys
N, M = map(int, input().split())

notsee = set()
for i in range(N):
    tmp = sys.stdin.readline().rstrip()
    notsee.add(tmp)

nothear = set()
for j in range(M):
    tmp = sys.stdin.readline().rstrip()
    nothear.add(tmp)

notseehear = sorted(notsee & nothear)

print(len(notseehear))
for k in range(len(notseehear)):
    print(notseehear[k])