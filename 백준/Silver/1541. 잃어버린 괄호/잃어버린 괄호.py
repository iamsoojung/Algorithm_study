# 그리디 알고리즘 : 선택의 순간 마다 당장 눈앞에 보이는 최적의 상황만 쫓음
# 현재의 최적 해 != 전체의 최적 해
# 마이너스를 만날 때 가장 큰 수를 빼면 됨

arr = input().split('-')
result = 0

for i in arr[0].split('+'):
    result += int(i)

for i in arr[1:]:
    for j in i.split('+'):
        result -= int(j)

print(result)