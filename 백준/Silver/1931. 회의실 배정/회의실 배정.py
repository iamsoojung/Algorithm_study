import sys
N = int(sys.stdin.readline())

# 끝나는 시간의 오름차순, 시작하는 시간의 오름차순
book = []
for i in range(N):
    start, end = map(int, sys.stdin.readline().split())
    book.append([start, end])

book = sorted(book, key=lambda a : a[0])
book = sorted(book, key=lambda a : a[1])

last = 0
cnt = 0

for start, end in book:
    if start >= last:
        cnt += 1
        last = end

print(cnt)