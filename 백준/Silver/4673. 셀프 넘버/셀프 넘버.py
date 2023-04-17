def d(n):
    n = n + sum(map(int, str(n)))
    return n

selfNum = set()

for i in range(1, 10001):
    selfNum.add(d(i))

for j in range(1, 10001):
    if j not in selfNum:
        print(j)