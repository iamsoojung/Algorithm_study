def solution(array, commands):
    answer = []
    
    for c in commands:
        cut_array = array[c[0]-1:c[1]]
        cut_array.sort()
        answer.append(cut_array[c[2]-1])
    
    return answer