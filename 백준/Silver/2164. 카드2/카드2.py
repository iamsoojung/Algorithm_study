import sys
from collections import deque

N = int(input())
card = deque()

for i in range(N):
    card.append(i+1)

idx=1
while len(card)>1:
    if idx%2!=0:
        card.popleft()
    else:
        card.append(card.popleft())
    idx += 1
    

print(card.pop())