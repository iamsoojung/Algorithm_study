def solution(n):
    answer = 0
    
    # 1 ~ n 까지 입력
    for i in range(1, n+1):
        # 나눠서 나머지가 0인지
        if n%i == 0:
            answer += i
    
    return answer