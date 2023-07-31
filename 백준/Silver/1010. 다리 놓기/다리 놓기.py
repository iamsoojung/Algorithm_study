# 조합 이용 -> 팩토리얼 이용
# def factorial(n):
#     result = 1
#     for i in range(1, n+1):
#         result *= i
#     return result
import math

T = int(input())
for t in range(T):
    N, M = list(map(int, input().split()))
    answer = math.factorial(M) // (math.factorial(N) * math.factorial(M-N))
    print(answer)