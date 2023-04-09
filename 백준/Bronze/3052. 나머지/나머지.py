remain = []
for i in range(10):
    n = int(input())
    remain.append(n%42)

print(len(set(remain)))