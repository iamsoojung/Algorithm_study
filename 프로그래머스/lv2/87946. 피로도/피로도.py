# 피로도 시스템 (0 이상의 정수)
# 최소 필요 피로도 / 소모 피로도
# 하루에 한 번씩 탐험할 수 있는 던전이 여러 개
# 이 던전들을 최대한 많이 탐험
# 현재 피로도 = k

from itertools import permutations

def solution(k, dungeons):
    answer = 0
    
    for p in permutations(dungeons, len(dungeons)):
        hp = k
        count = 0
        
        for need, spent in p:
            if hp >= need:
                hp -= spent
                count += 1
                
        answer = max(answer, count)
    
    return answer