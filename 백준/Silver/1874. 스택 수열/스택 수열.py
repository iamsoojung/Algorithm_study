import sys

n = int(sys.stdin.readline())
stack = []
answer = []
cur = 1
flag = 0

for i in range(n):
    num = int(sys.stdin.readline())
    while cur <= num:
        stack.append(cur)
        answer.append("+")
        cur += 1

    if stack[-1] == num:
        stack.pop()
        answer.append("-")
    else:
        print("NO")
        flag = 1
        break

if not flag:
    for i in answer:
        print(i)