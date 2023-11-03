# 숫자 카드 N개, 정수 M개
# 그 수가 적혀있는 숫자 카드를 몇개 가지고있는지 구하기

# 시간 초과 방지 -> dict 이용하여 key(카드 수), value(카드 숫자)

N = int(input())
card = list(map(int, input().split()))

M = int(input())
target = list(map(int, input().split()))

dic = dict()
result = []

for i in card:
    if i in dic:
        dic[i] += 1
    else:
        dic[i] = 1
        
# {6: 1, 3: 2, 2: 1, 10: 3, -10: 2, 7: 1}
        
for j in target:
    if j in dic:
        result.append(dic[j])
    else:
        result.append(0)

print(*result)