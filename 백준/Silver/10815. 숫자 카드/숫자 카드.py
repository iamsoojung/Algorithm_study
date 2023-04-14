N = int(input())
card = list(map(int, input().split()))
card = set(card)
M = int(input())
num = list(map(int, input().split()))
cnt = [0]*len(num)

for i in range(len(num)):
    if num[i] in card:
        cnt[i] += 1

print(*cnt)