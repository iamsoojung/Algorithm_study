import math
def solution(progresses, speeds):
    # 한 번에 몇 개씩 배포될 건지 담을 배열
    answer = []
    
    # 한 번에 배포되는 개수를 누적 체크하기 위해서
    count = 0
    
    # 개발 완료까지 남은 일수를 담을 큐
    release = []
    
    # 작업 일수 = math.ceil((100 - progresses[1]) / speed[1])
    for i in range(len(progresses)):
        release_day = math.ceil((100 - progresses[i]) / speeds[i])
        release.append(release_day)
    
    current = release[0]
    
    while release:
        # current랑 현재 기능이랑 비교해서 배포 가능하다면? current보다 작거나 같은 것
        if release[0] <= current:
            count += 1
            release.pop(0)
        else:
            answer.append(count)
            count = 0
            current = release[0]
        
    # while문 다 돌고 나옴 (큐가 비었음)
    # while문 안에서 count는 열심히 누적되어서 나왔을 겁니다
    answer.append(count)
    
    return answer