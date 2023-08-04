N, M = map(int, input().split())

A = list(map(int, input().split()))
B = list(map(int, input().split()))

answer = A + B
answer.sort()

print(*answer)