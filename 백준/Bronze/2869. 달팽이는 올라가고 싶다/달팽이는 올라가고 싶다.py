import math

A,B,V = map(int, input().split())

# 도착날 x, 올라감 X, 내려옴 x-1
# V = Ax - B(x-1)
# x = (V-B)/(A-B)

x = (V-B)/(A-B)
print(math.ceil(x))