from itertools import combinations

N, M = map(int, input().split())
card = list(map(int, input().split()))
result = []

for pick in combinations(card, 3):
    if sum(pick) > M:
        continue
    else:
        result.append(sum(pick))

print(max(result))