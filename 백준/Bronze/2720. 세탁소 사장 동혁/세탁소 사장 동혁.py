# 쿼터 : 0.25 / 다임 : 0.1 / 니켈 : 0.05 / 페니 : 0.01

# 1달러 = 100센트
# 쿼터 : 25 / 다임 : 10 / 니켈 : 5 / 페니 : 1

T = int(input())
for i in range(T):
    q, d, n, p = 0, 0, 0, 0
    money = int(input())
    if int(money/25) >= 1:
        q = int(money/25)
        money %= 25
    if int(money/10) >= 1:
        d = int(money/10)
        money %= 10
    if int(money/5) >= 1:
        n = int(money/5)
        money %= 5
    p = money
    
    print(q, d, n, p)