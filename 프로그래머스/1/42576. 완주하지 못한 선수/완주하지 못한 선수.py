def solution(participant, completion):
    answer = ''
    dict = {}
    
    for i in range(len(participant)):
        dict[participant[i]] = 0
    
    for p in participant:
        dict[p] += 1
    
    for c in completion:
        dict[c] -= 1
    
    for d in dict:
        if dict[d] > 0:
            answer += d    
    
    return answer