# 1~N번 사람, 순서대로 K번째 사람 제거
from collections import deque

N, K = map(int, input().split())

queue = deque(range(1, N+1))
# num = [_ for _ in range(1, N+1)]
# num = num[K-1:]+ num[:K-1]

result = []

while queue:
    for _ in range(K-1):
        queue.append(queue.popleft())
    result.append(queue.popleft())
 
print(str(result).replace('[', '<').replace(']', '>'))
