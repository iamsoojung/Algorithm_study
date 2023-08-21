def solution(a, b):
    # 모두 짝수
    if (a % 2 == 0 and b % 2 == 0):
        return abs(a-b)
    # 모두 홀수
    if (a % 2 == 1 and b % 2 == 1):
        return (a*a) + (b*b)
    return 2 * (a+b)