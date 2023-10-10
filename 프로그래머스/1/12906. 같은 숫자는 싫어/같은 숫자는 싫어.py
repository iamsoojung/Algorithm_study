def solution(arr):
    answer = []
    
    for i in range(len(arr)):
        if i+1 != len(arr) and arr[i] == arr[i+1]:
                continue
        answer.append(arr[i])
        pass
    
    return answer