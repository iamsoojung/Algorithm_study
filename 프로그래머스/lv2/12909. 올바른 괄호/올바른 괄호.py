'''
stack 이용
( -> ( 넣기
) -> ( 빼기
'''
def solution(s):
    answer = True
    stack = []
    
    for i in s:
        if i == "(":
            stack.append(i)
        if i == ")":
            if not stack:  # )가 많을 때
                return False
            else:
                stack.pop()
    if stack:  # (가 많을 때
        answer = False
    
    return answer