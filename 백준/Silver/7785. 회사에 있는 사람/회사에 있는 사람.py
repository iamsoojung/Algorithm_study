import sys
n = int(sys.stdin.readline())
person = dict()
for i in range(n):
    a,b = map(str, input().split())
    if b=="enter":
        person[a] = 1
    else:
        del person[a]
person = sorted(person.keys(), reverse=True)

for p in person:
    print(p)