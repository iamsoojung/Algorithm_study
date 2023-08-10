import sys

N, K = map(int, sys.stdin.readline().split())
arr = [i for i in range(1, N+1)]    # 1 ~ N

answer = []
num = 0

for i in range(N):
    num += (K-1)
    if num >= len(arr):
        num %= len(arr)
    answer.append(str(arr[num]))
    arr.pop(num)

print("<", ', '.join(answer), ">", sep="")