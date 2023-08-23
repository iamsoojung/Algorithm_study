'''
스택 사용
- 주어진 원본 배열에서 하나씩 요소를 빼서 스택에 담기
먼저 제일 처음 요소는 스택에 담고,
다음 원소랑 담은 원소를 비교해가면서 스택에 담으면 되지 않을까?
'''
def solution(arr):
    stack = []
    stack.append(arr[0])
    
    for i in range(1, len(arr)):
        if stack[-1] != arr[i]:
            stack.append(arr[i])
    
    return stack