a1, a0 = list(map(int, input().split()))
c = int(input())
n = int(input())

f = a1 * n + a0
g = c * n

if (f <= g) and (a1 <= c):
    print(1)
else:
    print(0)
    
# 반례 : a1이 음수인 경우
# 처음에는 O(n)의 정의를 만족 X -> 고려할 필요 X

# 반례 : a0이 음수인 경우
# a1 <= c 를 만족해야 함함