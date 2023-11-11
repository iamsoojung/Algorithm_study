from collections import defaultdict
def solution(id_list, report, k):
    answer = [0] * len(id_list)
    reported_user = [[] for _ in range(len(id_list))]
    reported_num = defaultdict(int)
    
    for r in report:
        r1, r2 = r.split()
        i = id_list.index(r1)
        
        if r2 not in reported_user[i]:
            reported_user[i].append(r2)
            reported_num[r2] += 1
    
    for i in range(len(reported_user)):
        for j in range(len(reported_user[i])):
            if reported_num[reported_user[i][j]] >= k:
                answer[i] += 1

    return answer
