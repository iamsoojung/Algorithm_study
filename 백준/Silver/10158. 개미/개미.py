import sys
w, h = map(int, sys.stdin.readline().split())
p, q = map(int, sys.stdin.readline().split())
t = int(sys.stdin.readline())

# 시간 초과 ㅠ.ㅠ
# x, y = 1, 1
# for i in range(t):
#     if p == w or p == 0:
#         x *= -1
#     if q == h or q == 0:
#         y *= -1
#     p += x
#     q += y
# print(p, q)

a = (p+t) // w
b = (q+t) // h

if a % 2 == 0:
    x = (p+t) % w
else:
    x = w - (p+t) % w

if b % 2 == 0 :
    y = (q+t) % h
else:
    y = h - (q+t) % h

print(x, y)

# 가로 : +1 +1 -1 -1 -1 -1 -1 -1 +1 +1
#         4  5  6  5  4  3  2  1  0  1
# 세로 : +1 +1 +1 -1 -1 -1 -1 +1 +1 +1
#         1  2  3  4  3  2  1  0  1  2