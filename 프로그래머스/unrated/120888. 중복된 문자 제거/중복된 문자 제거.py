def solution(my_string):
    answer = ''
    array = []
    
    for i in my_string:
        if not i in array:
            array.append(i)
    
    for a in array:
        answer += a
    
    return answer