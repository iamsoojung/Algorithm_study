N, M = map(int, input().split())
J = int(input())

apple = []
for i in range(J):
    apple.append(int(input()))

move = 0
start, end = 1, M
for j in range(J):
    if start <= apple[j] and apple[j] <= end:  # 현위치 범위 내 사과 떨어짐
        continue
    elif start > apple[j]:    # 현 위치의 왼쪽에 사과 떨어짐짐
        move += start - apple[j]
        start = apple[j]
        end = apple[j] + (M-1)
    elif end < apple[j]:  # 현위치의 오른쪽에 사과 떨어짐
        move += apple[j] - end
        start = apple[j] - (M-1)
        end = apple[j]

print(move)